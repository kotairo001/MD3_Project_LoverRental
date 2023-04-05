package service.user;

import model.User;
import service.IGenericService;

import java.util.List;

public interface IUser extends IGenericService<User> {
    boolean existedByUserName(String userName);
    boolean existedByEmail(String email);
    boolean checkRightPassword(String password);
    void updateLoginAccount(List<User> user);
    boolean checkLogin(String userName, String password);
    User getCurrentUser();

}
