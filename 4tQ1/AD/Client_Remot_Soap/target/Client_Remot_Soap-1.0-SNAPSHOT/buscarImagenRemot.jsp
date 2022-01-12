<%-- 
    Document   : buscarImagenRemot
    Created on : 29-nov-2021, 12:19:09
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
        <a href="menuServlet.jsp">
                <button>Volver al menú</button>
                 </a>
        <form action = "buscarImagenRemot" method = "GET">
            <!-- Campos del formulario -->
            
            <h1>Buscar imagen por id  </h1>
            
            <div>
                <h3>Id:</h3>
            <input type="text" name="id">
            </div>
            
            
            <input type="submit" name = "ident" value = "Buscar">
            
            <h1>Buscar imagen por titulo </h1>
            
            <div>
                <h3>Title:</h3>
            <input type="text" name="titulo2">
            </div>
            
            
            <input type="submit" name = "title" value = "Buscar">
            
            <h1>Buscar imagen por fecha de subida</h1>
            
            <div>
                <h3>Fecha:</h3>
            <input type="text" name="fechaC2">
            </div>
            
            
            <input type="submit" name = "fecha" value = "Buscar">
            
            <h1>Buscar imagen keywords </h1>
            
            <div>
                <h3>Keywords:</h3>
            <input type="text" name="keywords2">
            </div>
            
            
            <input type="submit" name = "key" value = "Buscar">
            
            <h1>Buscar imagen autor </h1>
            
            <div>
                <h3>Autor:</h3>
            <input type="text" name="autor2">
            </div>
            
           
            <input type="submit" name = "aut" value = "Buscar">
        </form>
    </body>
</html>
