package view;

import config.Config;
import controller.UserController;
import model.Role;
import model.RoleName;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfileView {
    UserController userController = new UserController();

    public ProfileView() {
        User user = userController.getCurrentLoginUser();
        if (user != null) {
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            int choice;
            if (roles.get(0).getName() == RoleName.ADMIN) {
                System.out.println(Config.WHITE_BRIGHT + "✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧MENU FOR ADMIN✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
                System.out.printf("|" + "  1. %-76s" + "|\n", "Show Users List");
                System.out.printf("|" + "  2. %-76s" + "|\n", "Change Role");
                System.out.printf("|" + "  3. %-76s" + "|\n", "Block User");
                System.out.printf("|" + "  4. %-76s" + "|\n", "Delete User");
                System.out.printf("|" + "  5. %-76s" + "|\n", "Log out");
                System.out.printf("|" + "  6. %-76s" + "|\n", "Exit");
                System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧" + Config.RESET);
                System.out.println("Input your choice");
                choice = Config.scanner().nextInt();
                switch (choice) {
//                    case 1:
//                        new UserView().register();
//                        break;
//                    case 2:
//                        new UserView().formLogin();
//                        break;
                    case 5:
                        new UserView().logOut();
                        break;
                    case 6:
                        System.exit(0);}
            } else if (roles.get(0).getName() == RoleName.USER) {
                System.out.println(Config.WHITE_BRIGHT + "✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧MENU FOR USER✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
                System.out.printf("|" + "  1. %-76s" + "|\n", "Change Profile");
                System.out.printf("|" + "  2. %-76s" + "|\n", "Show Lovers List");
                System.out.printf("|" + "  3. %-76s" + "|\n", "Show Top.5 Lovers");
                System.out.printf("|" + "  4. %-76s" + "|\n", "Log out");
                System.out.printf("|" + "  5. %-76s" + "|\n", "Exit");
                System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧" + Config.RESET);
                System.out.println("Input your choice");
                choice = Config.scanner().nextInt();
                switch (choice) {
//                    case 1:
//                        new UserView().register();
//                        break;
//                    case 2:
//                        new UserView().formLogin();
//                        break;
                    case 5:
                        new UserView().logOut();
                        break;
                    case 6:
                        System.exit(0);}
            } else {
                System.out.println(Config.WHITE_BRIGHT + "✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧MENU FOR LOVER✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
                System.out.printf("|" + "  1. %-76s" + "|\n", "Change Profile");
                System.out.printf("|" + "  2. %-76s" + "|\n", "Show Rent List");
                System.out.printf("|" + "  3. %-76s" + "|\n", "Show Comment");
                System.out.printf("|" + "  4. %-76s" + "|\n", "Log out");
                System.out.printf("|" + "  5. %-76s" + "|\n", "Exit");
                System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧" + Config.RESET);
                System.out.println("Input your choice");
                choice = Config.scanner().nextInt();
                switch (choice) {
//                    case 1:
//                        new UserView().register();
//                        break;
//                    case 2:
//                        new UserView().formLogin();
//                        break;
                    case 4:
                        new UserView().logOut();
                        break;
                    case 5:
                        System.exit(0);
                }
            }
        }
    }
}
