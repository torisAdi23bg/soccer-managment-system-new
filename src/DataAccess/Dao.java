package DataAccess;

import Domain.*;
import com.sun.tools.javac.util.Pair;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Dao {
    private static Dao single = null;
    private DBConnection con;
    private Dao()
    {
        con=new DBConnection();
    }
    public static Dao getInstance()
    {
        if (single == null)
            single = new Dao();

        return single;
    }

    // return null if no such subscriber
    //DONE
    public Subscriber getSubscriber(String username) {
        Subscriber subscriber=null;

    try {
        Connection connection= DBConnection.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="SELECT * FROM SUBSCRIBER";
        ps=connection.prepareStatement(sql);
        rs= ps.executeQuery();
        String res="";
        while(rs.next()){
            if(rs.getString("USERNAME").equals(username)){
                res=rs.getString("PASSWORD");
                break;}
        }
    if (res.equals(""))
        return null;
     subscriber = new Subscriber(username,res);
    }catch (Exception e){
    e.getMessage();
}
        return subscriber;

    }

    //return null if no such referee
    public Referee getReferee(String username) {
        Referee referee = null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM REFEREE";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String res = "";
            while (rs.next()) {
                if (rs.getString("USERNAME").equals(username)) {
                    res = rs.getString("QUALIFICATION");
                    break;
                }
            }
            if (res.equals(""))
                return null;
            referee = new Referee(username, res);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return referee;

    }
        //return null if no such league
    public League getLeague(String leagueID) {
        League league=null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM LEAGUE";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String res = "";
            while (rs.next()) {
                if (rs.getString("ID").equals(leagueID)) {
                    res = rs.getString("ID");
                    break;
                }
            }
            if (res.equals(""))
                return null;
            league = new League(res);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return league;
    }

    //return null if no such season
    public Season getSeason(String seasonID) {
        Season season=null;
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM SEASON";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String res = "";
            while (rs.next()) {
                if (rs.getString("ID").equals(seasonID)) {
                    res = rs.getString("ID");
                    break;
                }
            }
            if (res.equals(""))
                return null;
            season = new Season(res);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return season;
    }

     //TODO: CHECKKKKK
    //return null if no such leagueID,seasonID teams
    public LinkedList<Team> getAllTeams(String leagueID, String seasonID) {
        LinkedList<Team> teams=new LinkedList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM LEAGUESEASONTEAMS";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String res = "";
            while (rs.next()) {
                if (rs.getString("LEAGUE").equals(leagueID)&&rs.getString("SEASON").equals(seasonID)) {
                    teams.add(getTeam(rs.getString("TEAM")));
                    break;
                }
            }
            if (teams.size()==0)
                return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teams;
    }


   public Team  getTeam(String teamid){
       Team team=null;
       try {
           Connection connection = DBConnection.getConnection();
           PreparedStatement ps = null;
           ResultSet rs = null;
           String sql = "SELECT * FROM TEAM";
           ps = connection.prepareStatement(sql);
           rs = ps.executeQuery();
           String res = "";
           while (rs.next()) {
               if (rs.getString("ID").equals(teamid)) {
                   res="found";
                   break;
               }
           }
           if (res.equals(""))
               return null;
           team = new Team(rs.getString("ID"),rs.getString("HOMEFIELD")
           ,rs.getString("ACTIVATED").equals("TRUE"),rs.getString("CLOSEDFOREVER").equals("TRUE"));

       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return team;


    }



    //return true if succeeded
    public boolean saveGames(LinkedList<Game> newGamesToSave) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = null;
            String sql="INSERT INTO Game (TEAM1 , TEAM2 , MAINREFEREE , SCORE, DATE,FIELD ) VALUES(?,?,?,?,?,?,?)";
            for(int i=0;i< newGamesToSave.size();i++){
                Game g= newGamesToSave.get(i);
                ps=connection.prepareStatement(sql);
                ps.setString(1,g.hosting.id);
                ps.setString(2,g.visiting.id);
                ps.setString(3,null);//TODO:ADD REFEREE TO GAME!!!!!
                ps.setString(4,g.score);
                ps.setString(5,g.date);
                ps.setString(6,g.field);
                ps.executeQuery();
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }


    //return "true" if succeeded, "false" else
    //no need to check if existing in db. They are existing for sure.
    public String addRefereeToLEAGUESEASONTable(Referee referee, League league, Season season) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = null;
            String sql="INSERT INTO LeagueSeasonReferee (League , Season , Referee ) VALUES(?,?,?)";

                ps=connection.prepareStatement(sql);
                ps.setString(1,referee.username);
                ps.setString(2,league.id);
                ps.setString(3,season.id);
                ps.executeQuery();

        }catch (Exception e){
            return "false";
        }
        return "true";
    }

    //the referee is for sure inside db (no need to check)
//     return linkedList of <season,league> of the referee
    public LinkedList<Pair<Season, League>> getRefereeAssignments(Referee referee) {

        LinkedList<Pair<Season,League>> pairs=new LinkedList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM LeagueSeasonReferee";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("REFEREE").equals(referee.username)) {
                    pairs.add(new Pair<Season,League>(new Season(rs.getString("SEASON")),new League(rs.getString("LEAGUE"))));
                }
            }
            if (pairs.size()==0)
                return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pairs;
    }
}
