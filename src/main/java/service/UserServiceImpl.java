package service;
import dao.UserDAO;
import dao.UserHibDaoImpl;
import entity.User;

import java.util.List;

public class UserServiceImpl implements  UserService {

    private UserDAO userDao = new UserHibDaoImpl();

    @Override
    public User addUser(String name, String email) {
        List<User> userList = userDao.getUsersByEmail(email);
        if(userList.size()>0){
            return null;
        }
        String role;
        if(email.contains("lulabs.com")) {
            role = "ROLE_ADMIN";
        }
        else{
            role = "ROLE_USER";
        }
        return userDao.addNewUser(name, email, role);
    }
}
