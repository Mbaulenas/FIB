<%-- 
    Document   : loginServlet
    Created on : 27-oct-2021, 15:17:17
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action = "loginServlet" method = "POST">
            <input type="text" name="username">
            <input type="password" name="passwd">
            <input type="submit" name = "login" value="login">
            <input type="submit" name = "regist" value="registrarse">
        </form>
    </body>
</html>