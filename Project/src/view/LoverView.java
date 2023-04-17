package view;

import config.Color;
import config.Config;
import controller.UserController;
import model.Comment;
import model.Role;
import model.RoleName;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoverView {
    UserController userController = new UserController();
    List<User> listUser = userController.getUserList();
    User userLogin = userController.getCurrentLoginUser();

    public void changeProfile() {
        List<User> newLoginUser = new ArrayList<>();
        User oldUser = userLogin;
        System.out.println(Color.YELLOW_BOLD_BRIGHT + "Do you want to change profile? Y/N" + Color.RESET);
        String answer = Config.validateString();
        if (answer.equalsIgnoreCase("y")) {
            while (true) {
                String email;
                while (true) {
                    System.out.println("Input your email or type 'Skip' to skip: ");
                    email = Config.validateString();
                    if (email.equalsIgnoreCase("skip")) {
                        break;
                    } else {
                        if (!Config.validateInput(Config.VALIDATE_EMAIL, email)) {
                            System.err.println("Invalid Email");
                        } else if (userLogin.getEmail().equals(email)) {
                            System.err.println("Your email should not be the same with the last email.");
                        } else {
                            oldUser.setEmail(email);
                            break;
                        }
                    }
                }
                String userName;
                while (true) {
                    System.out.println("Username or type 'Skip' to skip: ");
                    userName = Config.validateString();
                    if (userName.equalsIgnoreCase("skip")) {
                        break;
                    } else {
                        if (userLogin.getUserName().equals(userName)) {
                            System.err.println("Your user name should not be the same with the last user name.");
                        } else {
                            oldUser.setUserName(userName);
                            break;
                        }
                    }
                }
                String password;
                while (true) {
                    System.out.println("Input old password or type 'Skip' to skip: ");
                    String oldPassword = Config.validateString();
                    if (oldPassword.equalsIgnoreCase("skip")) {
                        break;
                    } else {
                        if (!oldPassword.equalsIgnoreCase(oldUser.getPassword())) {
                            System.err.println("Your password is wrong.");
                        } else {
                            System.out.println("New password: ");
                            password = Config.validateString();
                            if (!Config.validateInput(Config.VALIDATE_PASSWORD, password)) {
                                System.err.println("Invalid Password");
                            } else if (userLogin.getPassword().equals(password)) {
                                System.err.println("Your password should not be the same with the last password.");
                            } else {
                                oldUser.setPassword(password);
                                break;
                            }
                            break;
                        }
                    }
                }
                userController.updateUser(oldUser);
                newLoginUser.add(oldUser);
                new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, newLoginUser);
//                List<User> readAccount = new Config<User>().readFromFile(Config.FILE_USER_PATH);
//                List<User> readAccountLogin = new Config<User>().readFromFile(Config.FILE_LOGIN_PATH);
//                System.out.println("register--->" + readAccount);
//                System.out.println("loginAccount--->" + readAccountLogin);
                System.out.println("Enter 'Back' to comeback Menu");
                String backMenu = Config.scanner().nextLine();
                if (backMenu.equalsIgnoreCase("back")) {
                    new ProfileView();
                    break;
                }
            }
        }
    }

    public void changeRentalPrice() {
        List<User> newLoginAccount = new ArrayList<>();
        double rentPrice;
        System.out.println(Color.RED_BOLD_BRIGHT + "Do you want to change rental price for this time? Y/N" + Color.RESET);
        String answer = Config.validateString();
        if (answer.equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("Input rental price: ");
                rentPrice = Config.validateDouble();
                if (rentPrice != userLogin.getRentPrice()) {
                    userLogin.setRentPrice(rentPrice);
                    userController.updateUser(userLogin);
                    newLoginAccount.add(userLogin);
                    new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, newLoginAccount);
                    System.out.println(Color.BLUE_BRIGHT + "Changed success!");
                    new ProfileView();
                    break;
                } else {
                    System.err.println("Your rental price should not be the same with the last price!");
                }
            }
        } else {
            new ProfileView();
        }
    }

    public void showComment() {
        List<Comment> commentList = userLogin.getComment();
        if (commentList == null || commentList.size() == 0) {
            System.out.println(Color.CYAN_BOLD + "There no comment for you. Please comeback again." + Color.RESET);
        } else {
            for (int i = 0; i < commentList.size(); i++) {
                commentList.get(i).displayData();
            }
        }
        System.out.println("Enter 'Back' to comeback Menu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new ProfileView();
        }

    }

    public void showRentalUser() {
        List<User> newLoginAccount = new ArrayList<>();
        List<User> rentalUserList = userLogin.getUserList();
        if (rentalUserList.size() > 0) {
            for (int i = 0; i < rentalUserList.size(); i++) {
//                System.out.println(rentalUserList.get(i));
                rentalUserList.get(i).displayData();
            }
            System.out.println(Color.CYAN_BOLD_BRIGHT + "Do you wan to match? Y/N" + Color.RESET);
            String answer = Config.validateString();
            if (answer.equalsIgnoreCase("y")) {
                System.out.println("Input the number of user you want to match:");
                int number = Config.validateInt();
                List<User> matchedLover = new ArrayList<>();
                List<User> forDeleteList = new ArrayList<>();
                boolean flag = false;
                for (int i = 0; i < rentalUserList.size(); i++) {
                    if (number == rentalUserList.get(i).getId()) {
                        userLogin.setRentCount(userLogin.getRentCount() + 1);
                        matchedLover.add(userLogin);
                        rentalUserList.get(i).setUserList(matchedLover);
                        userController.updateUser(rentalUserList.get(i));
                        forDeleteList.add(rentalUserList.get(i));
                        deleteNoMoreNeedUser(forDeleteList);
                        rentalUserList.remove(rentalUserList.get(i));
                        userLogin.setRentStatus(true);
                        userController.updateUser(userLogin);
                        flag = true;
                        newLoginAccount.add(userLogin);
                        new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, newLoginAccount);
                        System.out.println(Color.BLUE_BRIGHT + "You have matched successfully.");
                        new ProfileView();
                        break;
                    }
                }
                if (!flag) {
                    System.out.println(Color.RED + "There is no match user. Please try again." + Color.RESET);
                    showRentalUser();
                }
            } else {
                new ProfileView();
            }
        } else {
            System.out.println(Color.CYAN_BOLD + "You haven't been picked up yet. Please come back later." + Color.RESET);
            System.out.println("Enter 'Back' to comeback Menu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new ProfileView();
            }
        }
    }
    public void deleteNoMoreNeedUser(List<User> listForDelete){
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserList() != null) {
                List<User> loverList = listUser.get(i).getUserList();
                for (int j = 0; j < loverList.size(); j++) {
                    for (int k = 0; k < listForDelete.size(); k++) {
                        if (loverList.get(j).getId() == listForDelete.get(k).getId()) {
                            loverList.remove(j);
                            userController.updateUser(listUser.get(i));
                            break;
                        }
                    }
                }
            }
        }
    }
}
