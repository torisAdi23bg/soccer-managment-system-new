import DataAccess.Dao;
import Service.JavaApplication;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Integration {
    String legueId = "2022";
    String seasonID = "season1";
    String refereeUsername = "1";
    JavaApplication app = new JavaApplication();

    public void afterAll(){
        Dao db=Dao.getInstance();
        db.deleteAllRows("LeagueSeasonReferee");
        db.deleteAllRows("Game");
    }

    @Test
    public void login_and_assignReferee(){
        app.logout();
        //no login done, assignReferee shouldn't work
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));

        //username not exist, assignReferee shouldn't work
        app.login("notexist", "1234");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();

        //login as regular subscriber unsuccessful so assignReferee shouldn't work
        app.login("1", "1234");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();

        //login as regular subscriber successful so assignReferee shouldn't work
        app.login("1", "1");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();

        //login as AR unsuccessful so assignReferee shouldn't work
        app.login("2", "wrongPass");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();

        //login as AR successful. assignReferee wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee("",seasonID,refereeUsername));
        assertEquals("Enter valid details",app.assignReferee(legueId,"",refereeUsername));
        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,""));
        app.logout();

        //login as AR successful. assignReferee wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee("notexist",seasonID,refereeUsername));
        assertEquals("Enter valid details",app.assignReferee(legueId,"notexist",refereeUsername));
        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,"notexist"));
        app.logout();

//        login as AR successful so assignReferee should work
        app.login("2", "2");
        assertEquals("true",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
//
//        login as AR successful. assignment done twice
        app.login("2", "2");
        assertEquals("Assignment already exists",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();

        afterAll();
    }

    @Test
    public void login_and_assignGames(){
//        no login done, assignGames shouldn't work
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,true));
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,false));

        //username not exist, assignGames shouldn't work
        app.login("notexist", "1234");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,true));
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,false));
        app.logout();

        //login as regular subscriber unsuccessful so assignGames shouldn't work
        app.login("1", "1234");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,true));
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,false));
        app.logout();

        //login as regular subscriber successful so assignGames shouldn't work
        app.login("1", "1");
        assertEquals("The user that is currently logged in is not an Association Representive",app.assignGames(legueId,seasonID,true));
        assertEquals("The user that is currently logged in is not an Association Representive",app.assignGames(legueId,seasonID,false));
        app.logout();

        //login as AR unsuccessful so assignGames shouldn't work
        app.login("2", "wrongPass");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,true));
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,false));
        app.logout();

        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames("",seasonID,true));
        assertEquals("Enter valid details",app.assignGames(legueId,"",false));
        app.logout();

        //login as AR successful so assignGames should work
        app.login("2", "2");
        assertNotEquals("",app.assignGames(legueId,seasonID,true));
        afterAll();
        assertNotEquals("",app.assignGames(legueId,seasonID,false));
        app.logout();

        afterAll();
    }


}