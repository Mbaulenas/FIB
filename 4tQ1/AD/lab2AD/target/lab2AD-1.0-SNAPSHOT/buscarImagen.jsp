<%-- 
    Document   : buscarImagen
    Created on : 05-oct-2021, 8:13:35
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar imagen</title>
    </head>
    <body>
        <a href="menu.jsp">
                <button>Volver al menú</button>
                 </a>
        <form action = "buscarImagen" method = "GET">
            <!-- Campos del formulario -->
            
            
            <div>
                <h3>Title:</h3>
            <input type="text" name="titulo">
            </div>
            
            <div>
                <h3>Description:</h3>
            <input type="text" name="descripcion">
            </div>
            
            <div>
                <h3>Keyword:</h3>
            <input type="text" name="keywords">
            </div>
            
            <div>
                <h3>Author:</h3>
            <input type="text" name="autor">
            </div>
            
            <div>
                <h3>Creator:</h3>
            <input type="text" name="creador">
            </div>
           
            
            <div>
                <h3>Capture date(dd/MM/yyyy):</h3>
            <input type="text" name="fechaC">
            </div>
            
            <div>
                <h3>Storage date(dd/MM/yyyy):</h3>
            <input type="text" name="fechaA">
            </div>
            
            <div>
                <h3>Filename:</h3>
            <input type="text" name="nombreF">
            </div>
            
            
            <input type="submit">
        </form>
    </body>
</html>
