package service;

public class UserServiceImpl implements  UserService{

    @Override
    public String addUser(String name, String email) {
        return "Name: " + name + " / Email: " + email;
    }
}
