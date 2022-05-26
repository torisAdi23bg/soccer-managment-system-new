package Domain;

public class Team {
    /*
    Team class
     */

    public String id;
    public String homeField;
    public boolean activated;
    public boolean closedForEver;

    public Team(String id, String homeField,boolean activated,boolean closedForEvers) {
        /*
        Builder function
        input:
            String id
            String homefield
            boolean activated
            boolean closedForEvers
         */
        this.id = id;
        this.homeField = homeField;
        this.activated = activated;
        this.closedForEver = closedForEver;
    }

    @Override
    public boolean equals(Object o) {
        /*
        Equals override function
        output:
            returns true is equal
         */
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }


        if (!(o instanceof Team)) { //if o not an instance of team
            return false;
        }

        Team otherTeam = (Team) o;

        // Compare the data members and return accordingly
        return id==otherTeam.id;
    }
    @Override
    public String toString(){
        /*
        toString override function
        output:
            String bu chosen format
         */
        return id;
    }

}
