package Domain;

public class Controller {

    private static Controller single = null;
    private static Dao db;
    private Controller()
    {
        db= Dao.getInstance();
    }
    public static Controller getInstance()
    {
        if (single == null)
            single = new Controller();

        return single;
    }

    public static void signReferee(){
    }
}
