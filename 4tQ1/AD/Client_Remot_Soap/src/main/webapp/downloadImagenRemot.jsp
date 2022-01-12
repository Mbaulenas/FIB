<%-- 
    Document   : downloadImagenRemot
    Created on : 29-nov-2021, 13:50:38
    Author     : alumne
--%>

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
            
            <h1>Haga click en descargar para descargar la imagen</h1>
       
            <div>
            <form action = "downloadImagenRemot" method = "POST">

                <input type="submit" value="Descargar">

            </form>

            <a href="menuRemot.jsp">
                <button>Cancelar</button>
            </a>
            </div> 
            <%
            
        }else{
            %>
            <h2> Para descargar una imagen es necesario iniciar sesion</h2>"

            <a href='loginRemot.jsp'>"
                <button>IniciarSesion</button>
            </a>"
            <%
        }
        
        %>
        
    </body>
</html>
