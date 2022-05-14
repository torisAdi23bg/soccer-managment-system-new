package Domain;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class Referee extends Subscriber{
    public LinkedList<Pair<Season, League>> assignments;

    public String qualification;

    public Referee(String username,String password, String qualification) {
        super(username,password);
        this.qualification = qualification;
        this.assignments=new LinkedList<>();
    }

    public boolean isAssignmentExists(Season season, League league) {
        for (Pair<Season,League> assignment: assignments)
        {
            if (season.id.equals(assignment.getKey().id) && league.id.equals(assignment.getValue().id))
                return true;
        }
        return false;
    }
}
