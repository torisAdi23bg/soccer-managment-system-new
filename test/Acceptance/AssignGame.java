package Acceptance;

import Service.JavaApplication;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import Domain.Game;
import Domain.Team;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class AssignGame {
    JavaApplication app = new JavaApplication();
    String leagueID = "2022";
    String seasonID = "season1";
    Gson gson = new Gson();
    Type gameListType = new TypeToken<LinkedList<Game>>() {
    }.getType();
    String res1 = app.assignGames(leagueID, seasonID, true);
    String res2 = app.assignGames(leagueID, seasonID, false);
    @BeforeEach
    public void beforeEach(){
        app.login("stubAR", "1234");
    }
    @Test
    public void invalidSubscriber() {
        app.login("1", "1234");
        assertEquals("The user that is currently logged in is not an Association Representive",
                app.assignGames(leagueID, seasonID, true));

    }
    @Test
    public void assertInvalidInputs( ) {
        assertEquals("Enter valid details", app.assignGames(leagueID, null, true));
        assertEquals("Enter valid details", app.assignGames(null, seasonID, true));
        assertEquals("Enter valid details", app.assignGames("not exist", seasonID, true));
        assertEquals("Enter valid details", app.assignGames(leagueID, "not exist", true));
    }
    @Test
    public void assertPolicy1Even() {

        //all teams-BARCELONA,HAPOEL,MACCABI REAL
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);


        LinkedList<Game> expectedRes1 = new LinkedList<>();
        expectedRes1.add(new Game(maccabi.homeField, maccabi, barcelona));
        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
        assertEquals(res1, expectedRes1);
    }
    @Test
    public void assertPolicy1Odd() {

        //all teams-BARCELONA,HAPOEL,MACCABI REAL
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);
        Team hapoel = new Team("HAPOEL", "H",true,true);

        LinkedList<Game> expectedRes1 = new LinkedList<>();

        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
        expectedRes1.add(new Game(hapoel.homeField, hapoel, maccabi));
        expectedRes1.add(new Game(maccabi.homeField, maccabi, barcelona));
        assertEquals(res1, expectedRes1);
    }
    @Test
    public void assertPolicy2() {

        //all teams-BARCELONA,HAPOEL,MACCABI REAL
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);


        LinkedList<Game> expectedRes1 = new LinkedList<>();
        expectedRes1.add(new Game(maccabi.homeField, maccabi, barcelona));
        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
        assertEquals(res2, expectedRes1);
    }
}
