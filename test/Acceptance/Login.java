package Acceptance;

import DataAccess.Dao;
import Domain.Game;
import Service.JavaApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Type;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class Login {
    private static Dao db = Dao.getInstance();
    ;
    JavaApplication app = new JavaApplication();

    @BeforeEach
    public void initLogin() {
        //ar: association reepresentive
        app.domainController.logOut();
    }

    @Test
    public void loginTest() {
        //ar: association reepresentive
        assertEquals("false", app.login("", "1234"));
        assertEquals("false", app.login("stubAR", ""));
        assertEquals("false", app.login("", ""));
        assertEquals("false", app.login("not exist", ""));
        assertEquals("false", app.login("stubAR", "incorrect password"));

        assertEquals("true", app.login("stubAR", "1234"));
    }

//    @Test
//    public void assignGamesTest() {
////        assignGames(String leagueID, String seasonID, boolean policy)
//
//        String leagueID = "2022";
//        String seasonID = "season1";
//
//        //non AR assigning
//        app.login("ref1", "1234");
//        assignGameAssertions.assertInvalidUser(app, leagueID, seasonID);
//        app.login("stubAr", "1234");
//        //invalid inputs
//        assignGameAssertions.assertInvalidInputs(app, leagueID, seasonID);
//        //gson
//        Gson gson = new Gson();
//        Type gameListType = new TypeToken<LinkedList<Game>>() {
//        }.getType();
//        //policy 1
//        String stringRes1 = app.assignGames(leagueID, seasonID, true);
//        LinkedList<Game> resSavedGames1 = gson.fromJson(stringRes1, gameListType);
//        assignGameAssertions.assertPolicy1EvenOrPolicy2(resSavedGames1);
//        assignGameAssertions.assertPolicy1Odd(resSavedGames1);
//        // policy 2
//        String stringRes2 = app.assignGames(leagueID, seasonID, false);
//        LinkedList<Game> resSavedGames2 = gson.fromJson(stringRes2, gameListType);
//        assignGameAssertions.assertPolicy1EvenOrPolicy2(resSavedGames2);
//    }

//    @Test
//    public void assignRefereeTest() {
////        assignReferee(String leagueID,String seasonID, String refereeUsername)
//        String legueId = "2022";
//        String seasonID = "season1";
//        String refereeUsername = "ref1";
//
//        assignRefereeAssertions.assertInvalidUser(app, legueId, seasonID, refereeUsername);
//
//        app.login("stubAR", "1234");
//
//        assignRefereeAssertions.assertInvalidInput(app, legueId, seasonID, refereeUsername);
//        //invalid input
//
//        //todo : unassign referee
//
//        //ref1 already has assignment + assert success
//        assignRefereeAssertions.assertSuccess(app, legueId, seasonID, refereeUsername);
//        assignRefereeAssertions.assertRefAssigned(app, legueId, seasonID, refereeUsername);
//
//
//    }

}
