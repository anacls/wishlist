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
        String role = getRole(email);
        return userDao.addNewUser(name, email, role);
    }

    @Override
    public User getUser(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void removeUser(int id) {
        if(getUser(id) != null) {
            userDao.deleteUser(id);
        }
    }

    @Override
    public User updateUser(int id, String name, String email){
        if(getUser(id) == null){
            return null;
        };
        return userDao.updateUser(id, name, email);
    }

    private String getRole(String email){
        if(email.contains("lulabs.com")) {
           return "ROLE_ADMIN";
        }
        return  "ROLE_USER";
    }
}
