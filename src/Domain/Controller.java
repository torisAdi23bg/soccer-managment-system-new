package Domain;

import DataAccess.Dao;
import com.google.gson.Gson;
import javafx.util.Pair;

import java.util.LinkedList;

public class Controller {
    /*
    Controller class
     */

    private static Controller single = null;
    private static Dao db;
    private static Subscriber currentLogged;

    private Controller() {
        /*
        Builder function
        DB is a singleton
         */
        db = Dao.getInstance(); //get DB instance
        currentLogged = null;
    }

    public static Controller getInstance() {
        /*
        Get Instance function
        Controller is a singleton
        output:
            A new controller is created or return existing one
         */
        if (single == null)
            single = new Controller();

        return single;
    }

    public String login(String username, String password) {
        /*
        UC - login function
        input:
            String username
            String password
        output:
            String of boolean
         */
        Subscriber subscriber = db.getSubscriber(username); //check if is a subscriber
        if(subscriber==null)return "false"; //if notexist
        if (subscriber.login(password) == true) { //if input password matches db saved password
            currentLogged = subscriber;
            return "true";
        } else
            return "false";
    }
    public void logOut(){
        /*
        log out function
        sets null in current logged
         */
        currentLogged = null;
    }

    public String assignGames(String leagueID, String seasonID, boolean policy){
        /*
        UC - Assign games function
        input:
            String leagueID
            String seasonID
            boolean policy
        output:
            String of status according to input and DB

         this function creates games between teams in a certain league and season according to chosen policy.
         user currentLogged in should be association representative.
         */
        if (currentLogged == null) {
            return "No logged-in association representative";
        }

        if(!(currentLogged instanceof AssociationRepresentive)){
            return "The user that is currently logged in is not an Association Representive";
        }
        League league = db.getLeague(leagueID);
        Season season = db.getSeason(seasonID);
        if (league==null || season==null) //check if exist
        {
            return "Enter valid details";
        }
        //make AR assignGames
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
        /*
        UC - Assign Referee function
        input:
            String leagueID
            String seasonID
            String refereeUsername
        output:
            String of status according to input and DB

         this function assigns a Referee in a certain league and season by referee username.
         user currentLogged in should be association representative.
         */

        if (currentLogged == null || !(currentLogged instanceof AssociationRepresentive)) {
            return "No logged-in association representative";
        }

        Referee referee = db.getReferee(refereeUsername);
        League league = db.getLeague(leagueID);
        Season season = db.getSeason(seasonID);
        if (referee == null || league == null || season == null) {
            return "Enter valid details";
        }

        LinkedList<Pair<Season,League>> assignments = db.getRefereeAssignments(referee); //get all referee assignment

        referee.assignments=assignments;
        if (referee.isAssignmentExists(season,league)){ //check if he is already in season and league
            return "Assignment already exists";
        }
        String msg = db.addRefereeToLEAGUESEASONTable(referee,league,season); //add referee to DB
        return msg;
    }


}
