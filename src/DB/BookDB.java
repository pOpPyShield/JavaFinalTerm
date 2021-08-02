package DB;

import ObjectZZ.Book;

import java.util.ArrayList;

public class BookDB extends DBConnect{
    private ArrayList<Book> bookFromQuery;
    public BookDB(int port, String db, String account, String pwd) {
        super(port, db, account, pwd);
    }

    /*
    public ArrayList<Book> getBook() {

    }
     */

}
