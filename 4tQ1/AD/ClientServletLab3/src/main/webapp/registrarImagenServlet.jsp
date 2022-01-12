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
        
        
        
        <form action = "registrarImagenServlet" method = "POST" enctype="multipart/form-data">
            <!-- Campos del formulario --> 
            <div>
                <h3>File:</h3>
            <input type="file" name="file" required="required">
            </div>
            <div>
                <h3>Title:</h3>
            <input type="text" name="titulo" required="required">
            </div>
            
            <div>
                <h3>Description:</h3>
            <input type="text" name="descripcion" required="required">
            </div>
            
            <div>
                <h3>Keyword:</h3>
            <input type="text" name="keywords" required="required">
            </div>
            
            <div>
                <h3>Author:</h3>
            <input type="text" name="autor" required="required">
            </div>
           
            
            <div>
                <h3>Capture date(dd/MM/yyyy):</h3>
            <input type="text" name="fechaC" required="required">
            </div>
            
            
            <input type="submit">
        </form>
    </body>
</html>

