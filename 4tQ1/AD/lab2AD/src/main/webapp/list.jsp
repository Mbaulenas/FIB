<%-- 
    Document   : list
    Created on : 11-oct-2021, 16:23:59
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
        <title>List!</title>
    </head>
    <body>
                <a href="menu.jsp">
                <button>Volver al menú</button>
                 </a>
        <%
            HttpSession misesion = request.getSession(true);
            //ResultSet rs = (ResultSet) misesion.getAttribute("resultset");
            String username = "usertest";
            
            if(!misesion.isNew()) username =  (String) misesion.getAttribute("username");
            
            loginsql aux = new loginsql();
            
            ResultSet rs = aux.Lista();
            
            if(rs.next()){
            
            
                out.write("<table>");
                out.write("<tr>");

                    out.write("<th>Titulo</th>");
                    out.write("<th>Descripcion</th>");
                    out.write("<th>Keywords</th>");
                    out.write("<th>Autor</th>");
                    out.write("<th>Creador</th>");
                    out.write("<th>Fecha creacion</th>");
                    out.write("<th>Fecha subida</th>");
                    out.write("<th>Nombre archivo</th>");
                    out.write("<th>Ver imagen</th>");
                    out.write("<th>Modificable</th>");
                    out.write("<th>Eliminable</th>");
                out.write("</tr>");
                
                do{
                    out.write("<tr>");
                        out.write("<td>" + rs.getString("TITLE") + "</td>");
                        out.write("<td>" +rs.getString("DESCRIPTION") + "</td>");
                        out.write("<td>" +rs.getString("KEYWORDS") + "</td>");
                        out.write("<td>" +rs.getString("AUTHOR") + "</td>");
                        out.write("<td>" +rs.getString("CREATOR") + "</td>");
                        out.write("<td>" +rs.getString("CAPTURE_DATE") + "</td>");
                        out.write("<td>" +rs.getString("STORAGE_DATE") + "</td>");
                        out.write("<td>" +rs.getString("FILENAME") + "</td>");
                        
                        String nf = rs.getString("FILENAME");
                        if(!misesion.isNew()){
                            out.write("<td><a href=\"imagenes/"+nf+"\"><button>Ver imagen</button></a></td>");
                        }

                        if(username.equals(rs.getString("CREATOR"))){
                            
                            
                            
                            out.write("<td><a href=\"modificaImagen.jsp?param="+rs.getString("ID")+"\">\n" +  //S HA DE CANVIAR EL LINK
                                      "<button>Modificar</button>\n" +
                                      "</a></td>");

                            out.write("<td><a href=\"eliminarImagen.jsp?param="+rs.getString("ID")+"\">\n" +  //S HA DE CANVIAR EL LINK
                                      "<button>Eliminar</button>\n" +
                                      "</a></td>");
                        }
                    out.write("</tr>");
                }while(rs.next());

                
                out.write("</table>");
            }
            else out.write("<h2>No hay imágenes para listar</h2>");
            
        %>
    </body>
</html>
