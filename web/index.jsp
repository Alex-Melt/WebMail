<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022/8/9
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/RegisterServlet.do">
    <label for="name">用户名：<input type="text" name="name" id="name"><br></label>
    <label for="password">密码：<input type="password" name="password" id="password"><br></label>
    <label for="email">邮箱：<input type="email" name="email" id="email"><br></label>
    <input type="submit" value="注册">
    ${message}
</form>
</body>
</html>
