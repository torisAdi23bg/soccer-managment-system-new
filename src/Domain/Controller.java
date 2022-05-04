package Domain;

import DataAccess.Dao;

import java.util.List;

public class Controller {

    private static Controller single = null;
    private static Dao db;
    private static Subscriber currentLogged;

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

    /**
     * שחקן ראשי:נציג התאחדות
     * שחקנים משנים:
     * שופטים
     * preconditions:
     * נציג התאחדות מחובר למערכת
     * קיימת ליגה ועונה במערכת שלה לא משובצים עדיין שופטים
     * המשחק שלו נציג ההתאחדות רוצה להוסיף שופטים קיים
     *
     * תרחיש הצלחה ראשי:
     * נציג ההתאחדות נכנס לעריכת משחק
     * נציג ההתאחדות מוסיף השופטים שהוא רוצה לשבץ
     * נשלחת בקשה לשרת לשיבוץ
     * המערכת תודיע על הוספה מוצלחת
     * המערכת תתעד את ההוספה בLOGS
     *
     * תרחישים אלטרנטיבים:
     * התרחשה שגיאה בDB-המערכת תתריע לנציג
     * דרישות מיוחדות:
     * 1.חיבור לאינטרנט
     * טכנולוגיות:
     * 1.תקשורת עם פרוטוקול http
     * 2.חיבור לdb mongodb
     * תדירות:תמידית
     * עניינים פתוחים:
     * אין
     */


    public boolean login(String username,String password) {
    }

    public boolean assignGame(String gameId, String team1, String team2, List<String> referees, String mainReferee) {
    }

    public boolean assignReferee(String gameId, String refereeId) {
    }
}
