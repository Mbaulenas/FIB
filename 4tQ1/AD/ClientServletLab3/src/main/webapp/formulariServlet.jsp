<%-- 
    Document   : formulariServlet
    Created on : 27-oct-2021, 14:38:06
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulari Servlet</title>
    </head>
    <body>
        <body>
        <form action = "testServlet" method = "POST">
            <!-- Campos del formulario -->
            
            
            <div>
                <h3>Title:</h3>
            <input type="text" name="titulo" >
            </div>
            
            <div>
                <h3>Description:</h3>
            <input type="text" name="descripcion" >
            </div>
            
            <div>
                <h3>Keyword:</h3>
            <input type="text" name="keywords">
            </div>
            
            <div>
                <h3>Creator:</h3>
            <input type="text" name="creator" >
            </div>
            
            <div>
                <h3>Author:</h3>
            <input type="text" name="autor" >
            </div>
           
            
            <div>
                <h3>Capture date(dd/MM/yyyy):</h3>
            <input type="text" name="fechaC" >
            </div>
            
            <div>
                <h3>Storage date(dd/MM/yyyy):</h3>
            <input type="text" name="fechaA" >
            </div>
            
            <div>
                <h3>Filename:</h3>
            <input type="text" name="filename" >
            </div>
            
            <div>
                <h3>Id:</h3>
            <input type="text" name="id" >
            </div>
            
            <div>
                <h3>Test number (1-9)</h3>
            <input type="number" name="testnum" >
            </div>
            
            <input type="submit">
        </form>
    </body>
</html>
