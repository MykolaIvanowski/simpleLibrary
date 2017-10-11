package dao;

import entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
    void add(Book book) throws SQLException;
    List<Book> getAll() throws  SQLException;
    List<Book> getByName(String bookName) throws  SQLException;
    void update (Book book) throws  SQLException;
    void remove (int bookId) throws SQLException;
}
