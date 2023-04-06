package dto.request;

import model.Comment;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoverDTO {
    private int id;
    private String email;
    private String name;
    private String userName;
    private String password;
    private List<Comment> comment;
    private int rentCount;
    private double rentPrice;
    private boolean rentStatus;
    private boolean accountStatus;
    private boolean activeStatus;
    private List<User> userList;
    private Set<String> strRole = new HashSet<>();

    public LoverDTO() {
    }

    public LoverDTO(int id, String email, String userName, String name, String password, double rentPrice, Set<String> strRole) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.comment = new ArrayList<>();
        this.rentCount = 0;
        this.rentPrice = rentPrice;
        this.rentStatus = false;
        this.accountStatus = false;
        this.activeStatus = true;
        this.userList = new ArrayList<>();
        this.strRole = strRole;
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

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public int getRentCount() {
        return rentCount;
    }

    public void setRentCount(int rentCount) {
        this.rentCount = rentCount;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(float rentPrice) {
        this.rentPrice = rentPrice;
    }

    public boolean isRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
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
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Set<String> getStrRole() {
        return strRole;
    }

    public void setStrRole(Set<String> strRole) {
        this.strRole = strRole;
    }

    @Override
    public String toString() {
        return "LoverDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", comment=" + comment +
                ", rentCount=" + rentCount +
                ", rentPrice=" + rentPrice +
                ", rentStatus=" + rentStatus +
                ", accountStatus=" + accountStatus +
                ", activeStatus=" + activeStatus +
                ", userList=" + userList +
                ", strRole=" + strRole +
                '}';
    }
}
