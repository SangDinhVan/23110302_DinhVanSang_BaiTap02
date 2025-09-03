package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/admin/product/save")
public class ProductSave extends HttpServlet {
    private final ProductService service = new ProductServiceImpl();

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String idRaw = req.getParameter("productID"); // BẮT BUỘC vì không auto-increment
        String name   = req.getParameter("productName");
        String desc   = req.getParameter("description");
        String priceS = req.getParameter("price");
        String image  = req.getParameter("imageLink");
        String cidS   = req.getParameter("categoryID"); // có thể rỗng (NULL)
        String sidS   = req.getParameter("sellerID");
        String amountS= req.getParameter("amount");
        String stockS = req.getParameter("stock");

        if (idRaw == null || idRaw.isBlank() || name == null || name.isBlank() || priceS == null || priceS.isBlank()) {
            req.getSession().setAttribute("msg", "ProductID, ProductName, Price là bắt buộc.");
            resp.sendRedirect(req.getContextPath() + "/admin/product");
            return;
        }

        try {
            int id = Integer.parseInt(idRaw);
            Integer cid = (cidS == null || cidS.isBlank()) ? null : Integer.valueOf(cidS);
            Integer sid = (sidS == null || sidS.isBlank()) ? null : Integer.valueOf(sidS);
            int amount = (amountS == null || amountS.isBlank()) ? 0 : Integer.parseInt(amountS);
            int stock  = (stockS  == null || stockS.isBlank())  ? 0 : Integer.parseInt(stockS);
            BigDecimal price = new BigDecimal(priceS);

            Product p = new Product();
            p.setProductID(id);
            p.setProductName(name.trim());
            p.setDescription(desc);
            p.setPrice(price);
            p.setImageLink(image);
            p.setCategoryID(cid);   // cho phép null
            p.setSellerID(sid);     // cho phép null
            p.setAmount(amount);
            p.setStock(stock);

            // Nếu đã có id -> update, ngược lại -> create
            int changed = (service.get(id) == null) ? service.save(p) : service.save(p);

            req.getSession().setAttribute("msg", changed > 0 ? "Lưu sản phẩm thành công" : "Không có thay đổi");
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("msg", "Lỗi lưu: " + e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/admin/product");
    }
}
