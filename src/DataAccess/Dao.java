package DataAccess;

import Domain.*;
import javafx.util.Pair;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Dao {
    private static Dao single = null;
    private DBConnection con;
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    private Dao() {
        con = new DBConnection();
    }

    public static Dao getInstance() {
        if (single == null)
            single = new Dao();

        return single;
    }

    private static void closeDB(Connection connection, ResultSet rs, PreparedStatement ps) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) { /* Ignored */}
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) { /* Ignored */}
        }
//        if (connection != null) {
//            try {
////                connection.close();
////            } catch (SQLException e) { /* Ignored */}
//        }

    }

    // return null if no such subscriber
    //DONE
    public Subscriber getSubscriber(String username) {
        Subscriber subscriber = null;

        try {
            connection = con.getConnection();


            String sql = "SELECT * FROM SUBSCRIBER";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String res = "";
            while (rs.next()) {
                if (rs.getString("USERNAME").equals(username)) {
                    res = rs.getString("PASSWORD");
                    break;
                }

            }

            if (res.equals(""))
            {
                subscriber=null;
            }
            else {
                subscriber = new Subscriber(username, res);
            }

        } catch (Exception e) {
            e.getMessage();
        } finally {
            closeDB(connection, rs, ps);
        }
        return subscriberToRealInstance(subscriber);

    }



    private Subscriber subscriberToRealInstance(Subscriber subscriber) {
        try {
            connection= con.getConnection();
            String sql="SELECT * FROM AssociationRepresentive WHERE USERNAME='"+subscriber.username+"'";
            ps=connection.prepareStatement(sql);
            rs= ps.executeQuery();
            String res="";
            while(rs.next()){
                if(rs.getString("USERNAME").equals(subscriber.username)){
                    return new AssociationRepresentive(subscriber.username,subscriber.password);
                }

            }
            sql="SELECT * FROM Referee WHERE USERNAME='"+subscriber.username+"'";
            ps=connection.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                if(rs.getString("USERNAME").equals(subscriber.username)){
                    return new Referee(subscriber.username,subscriber.password,rs.getString("QUALIFICATION"));
                }

            }


        }catch (Exception e){

            e.getMessage();
        }
        finally {
            closeDB(connection,rs,ps);

        }
        return null;

    }


    //return null if no such referee
    public Referee getReferee(String username) {
        Referee referee = null;
        try {
            connection = con.getConnection();
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
            {
                referee=null;
            }
            else
            {
                referee = new Referee(username, res,res); //todo: why?
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return referee;

    }
    //return null if no such league
    public League getLeague(String leagueID) {
        League league=null;
        try {
            connection = con.getConnection();

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
            {
                league=null;
            }
            else
            {
                league = new League(res);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return league;
    }

    //return null if no such season
    public Season getSeason(String seasonID) {
        Season season=null;
        try {
            connection = con.getConnection();

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
            {
                season=null;
            }
            else
            {
                season = new Season(res);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return season;
    }

    //TODO: CHECKKKKK
    //return null if no such leagueID,seasonID teams
    public LinkedList<Team> getAllTeams(String leagueID, String seasonID) {
        LinkedList<Team> teams=new LinkedList<>();
        LinkedList<String> teamsNames=new LinkedList<>();

        try {
            connection = con.getConnection();

            String sql = "SELECT * FROM LeagueSeasonTeams";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String res = "";
            while (rs.next()) {
                if (rs.getString("LEAGUE").equals(leagueID)&&rs.getString("SEASON").equals(seasonID)) {
                    teamsNames.add(rs.getString("TEAM"));

                }
            }

            if (teamsNames.size()==0)
                return new LinkedList<Team>();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            closeDB(connection,rs,ps);
        }
        for(String team: teamsNames)
        {
            teams.add(getTeam(team));
        }
        return teams;
    }


    public Team  getTeam(String teamid){
        Team team=null;
        try {
            connection = con.getConnection();

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
        finally {
            closeDB(connection,rs,ps);
        }
        return team;


    }
    public boolean isGameExist(Game g){
        boolean flag=false;
        try {
            connection = con.getConnection();

            String sql = "SELECT * FROM Game";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String res = "";
            while (rs.next()) {
                if (rs.getString("TEAM1").equals(g.hosting)&&rs.getString("TEAM2").equals(g.visiting)
                        &&rs.getString("DATE").equals(g.date)&&rs.getString("Field").equals(g.field)) {
                    flag=true;
                    break;
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return flag;
    }



    //return true if succeeded
    //
    public boolean saveGames(LinkedList<Game> newGamesToSave) {
        //before inserting all the games we check if it is valid to enter all of them (if its not we return false and wont enter them)
        for(int i=0;i< newGamesToSave.size();i++) {
            Game g = newGamesToSave.get(i);
            if (isGameExist(g)) {
                return false;
            }
        }


        try {
            for(int i=0;i< newGamesToSave.size();i++){
                Game g= newGamesToSave.get(i);
            connection = con.getConnection();

            String sql="INSERT INTO Game (TEAM1 , TEAM2 , MAINREFEREE , SCORE, DATE,FIELD ) VALUES(?,?,?,?,?,?)";

                ps=connection.prepareStatement(sql);
                ps.setString(1,g.hosting.id);
                ps.setString(2,g.visiting.id);
                ps.setString(3,"RefereeNeed");//TODO:ADD REFEREE TO GAME!!!!!
                ps.setString(4,g.score);
                ps.setString(5,g.date);
                ps.setString(6,g.field);
                ps.executeUpdate();
            }
            connection.close();
        }catch (Exception e){
            return false;
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return true;
    }

    public boolean isTripleOfRefereeExist(Referee referee,League league,Season season){
        boolean flag=false;
        try {
            connection = con.getConnection();

            String sql = "SELECT * FROM LeagueSeasonReferee";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String res = "";
            while (rs.next()) {
                if (rs.getString("REFEREE").equals(referee.username)&&rs.getString("LEAGUE").equals(league.id)&&rs.getString("Season").equals(season.id)) {
                    flag=true;
                    break;
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return flag;
    }


    //return "true" if succeeded, "false" else
    //no need to check if existing in db. They are existing for sure.
    public String addRefereeToLEAGUESEASONTable(Referee referee, League league, Season season) {
        if (isTripleOfRefereeExist(referee,league,season)){
            return "false";
        }

        try {

            connection = con.getConnection();


            String sql="INSERT INTO LeagueSeasonReferee(League, Season, Referee) VALUES(?,?,?)";

            ps=connection.prepareStatement(sql);
            ps.setString(1,league.id);
            ps.setString(2,season.id);
            ps.setString(3,referee.username);

            ps.executeUpdate();


        }catch (Exception e){

            return "false";
        }
        finally {
            closeDB(connection,rs,ps);
        }

        return "true";
    }

    //the referee is for sure inside db (no need to check)
//     return linkedList of <season,league> of the referee
    public LinkedList<Pair<Season, League>> getRefereeAssignments(Referee referee) {

        LinkedList<Pair<Season,League>> pairs=new LinkedList<>();
        try {
            connection = con.getConnection();

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
        finally {
            closeDB(connection,rs,ps);
        }
        return pairs;
    }

    public boolean deleteLeagueSeasonReferee(String leagueId,String seasonId,String refereeID){
        try {
            connection = con.getConnection();

            String sql = "DELETE FROM "+"LeagueSeasonReferee WHERE LEAGUE="+leagueId
                    +" AND SEASON="+seasonId+ " AND REFEREE="+refereeID;
            ps=connection.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return false;

    }
    public boolean deleteGame(Game game){
        try {
            connection = con.getConnection();

            String sql = "DELETE FROM "+"LeagueSeasonReferee WHERE TEAM1="+game.hosting
                    +" AND TEAM2="+game.visiting+ " AND DATE="+game.date+" AND FIELD="+game.field;
            ps=connection.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return false;

    }
    public boolean deleteAllRows(String TableName){
        try {
            connection = con.getConnection();

            String sql = "DELETE FROM "+TableName;
            ps=connection.prepareStatement(sql);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            closeDB(connection,rs,ps);
        }
        return false;

    }
}