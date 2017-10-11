import consoleview.BookView;
import helper.CreateDB;
import info.Info;
import logger.Log;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) {
        CreateDB createDB = new CreateDB();
        try {
            createDB.createDB();
        }catch (SQLException ex){
            Log.infoMessage("can't create db");
            ex.printStackTrace();
        }
        System.out.println(Info.infoMessage);
        BookView view = new BookView();
        view.start();
    }
}
