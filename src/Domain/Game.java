package Domain;

import java.util.LinkedList;
import java.util.List;

public class Game {
    /*
    Game class
     */
    public String date;
    public String field;
    public String score;
    public List<Event> eventsLog;
    public Team hosting;
    public Team visiting;

    public Game(String field, Team hosting, Team visiting) {
        /*
        Builder function
        input:
            string field
            Team hosting
            Team visiting
         */
        this.date = "2022 SUMMER";
        this.field = field;
        this.hosting = hosting;
        this.visiting = visiting;
        this.score="0:0";
        this.eventsLog=new LinkedList<>();
    }
    @Override
    public boolean equals(Object other){
        /*
        Equals override function
        output:
            returns true is equal
         */
        if (other == this) { //if other is the same object
            return true;
        }
        if (!(other instanceof Game)) { //if other not an instance of game
            return false;
        }
        Game otherGame=(Game) other;
        return this.date==otherGame.date && this.field==otherGame.field && hosting.equals(otherGame.hosting)
                && visiting.equals(otherGame.visiting); //if game and other have the same date, field, hosting and visiting
    }
    @Override
    public String toString(){
        /*
        toString override function
        output:
            String bu chosen format
         */
        return
                "Hosting:"+hosting+"\n"+
                        "Visiting:"+visiting+"\n"+
                        "Field:"+field+"\n"+
                        "Date:"+date+"\n";
    }
}
