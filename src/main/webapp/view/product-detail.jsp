<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title><c:out value="${p.productName}"/></title></head>
<body>
<c:if test="${not empty p}">
  <img src="${p.imageLink}" alt="<c:out value='${p.productName}'/>" style="max-width:400px">
  <h2><c:out value="${p.productName}"/></h2>
  <p><c:out value="${p.description}"/></p>
  <h3><c:out value="${p.price}"/> ₫</h3>
  <p>Stock: <c:out value="${p.stock}"/></p>
</c:if>

<c:if test="${empty p}">
  <p>Product not found.</p>
</c:if>

  <a href="${pageContext.request.contextPath}/products">← Back</a>
</body>
</html>
