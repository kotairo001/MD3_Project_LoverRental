package service.user;

import model.User;
import service.IGenericService;

import java.util.List;

public interface IUser extends IGenericService<User> {
    boolean existedByUserName(String userName);
    boolean existedByEmail(String email);
    boolean checkLogin(String userName, String password);
    boolean checkAccountActiveStatus(String userName, String password);
    User getCurrentUser();
    List<User> getLoverList();
    List<User> findTopFiveLover();
    boolean checkSelectedUser(int choice);

}
