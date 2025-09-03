<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Category Form</title></head>
<body>
  <h2>${empty c ? 'Create' : 'Edit'} Category</h2>

  <form method="post" action="${pageContext.request.contextPath}/admin/category/save">
    <label>ID (bắt buộc, vì không auto-increment):</label><br/>
    <input type="number" name="categoryID" value="${c.categoryID}" ${empty c ? '' : 'readonly'} required><br/><br/>

    <label>Name:</label><br/>
    <input type="text" name="categoryName" value="${c.categoryName}" required><br/><br/>

    <label>Icon:</label><br/>
    <input type="text" name="icon" value="${c.icon}"><br/><br/>

    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/admin/category">Cancel</a>
  </form>
</body>
</html>
