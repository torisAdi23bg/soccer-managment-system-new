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
    @Override
    public boolean equals(Object other){
        if (other == this) {
            return true;
        }
        if (!(other instanceof Game)) {
            return false;
        }
        Game otherGame=(Game) other;
        return this.date==otherGame.date && this.field==otherGame.field && hosting.equals(otherGame.hosting)
                && visiting.equals(otherGame.visiting);
    }
    @Override
    public String toString(){
        return
                "Hosting:"+hosting+"\n"+
                        "Visiting:"+visiting+"\n"+
                        "Field:"+field+"\n"+
                        "Date:"+date+"\n";
    }
}
