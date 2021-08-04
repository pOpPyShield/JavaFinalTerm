package ObjectZZ;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    private int IDBook;
    private String NameOfBook;
    private float price;
    private Author author;
    private Date date;
    private String type;
    private File image;

    public Book(int IDBook, String nameOfBook, float price, Author author, Date date, String type, File image) {
        this.IDBook = IDBook;
        NameOfBook = nameOfBook;
        this.price = price;
        this.author = author;
        this.date = date;
        this.type = type;
        this.image = image;
    }

    public int getIDBook() {
        return IDBook;
    }

    public void setIDBook(int IDBook) {
        this.IDBook = IDBook;
    }

    public String getNameOfBook() {
        return NameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        NameOfBook = nameOfBook;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String displayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(this.date);
    }

    public String displayPrice() {
        return String.format("%,.2f", this.price);
    }

}
