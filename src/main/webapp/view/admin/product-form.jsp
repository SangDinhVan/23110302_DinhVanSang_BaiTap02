<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Product Form</title></head>
<body>
  <h2>${empty p ? 'Create' : 'Edit'} Product</h2>

  <form method="post" action="${pageContext.request.contextPath}/admin/product/save">
    <label>Product ID (bắt buộc, vì không auto-increment):</label><br/>
    <input type="number" name="productID" value="${p.productID}" ${empty p ? '' : 'readonly'} required><br/><br/>

    <label>Name:</label><br/>
    <input type="text" name="productName" value="${p.productName}" required><br/><br/>

    <label>Description:</label><br/>
    <textarea name="description" rows="4" cols="50">${p.description}</textarea><br/><br/>

    <label>Price:</label><br/>
    <input type="number" step="0.01" name="price" value="${p.price}" required><br/><br/>

    <label>Image Link:</label><br/>
    <input type="text" name="imageLink" value="${p.imageLink}"><br/><br/>

    <label>Category:</label><br/>
    <select name="categoryID">
      <option value="">-- None --</option>
      <c:forEach var="c" items="${categories}">
        <option value="${c.categoryID}" ${p.categoryID == c.categoryID ? 'selected' : ''}>
          <c:out value="${c.categoryName}"/>
        </option>
      </c:forEach>
    </select><br/><br/>

    <label>Seller ID:</label><br/>
    <input type="number" name="sellerID" value="${p.sellerID}"><br/><br/>

    <label>Amount:</label><br/>
    <input type="number" name="amount" value="${p.amount}"><br/><br/>

    <label>Stock:</label><br/>
    <input type="number" name="stock" value="${p.stock}"><br/><br/>

    <button type="submit">Save</button>
    <a href="${pageContext.request.contextPath}/admin/product">Cancel</a>
  </form>
</body>
</html>
