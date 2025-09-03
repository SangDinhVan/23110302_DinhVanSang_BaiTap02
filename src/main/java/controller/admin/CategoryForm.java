package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/category/form")
public class CategoryForm extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id"); // có id => edit, null => create
        if (id != null && !id.isBlank()) {
            try {
                Category c = service.get(Integer.parseInt(id));
                req.setAttribute("c", c);
            } catch (NumberFormatException ignored) { /* tạo mới */ }
        }
        req.getRequestDispatcher("/view/admin/category-form.jsp").forward(req, resp);
    }
}
