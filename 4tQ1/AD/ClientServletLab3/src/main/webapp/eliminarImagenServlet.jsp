<%-- 
    Document   : eliminarImagenServlet
    Created on : 28-oct-2021, 10:10:48
    Author     : alumne
--%>

<%@page import="javax.xml.ws.WebServiceRef"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
           
       String idfoto = request.getParameter("id");
        String filename = request.getParameter("filename");
        
        int id = Integer.parseInt(idfoto);
        
        HttpSession misesion = request.getSession(true);
        
        if(misesion != null){
            
            misesion.setAttribute("idfoto",id);
            %>
            <h1>Seguro que quieres eliminar la imagen ?</h1>
             <img src="http://localhost:8080/lab3AD/imagenes/<%out.write(filename);%> " />
        
            <div>
            <form action = "eliminarImagenServlet" method = "POST">

                <input type="submit" value="Eliminar">

            </form>

            <a href="menuServlet.jsp">
                <button>Cancelar</button>
            </a>
            </div> 
            <%
            
        }else{
            %>
            <h2> Para eliminar una imagen es necesario iniciar sesion</h2>"

            <a href='loginServlet.jsp'>"
                <button>IniciarSesion</button>
            </a>"
            <%
        }
        
        
        
        
        %>
        
    </body>
   
</html>

