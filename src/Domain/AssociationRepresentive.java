package Domain;

import DataAccess.Dao;

import java.util.LinkedList;

public class AssociationRepresentive extends Subscriber {
    public AssociationRepresentive(String username, String password) {
        super(username, password);
    }

    public LinkedList<Game> assignGames(League league, Season season, boolean policy){
        Dao db= Dao.getInstance();
        LinkedList<Team> allGroupsInSeasonLeague = db.getAllTeams(league.id,season.id);
        LinkedList<Game> newGamesToSave;

        if (policy==true){
            //every couple have one game. so every team plays at most one game .the field will be for the team lexicographically first.
            // If odd number - one team not playing.
            newGamesToSave=this.applyPolicy1(allGroupsInSeasonLeague);
        }
        else
        {
            //every team plays two games against every other team.
            // the field will be one time for the first group, and another time for the second. So each team plays 2*(n-1) games
            // (when n is number of groups).
            newGamesToSave=this.applyPolicy2(allGroupsInSeasonLeague);
        }
        return newGamesToSave;
    }
    private LinkedList<Game> applyPolicy1(LinkedList<Team> allGroupsInSeasonLeague ){
        LinkedList<Game> newGamesToSave = new LinkedList<>();
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
        return newGamesToSave;
    }
    private LinkedList<Game> applyPolicy2(LinkedList<Team> allGroupsInSeasonLeague ){
        LinkedList<Game> newGamesToSave = new LinkedList<Game>();
        for(Team team : allGroupsInSeasonLeague)
        {
            for (Team teamOther: allGroupsInSeasonLeague)
            {
                if (!team.equals(teamOther))
                {
                    Game newGame = new Game(team.homeField,team,teamOther);
                    newGamesToSave.add(newGame);
                }
            }
        }
        return newGamesToSave;
    }
}
