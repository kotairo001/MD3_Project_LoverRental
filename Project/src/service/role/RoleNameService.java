package service.role;

import model.Role;
import model.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleNameService implements IRoleService {
    public static List<Role>roleList = new ArrayList<>();
    static {
        roleList.add(new Role(1,RoleName.USER));
        roleList.add(new Role(2,RoleName.ADMIN));
        roleList.add(new Role(3,RoleName.LOVER));
    }

    @Override
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public Role findByName(RoleName name) {
        for (int i = 0; i < roleList.size(); i++) {
            if(roleList.get(i).getName()==name){
                return roleList.get(i);
            }
        }
        return null;
    }
}
