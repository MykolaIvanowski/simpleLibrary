package entity;

public class Book {
    @ColumnName(columnName = "book_id")
    private int id;
    @ColumnName(columnName = "book_name")
    private String bookName;
    @ColumnName(columnName = "count_page")
    private int countPage;
    @ColumnName(columnName = "author_name")
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCountPage() {
        return countPage;
    }

    public void setCountPage(int countPage) {
        this.countPage = countPage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
