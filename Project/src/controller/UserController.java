package controller;

import config.Config;
import dto.request.LoginDTO;
import dto.request.LoverDTO;
import dto.request.UserDTO;
import dto.respond.RespondMessage;
import model.Role;
import model.RoleName;
import model.User;
import service.role.IRoleService;
import service.role.RoleNameService;
import service.user.IUser;
import service.user.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    private IUser userService = new UserService();
    private IRoleService roleService = new RoleNameService();

    public RespondMessage registerUser(UserDTO sign) {
        if (userService.existedByUserName(sign.getUserName())) {
            return new RespondMessage("user_existed");
        }
        if (userService.existedByEmail(sign.getEmail())) {
            return new RespondMessage("email_existed");
        }
        Set<Role> roleSet = new HashSet<>();
        Set<String> strRole = sign.getStrRole();
        strRole.forEach(role -> {
            switch (role) {
                case "user":
                    roleSet.add(roleService.findByName(RoleName.USER));
                    break;
            }
        });
        User user = new User(sign.getId(), sign.getEmail(), sign.getUserName(), sign.getName(), sign.getPassword(), roleSet);
        userService.save(user);
        return new RespondMessage("created_success");
    }

    public RespondMessage registerLover(LoverDTO lover) {
        if (userService.existedByUserName(lover.getUserName())) {
            return new RespondMessage("user_existed");
        }
        if (userService.existedByEmail(lover.getEmail())) {
            return new RespondMessage("email_existed");
        }
        Set<Role> roleSet = new HashSet<>();
        Set<String> strRole = lover.getStrRole();
        strRole.forEach(role -> {
            switch (role) {
                case "lover":
                    roleSet.add(roleService.findByName(RoleName.LOVER));
                    break;
            }
        });
        User loverAccount = new User(lover.getId(), lover.getEmail(), lover.getUserName(), lover.getName(), lover.getPassword(), lover.getRentPrice(), roleSet);
        userService.save(loverAccount);
        return new RespondMessage("created_success");
    }

    public RespondMessage logIn(LoginDTO loginDTO) {
        if (!userService.checkLogin(loginDTO.getUserName(), loginDTO.getPassword())) {
            return new RespondMessage("login_fail");
        }
        return new RespondMessage("login_success");
    }

    public User getCurrentLoginUser() {
        return userService.getCurrentUser();

    }



    public List<User> getUserList() {
        return userService.findAll();
    }


    public void createUser(User registerAccount) {
        userService.save(registerAccount);
    }

    public void updateUser(User registerAccount) {
        userService.save(registerAccount);
    }

    public void deleteUser(int id) {
        userService.delete(id);
    }

    public void updateLogin(List<User> user) {
        userService.updateLoginAccount(user);
    }

}
