package Service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Acceptance {

    JavaApplication app=new JavaApplication();
    @BeforeEach
    public void initLogin() {
        //ar: association reepresentive
        app.login("stubAR","1234");
    }
    @Test
    public void loginTest() {
        //ar: association reepresentive
        assertFalse(app.login("","1234"));
        assertFalse(app.login("stubAR",""));
        assertFalse(app.login("",""));
        assertFalse(app.login("not exist",""));
        assertFalse(app.login("stubAR","incorrect password"));

        assertTrue(app.login("stubAR","1234"));
    }
    @Test
    public void assignGameTest(){
//        assignGame(String gameId,String team1,String team2,List<String> referees,String mainReferee)
        List<String> refList=new ArrayList<String>();
        refList.add("ref1");
        refList.add("ref2");

//        assertFalse(app.assignGame("","team1","team2",refList,"ref3"));
//        assertFalse(app.assignGame("game1","","team2",refList,"ref3"));
//        assertFalse(app.assignGame("game1","team1","",refList,"ref3"));
//        assertFalse(app.assignGame("game1","team1","team2",new ArrayList<>(),"ref3"));
//        assertFalse(app.assignGame("game1","team1","",refList,""));
//
//        assertFalse(app.assignGame("not exist","team1","team2",refList,"ref3"));
//        assertFalse(app.assignGame("game1","not exist","team2",refList,"ref3"));
//        assertFalse(app.assignGame("game1","team1","not exist",refList,"ref3"));
//        assertFalse(app.assignGame("game1","team1","not exist",null,"ref3"));
//        assertFalse(app.assignGame("game1","team1","team2",refList,"not exist"));
//
//        refList.add("ref not exist");
//        assertFalse(app.assignGame("game1","team1","team2",refList,"ref3"));
//        refList.remove("ref not exist");


//        assertTrue(app.assignGame("game1","team1","team2",refList,"ref3"));
    }
    @Test
    public void assignRefereeTest(){
//        assignReferee(String gameId,String refereeId)

        assertFalse(app.assignReferee("","ref1"));
        assertFalse(app.assignReferee("game1",""));

        assertFalse(app.assignReferee("not exist","ref1"));
        assertFalse(app.assignReferee("game1","not exist"));

        assertTrue(app.assignReferee("game1","ref1"));
    }

}