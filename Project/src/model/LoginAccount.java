package model;

import java.io.Serializable;
import java.util.List;

public class LoginAccount implements IGeneric, Serializable {
    private int loginID;
    private String userName;
    private String password;
    private boolean accountStatus;

    public LoginAccount() {
    }

    public LoginAccount(int loginID, String userName, String password) {
        this.loginID = loginID;
        this.userName = userName;
        this.password = password;
        this.accountStatus = false;
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }


    @Override
    public void displayData() {

    }

    @Override
    public void inputData() {

    }

    @Override
    public String toString() {
        return "LoginAccount{" +
                "loginID=" + loginID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accountStatus=" + accountStatus +
                '}';
    }
}
