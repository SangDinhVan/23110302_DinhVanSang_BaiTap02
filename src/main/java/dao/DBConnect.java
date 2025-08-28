package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    // ✅ URL chuẩn cho MySQL 8
    private static final String DB_URL =
        "jdbc:mysql://localhost:3306/test_sql"
      + "?useUnicode=true&characterEncoding=utf8"
      + "&useSSL=false&allowPublicKeyRetrieval=true"
      + "&serverTimezone=Asia/Ho_Chi_Minh";

    private static final String USER = "root";
    private static final String PASS = "Sang1234@";

    // ✅ Luôn ném lỗi khi fail (đừng return null)
    public static Connection getConnection() {
        try {
            // Ép Tomcat nạp driver (an toàn cho môi trường server)
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("✅ Kết nối MySQL thành công!");
            return con;
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Không tìm thấy MySQL Driver trên classpath!");
            throw new RuntimeException("Missing MySQL driver (com.mysql.cj.jdbc.Driver).", e);
        } catch (SQLException e) {
            System.err.println("❌ Kết nối MySQL thất bại!");
            throw new RuntimeException("Cannot connect to MySQL. URL/User/Pass?", e);
        }
    }

  
}
