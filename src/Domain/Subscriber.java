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

    public boolean login(String password){
        if (this.password.equals(password)){
            return true;
        }
        return false;
    }


}
