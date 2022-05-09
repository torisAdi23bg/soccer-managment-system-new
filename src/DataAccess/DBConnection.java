package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection(){
        Connection connectionToDB=null;
        try {
            Class.forName("org.sqlite.JDBC");
            connectionToDB=DriverManager.getConnection("jdbc:sqlite:src/DataAccess/DB/LeagueManagmentDB.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connectionToDB;
    }


}
