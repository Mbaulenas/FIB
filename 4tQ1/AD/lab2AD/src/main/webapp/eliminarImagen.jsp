<%-- 
    Document   : eliminarImagen
    Created on : 17-oct-2021, 11:56:38
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
        import = "classesjava.loginsql"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
        String idfoto = request.getParameter("param");
        
        HttpSession misesion = request.getSession(true);
        
        misesion.setAttribute("idfoto",idfoto);
        
        loginsql aux = new loginsql();
        
        String filename = aux.idtofilename(idfoto);
        
        //String path = "/home/alumne/NetBeansProjects/lab2AD/src/main/webapp/imagenes/"+filename;
        
        %>
        <h1>Seguro que quieres eliminar la imagen ?</h1>
        
        <img src="http://localhost:8080/lab2AD/imagenes/<%out.write(filename);%>" />
        
        <div>
        <form action = "eliminaImagen" method = "POST">
            
            <input type="submit" value="Eliminar">
            
        </form>
        
        <a href="list.jsp">
                <button>Cancelar</button>
            </a>
        </div>
    </body>
</html>
