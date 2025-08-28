<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Đăng nhập</title>
  <style>
    body{font-family:Arial,sans-serif;background:#f5f7fb;display:flex;justify-content:center;align-items:center;height:100vh}
    .card{background:#fff;padding:24px 28px;border-radius:12px;box-shadow:0 6px 24px rgba(0,0,0,.08);width:360px}
    h2{margin:0 0 16px}
    label{display:block;margin:10px 0 6px}
    input{width:100%;padding:10px;border:1px solid #ddd;border-radius:8px}
    .btn{margin-top:14px;padding:10px 14px;border:0;border-radius:8px;background:#3468d1;color:#fff;cursor:pointer}
    .msg{margin-top:10px;color:#d13b34}
    a{display:inline-block;margin-top:10px}
  </style>
</head>
<body>
  <div class="card">
    <h2>Đăng nhập</h2>
    <form method="post" action="${pageContext.request.contextPath}/login">
      <label>Tên đăng nhập</label>
      <input name="username" required>
      <label>Mật khẩu</label>
      <input type="password" name="password" required>

      <button class="btn" type="submit">Đăng nhập</button>
    </form>

    <div class="msg">${requestScope.error}</div>
  </div>
</body>
</html>
