package Service;

import Domain.*;

public class JavaApplication {
    public Controller domainController = Controller.getInstance();

    public String assignReferee(String leagueID,String seasonID, String refereeUsername) {
        return domainController.assignReferee(leagueID,seasonID,refereeUsername);
    }

    public String assignGames(String leagueID, String seasonID, boolean policy){
        return domainController.assignGames(leagueID, seasonID, policy);
    }

    public String login(String username,String password){
        return domainController.login(username,password);
    }
}