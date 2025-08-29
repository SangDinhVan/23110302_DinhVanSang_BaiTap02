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
    
    @Override
    public void register(User user) {
    	userDao.register(user);
    }
    
    @Override
    public int forgot(String username, String email, String sdt, String newPassword) {
        // 1) Chuẩn hóa
        username    = username    != null ? username.trim()            : "";
        email       = email       != null ? email.trim().toLowerCase() : "";
        sdt         = sdt         != null ? sdt.trim()                 : "";
        newPassword = newPassword != null ? newPassword                : "";

        // 2) Validate cơ bản
        if (username.isEmpty() || email.isEmpty() || sdt.isEmpty() || newPassword.isEmpty()) {
            throw new RuntimeException("Vui lòng nhập đầy đủ thông tin.");
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new RuntimeException("Email không hợp lệ.");
        }
        if (!sdt.matches("^\\d{10,11}$")) {
            throw new RuntimeException("SĐT phải gồm 10–11 chữ số.");
        }
        if (newPassword.length() < 6) {
            throw new RuntimeException("Mật khẩu mới tối thiểu 6 ký tự.");
        }

        // 3) Gọi DAO, truyền mật khẩu mới dạng plain text
        return userDao.forgot(username, email, sdt, newPassword);
    }

}