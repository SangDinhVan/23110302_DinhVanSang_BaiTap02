<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categories</title>
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
	<h2>Categories</h2>

	<c:if test="${not empty sessionScope.msg}">
		<p style="color: green">${sessionScope.msg}</p>
		<c:remove var="msg" scope="session" />
	</c:if>

	<p>
		<a href="${pageContext.request.contextPath}/admin/category/form">+
			New Category</a>
	</p>

	<table border="1" cellpadding="6" cellspacing="0">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Icon</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="c" items="${list}">
				<tr>
					<td>${c.categoryID}</td>
					<td><c:out value="${c.categoryName}" /></td>
					<td><c:out value="${c.icon}" /></td>
					<td><a
						href="${pageContext.request.contextPath}/admin/category/form?id=${c.categoryID}">Edit</a>
						|
						<form
							action="${pageContext.request.contextPath}/admin/category/delete"
							method="post" style="display: inline"
							onsubmit="return confirm('Xoá?')">
							<input type="hidden" name="id" value="${c.categoryID}">
							<button type="submit">Delete</button>
						</form></td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan="4">Chưa có category.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
</body>
</html>
