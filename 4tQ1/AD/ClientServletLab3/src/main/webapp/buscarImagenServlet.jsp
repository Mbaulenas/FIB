<%-- 
    Document   : buscarImatgeServlet
    Created on : 27-oct-2021, 18:26:58
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
        <form action = "buscarImagenServlet" method = "GET">
            <!-- Campos del formulario -->
            
            <h1>Busqueda multivalor </h1>
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
            
            
            <input type="submit" name = "completa" value = "completa">
            
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