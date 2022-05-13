package Domain;

import org.junit.Test;
import javafx.util.Pair;

import static org.junit.Assert.*;

public class Unit {

    @Test
    public void subscriber_login_test() {
        Subscriber sub1 = new Subscriber("user1", "password1");

        assertEquals(false, sub1.login("password2"));
        assertEquals(true, sub1.login("password1"));

    }

    @Test
    public void Referee_isAssignmentExist(){
        Referee ref1 = new Referee("ref1", "a 10 years experience");

        Season season1 = new Season("winter");
        League league1 = new League("majors league");

        Season season2 = new Season("summer");
        League league2 = new League("minors league");

        ref1.assignments.add(Pair<season1,league1>);

        assertEquals(false, ref1.isAssignmentExists(Pair<season2,league2>));
        assertEquals(false, ref1.isAssignmentExists(Pair<season1,league2>));
        assertEquals(false, ref1.isAssignmentExists(Pair<season2,league1>));
        assertEquals(true, ref1.isAssignmentExists(Pair<season1,league1>));

    }



}