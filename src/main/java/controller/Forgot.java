package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/forgot")
public class Forgot extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/view/forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username    = req.getParameter("username");
        String email       = req.getParameter("email");
        String phone       = req.getParameter("phone");
        String newPassword = req.getParameter("newPassword");

        try {
            int rows = service.forgot(username, email, phone, newPassword); // không hash theo yêu cầu
            if (rows > 0) {
                resp.sendRedirect(req.getContextPath() + "/view/login.jsp?reset=1");
            } else {
                req.setAttribute("error", "Thông tin không khớp hoặc tài khoản không tồn tại.");
                req.getRequestDispatcher("/view/forgot.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi: " + e.getMessage());
            req.getRequestDispatcher("/view/forgot.jsp").forward(req, resp);
        }
    }
}

