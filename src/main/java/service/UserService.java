package service;

import model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
    void register(User user);
    int forgot(String userName, String email, String sdt, String newPassword);
}