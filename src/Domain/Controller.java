package Domain;

import DataAccess.Dao;

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


    public boolean login(String username, String password) {
//        Subscriber subscriber = new Subscriber(username, password);
        Subscriber subscriber = db.getSubscriber(username, password);
        if (subscriber != null) {
            currentLogged = subscriber;
            return true;
        } else
            return false;
    }


    public boolean assignGame(String gameId, String team1, String team2, List<String> referees, String mainReferee) {
        return false;
    }

    public boolean assignReferee(String gameId, String refereeId) {
        return false;
    }

    public boolean assignGames(String leagueID, String seasonID, List<String> mainRefereesIDS, List<String> refereesIDS) {
    return false;
    }

}
