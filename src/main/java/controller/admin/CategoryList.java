package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/category")
public class CategoryList extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("list", service.getAll());
        req.getRequestDispatcher("/view/admin/category-list.jsp").forward(req, resp);
    }
}
