<%-- 
    Document   : modificarImagenServlet
    Created on : 28-oct-2021, 10:41:20
    Author     : alumne
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8" 
        import = "java.sql.ResultSet"
        import = "javax.servlet.http.HttpSession"
        import = "ClientServlet.Image"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar imagen</title>
    </head>
    
    <%
        
        String idfoto = request.getParameter("param");
        
        int id = Integer.parseInt(idfoto);
        
        
        HttpSession misesion = request.getSession(true);
        
        String aux = "http://localhost:8080/lab4AD/resources/javaee8/searchID/"+id;
        
        URL url = new URL(aux);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
                //conn.setRequestProperty("Accept","");
                
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            Image im = new Image();
            String output;
            String aux2 = new String();
                
                if(conn.getResponseCode() == 200){
                
                   while((output=br.readLine()) != null){
                       if(output.length()>0){
                            aux2+=output;
                        }
                   }
                    
                        
                  Gson gson = new Gson();
                  im = gson.fromJson(aux2, Image.class);
                }
        
        if(misesion != null){
            misesion.setAttribute("idfoto",id);
            
            %>
            <body>
                <form action = "modificarImagenServlet" method = "POST" contentType="application/x-www-form-urlencoded">
                <!-- Campos del formulario -->

                  

                    <div>
                        <h3>Title:</h3>
                        <input type="text" name="titulo" required="required" value="<%out.print(im.getTitulo());%>">
                    </div>

                    <div>
                        <h3>Description:</h3>
                        <input type="text" name="descripcion" required="required" value="<%out.print(im.getDescripcion());%>">
                    </div>

                    <div>
                        <h3>Keyword:</h3>
                    <input type="text" name="keywords" required="required" value="<%out.print(im.getKeywords());%>">
                    </div>

                    <div>
                        <h3>Author: <%im.getAutor();%></h3>
                    <input type="text" name="autor"  required="required" value="<%out.print(im.getAutor());%>">
                    </div>


                    <div>
                        <h3>Capture date(yyyy-MM-dd):</h3>
                    <input type="text" name="fechaC"  required="required" value="<%out.print(im.getFechaC());%>">
                    </div>


                    <input type="submit">
                </form>
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

