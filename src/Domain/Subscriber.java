package Domain;

public class Subscriber {
    /*
    Subscriber class
     */

    public String username;
    public String password;
    public String class_name;
    public boolean mailOrAlert;

    public Subscriber(String username, String password) {
        /*
        Builder function
        input:
            String username
            String password
         */
        this.username = username;
        this.password = password;
        this.class_name="None";
        mailOrAlert=false;
    }

    public boolean login(String password){
        /*
        login function
        input:
            String password
        output:
            returns true if login is successful, else false
         */
        if (this.password.equals(password)){
            return true;
        }
        return false;
    }


}
