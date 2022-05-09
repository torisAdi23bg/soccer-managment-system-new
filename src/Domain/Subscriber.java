package Domain;

public abstract class Subscriber {

    public String username;
    public String password;
    public String class_name;
    public boolean mailOrAlert;

    public Subscriber(String username, String password) {
        this.username = username;
        this.password = password;
        this.class_name="None";
        mailOrAlert=false;
    }


}
