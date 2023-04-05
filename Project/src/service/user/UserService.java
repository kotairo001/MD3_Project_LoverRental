package service.user;

import config.Config;
import model.Role;
import model.RoleName;
import model.User;
import service.role.RoleNameService;

import java.util.*;

public class UserService implements IUser {
    public static List<User> users = new ArrayList<>();

    List<User> userList = new Config<User>().readFromFile(Config.FILE_USER_PATH);


    @Override
    public List findAll() {
        return userList;
    }

    @Override
    public void save(User account) {
        if (findById(account.getId()) != null) {
            userList.set(userList.indexOf(findById(account.getId())), account);
        } else {
            if (userList == null) {
                userList = new ArrayList<>();
                userList.add(account);
            } else {
                userList.add(account);
            }
        }
        new Config<User>().writeToFile(Config.FILE_USER_PATH, userList);
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                userList.remove(i);
            }
        }
    }

    @Override
    public boolean existedByUserName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(userName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkRightPassword(String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateLoginAccount(List<User> user) {
        new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, user);
    }

    @Override
    public boolean checkLogin(String userName, String password) {
        List<User> userLogin = new Config<User>().readFromFile(Config.FILE_LOGIN_PATH);

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(userName) && userList.get(i).getPassword().equals(password)) {
                userLogin.add(userList.get(i));
                new Config<User>().writeToFile(Config.FILE_LOGIN_PATH, userLogin);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        if (new Config<User>().readFromFile(Config.FILE_LOGIN_PATH).size() != 0) {
            return new Config<User>().readFromFile(Config.FILE_LOGIN_PATH).get(0);
        }
        return null;
    }


}
