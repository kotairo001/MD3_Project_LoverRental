package dto.request;

import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private int id;
    private String email;
    private String name;
    private String userName;
    private String password;
    private boolean accountStatus;
    private boolean activeStatus;
    private List<User> loverList;
    private Set<String> strRole = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(int id, String email, String userName, String name, String password, Set<String> strRole) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.strRole = strRole;
        this.accountStatus=false;
        this.activeStatus=true;
        this.loverList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<String> getStrRole() {
        return strRole;
    }

    public void setStrRole(Set<String> strRole) {
        strRole = strRole;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public List<User> getUserList() {
        return loverList;
    }

    public void setUserList(List<User> loverList) {
        this.loverList = loverList;
    }
}
