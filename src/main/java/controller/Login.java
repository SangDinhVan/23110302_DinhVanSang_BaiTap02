package controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = service.login(username, password); // trả về User có roleid

        if (user == null) {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("auth", user);

        // Nếu có URL đích trước đó (khi bị chặn bởi AdminFilter) thì ưu tiên quay lại
        String redirectTo = (String) session.getAttribute("redirectTo");
        if (redirectTo != null && !redirectTo.isBlank()) {
            session.removeAttribute("redirectTo");
            resp.sendRedirect(req.getContextPath() + redirectTo); // redirectTo là đường dẫn tương đối (bắt đầu bằng /)
            return;
        }

        // Chuyển hướng theo role
        System.out.println(">>> LOGIN OK: user=" + user.getUserName() + ", roleid=" + user.getRoleid());

        if (user.getRoleid() == 1) {
        	
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        } else {
            resp.sendRedirect(req.getContextPath() + "/products");
        }
    }
}
