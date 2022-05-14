import DataAccess.Dao;
import Domain.*;
import Service.JavaApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.util.Pair;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Acceptance {
    private static Dao db=Dao.getInstance();;
    JavaApplication app=new JavaApplication();
//    @BeforeEach
//    public void initLogin() {
//        //ar: association reepresentive
//        app.login("stubAR","1234");
//    }
    @Test
    public void loginTest() {
        //ar: association reepresentive
        assertEquals("false",app.login("","1234"));
        assertEquals("false",app.login("stubAR",""));
        assertEquals("false",app.login("",""));
        assertEquals("false",app.login("not exist",""));
        assertEquals("false",app.login("stubAR","incorrect password"));

        assertEquals("true",app.login("stubAR","1234"));
    }
    @Test
    public void assignGamesTest() {
//        assignGames(String leagueID, String seasonID, boolean policy)

        String leagueID = "league1";
        String seasonID = "season1";
        LinkedList<Team> allGroupsInSeasonLeague = db.getAllGroups(leagueID, seasonID);

        //invalid inputs
        assertEquals("Enter valid details", app.assignGames(leagueID, null, true));
        assertEquals("Enter valid details", app.assignGames(null, seasonID, true));
        //todo : assert ids that are not in the DB

        Gson gson = new Gson();
        // policy 1
        String stringRes1 = app.assignGames(leagueID, seasonID, true);
        System.out.printf(stringRes1);
        Type gameListType = new TypeToken<LinkedList<Game>>(){}.getType();
        LinkedList<Game> resSavedGames1 = gson.fromJson(stringRes1, gameListType);

        for (int i = 0; i < allGroupsInSeasonLeague.size(); i += 2) {
            Team team1 = allGroupsInSeasonLeague.get(i);
            Team team2 = allGroupsInSeasonLeague.get(i + 1);
            Game newGame;
            if (team1.id.compareTo(team2.id) < 0) //team1 is before team2 lexicographically
            {
                newGame = new Game(team1.homeField, team1, team2);
            } else {
                newGame = new Game(team2.homeField, team2, team1);
            }
            assert (resSavedGames1.contains(newGame));

        }
        //todo : delete saved games from DB before next section?

        // policy 2
        String stringRes2 = app.assignGames(leagueID, seasonID, false);
        LinkedList<Game> resSavedGames2 = gson.fromJson(stringRes2, LinkedList.class);
        for (int i = 0; i < allGroupsInSeasonLeague.size() - 1; i += 2) {
            Team team1 = allGroupsInSeasonLeague.get(i);
            Team team2 = allGroupsInSeasonLeague.get(i + 1);
            Game newGame;
            if (team1.id.compareTo(team2.id) < 0) //team1 is before team2 lexicographically
            {
                newGame = new Game(team1.homeField, team1, team2);
            } else {
                newGame = new Game(team2.homeField, team2, team1);
            }
            assert (resSavedGames2.contains(newGame));

        }
    }
    @Test
    public void assignRefereeTest(){
//        assignReferee(String leagueID,String seasonID, String refereeUsername)
        String legueId="stubAR";
        String seasonID = "season1";
        String refereeUsername="ref1";

        //todo : no user is currently logged in use case
        //todo : a user that is not AR is currently logged in use case

        app.login("stubAR","1234");


        //invalid input
        assertEquals("Enter valid details",app.assignReferee("not exist",seasonID,refereeUsername));
        assertEquals("Enter valid details",app.assignReferee(legueId,"not exist",refereeUsername));
        assertEquals("Enter valid details",app.assignReferee(legueId,seasonID,"not exist"));
        //todo : unassign referee

        //ref1 already has assignment + assert success
        assertEquals("true",app.assignReferee(legueId,seasonID,refereeUsername)); //assign so thr ref would have an assignment
        assertEquals("Assignment already exists",app.assignReferee(legueId,seasonID,refereeUsername));



    }

}