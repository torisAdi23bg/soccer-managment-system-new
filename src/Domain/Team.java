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
}
