package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/category/delete")
public class CategoryDelete extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idRaw = req.getParameter("id");
        try {
            int id = Integer.parseInt(idRaw);
            service.delete(id);
            req.getSession().setAttribute("msg", "Đã xoá category " + id);
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Xoá thất bại: " + e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }
}
