package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.ProductService;
import service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/admin/product/delete")
public class ProductDelete extends HttpServlet {
    private final ProductService service = new ProductServiceImpl();

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idRaw = req.getParameter("id");
        try {
            int id = Integer.parseInt(idRaw);
            service.delete(id);
            req.getSession().setAttribute("msg", "Đã xoá sản phẩm " + id);
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Xoá thất bại: " + e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/admin/product");
    }
}
