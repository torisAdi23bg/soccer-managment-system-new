package Domain;

import DataAccess.Dao;

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
    public static void signReferee(){
        if (currentLogged instanceof AssociationRepresentative){
            db.isExisting("Game",GameID)
        }
        else
        {
            return;
        }
    }
}
