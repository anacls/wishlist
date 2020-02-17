package service;
import dao.UserDAO;
import dao.UserHibDaoImpl;
import entity.User;

import java.util.List;

public class UserServiceImpl implements  UserService {

    private UserDAO userDao = new UserHibDaoImpl();

    @Override
    public User addUser(String name, String email) {
        User user = userDao.findUserByEmail(email);
        if(user != null) {
            return null;
        }
        String role;
        if(email.contains("lulabs.com")) {
            role = "ROLE_ADMIN";
        }
        else{
            role = "ROLE_USER";
        }
        return userDao.createUser(name, email, role);
    }

    @Override
    public User getUser(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
