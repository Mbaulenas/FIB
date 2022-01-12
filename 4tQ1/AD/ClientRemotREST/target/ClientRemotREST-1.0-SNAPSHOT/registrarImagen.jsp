<%-- 
    Document   : registrarImagenServlet
    Created on : 28-oct-2021, 9:40:33
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Imagen</title>
    </head>
    <body>
        <div>
                <a href="menuServlet.jsp">
                <button>Volver al menú</button>
                 </a>
        </div>
        
        
        
        <form action = "registrarImagen" method = "POST" contentType="application/x-www-form-urlencoded" enctype="multipart/form-data">
            <!-- Campos del formulario --> 
            
            <div>
                <h3>File</h3>
            <input type="file" name="file" required="required">
            </div>
            
            <div>
                <h3>Title:</h3>
            <input type="text" name="title" required="required">
            </div>
            
            <div>
                <h3>Description:</h3>
            <input type="text" name="description" required="required">
            </div>
            
            <div>
                <h3>Keyword:</h3>
            <input type="text" name="keywords" required="required">
            </div>
            
            <div>
                <h3>Author:</h3>
            <input type="text" name="author" required="required">
            </div>
            
            
           
            
            <div>
                <h3>Capture date(yyyy-mm-dd):</h3>
            <input type="text" name="capture" required="required">
            </div>
            
            
            <input type="submit">
        </form>
    </body>
</html>

