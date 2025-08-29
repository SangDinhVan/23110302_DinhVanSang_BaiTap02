package controller;

import java.io.IOException;
import java.sql.Date; // java.sql.Date (con của java.util.Date)

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Mở form đăng ký
        req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String email       = req.getParameter("email");
        String username    = req.getParameter("username");
        String fullName    = req.getParameter("fullname");
        String password    = req.getParameter("password");
        String phone       = req.getParameter("phone");
        String roleStr     = req.getParameter("roleid");
        String createdStr  = req.getParameter("createdDate"); // yyyy-MM-dd

        // Chuyển kiểu
        int roleid = 1;
        try { roleid = Integer.parseInt(roleStr); } catch (Exception ignored) {}

        // createdDate: input type="date" trả yyyy-MM-dd -> dùng java.sql.Date.valueOf
        Date createdDate = null;
        try {
            if (createdStr != null && !createdStr.isBlank()) {
                createdDate = Date.valueOf(createdStr); // hợp lệ cho field java.util.Date trong model
            } else {
                createdDate = new Date(System.currentTimeMillis());
            }
        } catch (Exception e) {
            // Nếu format sai, gán ngày hiện tại
            createdDate = new Date(System.currentTimeMillis());
        }

        // Tạo model User
        User user = new User();
        user.setEmail(email);
        user.setUserName(username);
        user.setFullName(fullName);
        user.setPassWord(password);
        user.setPhone(phone);
        user.setRoleid(roleid);
        user.setCreatedDate(createdDate);

        // Gọi service
        UserService service = new UserServiceImpl();
        try {
            // Nếu bạn để service.register trả về boolean thì thay thế cho phù hợp
            service.register(user);

            // Thành công -> về login kèm cờ báo đã đăng ký
            resp.sendRedirect(req.getContextPath() + "/view/login.jsp?registered=1");
        } catch (Exception e) {
            // Thất bại -> quay lại form và hiển thị lỗi
            req.setAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        }
    }
}
