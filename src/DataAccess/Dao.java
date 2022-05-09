package DataAccess;

import Domain.Controller;
import Domain.Subscriber;

public class Dao {
    private static Dao single = null;

    private Dao()
    {
    }
    public static Dao getInstance()
    {
        if (single == null)
            single = new Dao();

        return single;
    }

    // return null if no such subscriber
    public Subscriber getSubscriber(String username, String password) {
        return null;
    }

    public String getClassName(Subscriber subscriber) {
        return "";
    }
}
