<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Quên mật khẩu</title>
  <style>
    body{font-family:Arial,sans-serif;background:#f5f7fb;display:flex;justify-content:center;align-items:center;height:100vh}
    .card{background:#fff;padding:24px 28px;border-radius:12px;box-shadow:0 6px 24px rgba(0,0,0,.08);width:400px}
    h2{margin:0 0 16px}
    .field-label{display:block;margin:10px 0 6px}
    input{width:100%;padding:10px;border:1px solid #ddd;border-radius:8px}
    .btn{margin-top:14px;padding:10px 14px;border:0;border-radius:8px;background:#3468d1;color:#fff;cursor:pointer;width:100%}
    .msg{margin-top:10px;color:#d13b34}
    a{display:inline-block;margin-top:10px;color:#3468d1;text-decoration:none}
    a:hover{text-decoration:underline}
  </style>
</head>
<body>
  <div class="card">
    <h2>Quên mật khẩu</h2>
    <form method="post" action="${pageContext.request.contextPath}/forgot">
      <label class="field-label">Tên đăng nhập</label>
      <input type="text" name="username" required>

      <label class="field-label">Email</label>
      <input type="email" name="email" required>

      <label class="field-label">Số điện thoại</label>
      <input type="text" name="phone" required>

      <label class="field-label">Mật khẩu mới</label>
      <input type="password" name="newPassword" required>

      <button class="btn" type="submit">Cập nhật mật khẩu</button>
    </form>

    <div class="msg">${requestScope.error}</div>
    <a href="${pageContext.request.contextPath}/view/login.jsp">Quay lại đăng nhập</a>
  </div>
</body>
</html>
