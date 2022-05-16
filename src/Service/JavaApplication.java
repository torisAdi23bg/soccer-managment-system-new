package Service;

import Domain.*;

public class JavaApplication {
    public Controller domainController = Controller.getInstance();

    public String assignReferee(String leagueID,String seasonID, String refereeUsername) {
        String res= domainController.assignReferee(leagueID,seasonID,refereeUsername);
        Logger.getInstance().logAssignReferee(leagueID, seasonID, refereeUsername,res);
        return res;
    }

    public String assignGames(String leagueID, String seasonID, boolean policy){
        String res=domainController.assignGames(leagueID, seasonID, policy);
        Logger.getInstance().logAssignGames(leagueID, seasonID, policy,res);
        return res;
    }

    public String login(String username,String password){
        String res= domainController.login(username,password);
        Logger.getInstance().logLogin(username, password,res);
        return res;
    }
}