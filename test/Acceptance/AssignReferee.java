package Acceptance;

import Service.JavaApplication;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class AssignReferee {
    String legueId = "2022";
    String seasonID = "season1";
    String refereeUsername = "1";
    JavaApplication app = new JavaApplication();

    @BeforeEach
    public void beforeEach(){
        app.login("stubAR", "1234");
    }

//    @Test
//    public void invalidUser(){
//        app.domainController.logOut();
//        //no user is currently logged in
//        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
//        //a user that is not AR is currently logged in use case
//        app.login("ref1","1234");
//        assertEquals("No logged-in association representative",app.assignReferee(legueId,seasonID,refereeUsername));
//    }
//    @Test
//    public void assertInvalidInput(){
//        assertEquals("Enter valid details",app.assignReferee("not exist",seasonID,refereeUsername));
//        assertEquals("Enter valid details",app.assignReferee(legueId,"not exist",refereeUsername));
//        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,"not exist"));
//    }
    @Test
    public void assertSuccess(){
        app.login("stubAR", "1234");
        //assign so thr ref would have an assignment
        assertEquals("true",app.assignReferee(legueId,seasonID,refereeUsername));
    }
//    @Test
//    public void assertRefAssigned(){
//        assertEquals("Assignment already exists",app.assignReferee(legueId,seasonID,refereeUsername));
//
//    }
}
