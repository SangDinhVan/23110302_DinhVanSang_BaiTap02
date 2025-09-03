<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Danh sách sản phẩm</title>
  <style>
    .grid { display:flex; flex-wrap:wrap; gap:12px; }
    .card { width:240px; border:1px solid #ddd; padding:10px; border-radius:8px; }
    .card img { width:100%; height:160px; object-fit:cover; display:block; }
    .paging a { margin:0 4px; text-decoration:none; }
    .paging .active { font-weight:bold; text-decoration:underline; }
  </style>
</head>
<body>

  <!-- Filter -->
  <form method="get" action="${pageContext.request.contextPath}/products">
    <select name="cid" onchange="this.form.submit()">
      <option value="">-- All Categories --</option>
      <c:forEach var="c" items="${categories}">
        <option value="${c.categoryID}" ${cid == c.categoryID ? 'selected' : ''}>
          <c:out value="${c.categoryName}"/>
        </option>
      </c:forEach>
    </select>
    <!-- giữ size khi đổi category -->
    <input type="hidden" name="size" value="${size}"/>
  </form>

  <!-- Grid products -->
  <div class="grid">
    <c:forEach var="p" items="${products}">
      <div class="card">
        <a href="${pageContext.request.contextPath}/product?id=${p.productID}">
          <img src="${empty p.imageLink ? '/img/placeholder.png' : p.imageLink}"
               alt="<c:out value='${p.productName}'/>">
          <h4><c:out value="${p.productName}"/></h4>
        </a>
        <p><c:out value="${p.price}"/> đ</p>
        <small>Stock: <c:out value="${p.stock}"/></small>
      </div>
    </c:forEach>

    <c:if test="${empty products}">
      <p>Không có sản phẩm.</p>
    </c:if>
  </div>

  <!-- Paging -->
  <div class="paging" style="margin-top:12px">
    <c:forEach begin="1" end="${totalPages}" var="i">
      <c:url var="pageLink" value="${pageContext.request.contextPath}/products">
        <c:param name="page" value="${i}"/>
        <c:param name="size" value="${size}"/>
        <c:if test="${cid != null}">
          <c:param name="cid" value="${cid}"/>
        </c:if>
      </c:url>

      <a class="${i == page ? 'active' : ''}" href="${pageLink}">${i}</a>
    </c:forEach>
  </div>

</body>
</html>
