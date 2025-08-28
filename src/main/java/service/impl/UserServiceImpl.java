package service.impl;

import dao.UserDAO;
import dao.impl.UserDaoImpl;
import model.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    UserDAO userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
    	username = username.trim();
        password = password.trim();
        
        User user = userDao.get(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }
    
    @Override
    public User get(String username) {
        return userDao.get(username);
    }
}