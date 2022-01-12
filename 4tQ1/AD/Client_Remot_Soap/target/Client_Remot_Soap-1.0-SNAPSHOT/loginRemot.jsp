<%-- 
    Document   : loginRemot
    Created on : 29-nov-2021, 10:48:29
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
        <form action = "loginRemot" method = "GET">
            <input type="text" name="username">
            <input type="password" name="passwd">
            <input type="submit" name = "login" value="login">
        </form>
    </body>
</html>
