package view;

import config.Config;
import controller.UserController;
import dto.request.LoginDTO;
import dto.request.LoverDTO;
import dto.request.UserDTO;
import dto.respond.RespondMessage;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserView {
    UserController userController = new UserController();
    List<User> userList = userController.getUserList();
    List<User> userLogin = new Config<User>().readFromFile(Config.FILE_LOGIN_PATH);

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
                boolean flag = true;
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
                    System.out.println("Username is existed");
                    register();
                }
                if (message.getMessage().equals("email_existed")) {
                    System.out.println("Email is existed");
                    register();
                }
                if (message.getMessage().equals("create_success")) {
                    System.out.println("Register success");
                }
            } else if (role.equalsIgnoreCase("lover")) {
                strRole.add(role);
                System.out.println("Input rent price");
                float rentPrice = Config.scanner().nextFloat();
                LoverDTO loverDTO = new LoverDTO(id, email, userName, name, password, rentPrice, strRole);
                RespondMessage message = userController.registerLover(loverDTO);
                if (message.getMessage().equals("user_existed")) {
                    System.out.println("Username is existed");
                    register();
                }
                if (message.getMessage().equals("email_existed")) {
                    System.out.println("Email is existed");
                    register();
                }
                if (message.getMessage().equals("create_success")) {
                    System.out.println("Register success");
                }
            }

            List<User> readAccount = new Config<User>().readFromFile(Config.FILE_USER_PATH);
            for (int i = 0; i < readAccount.size(); i++) {
                readAccount.get(i).displayData();
            }
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
            if (respondMessage.getMessage().equals("login_fail")) {
                System.err.println("Login Fail");
                formLogin();
            } else {
                System.out.println("Login Success");
                new NavBar();
                break;
            }
        }
    }

    public void logOut() {
        System.out.println("Do you want to log out? (Y/N)");
        String confirmMessage = Config.scanner().nextLine();
        if (confirmMessage.equalsIgnoreCase("y")) {
            userLogin.remove(0);
            new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, userLogin);
            new NavBar();
        } else {
            new ProfileView();
        }
    }

    public void showLoverList() {
        List<User> loverList = userController.getLoverList();
        System.out.println("------------------AVAILABLE LOVER LIST------------------");
        for (int i = 0; i < loverList.size(); i++) {
            if (loverList.get(i).isRentStatus() == false) {
                loverList.get(i).displayData();
            }
        }
        pickLover();
    }

    public void pickLover() {
        List<User> loverList = userController.getLoverList();
        System.out.println("Do you want to pick a lover? Y/N");
        String answer = Config.scanner().nextLine();
        boolean flag = false;
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Input the number of lover you like: ");
            int choice = Config.scanner().nextInt();
            List<User> pickedUser;
            for (int i = 0; i < loverList.size(); i++) {
                if (loverList.get(i).getId() == choice) {
                    RespondMessage respondMessage = userController.checkSelectedUser(choice);
                    if (respondMessage.equals("available")) {
                        pickedUser = loverList.get(i).getUserList();
                        pickedUser.add(userLogin.get(0));
                        loverList.get(i).setUserList(pickedUser);
                        loverList.get(i).setRentCount(loverList.get(i).getRentCount() + 1);
                        userController.updateUser(loverList.get(i));
                        new ProfileView();
                        flag = true;
                    }
                    if (respondMessage.equals("existed_account")) {
                        System.err.println("Your already pick " + loverList.get(i).getName() + " as Lover. Please wait for respond");
                        new ProfileView();
                    }
                }
            }
            if (!flag) {
                System.err.println("Invalid Input. Please input again");
                new ProfileView();
            }
        } else {
            new ProfileView();
        }
    }


}
