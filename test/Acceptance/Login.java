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
        app.logout();

        assertEquals("false", app.login("stubAR", ""));
        app.logout();

        assertEquals("false", app.login("", ""));
        app.logout();

        assertEquals("false", app.login("not exist", ""));
        app.logout();

        assertEquals("false", app.login("stubAR", "incorrect password"));
        app.logout();


        assertEquals("true", app.login("stubAR", "1234"));
        app.logout();

    }
}
