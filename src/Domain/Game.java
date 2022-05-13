package Domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Game {
    public String date;
    public String field;
    public String score;
    public List<Event> eventsLog;
    public Team hosting;
    public Team visiting;

    public Game(String field, Team hosting, Team visiting) {
        this.date = "2022 SUMMER";
        this.field = field;
        this.hosting = hosting;
        this.visiting = visiting;
        this.score="0:0";
        this.eventsLog=new LinkedList<>();
    }
}
