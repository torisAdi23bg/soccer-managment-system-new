package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

public class JavaApplication {
    Controller uc = Controller.getInstance();

    public void signReferee() {
        uc.signReferee();
    }

    public void signGame(){
        uc.signGame();
    }

    public void login(){
        uc.login();
    }
}