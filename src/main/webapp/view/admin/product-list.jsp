<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
</head>
<body>
	<div>
		<a href="${pageContext.request.contextPath}/admin/category">Quản
			lý Category</a> | <a
			href="${pageContext.request.contextPath}/admin/product">Quản lý
			Product</a> | <a href="${pageContext.request.contextPath}/logout">Đăng
			xuất</a>
	</div>
	<hr />
	<h2>Products</h2>

	<c:if test="${not empty sessionScope.msg}">
		<p style="color: green">${sessionScope.msg}</p>
		<c:remove var="msg" scope="session" />
	</c:if>

	<p>
		<a href="${pageContext.request.contextPath}/admin/product/form">+
			New Product</a>
	</p>

	<table border="1" cellpadding="6" cellspacing="0">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>CategoryID</th>
				<th>Stock</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="p" items="${list}">
				<tr>
					<td>${p.productID}</td>
					<td><c:out value="${p.productName}" /></td>
					<td><c:out value="${p.price}" /></td>
					<td><c:out value="${p.categoryID}" /></td>
					<td><c:out value="${p.stock}" /></td>
					<td><a
						href="${pageContext.request.contextPath}/admin/product/form?id=${p.productID}">Edit</a>
						|
						<form
							action="${pageContext.request.contextPath}/admin/product/delete"
							method="post" style="display: inline"
							onsubmit="return confirm('Xoá?')">
							<input type="hidden" name="id" value="${p.productID}">
							<button type="submit">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan="6">Chưa có sản phẩm.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</body>
</html>
