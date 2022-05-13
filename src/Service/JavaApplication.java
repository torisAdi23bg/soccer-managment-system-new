package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

public class JavaApplication {
    Controller uc = Controller.getInstance();

    public boolean assignReferee(String gameId,String refereeId) {
        return uc.assignReferee(gameId,refereeId);
    }

    public String assignGames(String leagueID, String seasonID, boolean policy){
        return uc.assignGames(leagueID, seasonID, policy);
    }

    public String login(String username,String password){
        return uc.login(username,password);
    }
}