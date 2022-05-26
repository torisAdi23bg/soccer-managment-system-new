package Domain;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    /*
    Logger class
     */
    private final String logFile = "src/DataAccess/Log";
    private  FileWriter writer;

    private static Logger logger;

    private Logger() {
        /*
        Builder class
         */
        try {
            writer = new FileWriter(logFile,true);
        } catch (IOException e) {}
    }


    public static Logger getInstance(){
        /*
        getInstance function
        Logger is a singleton
        output:
            Logger
         */

        if (logger==null){
            return  new Logger();
        }
        return logger;
    }
    public   void logLogin (String username, String password,String message) {
        /*
        Logger writes to log file after login by chosen format
         */
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
        /*
        Logger writes to log file after AssigningGames by chosen format
         */
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
        /*
        Logger writes to log file after AssigningReferee by chosen format
         */
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write("USER ASSIGNED REFEREE IN: LEAGUE:"+leagueID+"|SEASON:"+seasonID+"|REFEREE:"+refereeUsername+" |MESSAGE"+message+" TIME: "+dtf.format(now)+"\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void logLogout(){
        /*
        Logger writes to log file after logout by chosen format
         */
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write("USER LOGGED OUT: USER:"+" TIME: "+dtf.format(now)+"\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}