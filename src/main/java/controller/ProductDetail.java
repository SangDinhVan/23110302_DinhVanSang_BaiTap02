package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;
import java.io.IOException;

@WebServlet(urlPatterns = "/product")
public class ProductDetail extends HttpServlet {
    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product p = productService.get(id);
        if (p == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        req.setAttribute("p", p);
        req.getRequestDispatcher("/view/product-detail.jsp").forward(req, resp);
    }
}
