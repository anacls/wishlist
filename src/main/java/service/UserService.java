package service;

import entity.User;

public interface UserService {
    User addUser(String name, String email);
}
