package ObjectZZ;

import DB.DBConnect;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterAndLogin {
    String userName;
    String password;
    String passwordAgain;
    String typeOfUser;
    DBConnect zz;
    public RegisterAndLogin(String userName, String password, String passwordAgain, String typeOfUser) {
        this.userName = userName;
        this.password = password;
        this.passwordAgain = passwordAgain;
        this.typeOfUser = typeOfUser;
        this.zz = new DBConnect(3306,"javafinalimportant","adminjava","Admin1234@");
    }

    public RegisterAndLogin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //Method validate each field is empty or not
    private boolean checkUserName() {
        return userName.isEmpty();
    }

    private boolean checkPasswordLogin() {
        return password.isEmpty();
    }

    private boolean checkPasswordAgain() {
        return passwordAgain.isEmpty();
    }

    private boolean isItEqual() {
        return password.equals(passwordAgain);
    }

    private boolean isItGreaterThan() {
        return password.length() > 8;
    }

    private boolean isUserNameGreaterThan() {
        return userName.length() > 6;
    }

    private boolean isUserNameHasSpace() {
        return userName.contains(" ");
    }

    //Communication with DBConnect object
    private boolean isTheUserInSystem() {
        boolean checkUser = true;
        try {
            this.zz.setQuery("SELECT * FROM User WHERE Name = '" + this.userName + "'");
            if (this.zz.selectFromDB().next()) {
                checkUser = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return checkUser;
    }


    private void insertIfTheUserNotInTheSystem() {
        if(this.typeOfUser.equals("user")) {
            this.zz.setQuery("INSERT INTO User(Name, Password) VALUES ('" + this.userName + "', '" + this.password + "')");
            this.zz.anotherCommand();
            this.zz.setQuery("INSERT INTO Permissions(IDUser, RoleID) VALUES (LAST_INSERT_ID(), 2)");
            this.zz.anotherCommand();
        } else if(this.typeOfUser.equals("manager")) {

        }
    }
    //Public register method call
    public void checkInRegisterTwoFieldMatch() {
        if(!(checkPasswordLogin() || checkPasswordAgain() || checkUserName())) {
            if(!isUserNameHasSpace()) {
                if (isUserNameGreaterThan()) {
                    if (isItGreaterThan()) {
                        if (isItEqual()) {
                            if(!isTheUserInSystem()) {
                                displayWarningPassword("Choose another username.", "fail");
                            } else {
                                insertIfTheUserNotInTheSystem();
                                displayWarningPassword("Register success", "success");
                            }
                        } else {
                            displayWarningPassword("Two password not match.", "fail");
                        }
                    } else {
                      displayWarningPassword("Password must greater than 8.", "fail");
                    }
                } else {
                    displayWarningPassword("User name must greater than 6.", "fail");
                }
            } else {
                displayWarningPassword("User name not contain space.", "fail");
            }
        } else {
            displayWarningPassword("Input all field.", "fail");
        }
    }

    private void displayWarningPassword(String message, String typeOfWarning) {
        if(typeOfWarning.toUpperCase().equals("SUCCESS")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), message);
        } else if (typeOfWarning.toUpperCase().equals("FAIL")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),message,"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Public login method call
    public void checkInLogin() {
        if(!(checkUserName() || checkPasswordLogin())) {
            if(!isUserNameHasSpace()) {
                if (isUserNameGreaterThan()) {
                    if (isItGreaterThan()) {
                        //Do something
                    } else {
                        displayWarningPassword("Password must greater than 8.", "fail");
                    }
                } else {
                    displayWarningPassword("User name must greater than 6.", "fail");
                }
            } else {
                displayWarningPassword("User name not contain space","fail");
            }
        } else {
            displayWarningPassword("Input all field.", "fail");
        }
    }

}
