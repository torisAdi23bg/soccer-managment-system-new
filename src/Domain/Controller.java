package Domain;
import com.google.gson.Gson; //todo: delete when finishing

import DataAccess.Dao;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class Controller {

    private static Controller single = null;
    private static Dao db;
    private static Subscriber currentLogged;

    private Controller() {
        db = Dao.getInstance();
        currentLogged = null;
    }

    public static Controller getInstance() {
        if (single == null)
            single = new Controller();

        return single;
    }

    public String login(String username, String password) {
//        Subscriber subscriber = new Subscriber(username, password);
        Subscriber subscriber = db.getSubscriber(username);
        if(subscriber==null)return "false";
        if (subscriber.login(password) == true) {
            currentLogged = subscriber;
            return "true";
        } else
            return "false";
    }

    public String assignGames(String leagueID, String seasonID, boolean policy){
        //todo : check if current user is AR
        League league = db.getLeague(leagueID);
        Season season = db.getSeason(seasonID);
        if (league==null || season==null)
        {
            return "Enter valid details";
        }

        LinkedList<Team> allGroupsInSeasonLeague = db.getAllGroups(leagueID,seasonID);
        LinkedList<Game> newGamesToSave = new LinkedList<>();

        if (policy==true){ //every couple have one game. so every team plays at most one game .the field will be for the team lexicographically first. If odd number - one team not playing.
            if (allGroupsInSeasonLeague.size()%2==0)//even
            {
                for(int i=0; i<allGroupsInSeasonLeague.size(); i+=2)
                {
                    Team team1=allGroupsInSeasonLeague.get(i);
                    Team team2=allGroupsInSeasonLeague.get(i+1);
                    Game newGame;
                    if (team1.id.compareTo(team2.id)<0) //team1 is before team2 lexicographically
                    {
                        newGame = new Game(team1.homeField, team1,team2);
                    }
                    else {
                        newGame = new Game(team2.homeField, team2, team1);
                    }
                    newGamesToSave.add(newGame);
                }
            }
            else
            {
                for(int i=0; i<allGroupsInSeasonLeague.size()-1; i+=2)
                {
                    Team team1=allGroupsInSeasonLeague.get(i);
                    Team team2=allGroupsInSeasonLeague.get(i+1);
                    Game newGame;
                    if (team1.id.compareTo(team2.id)<0) //team1 is before team2 lexicographically
                    {
                        newGame = new Game(team1.homeField, team1,team2);
                    }
                    else {
                        newGame = new Game(team2.homeField, team2, team1);
                    }
                    newGamesToSave.add(newGame);
                }
            }
        }
        else //every team plays two games against every other team. the field will be one time for the first group, and another time for the second. So each team plays 2*(n-1) games (when n is number of groups).
        {
            for(Team team : allGroupsInSeasonLeague)
            {
                for (Team teamOther: allGroupsInSeasonLeague)
                {
                    if (team != teamOther)
                    {
                        Game newGame = new Game(team.homeField,team,teamOther);
                        newGamesToSave.add(newGame);
                    }
                }
            }
        }

        if (db.saveGames(newGamesToSave)==true)//success;
        {
            Gson gson = new Gson();
            String jsonInString = gson.toJson(newGamesToSave);
            return jsonInString;
        }
        return "";
    }

    public String assignReferee(String leagueID,String seasonID, String refereeUsername) {

        if (currentLogged == null) {
            return "No logged-in association representative";
        }

        if (!(currentLogged.class_name.equals("AssociationRepresentative"))) {
            return "No logged-in association representative";
        }

        Referee referee = db.getReferee(refereeUsername);
        League league = db.getLeague(leagueID);
        Season season = db.getSeason(seasonID);
        if (referee == null || league == null || season == null) {
            return "Enter valid details";
        }

        LinkedList<Pair<Season,League>> assignments = db.getRefereeAssignments(referee);
        referee.assignments=assignments;
        if (referee.isAssignmentExists(season,league)){
            return "Assignment already exists";
        }
        String msg = db.addRefereeToLEAGUESEASONTable(referee,league,season);
        return msg;
    }


}
