package Service;

import Domain.*;

public class JavaApplication {
    /*
    Java application
    League managment app
    available options:
        *Login
        *AssignGames
        *AssignReferee
        *LogOut
     */
    public Controller domainController = Controller.getInstance();

    public String login(String username,String password){
        /*
        login function
        input:
            String username
            String password
        output:
            String status result
        login as user with username and password
         */
        String res= domainController.login(username,password);
        Logger.getInstance().logLogin(username, password,res);
        return res;
    }

    public String assignGames(String leagueID, String seasonID, boolean policy){
        /*
        AssignGames function
        input:
            String leagueID
            String seasonID
            boolean policy
        output:
            String status result
        Assign games for selected league and season according to chosen policy.
        Must be connected as Association Representative
         */
        String res=domainController.assignGames(leagueID, seasonID, policy);
        Logger.getInstance().logAssignGames(leagueID, seasonID, policy,res);
        return res;
    }

    public String assignReferee(String leagueID,String seasonID, String refereeUsername) {
        /*
        AssignReferee function
        input:
            String leagueID
            String seasonID
            String refereeUsername
        output:
            String status result
        Assign Referee to a selected league and season according to chosen username referee.
        Must be connected as Association Representative
        */
        String res= domainController.assignReferee(leagueID,seasonID,refereeUsername);
        Logger.getInstance().logAssignReferee(leagueID, seasonID, refereeUsername,res);
        return res;
    }

    public boolean logout()
    {
        /*
        logout function
        output:
            true
        logout from current user logged in
         */
        domainController.logOut();
        Logger.getInstance().logLogout();
        return true;
    }
}