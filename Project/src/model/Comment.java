package model;

import config.Color;

import java.io.Serializable;

public class Comment implements IGeneric, Serializable {
    private String comment;
    private User user;

    public Comment() {
    }

    public Comment(String comment, User user) {
        this.comment = comment;
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Content: " + comment + ", From: " + user.getName();
    }

    @Override
    public void displayData() {
        System.out.println(Color.YELLOW_BOLD_BRIGHT + "From: " + getUser().getName() + "\n" + "Comment's Content: " + getComment() + Color.RESET);
    }
}
