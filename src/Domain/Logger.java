package Domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private final String logFile = "log.txt";
    private PrintWriter writer;
    private Logger() {
        try {
            FileWriter fw = new FileWriter(logFile);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {}
    }
    public void logLogin (String username, String password,String message) {
        writer.println("USER LOGGED IN: USERNAME:"+username+"|PASSWORD:"+password+"|MESSAGE:"+message);
    }
    public void logAssignGames(String leagueID, String seasonID, boolean policy){
        writer.println("USER ASSIGNED GAMES IN: LEAGUE:"+leagueID+"|SEASON:"+seasonID+"|POLICY:"+policy);
    }





}