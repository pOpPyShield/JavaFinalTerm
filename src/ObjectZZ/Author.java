package ObjectZZ;

import java.io.FileInputStream;

public class Author {
    private String nameOfAuthor;
    private String day,month,year;
    private byte[] image;

    public Author(String nameOfAuthor, String day, String month, String year, byte[] image) {
        this.nameOfAuthor = nameOfAuthor;
        this.day = day;
        this.month = month;
        this.year = year;
        this.image = image;
    }

    public Author(String name) {
        this.nameOfAuthor = name;
    }
    public String getNameOfAuthor() {
        return nameOfAuthor;
    }

    public void setNameOfAuthor(String nameOfAuthor) {
        this.nameOfAuthor = nameOfAuthor;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return nameOfAuthor;
    }

}
