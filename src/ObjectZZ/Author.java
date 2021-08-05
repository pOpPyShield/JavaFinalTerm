package ObjectZZ;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Author {
    private int idAuthor;
    private String nameOfAuthor;
    private Date date;
    private byte[] image;

    //Id in sql
    private int idSql;
    public Author(int idAuthorMySql, String nameOfAuthor, Date date, byte[] image) {
        this.nameOfAuthor = nameOfAuthor;
        this.date = date;
        this.image = image;
        this.idSql = idAuthorMySql;
    }

    public Author(int idAuthorMysql, String nameOfAuthor) {
        this.idSql = idAuthorMysql;
        this.nameOfAuthor = nameOfAuthor;
    }

    public Author() {}
    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public int getIdSql() {
        return idSql;
    }

    public void setIdSql(int idSql) {
        this.idSql = idSql;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String displayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(this.date);
    }
    @Override
    public String toString() {
        return nameOfAuthor;
    }


}
