package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

public class JavaApplication {
    Controller uc = Controller.getInstance();

    public String assignReferee(String leagueID,String seasonID, String refereeUsername) {
        return uc.assignReferee(leagueID,seasonID,refereeUsername);
    }

    public String assignGames(String leagueID, String seasonID, boolean policy){
        return uc.assignGames(leagueID, seasonID, policy);
    }

    public String login(String username,String password){
        return uc.login(username,password);
    }
}