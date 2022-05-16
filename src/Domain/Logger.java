package Domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final String logFile = "src/DataAccess/Log";
    private  FileWriter writer;

    private static Logger logger;

    private Logger() {
        try {
            writer = new FileWriter(logFile,true);
        } catch (IOException e) {}
    }


    public static Logger getInstance(){

        if (logger==null){
            return  new Logger();
        }
        return logger;
    }
    public   void logLogin (String username, String password,String message) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write("USER LOGGED IN: USERNAME: "+username+" | PASSWORD: "+password+" | MESSAGE: "+message+" TIME: "+dtf.format(now)+"\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  void logAssignGames(String leagueID, String seasonID, boolean policy,String message){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write("USER ASSIGNED GAMES IN: LEAGUE:"+leagueID+"|SEASON:"+seasonID+"|POLICY:"+policy+" | MESSAGE:"+message+" TIME: "+dtf.format(now)+"\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  void logAssignReferee(String leagueID,String seasonID, String refereeUsername,String message){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write("USER ASSIGNED REFEREE IN: LEAGUE:"+leagueID+"|SEASON:"+seasonID+"|REFEREE:"+refereeUsername+" |MESSAGE"+message+" TIME: "+dtf.format(now)+"\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void lofLogout(String username){
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write("USER LOGGED OUT: USER: "+username+" TIME: "+dtf.format(now)+"\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}