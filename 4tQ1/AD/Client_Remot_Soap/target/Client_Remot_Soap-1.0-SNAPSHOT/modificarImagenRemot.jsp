<%-- 
    Document   : modificarImagenRemot
    Created on : 29-nov-2021, 12:48:56
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
                
                
                <form action = "modificarImagenRemot" method = "POST" >    
                <!-- Campos del formulario -->
                   
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
                <a href="menuRemot.jsp">
                <button>Cancelar</button>
                </a>
            </div>  
            </body>
            <%
        }
        else {
             %>
            <h2> Para modificar una imagen es necesario iniciar sesion</h2>"

            <a href='loginRemot.jsp'>"
                <button>IniciarSesion</button>
            </a>"
            <%
        }
        
        
    %>
  
</html>
