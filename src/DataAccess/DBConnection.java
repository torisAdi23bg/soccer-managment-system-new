package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connectionToDB=null;
    public static Connection getConnection(){

        try {
            if(connectionToDB!=null){connectionToDB.close();}
            Class.forName("org.sqlite.JDBC");
            connectionToDB=DriverManager.getConnection("jdbc:sqlite:src/DataAccess/DB/LeagueManagmentDB.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connectionToDB;
    }


}
