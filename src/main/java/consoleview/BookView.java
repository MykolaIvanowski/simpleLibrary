package consoleview;

import controller.BookController;
import entity.Book;
import logger.Log;

import java.util.List;
import java.util.Scanner;

public class BookView {
    BookController controller = new BookController();

    public  void start(){
        while(true){
            System.out.println("write command value");
            Scanner scanner = new Scanner(System.in);
            String consoleRead = scanner.nextLine();
            String[] array = consoleRead.split(" ",2);
            try {
                String command = array[0];
                if(command.equals("-exit")){
                    System.out.println("exit program");
                    break;
                }
                if(command.equals("-add")) {
                    add(array[1]);
                }else if(command.equals("-remove")){
                    remove(array[1]);
                }else if(command.equals("-edit")){
                    editBook(array[1]);
                }else if(command.equals("-all")){
                    printAllBook();
                }else if (command.equals("-find")){
                    findByName(array[1]);
                }else{
                    System.out.println(command+" not a command");
                }
            }catch (ArrayIndexOutOfBoundsException ex){
                System.out.println(" after command should be a value");
            }
        }
    }

    public void add(String book){
        controller.add(book);
        Log.infoMessage(book +" was added");
    }

    public void findByName(String name){
       List<Book> books = controller.findByName(name);
       if(books.isEmpty()){
           System.out.println("don't find book");
       }else{
            printBooks(books);
       }
    }

    public void remove(String name){

        controller.remove(name);
        Log.infoMessage(name +" was remove");
    }

    public void editBook(String name){
        controller.update(name);
        Log.infoMessage(name +" was edit");
    }

    public void printAllBook(){
      List<Book> books = controller.getAll();
        printBooks(books);
    }

    private void printBooks(List<Book> books){
        System.out.println("Books library\n        id | author name     | book name                 | count page   |");
        for (Book b:books) {
            System.out.println(String.format(" %1$9d | %2$15s | %3$25s | %4$12d |",b.getId(),
                    b.getAuthor(),b.getBookName(),b.getCountPage()));
        }
    }
}
