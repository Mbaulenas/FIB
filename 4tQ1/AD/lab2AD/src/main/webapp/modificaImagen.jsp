<%-- 
    Document   : modificaImagen
    Created on : 17-oct-2021, 16:45:51
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" 
        import = "java.sql.ResultSet"
        import = "javax.servlet.http.HttpSession"
        import = "classesjava.loginsql"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar imagen</title>
    </head>
    
    <%
        loginsql aux = new loginsql();
        
        String idfoto = request.getParameter("param");
        
        ResultSet rs = aux.idtoentry(idfoto);
        
        rs.next();
        
        HttpSession misesion = request.getSession(true);
        
        misesion.setAttribute("idfoto",idfoto);
        
    %>
    <body>
        <form action = "modificaImagen" method = "GET">
            <!-- Campos del formulario -->
            
            
            
            <div>
                <h3>Title:</h3>
                <input type="text" name="titulo" value="<%out.write(rs.getString("TITLE"));%>" required="required">
            </div>
            
            <div>
                <h3>Description:</h3>
            <input type="text" name="descripcion" value="<%out.write(rs.getString("DESCRIPTION"));%>" required="required">
            </div>
            
            <div>
                <h3>Keyword:</h3>
            <input type="text" name="keywords" value="<%out.write(rs.getString("KEYWORDS"));%>" required="required">
            </div>
            
            <div>
                <h3>Author:</h3>
            <input type="text" name="autor" value="<%out.write(rs.getString("AUTHOR"));%>" required="required">
            </div>
           
            
            <div>
                <h3>Capture date(dd/MM/yyyy):</h3>
            <input type="text" name="fechaC" value="<%out.write(rs.getString("CAPTURE_DATE"));%>" required="required">
            </div>
            
            
            <input type="submit">
        </form>
    </body>
</html>
