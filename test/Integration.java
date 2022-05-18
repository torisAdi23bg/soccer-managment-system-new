import DataAccess.Dao;
import Service.JavaApplication;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Integration {
    String legueId = "2022";
    String seasonID = "season1";
    String refereeUsername = "1";
    JavaApplication app = new JavaApplication();
    Dao db=Dao.getInstance();


    @Test
    public void user_is_logged_out_and_trying_to_assign_referee(){
        app.logout();
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
    }
    @Test
    public void username_does_not_exist_but_trying_to_assign_referee(){
        //username not exist, assignReferee shouldn't work
        app.login("notexist", "1234");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void login_is_unsuccessful_and_trying_to_assign_referee(){
        //login as regular subscriber unsuccessful so assignReferee shouldn't work
        app.login("1", "1234");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void login_as_non_AR_subscriber_and_trying_to_assign_referee(){
        //login as regular subscriber successful so assignReferee shouldn't work
        app.login("1", "1");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void login_as_AR_is_unsuccessfully_and_trying_to_assign_referee(){
        //login as AR unsuccessful so assignReferee shouldn't work
        app.login("2", "wrongPass");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void login_as_AR_and_trying_to_assign_referee_when_league_is_empty_string(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee("",seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void login_as_AR_and_trying_to_assign_referee_when_season_is_empty_string(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee(legueId,"",refereeUsername));
        app.logout();
    }
    @Test
    public void login_as_AR_and_trying_to_assign_referee_when_referee_is_empty_string(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,""));
        app.logout();
    }

    @Test
    public void login_as_AR_and_trying_to_assign_referee_when_league_is_not_exist(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee("notexist",seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void login_as_AR_and_trying_to_assign_referee_when_season_is_not_exist(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee(legueId,"notexist",refereeUsername));
        app.logout();
    }
    @Test
    public void login_as_AR_and_trying_to_assign_referee_when_referee_is_not_exist(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,"notexist"));
        app.logout();
    }
    @Test
    public void login_as_AR_and_trying_to_assign_referee_successfully(){
        app.login("2", "2");
        assertEquals("true",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
        db.deleteLeagueSeasonReferee(legueId,seasonID,refereeUsername);
    }
    @Test
    public void login_as_AR_and_trying_to_assign_same_referee_twice(){
//        login as AR successful. assignment done twice
        app.login("2", "2");
        app.assignReferee(legueId,seasonID,refereeUsername);
        assertEquals("Assignment already exists",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
        db.deleteLeagueSeasonReferee(legueId,seasonID,refereeUsername);
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
        db.deleteLeagueSeasonReferee(legueId,seasonID,refereeUsername);
        assertNotEquals("",app.assignGames(legueId,seasonID,false));
        app.logout();

        db.deleteLeagueSeasonReferee(legueId,seasonID,refereeUsername);
    }


}