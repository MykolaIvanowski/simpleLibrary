package helper;

import logger.Log;
import util.ConnectionDB;
import java.sql.*;

public class CreateDB {
    public static final String CREATE_DB ="CREATE SCHEMA `library_task_mi` ;";
    public static final String CREATE_TABLE ="CREATE TABLE `library_task_mi`.`book` (\n" +
            "  `book_id` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `author_name` VARCHAR(45) NULL,\n" +
            "  `book_name` VARCHAR(45) NULL,\n" +
            "  `count_page` INT NULL,\n" +
            "  PRIMARY KEY (`book_id`),\n" +
            "  UNIQUE INDEX `book_id_UNIQUE` (`book_id` ASC));";
    Connection connection;
    Statement statement;
    public void createDB()throws SQLException{
        try{
            connection = ConnectionDB.getConnection();
            statement = connection.createStatement();
            DatabaseMetaData metaData = connection.getMetaData();
            if(findSchema(metaData)){
                statement.executeUpdate(CREATE_DB);
                statement.executeUpdate(CREATE_TABLE);
                Log.infoMessage("data base created");
            }else{
                Log.infoMessage("data base already exist");
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }finally {
            if(connection !=null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
        }
    }
    private boolean findSchema(DatabaseMetaData metaData) throws SQLException {
        ResultSet resultSet = metaData.getCatalogs();
        while(resultSet.next()){
            String dbName = resultSet.getString(1);
            if(dbName.equals("library_task_mi")){
                return false;
            }
        }
        return true;
    }
}
