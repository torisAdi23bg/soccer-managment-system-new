package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

public class JavaApplication {
    Controller uc = Controller.getInstance();

    public void assignReferee(int gameId,String refereeId) {
        uc.signReferee();
    }

    public void assignGame(int gameId,String team1,String team2,List<String> referees,String mainReferee){
        uc.assignGame(gameId,team1,team2,referees,mainReferee);
    }

    public boolean login(String username,String password){
        uc.login(username,password);
    }
}