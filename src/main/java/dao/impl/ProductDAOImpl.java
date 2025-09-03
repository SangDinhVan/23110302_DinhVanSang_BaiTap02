package dao.impl;

import dao.ProductDAO;
import model.Product;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;
import dao.DBConnect;

public class ProductDAOImpl implements ProductDAO {

    private Product map(ResultSet rs) throws SQLException {
        Product p = new Product();
        p.setProductID(rs.getInt("ProductID"));
        p.setProductName(rs.getString("ProductName"));
        p.setDescription(rs.getString("Description"));
        p.setPrice(rs.getBigDecimal("Price"));
        p.setImageLink(rs.getString("ImageLink"));
        int cid = rs.getInt("CategoryID");
        p.setCategoryID(rs.wasNull() ? null : cid);
        int sid = rs.getInt("SellerID");
        p.setSellerID(rs.wasNull() ? null : sid);
        p.setAmount(rs.getInt("Amount"));
        p.setStock(rs.getInt("Stock"));
        return p;
    }

    @Override
    public List<Product> findAll(int page, int pageSize) {
        String sql = """
            SELECT ProductID, ProductName, Description, Price, ImageLink,
                   CategoryID, SellerID, Amount, Stock
            FROM Product
            ORDER BY ProductID DESC
            LIMIT ? OFFSET ?
            """;
        List<Product> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pageSize);
            ps.setInt(2, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Product> findByCategory(int categoryId, int page, int pageSize) {
        String sql = """
            SELECT ProductID, ProductName, Description, Price, ImageLink,
                   CategoryID, SellerID, Amount, Stock
            FROM Product
            WHERE CategoryID = ?
            ORDER BY ProductID DESC
            LIMIT ? OFFSET ?
            """;
        List<Product> list = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, pageSize);
            ps.setInt(3, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Product findById(int id) {
        String sql = """
            SELECT ProductID, ProductName, Description, Price, ImageLink,
                   CategoryID, SellerID, Amount, Stock
            FROM Product WHERE ProductID=?
            """;
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public int countAll() {
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Product");
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int countByCategory(int categoryId) {
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM Product WHERE CategoryID=?")) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int create(Product p) {
        String sql = """
            INSERT INTO Product(ProductName, Description, Price, ImageLink,
                                CategoryID, SellerID, Amount, Stock)
            VALUES (?,?,?,?,?,?,?,?)
            """;
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setString(4, p.getImageLink());
            if (p.getCategoryID()==null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, p.getCategoryID());
            if (p.getSellerID()==null) ps.setNull(6, Types.INTEGER); else ps.setInt(6, p.getSellerID());
            ps.setInt(7, p.getAmount());
            ps.setInt(8, p.getStock());
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int update(Product p) {
        String sql = """
            UPDATE Product SET
                ProductName=?, Description=?, Price=?, ImageLink=?,
                CategoryID=?, SellerID=?, Amount=?, Stock=?
            WHERE ProductID=?
            """;
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getDescription());
            ps.setBigDecimal(3, p.getPrice());
            ps.setString(4, p.getImageLink());
            if (p.getCategoryID()==null) ps.setNull(5, Types.INTEGER); else ps.setInt(5, p.getCategoryID());
            if (p.getSellerID()==null) ps.setNull(6, Types.INTEGER); else ps.setInt(6, p.getSellerID());
            ps.setInt(7, p.getAmount());
            ps.setInt(8, p.getStock());
            ps.setInt(9, p.getProductID());
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int delete(int id) {
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM Product WHERE ProductID=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}
