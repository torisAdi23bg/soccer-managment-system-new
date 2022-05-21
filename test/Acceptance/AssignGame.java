package Acceptance;

import DataAccess.Dao;
import Domain.Game;
import Domain.Team;
import Service.JavaApplication;
import com.google.gson.Gson;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class AssignGame {
    JavaApplication app = new JavaApplication();
    String leagueID = "2022";
    String seasonID = "season1";
    Gson gson = new Gson();

    Dao db=Dao.getInstance();


    public void afterAll(){
        Dao db=Dao.getInstance();
        db.deleteAllRows("LeagueSeasonReferee");
        db.deleteAllRows("Game");
    }

    @Test
    public void invalidSubscriber() {
        app.login("1", "1");
        assertEquals("The user that is currently logged in is not an Association Representive",
                app.assignGames(leagueID, seasonID, true));
        app.logout();
        afterAll();

    }
    @Test
    public void assertNullSeason( ) {
        app.login("2","2");
        assertEquals("Enter valid details", app.assignGames(leagueID, null, true));
        app.logout();
    }
    @Test
    public void assertNullLeague( ) {
        app.login("2","2");
        assertEquals("Enter valid details", app.assignGames(null, seasonID, true));
        app.logout();
    }
    @Test
    public void assertLeagueDoesntExist( ) {
        app.login("2","2");
        assertEquals("Enter valid details", app.assignGames("not exist", seasonID, true));
        app.logout();
    }
    @Test
    public void assertSeasonDoesntExist( ) {
        app.login("2","2");
        assertEquals("Enter valid details", app.assignGames(leagueID, "not exist", true));
        app.logout();
    }

    @Test
    public void assertPolicy1Even() {
        app.login("2","2");
        String res1 = app.assignGames(leagueID, "season1", true);
        db.deleteAllRows("Game");

        //all teams-BARCELONA,HAPOEL,MACCABI REAL
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);
        LinkedList<Game> expectedRes1 = new LinkedList<>();
        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
        String expected = gson.toJson(expectedRes1);
        assertEquals(expected,res1);
        afterAll();
        app.logout();

    }
    @Test
    public void assertPolicy1Odd() {
        app.login("2","2");
        String res2 = app.assignGames(leagueID, "summer", true);
        db.deleteAllRows("Game");

        //all teams-BARCELONA,HAPOEL,MACCABI REAL
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);
        Team hapoel = new Team("HAPOEL", "H",true,true);

        LinkedList<Game> expectedRes1 = new LinkedList<>();

        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
//        expectedRes1.add(new Game(hapoel.homeField, hapoel, maccabi));
//        expectedRes1.add(new Game(maccabi.homeField, maccabi, barcelona));
        String expected = gson.toJson(expectedRes1);
        assertEquals(expected,res2);
        afterAll();
        app.logout();

    }
    @Test
    public void assertPolicy2() {
        app.login("2","2");
        String res3 = app.assignGames(leagueID, "summer", false);
        db.deleteAllRows("Game");

        //all teams-BARCELONA,HAPOEL,MACCABI REAL
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);
        Team hapoel = new Team("HAPOEL", "H",true,true);

        LinkedList<Game> expectedRes1 = new LinkedList<>();
        expectedRes1.add(new Game(maccabi.homeField, maccabi, barcelona));
        expectedRes1.add(new Game(maccabi.homeField, maccabi, hapoel));
        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
        expectedRes1.add(new Game(barcelona.homeField, barcelona, hapoel));
        expectedRes1.add(new Game(hapoel.homeField, hapoel, maccabi));
        expectedRes1.add(new Game(hapoel.homeField, hapoel, barcelona));
        String expected = gson.toJson(expectedRes1);

        assertEquals(expected, res3);
        afterAll();
        app.logout();

    }
}
