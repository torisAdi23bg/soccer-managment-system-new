package DataAccess;

import Domain.Controller;

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

}
