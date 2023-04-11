package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements IGeneric, Serializable {
    private int id;
    private String email;
    private String userName;
    private String name;
    private String password;
    private List<Comment> comment;
    private int rentCount;
    private double rentPrice;
    private boolean rentStatus;
    private boolean accountStatus;
    private boolean activeStatus;
    private Set<Role> roles = new HashSet<>();
    private List<User> userList;


    public User() {
    }

    public User(int id, String email, String userName, String name, String password, boolean activeStatus, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.activeStatus = activeStatus;
        this.roles = roles;
    }

    public User(int id, String email, String userName, String name, String password, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.accountStatus = false;
        this.activeStatus = true;
        this.userList = new ArrayList<>();
    }

    public User(int id, String email, String userName, String name, String password, double rentPrice, Set<Role> roleSet) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.comment = new ArrayList<>();
        this.rentCount = 0;
        this.rentPrice = rentPrice;
        this.roles = roleSet;
        this.rentStatus = false;
        this.accountStatus = false;
        this.activeStatus = true;
        this.userList = new ArrayList<>();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }

    public boolean isRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", comment=" + comment +
                ", rentCount=" + rentCount +
                ", rentPrice=" + rentPrice +
                ", rentStatus=" + rentStatus +
                ", accountStatus=" + accountStatus +
                ", activeStatus=" + activeStatus +
                ", roles=" + roles +
                ", userList=" + userList +
                '}';
    }

    @Override
    public void displayData() {
        Set<Role> roleSet = getRoles();
        List<Role> roleList = new ArrayList<>(roleSet);
        RoleName roleName = roleList.get(0).getName();
        if (roleName == RoleName.LOVER) {
            System.out.println("No." + id + "\n" +
                    "Name: " + name + '\n' +
                    "Comment: " + (comment== null||comment.size()==0 ? "N/A" : comment) + '\n' +
                    "Rental Count: " + rentCount + '\n' +
                    "Rental Price: " + (int) rentPrice + '\n' +
                    "Rental Status: " + (rentStatus == false ? "Available" : "Already rented") + '\n' +
                    "Role: " + roles + '\n' +
                    "User List: " + (userList == null||userList.size()==0 ? "N/A" : getNameInTheList()) + '\n' +
                    "Status: " + (isActiveStatus() == false ? "Inactive" : "Active") + "\n" +
                    "-------------------------------------------------------" + '\n');
        } else if (roleName == RoleName.ADMIN) {
            System.out.println("No." + id + "\n" +
                    "Name: " + name + '\n' +
                    "Role: " + roles + '\n' +
                    "-------------------------------------------------------" + '\n');
        } else {
            System.out.println("No." + id + "\n" +
                    "Name: " + name + '\n' +
                    "Role: " + roles + '\n' +
                    "Lover List: " + (userList == null ||userList.size()==0? "N/A" : getNameInTheList()) + '\n' +
                    "Status: " + (isActiveStatus() == false ? "Inactive" : "Active") + "\n" +
                    "-------------------------------------------------------" + '\n');
        }
    }

    public List<String> getNameInTheList() {
        List<String> userName = new ArrayList<>();
        for (int i = 0; i < getUserList().size(); i++) {
            userName.add(getUserList().get(i).getName());
        }
        return userName;
    }


}
