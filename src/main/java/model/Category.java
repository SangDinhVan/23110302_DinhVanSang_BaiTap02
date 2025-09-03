package model;
import java.io.Serializable;

public class Category implements Serializable {
    private int categoryID;
    private String categoryName;
    private String icon;

    // getters/setters
    public int getCategoryID() { return categoryID; }
    public void setCategoryID(int categoryID) { this.categoryID = categoryID; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
}
