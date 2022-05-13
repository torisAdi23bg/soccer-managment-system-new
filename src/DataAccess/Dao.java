package DataAccess;

import Domain.*;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.LinkedList;

public class Dao {
    private static Dao single = null;

    private Dao()
    {
    }
    public static Dao getInstance()
    {
        if (single == null)
            single = new Dao();

        return single;
    }

    // return null if no such subscriber
    public Subscriber getSubscriber(String username) {
        String sql="";
        String res="";
        if (res.equals(""))
            return null;
        String password_fromDB="";
        Subscriber subscriber = new Subscriber(username,password_fromDB);
        return subscriber;
    }

    //return null if no such referee
    public Referee getReferee(String username) {
        String sql="";
        String res="";
        if (res.equals(""))
            return null;
        String qualification_fromDB="";
        Referee referee = new Referee(username,qualification_fromDB);
        return referee;

    }

    //return null if no such league
    public League getLeague(String leagueID) {
        String sql="";
        String res="";
        if (res.equals(""))
            return null;
        League league = new League(leagueID);
        return league;
    }

    //return null if no such season
    public Season getSeason(String seasonID) {
        String sql="";
        String res="";
        if (res.equals(""))
            return null;
        Season season = new Season(seasonID); //todo: NIV (!!!) why there is leagueID in season in db?
        return season;
    }


    //return null if no such leagueID,seasonID teams
    public LinkedList<Team> getAllGroups(String leagueID, String seasonID) {
        String sql="";
        String res="";
        if (res.equals(""))
            return null;
        //todo: NIV - create the list, i dont know how
        return null;

    }

    //return true if succeeded
    public boolean saveGames(LinkedList<Game> newGamesToSave) {
        return true;
    }


    //return "true" if succeeded, "false" else
    //no need to check if existing in db. They are existing for sure.
    public String addRefereeToLEAGUESEASONTable(Referee referee, League league, Season season) {
        return "true";
    }

    //the referee is for sure inside db (no need to check)
    // return linkedList of <season,league> of the referee
    public LinkedList<Pair<Season, League>> getRefereeAssignments(Referee referee) {
        return null;
    }
}
