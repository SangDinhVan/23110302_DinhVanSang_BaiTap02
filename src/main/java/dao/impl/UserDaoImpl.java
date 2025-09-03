package dao.impl;

import model.User;
import java.sql.*;

import dao.DBConnect;
import dao.UserDAO;

public class UserDaoImpl implements UserDAO {
	@Override
	public User get(String username) {
		String sql = "SELECT userName, passWord , roleid FROM Users WHERE userName = ?";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setUserName(rs.getString("userName"));
					user.setPassWord(rs.getString("passWord"));
					user.setRoleid(rs.getInt("roleid"));
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

	@Override
	public void register(User user) {
		String sql = "INSERT INTO Users(email,userName, fullName, passWord, roleid, phone, createdDate) "
				+ "VALUES (?, ?, ? ,?, ?, ?, ?)";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
			
			ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setInt(5, user.getRoleid());
            ps.setString(6, user.getPhone());
            ps.setDate(7, new java.sql.Date(user.getCreatedDate().getTime()));
            ps.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public int forgot(String userName, String email, String sdt, String newPassword) {
	    String sql = "UPDATE Users SET passWord = ? WHERE userName = ? AND email = ? AND phone = ?";
	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, newPassword);  
	        ps.setString(2, userName);
	        ps.setString(3, email);
	        ps.setString(4, sdt);

	        return ps.executeUpdate();      // số dòng cập nhật (0 = không khớp thông tin)
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

}
