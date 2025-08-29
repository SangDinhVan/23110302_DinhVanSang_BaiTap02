package dao;

import model.User;

public interface UserDAO {
    User get(String username);
	void register(User user);
	int forgot (String userName, String email, String sdt, String newPassword );
}