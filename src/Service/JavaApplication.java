package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

public class JavaApplication {
    Controller uc = Controller.getInstance();

    public boolean assignReferee(String gameId,String refereeId) {
        return uc.assignReferee(gameId,refereeId);
    }

    public boolean assignGames(String leagueID, String seasonID, List<String> mainRefereesIDS, List<String> refereesIDS){
//    public boolean assignGame(String gameId,String team1,String team2,List<String> referees,String mainReferee){
        return uc.assignGames(leagueID, seasonID, mainRefereesIDS, refereesIDS);
//        return uc.assignGames(gameId,team1,team2,referees,mainReferee);
    }

    public boolean login(String username,String password){
        return uc.login(username,password);
    }
}