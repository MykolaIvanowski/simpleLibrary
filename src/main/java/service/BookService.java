package service;

import dao.BookDAO;
import entity.Book;
import logger.Log;
import util.ConnectionDB;

import java.sql.*;
import java.util.List;

public class BookService implements BookDAO {
    private Connection connection;
    PreparedStatement preparedStatement;
    Statement statement;
    HelpTransform<Book> transform = new HelpTransform<>();
    private  static final String INSERT = "INSERT INTO library_task_mi.book (book_name,count_page,author_name)"+
            "VALUES(?,?,?)";
    private static final String GET_ALL = "SELECT * FROM library_task_mi.book";
    private static final String GET_BY_NAME ="SELECT * FROM library_task_mi.book WHERE book_name LIKE ?";
    private static final String UPDATE ="UPDATE library_task_mi.book SET author_name=?,book_name=?,count_page=?" +
            " WHERE book_id = ?";
    private static final String DELETE ="DELETE FROM library_task_mi.book WHERE book_id =?";



    public void add(Book book) throws SQLException {
        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setInt(2, book.getCountPage());
            preparedStatement.setString(3,book.getAuthor());
            preparedStatement.executeUpdate();
            Log.infoMessage("add successes");
        }finally {
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public List<Book> getAll() throws SQLException {
        List<Book> books;
        try{
            connection = ConnectionDB.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            books = transform.transfer(resultSet,Book.class);
            Log.infoMessage("get all successes");
        }
        finally {
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
        return books;
    }

    public List<Book> getByName(String  bookName) throws SQLException {
        List<Book> books ;
        try{
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(GET_BY_NAME);
            preparedStatement.setString(1,bookName.trim()+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            books = transform.transfer(resultSet,Book.class);
            Log.infoMessage("get by name successes");
        }finally {
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
        return books;
    }

    public void update(Book book) throws SQLException {
        try{
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,book.getAuthor());
            preparedStatement.setString(2,book.getBookName());
            preparedStatement.setInt(3,book.getCountPage());
            preparedStatement.setInt(4,book.getId());
            preparedStatement.executeUpdate();
            Log.infoMessage("update successes");
        }finally {
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public void remove(int bookId)throws SQLException {
        try{
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,bookId);
            preparedStatement.execute();
            Log.infoMessage("remove successes");
        }finally {
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }
    }
}
