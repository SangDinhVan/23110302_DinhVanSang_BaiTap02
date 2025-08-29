<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #f5f7fb;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh
}

.card {
	background: #fff;
	padding: 24px 28px;
	border-radius: 12px;
	box-shadow: 0 6px 24px rgba(0, 0, 0, .08);
	width: 360px
}

h2 {
	margin: 0 0 16px
}

.field-label {
	display: block;
	margin: 10px 0 6px
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 8px;
}

.remember {
	display: flex;
	align-items: center;
	gap: 8px;
	margin: 10px 0;
}

.remember input[type="checkbox"] {
	width: auto;
	padding: 0;
}

.remember label {
	margin: 0;
	display: inline;
}

.btn {
	margin-top: 14px;
	padding: 10px 14px;
	border: 0;
	border-radius: 8px;
	background: #3468d1;
	color: #fff;
	cursor: pointer;
	width: 100%
}

.msg {
	margin-top: 10px;
	color: #d13b34
}

/* thêm style cho link dưới nút */
.links {
	margin-top: 12px;
	display: flex;
	justify-content: space-between;
	font-size: 14px;
}

.links a {
	color: #3468d1;
	text-decoration: none;
	cursor: pointer;
}

.links a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="card">
		<h2>Đăng nhập</h2>
		<form method="post" action="${pageContext.request.contextPath}/login">
			<label class="field-label">Tên đăng nhập</label> <input type="text"
				name="username" required> <label class="field-label">Mật
				khẩu</label> <input type="password" name="password" required>

			<div class="remember">
				<input type="checkbox" id="remember" name="remember" value="on">
				<label for="remember">Ghi nhớ đăng nhập lần sau!</label>
			</div>

			<button class="btn" type="submit">Đăng nhập</button>

			<!-- Link đăng ký và quên mật khẩu -->
			<div class="links">
				<a href="${pageContext.request.contextPath}/view/register.jsp">Đăng
					ký</a> <a href="${pageContext.request.contextPath}/view/forgot.jsp">Quên
					mật khẩu?</a>
			</div>
		</form>

		<div class="msg">${requestScope.error}</div>
	</div>
</body>
</html>
