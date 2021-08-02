package ObjectZZ;

import DB.DBConnect;

import javax.swing.*;
import java.sql.ResultSet;

public class RegisterAndLogin {
    String userName;
    String password;
    String passwordAgain;
    String typeOfUser;
    DBConnect zz;
    int checkInTheTwoField;
    private int getID;
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
        this.zz = new DBConnect(3306, "javafinalimportant", "adminjava","Admin1234@");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    private void setCheckInTheTwoField(int checkInTheTwoField) {
        this.checkInTheTwoField = checkInTheTwoField;
    }

    public int getCheckInTheTwoField() {
        return this.checkInTheTwoField;
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

    private void executeInsert(int ID){
        this.zz.setQuery("INSERT INTO User(Name, Password) VALUES ('" + this.userName + "','" + this.password +"');");
        this.zz.threeCommand();
        int usrId = 0;
        try {
            ResultSet gg = this.zz.getStmt().getGeneratedKeys();
            if (gg.next()) {
                usrId = gg.getInt(1);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        this.zz.setQuery("INSERT INTO Permissions(IDUser, RoleID) VALUES ("+ usrId + "," + ID + ");");
        this.zz.threeCommand();
    }

    private void insertToTheDBAfterSetUserType() {
        if(getCheckInTheTwoField() == 2) {
            executeInsert(getCheckInTheTwoField());
        } else if(getCheckInTheTwoField() == 3) {
            executeInsert(getCheckInTheTwoField());
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
                                if(this.typeOfUser.equals("user")) {
                                    displayWarningPassword("Register success.", "success");
                                    setCheckInTheTwoField(2);
                                    insertToTheDBAfterSetUserType();
                                } else {
                                    displayWarningPassword("Register with manager success.", "success");
                                    setCheckInTheTwoField(3);
                                    insertToTheDBAfterSetUserType();
                                }
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
    public boolean checkInLogin() {
        if(!(checkUserName() || checkPasswordLogin())) {
            if(!isUserNameHasSpace()) {
                if (isUserNameGreaterThan()) {
                    if (isItGreaterThan()) {
                        if(checkUserInLoginWithUsrNameAndPassword()) {
                            return true;
                        } else {
                            displayWarningPassword("Username or password is not correct.", "fail");
                            return false;
                        }
                    } else {
                        displayWarningPassword("Password must greater than 8.", "fail");
                        return false;
                    }
                } else {
                    displayWarningPassword("User name must greater than 6.", "fail");
                    return false;
                }
            } else {
                displayWarningPassword("User name not contain space","fail");
                return false;
            }
        } else {
            displayWarningPassword("Input all field.", "fail");
            return false;
        }
    }

    private boolean checkUserInLoginWithUsrNameAndPassword() {
        boolean checkLogin = false;
        this.zz.setQuery("SELECT IdUser,Name,Password FROM User WHERE Name = '" + this.userName + "' AND Password = '" + this.password+"'");
        ResultSet getResult = this.zz.selectFromDB();
        //If this has record like field, then return true
        try {
            if (getResult.next()) {
                getIdAfterCheckLogin(getResult.getInt("IdUser"));
                checkLogin = true;
            }
        } catch (Exception ex) {ex.printStackTrace();}
        return checkLogin;
    }

    private void getIdAfterCheckLogin(int getID) {
        this.getID = getID;
    }

    private int getID() {
        return this.getID;
    }

    public int checkUserType() {
        int usrType = 0;
        //2 is user, 3 is manager
        this.zz.setQuery("SELECT * FROM Permissions WHERE IDUser=" + getID());
        ResultSet getResult = this.zz.selectFromDB();
        try {
            if(getResult.next()) {
                usrType = getResult.getInt("RoleID");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usrType;
    }

}
