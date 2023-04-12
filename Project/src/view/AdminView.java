package view;

import config.Color;
import config.Config;
import controller.UserController;
import model.Role;
import model.RoleName;
import model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminView {
    UserController userController = new UserController();
    List<User> listUser = userController.getUserList();

    public void showUserList() {
//        System.out.println(listUser);
        for (int i = 0; i < listUser.size(); i++) {
            listUser.get(i).displayData();
        }
        System.out.println("Enter 'Back' to comeback Menu");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("back")) {
            new ProfileView();
        }
    }

    public void changeRole() {
        for (int i = 0; i < listUser.size(); i++) {
            listUser.get(i).displayData();
        }
        while (true) {
            System.out.println("Input the number of account you want to change role");
            int choice = Config.validateInt();
            List<User> listForDelete = new ArrayList<>();
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getId() == choice) {
                    System.out.println("What's role you want to change for this account?");
                    String answer = Config.validateString();
                    Set<Role> roleSet = listUser.get(i).getRoles();
                    List<Role> roleList = new ArrayList<>(roleSet);
                    if (answer.equalsIgnoreCase("lover")) {
                        roleList.get(0).setName(RoleName.LOVER);
                        User newLover = listUser.get(i);
                        newLover = new User(newLover.getId(), newLover.getEmail(), newLover.getUserName(),
                                newLover.getName(), newLover.getPassword(), newLover.getRentPrice(), new HashSet<>(roleList));
                        userController.updateUser(newLover);
                        listForDelete.add(newLover);
                        System.out.println(Color.BLUE_BRIGHT + "Changed role success" + Color.RESET);
                    }
                    if (answer.equalsIgnoreCase("user")) {
                        roleList.get(0).setName(RoleName.USER);
                        User newUser = listUser.get(i);
                        newUser = new User(newUser.getId(), newUser.getEmail(), newUser.getUserName(), newUser.getName(),
                                newUser.getPassword(), new HashSet<>(roleList));
                        userController.updateUser(newUser);
                        System.out.println(Color.BLUE_BRIGHT + "Changed role success" + Color.RESET);
                    }
                }
            }
            deleteNoMoreNeedUser(listForDelete);
            System.out.println("Enter 'Back' to comeback Menu");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("back")) {
                new ProfileView();
                break;
            }
        }
    }

    public void ActivateAccount() {
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).isActiveStatus() == false) {
                listUser.get(i).displayData();
            }
        }
        boolean flag = false;
        System.out.println("Input the number of account you want to activate");
        int choice = Config.validateInt();
        System.out.println(Color.RED + "Do you want to active the account with ID " + choice + "? (Y/N)");
        String answer = Config.validateString();
        if (answer.equalsIgnoreCase("y")) {
            for (int i = 0; i < listUser.size(); i++) {
                if (listUser.get(i).getId() == choice) {
                    listUser.get(i).setActiveStatus(true);
                    userController.updateUser(listUser.get(i));
                    System.out.println(Color.BLUE_BRIGHT + "Activated success" + Color.RESET);
                    flag = true;
                    new ProfileView();
                    break;
                }
            }
            if (!flag) {
                System.out.println("Input invalid. Please input again");
                ActivateAccount();
            }
        } else {
            new ProfileView();
        }
    }

    public void InactiveAccount() {
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).isActiveStatus()) {
                listUser.get(i).displayData();
            }
        }
        boolean flag = false;
        System.out.println("Input the number of account you want to inactivate");
        int choice = Config.validateInt();
        if (choice == 1) {
            System.out.println("You can't inactivate admin account");
        } else {
            System.out.println(Color.RED + "Do you want to inactivate the account with ID " + choice + "? (Y/N)");
            String answer = Config.validateString();
            if (answer.equalsIgnoreCase("y")) {
                for (int i = 0; i < listUser.size(); i++) {
                    if (listUser.get(i).getId() == choice) {
                        listUser.get(i).setActiveStatus(false);
                        userController.updateUser(listUser.get(i));
                        System.out.println(Color.BLUE_BRIGHT + "Inactivated success" + Color.RESET);
                        flag = true;
                        new ProfileView();
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Input invalid. Please input again");
                    InactiveAccount();
                }
            } else {
                new ProfileView();
            }

        }
    }

    public void deleteUser() {
        for (int i = 0; i < listUser.size(); i++) {
            listUser.get(i).displayData();
        }
        List<User> listForDelete = new ArrayList<>();
        boolean flag = false;
        System.out.println("Input the number of account you want to delete");
        int choice = Config.validateInt();
        if (choice == 1) {
            System.err.println("You can delete an admin account");
            deleteUser();
        } else {
            System.out.println(Color.RED_BOLD_BRIGHT + "Do you want to delete account with ID " + choice + "? (Y/N)" + Color.RESET);
            String answer = Config.validateString();
            if (answer.equalsIgnoreCase("y")) {
                for (int i = 0; i < listUser.size(); i++) {
                    if (listUser.get(i).getId() == choice) {
                        listForDelete.add(listUser.get(i));
                        deleteNoMoreNeedUser(listForDelete);
                        userController.deleteUser(listUser.get(i).getId());
                        System.out.println(Color.BLUE_BRIGHT + "Delete success" + Color.RESET);
                        flag = true;
                        new ProfileView();
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Input invalid. Please input again");
                    InactiveAccount();
                }
            } else {
                new ProfileView();
            }
        }
    }

    public void deleteNoMoreNeedUser(List<User> listForDelete) {
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
