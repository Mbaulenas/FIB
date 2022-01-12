<%-- 
    Document   : modificarImagenServlet
    Created on : 28-oct-2021, 10:41:20
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" 
        import = "java.sql.ResultSet"
        import = "javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar imagen</title>
    </head>
    
    <%
        
        String idfoto = request.getParameter("id");
        String filename = request.getParameter("filename");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String autor = request.getParameter("autor");
        String fechaC = request.getParameter("fechaC");
        String keywords = request.getParameter("keywords");
        
        int id = Integer.parseInt(idfoto);
        
        HttpSession misesion = request.getSession(true);
        
        if(misesion != null){
            misesion.setAttribute("idfoto",id);
            %>
            <body>
                
                <img src="http://localhost:8080/lab3AD/imagenes/<%out.write(filename);%> " />
                <form action = "modificarImagenServlet" method = "POST">    
                <!-- Campos del formulario -->

                    <div>
                        <h3>Title:</h3>
                        <input type="text" name="titulo" value="<%out.write(title);%>" required="required">
                    </div>

                    <div>
                        <h3>Description:</h3>
                    <input type="text" name="descripcion" value="<%out.write(description);%>" required="required">
                    </div>

                    <div>
                        <h3>Keyword:</h3>
                    <input type="text" name="keywords" value="<%out.write(keywords);%>" required="required">
                    </div>

                    <div>
                        <h3>Author:</h3>
                    <input type="text" name="autor" value="<%out.write(autor);%>" required="required">
                    </div>


                    <div>
                        <h3>Capture date(dd/MM/yyyy):</h3>
                    <input type="text" name="fechaC" value="<%out.write(fechaC);%>" required="required">
                    </div>


                    <input type="submit">
                </form>
                <a href="menuServlet.jsp">
                <button>Cancelar</button>
                </a>
            </div>  
            </body>
            <%
        }
        else {
             %>
            <h2> Para modificar una imagen es necesario iniciar sesion</h2>"

            <a href='loginServlet.jsp'>"
                <button>IniciarSesion</button>
            </a>"
            <%
        }
        
        
    %>
  
</html>

