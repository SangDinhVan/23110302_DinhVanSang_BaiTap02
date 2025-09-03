package dao.impl;

import dao.CategoryDAO;
import model.Category;
import java.sql.*;
import java.util.*;
import dao.DBConnect;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() {
        String sql = "SELECT CategoryID, CategoryName, Icon FROM Category ORDER BY CategoryName";
        List<Category> list = new ArrayList<>();
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("CategoryID"));
                c.setCategoryName(rs.getString("CategoryName"));
                c.setIcon(rs.getString("Icon"));
                list.add(c);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Category findById(int id) {
        String sql = "SELECT CategoryID, CategoryName, Icon FROM Category WHERE CategoryID=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category c = new Category();
                    c.setCategoryID(rs.getInt(1));
                    c.setCategoryName(rs.getString(2));
                    c.setIcon(rs.getString(3));
                    return c;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public int create(Category c) {
        String sql = "INSERT INTO Category(CategoryName, Icon) VALUES(?,?)";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCategoryName());
            ps.setString(2, c.getIcon());
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int update(Category c) {
        String sql = "UPDATE Category SET CategoryName=?, Icon=? WHERE CategoryID=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getCategoryName());
            ps.setString(2, c.getIcon());
            ps.setInt(3, c.getCategoryID());
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM Category WHERE CategoryID=?";
        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
        return 0;
    }
}
