package DataAccess;

import Domain.Controller;
import Domain.Subscriber;
import Domain.Team;

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
}
