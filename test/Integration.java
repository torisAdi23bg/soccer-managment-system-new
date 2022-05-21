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
    public void Int1_no_login_assign_policyTrue(){
        //        no login done, assignGames shouldn't work
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,true));
    }

    @Test
    public void Int2_no_login_assign_policyFalse(){
        //        no login done, assignGames shouldn't work
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,false));
    }

    @Test
    public void Int3_Notexist_login_assign_policyTrue(){
        //username not exist, assignGames shouldn't work
        app.login("notexist", "1234");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,true));
        app.logout();
    }
    @Test
    public void Int4_Notexist_login_assign_policyFalse(){
        //username not exist, assignGames shouldn't work
        app.login("notexist", "1234");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,false));
        app.logout();
    }
    @Test
    public void Int5_login_as_regularSub_unsuccessful_assign_policyTrue(){
        //login as regular subscriber unsuccessful so assignGames shouldn't work
        app.login("1", "1234");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,true));
        app.logout();
    }
    @Test
    public void Int6_login_as_regularSub_unsuccessful_assign_policyFalse(){
        //login as regular subscriber unsuccessful so assignGames shouldn't work
        app.login("1", "1234");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,false));
        app.logout();
    }
    @Test
    public void Int7_login_as_regularSub_assign_policyTrue(){
        //login as regular subscriber successful so assignGames shouldn't work
        app.login("1", "1");
        assertEquals("The user that is currently logged in is not an Association Representive",app.assignGames(legueId,seasonID,true));
        app.logout();
    }
    @Test
    public void Int8_login_as_regularSub_assign_policyFalse(){
        //login as regular subscriber successful so assignGames shouldn't work
        app.login("1", "1");
        assertEquals("The user that is currently logged in is not an Association Representive",app.assignGames(legueId,seasonID,false));
        app.logout();
    }
    @Test
    public void Int9_login_as_AR_unsuccessful_assign_policyTrue(){
        //login as AR unsuccessful so assignGames shouldn't work
        app.login("2", "wrongPass");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,true));
        app.logout();
    }
    @Test
    public void Int10_login_as_AR_unsuccessful_assign_policyFalse(){
        //login as AR unsuccessful so assignGames shouldn't work
        app.login("2", "wrongPass");
        assertEquals("No logged-in association representative",app.assignGames(legueId,seasonID,false));
        app.logout();
    }
    @Test
    public void Int11_login_as_AR_assign_policyTrue_nullSeason(){
        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames(null,seasonID,true));
        app.logout();
    }
    @Test
    public void Int12_login_as_AR_assign_policyFalse_nullSeason(){
        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames(null,seasonID,false));
        app.logout();
    }
    @Test
    public void Int13_login_as_AR_assign_policyTrue_nullLeague(){
        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames(legueId,null,true));
        app.logout();
    }
    @Test
    public void Int14_login_as_AR_assign_policyFalse_nullLeague(){
        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames(legueId,null,false));
        app.logout();
    }
    @Test
    public void Int15_login_as_AR_assign_policyTrue_notExist_Season(){
        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames("notexist",seasonID,true));
        app.logout();
    }
    @Test
    public void Int16_login_as_AR_assign_policyFalse_notExist_Season(){
        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames("notexist",seasonID,false));
        app.logout();
    }
    @Test
    public void Int17_login_as_AR_assign_policyTrue_notExist_League(){
        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames(legueId,"notexist",true));
        app.logout();
    }
    @Test
    public void Int18_login_as_AR_assign_policyFalse_notExist_League(){
        //login as AR successful. assignGames wont work because of null inputs
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignGames(legueId,"notexist",false));
        app.logout();
    }
    @Test
    public void Int19_login_as_AR_assign_policyTrue(){
        //login as AR successful so assignGames should work
        app.login("2", "2");
        assertNotEquals("",app.assignGames(legueId,seasonID,true));
        db.deleteLeagueSeasonReferee(legueId,seasonID,refereeUsername);
        app.logout();
    }
    @Test
    public void Int20_login_as_AR_assign_policyFalse(){
        //login as AR successful so assignGames should work
        app.login("2", "2");
        assertNotEquals("",app.assignGames(legueId,seasonID,false));
        db.deleteLeagueSeasonReferee(legueId,seasonID,refereeUsername);
        app.logout();
    }

    @Test
    public void Int21_user_is_logged_out_and_trying_to_assign_referee(){
        app.logout();
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
    }
    @Test
    public void Int22_username_does_not_exist_but_trying_to_assign_referee(){
        //username not exist, assignReferee shouldn't work
        app.login("notexist", "1234");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void Int23_login_is_unsuccessful_and_trying_to_assign_referee(){
        //login as regular subscriber unsuccessful so assignReferee shouldn't work
        app.login("1", "1234");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void Int24_login_as_non_AR_subscriber_and_trying_to_assign_referee(){
        //login as regular subscriber successful so assignReferee shouldn't work
        app.login("1", "1");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void Int25_login_as_AR_is_unsuccessfully_and_trying_to_assign_referee(){
        //login as AR unsuccessful so assignReferee shouldn't work
        app.login("2", "wrongPass");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void Int26_login_as_AR_and_trying_to_assign_referee_when_league_is_empty_string(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee("",seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void Int27_login_as_AR_and_trying_to_assign_referee_when_season_is_empty_string(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee(legueId,"",refereeUsername));
        app.logout();
    }
    @Test
    public void Int28_login_as_AR_and_trying_to_assign_referee_when_referee_is_empty_string(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,""));
        app.logout();
    }

    @Test
    public void Int29_login_as_AR_and_trying_to_assign_referee_when_league_is_not_exist(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee("notexist",seasonID,refereeUsername));
        app.logout();
    }
    @Test
    public void Int30_login_as_AR_and_trying_to_assign_referee_when_season_is_not_exist(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee(legueId,"notexist",refereeUsername));
        app.logout();
    }
    @Test
    public void Int31_login_as_AR_and_trying_to_assign_referee_when_referee_is_not_exist(){
        app.login("2", "2");
        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,"notexist"));
        app.logout();
    }
    @Test
    public void Int32_login_as_AR_and_trying_to_assign_referee_successfully(){
        app.login("2", "2");
        assertEquals("true",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
        db.deleteLeagueSeasonReferee(legueId,seasonID,refereeUsername);
    }
    @Test
    public void Int33_login_as_AR_and_trying_to_assign_same_referee_twice(){
//        login as AR successful. assignment done twice
        app.login("2", "2");
        app.assignReferee(legueId,seasonID,refereeUsername);
        assertEquals("Assignment already exists",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
        db.deleteLeagueSeasonReferee(legueId,seasonID,refereeUsername);
    }
    //

    //
    //
}