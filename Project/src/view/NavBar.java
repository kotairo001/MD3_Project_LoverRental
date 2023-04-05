package view;

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
        if (user==null) {
        System.out.println(Config.WHITE_BRIGHT + "✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧LOVER RENTAL✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
            System.out.printf("|" + "  1. %-76s" + "|\n", "Register");
            System.out.printf("|" + "  2. %-76s" + "|\n", "Login");
            System.out.printf("|" + "  3. %-76s" + "|\n", "Exit");
            System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧" + Config.RESET);
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
            System.err.printf("Welcome " + user.getName() + " as " + roleList.get(0).getName() +"\n");
            new ProfileView();
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

