package DataAccess;

import Domain.*;
import javafx.util.Pair;

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
        return null;
    }

    //return null if no such leagueID,seasonID teams
    public LinkedList<Team> getAllGroups(String leagueID, String seasonID) {
        return null;
    }

    //return true if succeeded
    public boolean saveGames(LinkedList<Game> newGamesToSave) {
        return true;
    }
    //return null if no such referee
    public Referee getReferee(String refereeId) {
        return null;
    }

    //return null if no such league
    public League getLeague(String leagueID) {
        return null;
    }

    //return null if no such season
    public Season getSeason(String seasonID) {
        return null;
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
