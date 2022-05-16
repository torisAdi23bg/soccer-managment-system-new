package Acceptance;

import DataAccess.Dao;
import Service.JavaApplication;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AssignReferee {
    String legueId = "2022";
    String seasonID = "season1";
    String refereeUsername = "1";
    JavaApplication app = new JavaApplication();
    Dao db=Dao.getInstance();

    public void afterAll(){
        Dao db=Dao.getInstance();
        db.deleteAllRows("LeagueSeasonReferee");
        db.deleteAllRows("Game");
    }

    @Test
    public void invalidUser(){
        app.logout();
        //no user is currently logged in
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        //a user that is not AR is currently logged in use case
        app.login("1","1");
        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
        afterAll();
    }
    @Test
    public void assertInvalidInput(){
        app.login("2","2");
        assertEquals("Enter valid details",app.assignReferee("not exist",seasonID,refereeUsername));
        assertEquals("Enter valid details",app.assignReferee(legueId,"not exist",refereeUsername));
        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,"not exist"));
        app.logout();
        afterAll();
    }
    @Test
    public void assertSuccess(){
        app.login("stubAR", "1234");
        //assign so thr ref would have an assignment
        assertEquals("true",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
        afterAll();
    }
    @Test
    public void assertRefAssigned(){
        app.login("stubAR", "1234");
        app.assignReferee(legueId,seasonID,refereeUsername);
        assertEquals("Assignment already exists",app.assignReferee(legueId,seasonID,refereeUsername));
        app.logout();
        afterAll();

    }
}
