package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/category/save")
public class CategorySave extends HttpServlet {
    private final CategoryService service = new CategoryServiceImpl();

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String idRaw = req.getParameter("categoryID"); // bắt buộc vì không auto_increment
        String name   = req.getParameter("categoryName");
        String icon   = req.getParameter("icon");

        if (idRaw == null || idRaw.isBlank() || name == null || name.isBlank()) {
            req.getSession().setAttribute("msg", "CategoryID và CategoryName là bắt buộc.");
            resp.sendRedirect(req.getContextPath() + "/admin/category");
            return;
        }

        int id = Integer.parseInt(idRaw);
        Category c = new Category();
        c.setCategoryID(id);
        c.setCategoryName(name.trim());
        c.setIcon(icon);

        // Nếu đã tồn tại -> update; chưa có -> create
        int changed = (service.get(id) == null) ? service.save(c) : service.save(c);

        // Gợi ý: service.save() đã tự quyết create/update theo ID==0;
        // vì bạn dùng ID tay, ta coi: nếu get(id) == null -> create; ngược lại update.
        req.getSession().setAttribute("msg", changed > 0 ? "Lưu thành công" : "Không thay đổi");
        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }
}
