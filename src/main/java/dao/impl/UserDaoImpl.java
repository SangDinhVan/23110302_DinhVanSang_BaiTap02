package dao.impl;

import model.User;
import java.sql.*;

import dao.DBConnect;
import dao.UserDAO;

public class UserDaoImpl implements UserDAO {
    @Override
    public User get(String username) {
        String sql = "SELECT userName, passWord FROM Users WHERE userName = ?";
        try (
            Connection conn = DBConnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserName(rs.getString("userName"));
                    user.setPassWord(rs.getString("passWord"));
                 // ✅ DEBUG ra console
                    System.out.println("==[DAO DEBUG]==");
                    System.out.println("Username: " + user.getUserName());
                    System.out.println("Password DB: " + user.getPassWord());
                    return user;
                } else {
                	 System.out.println("Không tìm thấy@@@");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
