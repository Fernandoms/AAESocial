<%--
  Created by IntelliJ IDEA.
  User: arthurlorenzi
  Date: 04/10/17
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form method="POST" action="FrontController">
        <input type="hidden" name="action" value="Login">
        Login: <input type="text" name="login"/>
        <br/>
        Password: <input type="password" name="password"/>
        <br/>
        <input type="submit" value="login">
    </form>
</body>
</html>
