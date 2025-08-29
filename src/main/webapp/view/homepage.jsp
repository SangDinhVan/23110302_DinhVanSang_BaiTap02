<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<title>Trang chủ</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<style>
:root {
	--bg: #f5f7fb;
	--card: #fff;
	--text: #0f172a;
	--muted: #64748b;
	--brand: #2d6cdf;
	--brand-hover: #255ac0;
	--ring: rgba(45, 108, 223, .15);
	--shadow: 0 10px 30px rgba(15, 23, 42, .08);
	--radius: 16px;
}

* {
	box-sizing: border-box;
}

html, body {
	height: 100%;
}

body {
	margin: 0;
	font-family: ui-sans-serif, system-ui, -apple-system, Segoe UI, Roboto,
		Helvetica, Arial;
	background: var(--bg);
	color: var(--text);
	display: grid;
	place-items: center;
	padding: 24px;
}

.card {
	width: 100%;
	max-width: 560px;
	background: var(--card);
	border-radius: var(--radius);
	box-shadow: var(--shadow);
	padding: 28px;
	border: 1px solid #eef2ff;
}

.header {
	display: flex;
	align-items: center;
	gap: 12px;
	margin-bottom: 14px;
}

.dot {
	width: 12px;
	height: 12px;
	border-radius: 50%;
	background: linear-gradient(135deg, #6ee7b7, #22d3ee);
	box-shadow: 0 0 0 6px var(--ring);
}

h2 {
	margin: 0;
	font-size: 20px;
	color: var(--brand);
	letter-spacing: .2px;
	font-weight: 700;
}

.sub {
	margin: 6px 0 18px;
	color: var(--muted);
	font-size: 14px;
	line-height: 1.5;
}

.greet {
	display: inline-block;
	background: #f1f5ff;
	color: #1e293b;
	border: 1px solid #e2e8ff;
	padding: 10px 14px;
	border-radius: 12px;
	font-weight: 600;
}

.actions {
	margin-top: 22px;
	display: flex;
	gap: 10px;
	flex-wrap: wrap;
}

.btn {
	appearance: none;
	border: 0;
	cursor: pointer;
	padding: 10px 14px;
	border-radius: 12px;
	font-weight: 600;
	transition: transform .04s ease, background .18s ease;
}

.btn:active {
	transform: translateY(1px);
}

.btn-primary {
	background: var(--brand);
	color: white;
}

.btn-primary:hover {
	background: var(--brand-hover);
}

.btn-ghost {
	background: #f8fafc;
	color: #0f172a;
	border: 1px solid #e2e8f0;
}

.sep {
	height: 1px;
	background: #eef2ff;
	margin: 18px 0;
}

.muted {
	color: var(--muted);
	font-size: 13px;
}

a.clean {
	color: var(--brand);
	text-decoration: none;
	font-weight: 600;
}

a.clean:hover {
	text-decoration: underline;
}

.center {
	text-align: center;
}
</style>
</head>
<body>
	<div class="card">
		<div class="header">
			<span class="dot"></span>
			<h2>Trang chủ</h2>
		</div>

		<!-- Nếu đã đăng nhập -->
		<c:if test="${not empty sessionScope.account}">
			<p class="sub">Bạn đã đăng nhập vào hệ thống. Dưới đây là thông
				tin nhanh.</p>

			<div class="greet">Xin chào, ${sessionScope.account.userName}
				👋</div>

			<div class="actions">
				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/home">Đi tới bảng điều
					khiển</a> <a class="btn btn-ghost"
					href="${pageContext.request.contextPath}/profile">Hồ sơ</a> <a
					class="btn btn-ghost"
					href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
			</div>

			<div class="sep"></div>
			<p class="muted">
				Tip: Bạn có thể quay lại trang đăng nhập bất cứ lúc nào tại <a
					class="clean" href="${pageContext.request.contextPath}/login">/login</a>.
			</p>
		</c:if>

		<!-- Nếu chưa đăng nhập -->
		<c:if test="${empty sessionScope.account}">
			<div class="center">
				<div class="greet">Bạn chưa đăng nhập</div>
				<p class="sub">Vui lòng đăng nhập để tiếp tục sử dụng các tính
					năng.</p>
				<div class="actions center" style="justify-content: center">
					<a class="btn btn-primary"
						href="${pageContext.request.contextPath}/login">Quay lại đăng
						nhập</a> <a class="btn btn-ghost"
						href="${pageContext.request.contextPath}/">Trang chủ</a>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>
