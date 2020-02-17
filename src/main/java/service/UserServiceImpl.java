package service;

import dao.UserHibDaoImpl;
import entity.User;

import java.util.List;

public class UserServiceImpl implements  UserService{

    private UserHibDaoImpl userHibDao = new UserHibDaoImpl();

    @Override
    public User addUser(String name, String email) {
        List<User> userList = userHibDao.getUserByEmail(email);
        if(userList.size()>0){
            return null;
        }
        String role;
        if(email.contains("lulabs.com")){
            role = "ROLE_ADMIN";
        }
        else{
            role = "ROLE_USER";
        }
        return userHibDao.addNewUser(name, email, role);
    }
}
