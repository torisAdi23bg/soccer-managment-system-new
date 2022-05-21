import DataAccess.Dao;
import Domain.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.function.ToDoubleBiFunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Unit {
    Referee ref1 = new Referee("ref1", "a 10 years experience","qual");

    Season season1 = new Season("season1");
    League league1 = new League("2022");

    Season season2 = new Season("summer");
    League league2 = new League("2022");

    AssociationRepresentive AR = new AssociationRepresentive("2", "2");
//
    @Test
    public void Unit1_AssignmentExist_wrong_inputs(){
        ref1.assignments.add(new Pair(season1,league1));
        assertEquals(false, ref1.isAssignmentExists(season2,league2));
        ref1.assignments.remove(0);
    }

    @Test
    public void Unit2_AssignmentExist_wrong_inputs(){
        ref1.assignments.add(new Pair(season1,league1));
        assertEquals(false, ref1.isAssignmentExists(season1,league2));
        ref1.assignments.remove(0);
    }

    @Test
    public void Unit3_AssignmentExist_wrong_inputs(){
        ref1.assignments.add(new Pair(season1,league1));
        assertEquals(false, ref1.isAssignmentExists(season2,league1));
        ref1.assignments.remove(0);
    }

    @Test
    public void Unit4_AssignmentExist_correct_inputs(){
        ref1.assignments.add(new Pair(season1,league1));
        assertEquals(true, ref1.isAssignmentExists(season1,league1));
        ref1.assignments.get(0);
    }

    @Test
    public void Unit5_assignGames_policyTrue_even(){
        LinkedList<Game> result = AR.assignGames(league1, season1, true);
        //all teams-BARCELONA,HAPOEL,MACCABI REAL
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);
        LinkedList<Game> expectedRes1 = new LinkedList<>();
        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
        assertEquals(result.get(0), expectedRes1.get(0));
    }

    @Test
    public void Unit6_assignGames_policyTrue_odd(){
        LinkedList<Game> result = AR.assignGames(league1, season2, true);
        //all teams-BARCELONA,HAPOEL,MACCABI REAL
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);

        LinkedList<Game> expectedRes1 = new LinkedList<>();
        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));

        assertEquals(result.get(0), expectedRes1.get(0));
    }

    private LinkedList<Game> true_stub(LinkedList<Team> teams){
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);
        LinkedList<Game> expectedRes1 = new LinkedList<>();
        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
        return expectedRes1;
    }

    private LinkedList<Game> assignGames_with_stub_policy1(League league, Season season, boolean policy){
        Dao db= Dao.getInstance();
        LinkedList<Team> allGroupsInSeasonLeague = db.getAllTeams(league.id,season.id);
        LinkedList<Game> newGamesToSave;

        if (policy==true){
            //every couple have one game. so every team plays at most one game .the field will be for the team lexicographically first.
            // If odd number - one team not playing.
            newGamesToSave=true_stub(allGroupsInSeasonLeague);
        }
        else
        {
            //every team plays two games against every other team.
            // the field will be one time for the first group, and another time for the second. So each team plays 2*(n-1) games
            // (when n is number of groups).
            newGamesToSave=null;
        }
        return newGamesToSave;
    }

    @Test
    public void Unit7_assignGames_policyTrue_stub(){
        LinkedList<Game> result =  AR.assignGames(league1, season1, true);
        LinkedList<Game> stubed_results = this.assignGames_with_stub_policy1(league1, season1, true);
        assertEquals(result, stubed_results);
    }

    @Test
    public void Unit8_assignGames_policyFalse(){
        LinkedList<Game> result = AR.assignGames(league1, season2, false);

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

        boolean check_if_equal = true;
        for (int i=0;i<result.size();i++){
            check_if_equal = check_if_equal && result.get(i).equals(expectedRes1.get(i));
        }
        assertTrue(check_if_equal);
    }

    private LinkedList<Game> false_stub(LinkedList<Team> teams){
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
        return expectedRes1;
    }

    private LinkedList<Game> assignGames_with_stub_policy2(League league, Season season, boolean policy){
        Dao db= Dao.getInstance();
        LinkedList<Team> allGroupsInSeasonLeague = db.getAllTeams(league.id,season.id);
        LinkedList<Game> newGamesToSave;

        if (policy==true){
            //every couple have one game. so every team plays at most one game .the field will be for the team lexicographically first.
            // If odd number - one team not playing.
            newGamesToSave=null;
        }
        else
        {
            //every team plays two games against every other team.
            // the field will be one time for the first group, and another time for the second. So each team plays 2*(n-1) games
            // (when n is number of groups).
            newGamesToSave=false_stub(allGroupsInSeasonLeague);
        }
        return newGamesToSave;
    }

    @Test
    public void Unit9_assignGames_policyFalse_stub(){
        LinkedList<Game> result = AR.assignGames(league1, season2, false);
        LinkedList<Game> stubed_result = this.assignGames_with_stub_policy2(league1, season2, false);
        assertEquals(result, stubed_result);
    }

    @Test
    public void Unit10_subscriber_login_test_failure() {
        Subscriber sub1 = new Subscriber("user1", "password1");
        assertEquals(false, sub1.login("password2"));
    }

    @Test
    public void Unit11_subscriber_login_test_success() {
        Subscriber sub1 = new Subscriber("user1", "password1");
        assertEquals(true, sub1.login("password1"));
    }

    //TODO talk with harel

    /*@Test
    public void Test_assignpolicy1_even(){
    }

    @Test
    public void Test_assignpolicy1_odd(){
    }

    @Test
    public void Test_assignpolicy2(){
        LinkedList<Team> Teams = new LinkedList<>();
        Team maccabi = new Team("MACCABI", "M",true,true);
        Team barcelona = new Team("BARCELONA", "B",true,true);
        Team hapoel = new Team("HAPOEL", "H",true,true);

        Teams.add(maccabi);
        Teams.add(barcelona);
        Teams.add(hapoel);

        LinkedList<Game> result = AR.applypolicy2(Teams);


        LinkedList<Game> expectedRes1 = new LinkedList<>();
        expectedRes1.add(new Game(maccabi.homeField, maccabi, barcelona));
        expectedRes1.add(new Game(maccabi.homeField, maccabi, hapoel));
        expectedRes1.add(new Game(barcelona.homeField, barcelona, maccabi));
        expectedRes1.add(new Game(barcelona.homeField, barcelona, hapoel));
        expectedRes1.add(new Game(hapoel.homeField, hapoel, maccabi));
        expectedRes1.add(new Game(hapoel.homeField, hapoel, barcelona));

        boolean check_if_equal = true;
        for (int i=0;i<result.size();i++){
            check_if_equal = check_if_equal && result.get(i).equals(expectedRes1.get(i));
        }
        assertTrue(check_if_equal);


    }*/
}