package controller;

import entity.Book;
import helper.FindSubString;
import service.BookService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookController {

    BookService bookService = new BookService();
    Book book = new Book();
    List<Book> books = new ArrayList<>();
    FindSubString finder = new FindSubString();
    public void add(String value){
        try{
            book.setBookName(finder.findString(value,FindSubString.FIND_BETWEEN_QOUTES,1));
            int countPage =0;
            if(tryParseInt(value,FindSubString.FIND_LAST_NUMBER)){
                countPage = Integer.parseInt(finder.findString(value,FindSubString.FIND_LAST_NUMBER));
            }
            book.setCountPage(countPage);
            book.setAuthor(finder.findString(value,FindSubString.FIND_AUTHOR));
            bookService.add(book);
        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("incorrect value");
        }
    }
    private boolean  tryParseInt(String value,String pattern){
        try{
            Integer.parseInt(finder.findString(value,pattern));
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
    public  void remove(String value){
        try{
            bookService.remove(Integer.parseInt(finder.findString(value,FindSubString.FIND_FIRST_NUMBER)));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<Book> getAll(){
        try {
            books = bookService.getAll();
            sortBooks(books);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return books;
    }

    public void update(String value){
        try {
            book.setId(Integer.parseInt(finder.findString(value,FindSubString.FIND_FIRST_NUMBER)));
            int countPage =0;
            if(tryParseInt(value,FindSubString.FIND_LAST_NUMBER)){
                countPage = Integer.parseInt(finder.findString(value,FindSubString.FIND_LAST_NUMBER));
            }
            book.setCountPage(countPage);
            book.setBookName(finder.findString(value,FindSubString.FIND_BETWEEN_QOUTES,1));
            book.setAuthor(finder.findString(value,FindSubString.FIND_AUTHOR));
            bookService.update(book);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public List<Book> findByName(String bookName){
        try {
             books = bookService.getByName(bookName);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return books;
    }

    public List<Book> sortBooks(List<Book> books){
        if(!books.isEmpty()){
            Collections.sort(books, new Comparator<Book>() {
                @Override
                public int compare(final Book b1,final Book b2) {
                    return b1.getBookName().trim().toUpperCase()
                            .compareTo(b2.getBookName().trim().toUpperCase());
                }
            });
        }
        return books;
    }
}

