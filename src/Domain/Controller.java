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
        if(!(currentLogged instanceof AssociationRepresentive)){
            return "The user that is currently logged in is not an Association Representive";
        }
        League league = db.getLeague(leagueID);
        Season season = db.getSeason(seasonID);
        if (league==null || season==null)
        {
            return "Enter valid details";
        }
        LinkedList<Game> newGamesToSave = ((AssociationRepresentive) currentLogged).assignGames(league,season,policy);

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
