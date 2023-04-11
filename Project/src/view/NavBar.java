package view;

import config.Color;
import config.Config;
import controller.UserController;
import model.Role;
import model.RoleName;
import model.User;
import service.role.RoleNameService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NavBar {
    UserController userController = new UserController();


    public NavBar() {
        User user = userController.getCurrentLoginUser();
        if (user == null) {
            System.out.println(Color.PURPLE_BOLD_BRIGHT + "✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧LOVER RENTAL✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
            System.out.printf("|" + "  1. %-76s" + "|\n", "Register");
            System.out.printf("|" + "  2. %-76s" + "|\n", "Login");
            System.out.printf("|" + "  3. %-76s" + "|\n", "Exit");
            System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧" + Color.RESET);
            System.out.println("Input your choice");
            int choice = Config.scanner().nextInt();
            switch (choice) {
                case 1:
                    new UserView().register();
                    break;
                case 2:
                    new UserView().formLogin();
                    break;
                case 3:
                    System.exit(0);
            }
        } else {
            Set<Role> roles = user.getRoles();
            List<Role> roleList = new ArrayList<>(roles);
            try {
                for (int i = 3; i > 0; i--) {
                    System.out.println(Color.BLUE_BOLD + "Waiting for " + i + " second" + Color.RESET);
                    Thread.sleep(1000);
                }
                System.err.printf(Color.PURPLE_BOLD_BRIGHT + "Welcome " + user.getName() + " as " + roleList.get(0).getName() + "\n" + Color.RESET);
                if (roleList.get(0).getName() == RoleName.USER) {
                    if (user.getUserList() == null || user.getUserList().size() == 0) {
                        System.out.println("");
                    } else {
                        System.err.printf(Color.CYAN_BOLD_BRIGHT + "You have been matched with " + user.getUserList().get(0).getName() + "\n" + Color.RESET);
                    }
                } else if (roleList.get(0).getName() == RoleName.LOVER) {
                    if (user.getUserList() == null || user.getUserList().size() == 0) {
                        System.out.println("");
                    } else {
                        System.err.printf(Color.CYAN_BOLD_BRIGHT + "You have been chosen by " + user.getUserList().size() + " person(s). Please check rental list." + "\n" + Color.RESET);
                    }
                }
                new ProfileView();
            } catch (InterruptedException i) {
                i.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new NavBar();
//        List<User> userList = new ArrayList<>();
//        Set<Role> roleSet = new HashSet<>();
//        RoleNameService roleService = new RoleNameService();
//        roleSet.add(roleService.findByName(RoleName.ADMIN));
//        User user = new User(1,"admin@gmail.com","admin","Admin","Admin123",true,roleSet);
//        userList.add(user);
//        UserController userController1 = new UserController();
//        userController1.createUser(user);
    }
}

