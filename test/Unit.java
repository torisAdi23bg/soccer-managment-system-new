import DataAccess.Dao;
import Domain.*;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class Unit {
    Referee ref1 = new Referee("ref1", "a 10 years experience","qual");

    Season season1 = new Season("season1");
    League league1 = new League("2022");

    Season season2 = new Season("summer");
    League league2 = new League("2022");

    AssociationRepresentive AR = new AssociationRepresentive("2", "2");

    @Test
    public void subscriber_login_test_failure() {
        Subscriber sub1 = new Subscriber("user1", "password1");
        assertEquals(false, sub1.login("password2"));
    }

    @Test
    public void subscriber_login_test_success() {
        Subscriber sub1 = new Subscriber("user1", "password1");
        assertEquals(true, sub1.login("password1"));
    }

    @Test
    public void Test1_AssignmentExist_wrong_inputs(){
        ref1.assignments.add(new Pair(season1,league1));
        assertEquals(false, ref1.isAssignmentExists(season2,league2));
        ref1.assignments.remove(0);
    }

    @Test
    public void Test2_AssignmentExist_wrong_inputs(){
        ref1.assignments.add(new Pair(season1,league1));
        assertEquals(false, ref1.isAssignmentExists(season1,league2));
        ref1.assignments.remove(0);
    }

    @Test
    public void Test3_AssignmentExist_wrong_inputs(){
        ref1.assignments.add(new Pair(season1,league1));
        assertEquals(false, ref1.isAssignmentExists(season2,league1));
        ref1.assignments.remove(0);
    }

    @Test
    public void Test4_AssignmentExist_correct_inputs(){
        ref1.assignments.add(new Pair(season1,league1));
        assertEquals(true, ref1.isAssignmentExists(season1,league1));
        ref1.assignments.get(0);
    }

    @Test
    public void Test5_assignGames_policyTrue(){
        LinkedList<Game> result = AR.assignGames(league1, season1, true);
    }

    @Test
    public void Test6_assignGames_policyTrue_stub(){
        LinkedList<Game> result =  AR.assignGames(league1, season1, true);
    }
    @Test
    public void Test7_assignGames_policyFalse(){
        LinkedList<Game> result = AR.assignGames(league1, season1, false);
    }

    @Test
    public void Test8_assignGames_policyFalse_stub(){
        LinkedList<Game> result = AR.assignGames(league1, season1, true);
    }