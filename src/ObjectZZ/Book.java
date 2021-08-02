package ObjectZZ;

public class Book {
    private int IDBook;
    private String NameOfBook;
    private long price;
    private String NameAuthor;
    private String type;

    public Book(int IDBook, String nameOfBook, long price, String nameAuthor, String type) {
        this.IDBook = IDBook;
        NameOfBook = nameOfBook;
        this.price = price;
        NameAuthor = nameAuthor;
        this.type = type;
    }

    public int getIDBook() {
        return IDBook;
    }

    public String getNameOfBook() {
        return NameOfBook;
    }

    public long getPrice() {
        return price;
    }

    public String getNameAuthor() {
        return NameAuthor;
    }

    public String getType() {
        return type;
    }
}
