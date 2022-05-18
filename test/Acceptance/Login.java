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
    public void assertBlankUsername() {
        assertEquals("false", app.login("", "1234"));
        app.logout();
    }
    @Test
    public void assertBlankPassword() {
        assertEquals("false", app.login("stubAR", ""));
        app.logout();
    }
    @Test
    public void assertBlankUsernameAndPassword() {
        assertEquals("false", app.login("", ""));
        app.logout();
    }
    @Test
    public void assertUsernameDontExist() {
        assertEquals("false", app.login("not exist", "1234"));
        app.logout();
    }
    @Test
    public void assertIncorrectPassword() {
        assertEquals("false", app.login("stubAR", "incorrect password"));
        app.logout();
    }
    @Test
    public void assertSuccess() {
        assertEquals("true", app.login("stubAR", "1234"));
        app.logout();
    }

}
