package DB;

import Exceptionzz.NotFoundCommandException;
import Exceptionzz.NullStringException;

import java.sql.*;

public class DBConnect {
    String port;
    String db;
    String account;
    String pwd;
    Connection conn;
    ResultSet rs;
    Statement stmt;
    String query;
    public DBConnect(int port, String db, String account, String pwd) {
        this.port = String.valueOf(port);
        this.db = db;
        this.account = account;
        this.pwd = pwd;
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:" + this.port + "/" + this.db, this.account, this.pwd);
            this.stmt = this.conn.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Select from db
    public void setQuery(String sqlQuery) {
        this.query = sqlQuery;
    }

    private void initialize() throws Exception {
        if(this.query != null) {
            this.rs = this.stmt.executeQuery(this.query);
        } else {
            throw new NullStringException("Set query before use initialize function in DBConnect class.");
        }
    }

    public ResultSet selectFromDB() {
        try {
            initialize();
        }catch (Exception ex) {
            if(ex instanceof NullStringException) {
                ex.printStackTrace();
            } else if(ex instanceof SQLException) {
                ex.printStackTrace();
            } else if(ex instanceof NullPointerException) {
                ex.printStackTrace();
            }
        }
        return this.rs;
    }

    //UPDATE, INSERT, DELETE
    private int initializeForThreeCommand() throws Exception {
        if(this.query != null) {
            if(checkQuery() == 1) {
                return this.stmt.executeUpdate(this.query);
            } else {
                return -1;
            }
        }  else {
            throw new NullStringException("Set query before use initializeThreeCommand in DBConnect class.");
        }
    }
    //Validate if the string has the update, insert, delete in first string
    private int checkQuery() {
        String[] split = this.query.split("\\s+");
        return switch (split[0].toUpperCase()) {
            case "UPDATE", "DELETE", "INSERT" -> 1;
            default -> -1;
        };
    }
    public void anotherCommand(){
        try {
            int numberInform = initializeForThreeCommand();
            if(numberInform == -1) {
                throw new NotFoundCommandException("Command not found, use Update, Insert, Delete");
            } else {
                System.out.println(numberInform + " Affected");
            }
        } catch (Exception ex) {
            if(ex instanceof NullStringException) {
                ex.printStackTrace();
            } else if (ex instanceof SQLException) {
                ex.printStackTrace();
            } else if(ex instanceof NotFoundCommandException) {
                ex.printStackTrace();
            } else if (ex instanceof NullPointerException) {
                ex.printStackTrace();
            }
        }
    }


}
