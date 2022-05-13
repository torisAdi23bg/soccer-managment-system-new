package Domain;

public class Team {

    public String id;
    public String homeField;
    public boolean activated;
    public boolean closedForEver;

    public Team(String id, String homeField, boolean activated, boolean closedForEver) {
        this.id = id;
        this.homeField = homeField;
        this.activated = activated;
        this.closedForEver = closedForEver;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }


        if (!(o instanceof Team)) {
            return false;
        }

        Team otherTeam = (Team) o;

        // Compare the data members and return accordingly
        return id==otherTeam.id;
    }

}
