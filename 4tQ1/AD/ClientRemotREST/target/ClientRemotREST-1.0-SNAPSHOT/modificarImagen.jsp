<%-- 
    Document   : modificarImagenServlet
    Created on : 28-oct-2021, 10:41:20
    Author     : alumne
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8" 
        import = "java.sql.ResultSet"
        import = "javax.servlet.http.HttpSession"
        %>

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
        
        String aux = "http://10.10.73.48:8080/Practica04_REST_WS/resources/javaee8/searchID/"+id;
        
        URL url = new URL(aux);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
                //conn.setRequestProperty("Accept","");
                
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String content = null;
            StringBuilder sb = new StringBuilder();
            while((content = in.readLine()) != null) sb.append(content);
            String aux2 = sb.toString();
                  
                  JSONArray array = new JSONArray();
                  JSONObject obj = new JSONObject(aux2);
                  array = obj.getJSONArray("llistaimatges");  
                  
                  
                  JSONObject object = array.getJSONObject(0);
                  
                      
                      
                   
        
        if(misesion != null){
            misesion.setAttribute("idfoto",id);
            
            %>
            <body>
                <form action = "modificarImagen" method = "POST" contentType="application/x-www-form-urlencoded">
                <!-- Campos del formulario -->

                  

                    <div>
                        <h3>Title:</h3>
                        <input type="text" name="titulo" required="required" value="<%out.print(object.getString("title"));%>">
                    </div>

                    <div>
                        <h3>Description:</h3>
                        <input type="text" name="descripcion" required="required" value="<%out.print(object.getString("Description"));%>">
                    </div>

                    <div>
                        <h3>Keyword:</h3>
                    <input type="text" name="keywords" required="required" value="<%out.print(object.getString("Keywords"));%>">
                    </div>

                    <div>
                        <h3>Author:%></h3>
                    <input type="text" name="autor"  required="required" value="<%out.print(object.getString("Author"));%>">
                    </div>


                    <div>
                        <h3>Capture date(yyyy-MM-dd):</h3>
                    <input type="text" name="fechaC"  required="required" value="<%out.print(object.getString("Capture date"));%>">
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

