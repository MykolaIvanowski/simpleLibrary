package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
    private static final  String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final  String DB_URL ="jdbc:mysql://localhost:3306/?library_task";
    private static final  String DB_USERNAME ="root";
    private static final  String DB_PASSWORD="admin";

    private ConnectionDB(){}

    public static Connection getConnection()throws SQLException{
        Connection connection = null;
        if(connection == null) {
            try {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            }catch ( ClassNotFoundException | SQLException ex){
                ex.printStackTrace();
            }
        }
        return  connection;
    }
}
