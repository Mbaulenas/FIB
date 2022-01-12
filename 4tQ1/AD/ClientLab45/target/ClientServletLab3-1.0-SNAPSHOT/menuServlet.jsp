<%-- 
    Document   : menuServlet
    Created on : 27-oct-2021, 18:25:22
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    
    
  
        <form action = "listServlet" method = "POST">
            
            <input type="submit" name = "regist" value="Registrar imagen">
            <input type="submit" name = "buscar" value="Buscar imagen">
            <input type="submit" name = "list" value="Listado de imagenes">
            
        </form>
    
    <body>
        
    </body>
</html>
