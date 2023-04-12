package view;

import config.Color;
import config.Config;
import controller.UserController;
import dto.request.LoginDTO;
import dto.request.LoverDTO;
import dto.request.UserDTO;
import dto.respond.RespondMessage;
import model.Comment;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserView {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();
    List<User> userLogin = new Config<User>().readFromFile(Config.FILE_LOGIN_PATH);
    User loginAccount = userController.getCurrentLoginUser();
    List<User> loverList = userController.getLoverList();

    public void showProfile() {
        loginAccount.displayData();
        System.out.println("Enter 'Back' to comeback Menu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new ProfileView();
        }
    }

    public void register() {
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        while (true) {
            String email;
            while (true) {
                System.out.println("Input your email: ");
                email = Config.scanner().nextLine();
                if (!Config.validateInput(Config.VALIDATE_EMAIL, email) || email.equals("")) {
                    System.err.println("Invalid Email");
                } else {
                    break;
                }
            }
            String userName;
            while (true) {
                System.out.println("Username: ");
                userName = Config.scanner().nextLine();
                if (userName.equals("")) {
                    System.err.println("Invalid user name");
                } else {
                    break;
                }
            }
            String name;
            while (true) {
                System.out.println("Name: ");
                name = Config.scanner().nextLine();
                if (name.equals("")) {
                    System.err.println("Invalid user name");
                } else {
                    break;
                }
            }
            String password;
            while (true) {
                System.out.println("Password: ");
                password = Config.scanner().nextLine();
                if (!Config.validateInput(Config.VALIDATE_PASSWORD, password) || password.equals("")) {
                    System.err.println("Invalid Password");
                } else {
                    break;
                }
            }
            while (true) {
                System.out.println("Confirm Password: ");
                String cfPassword = Config.scanner().nextLine();
                if (!cfPassword.equals(password) || cfPassword.equals("")) {
                    System.err.println("Wrong Password");
                } else {
                    break;
                }
            }
            System.out.println("Enter the role");
            String role = Config.scanner().nextLine();
            Set<String> strRole = new HashSet<>();
            if (role.equalsIgnoreCase("user")) {
                strRole.add(role);
                UserDTO userDTO = new UserDTO(id, email, userName, name, password, strRole);
                RespondMessage message = userController.registerUser(userDTO);
                if (message.getMessage().equals("user_existed")) {
                    System.err.println("Username is existed");
                    register();
                }
                if (message.getMessage().equals("email_existed")) {
                    System.err.println("Email is existed");
                    register();
                }
                if (message.getMessage().equals("create_success")) {
                    System.out.println(Color.CYAN_BOLD_BRIGHT + "Register success" + Color.RESET);
                }
            } else if (role.equalsIgnoreCase("lover")) {
                strRole.add(role);
                System.out.println("Input rent price");
                float rentPrice = Config.scanner().nextFloat();
                LoverDTO loverDTO = new LoverDTO(id, email, userName, name, password, rentPrice, strRole);
                RespondMessage message = userController.registerLover(loverDTO);
                if (message.getMessage().equals("user_existed")) {
                    System.err.println("Username is existed");
                    register();
                }
                if (message.getMessage().equals("email_existed")) {
                    System.err.println("Email is existed");
                    register();
                }
                if (message.getMessage().equals("create_success")) {
                    System.out.println(Color.CYAN_BOLD_BRIGHT + "Register success" + Color.RESET);
                }
            }

//            List<User> readAccount = new Config<User>().readFromFile(Config.FILE_USER_PATH);
//            for (int i = 0; i < readAccount.size(); i++) {
//                readAccount.get(i).displayData();
//            }
            System.out.println("Enter 'Back' to comeback Menu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new NavBar();
                break;
            }
        }
    }

    public void formLogin() {
        System.out.println("Form Login");
        System.out.println("Enter Username: ");
        String userName = Config.scanner().nextLine();
        System.out.println("Enter password: ");
        String password = Config.scanner().nextLine();
        LoginDTO loginDTO = new LoginDTO(userName, password);
        while (true) {
            RespondMessage respondMessage = userController.logIn(loginDTO);
            RespondMessage checkStatusMessage = userController.checkActiveAccount(loginDTO);
            if (respondMessage.getMessage().equals("login_fail")) {
                System.err.println("Login Fail");
                formLogin();
            } else {
                if (checkStatusMessage.getMessage().equals("active_account")) {
                    System.out.println(Color.BLUE_BRIGHT + "Login Success" + Color.RESET);
                    new NavBar();
                } else {
                    System.err.println("Your account has been inactive. Please contact with admin for more information.");
                    new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, new ArrayList<>());
                    new NavBar();
                    break;
                }
            }
        }
    }

    public void logOut() {
        System.out.println(Color.RED_BOLD_BRIGHT + "Do you want to log out? (Y/N)" + Color.RESET);
        String confirmMessage = Config.scanner().nextLine();
        if (confirmMessage.equalsIgnoreCase("y")) {
            userLogin.remove(0);
            new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, userLogin);
            new NavBar();
        } else {
            new ProfileView();
        }
    }

    public void changeProfile() {
        List<User> newLoginUser = new ArrayList<>();
        User oldUser = userController.getCurrentLoginUser();
        System.out.println(Color.YELLOW_BOLD_BRIGHT + "Do you want to change profile? Y/N" + Color.RESET);
        String answer = Config.scanner().nextLine();
        if (answer.equalsIgnoreCase("y")) {
            while (true) {
                String email;
                while (true) {
                    System.out.println("Input your email or type 'Skip' to skip: ");
                    email = Config.scanner().nextLine();
                    if (email.equalsIgnoreCase("skip")) {
                        break;
                    } else {
                        if (!Config.validateInput(Config.VALIDATE_EMAIL, email)) {
                            System.err.println("Invalid Email");
                        } else if (loginAccount.getEmail().equals(email)) {
                            System.err.println("Your email should not be the same with the last email");
                        } else {
                            oldUser.setEmail(email);
                            break;
                        }
                    }
                }
                String userName;
                while (true) {
                    System.out.println("Username or type 'Skip' to skip: ");
                    userName = Config.scanner().nextLine();
                    if (userName.equalsIgnoreCase("skip")) {
                        break;
                    } else {
                        if (loginAccount.getUserName().equals(userName)) {
                            System.err.println("Your user name should not be the same with the last user name");
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
                            password = Config.scanner().nextLine();
                            if (!Config.validateInput(Config.VALIDATE_PASSWORD, password)) {
                                System.err.println("Invalid Password");
                            } else if (loginAccount.getPassword().equals(password)) {
                                System.err.println("Your password should not be the same with the last password");
                            } else {
                                oldUser.setPassword(password);
                                break;
                            }
                        }
                    }
                }
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
        } else {
            new ProfileView();
        }
    }

    public void showLoverList() {
        System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧AVAILABLE LOVER LIST✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
        for (int i = 0; i < loverList.size(); i++) {
            if (loverList.get(i).isRentStatus() == false) {
                loverList.get(i).displayData();
            }
        }
        System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
        pickLover();
    }

    public void pickLover() {
        System.out.println("Do you want to pick a lover? Y/N");
        String answer = Config.scanner().nextLine();
        boolean flag = false;
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Input the number of lover you like: ");
            int choice = Config.validateInt();
            RespondMessage respondMessage = userController.checkSelectedUser(choice);
            List<User> pickedUser;
            for (int i = 0; i < loverList.size(); i++) {
                if (loverList.get(i).getId() == choice) {
                    if (respondMessage.getMessage().equals("available")) {
                        pickedUser = loverList.get(i).getUserList();
                        pickedUser.add(loginAccount);
                        loverList.get(i).setUserList(pickedUser);
                        userController.updateUser(loverList.get(i));
                        flag = true;
                        addComment();
                        new ProfileView();
                        break;
                    } else if (respondMessage.getMessage().equals("existed_account")) {
                        System.err.println("Your already pick " + loverList.get(i).getName() + " as Lover. Please wait for respond");
                        addComment();
                        new ProfileView();
                        break;
                    }
                }
            }
            if (!flag) {
                System.err.println("Invalid Input. Please input again");
                pickLover();
            }
        } else {
            addComment();
        }
    }

    public void showTopFiveLover() {
        System.out.println(Color.YELLOW_BOLD_BRIGHT + "✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧TOP.5 LOVER LIST✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
        List<User> topFiveLover = userController.findTopFiveLover();
        if (topFiveLover.size() > 5) {
            for (int i = topFiveLover.size() - 1; i > topFiveLover.size() - 5; i--) {
                topFiveLover.get(i).displayData();
            }
        } else {
            for (int i = topFiveLover.size() - 1; i >= 0; i--) {
                topFiveLover.get(i).displayData();
            }
        }
        System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧" + Color.RESET);
        System.out.println("Enter 'Back' to comeback Menu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new ProfileView();
        }
    }

    public void addComment() {
        System.out.println("Do you want to add comment? Y/N");
        String answer = Config.scanner().nextLine();
        Comment comment = new Comment();
        boolean flag = false;
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Input the number of lover you want to comment: ");
            int choice = Config.validateInt();
            for (int i = 0; i < loverList.size(); i++) {
                if (loverList.get(i).getId() == choice) {
                    System.out.println("Your comment: ");
                    String strComment = Config.scanner().nextLine();
                    List<Comment> commentList = loverList.get(i).getComment();
                    comment.setComment(strComment);
                    comment.setUser(loginAccount);
                    commentList.add(comment);
//                    System.out.println(loverList.get(i));
                    userController.updateUser(loverList.get(i));
                    flag = true;
                    new ProfileView();
                    break;
                }
            }
            if (!flag) {
                System.err.println("Invalid Input. Please input again");
                addComment();
            }
        } else {
            new ProfileView();
        }
    }

    public void finishRental() {
        User loginAccount = userLogin.get(0);
        if (loginAccount.getUserList() == null || loginAccount.getUserList().size() == 0) {
            System.out.println(Color.RED + "You haven't matched with anyone. Please pick the one you like!" + Color.RESET);
            new ProfileView();
        } else {
            if (loginAccount.getUserList() != null) {
                while (true) {
                    System.out.println(Color.BLUE_BOLD_BRIGHT + "Have your rental been finished? Y/N" + Color.RESET);
                    String result = Config.scanner().nextLine();
                    if (result.equalsIgnoreCase("y")) {
                        for (int i = 0; i < loverList.size(); i++) {
                            if (loginAccount.getUserList().get(0).getName().equalsIgnoreCase(loverList.get(i).getName())) {
                                User lover = userController.findById(loverList.get(i).getId());
                                lover.setRentStatus(false);
                                userController.updateUser(lover);
                                loginAccount.getUserList().remove(0);
                                userController.updateUser(loginAccount);
                                new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, userLogin);
                                System.out.println(Color.YELLOW_BOLD_BRIGHT + "Thank you for using our service!" + Color.RESET);
                                new ProfileView();
                                break;
                            }
                        }
                    } else {
                        new ProfileView();
                        break;
                    }
                }
            }
        }
    }

}
