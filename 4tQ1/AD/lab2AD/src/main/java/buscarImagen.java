/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

import classesjava.loginsql;


/**
 *
 * @author alumne
 */
@WebServlet(urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, ResultSet rs)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession misesion = (HttpSession) request.getSession();
        String username = (String) misesion.getAttribute("username");
        
        try (PrintWriter out = response.getWriter()) {
            
            
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
            else{
                out.write("<h1>La búsqueda no ha encontrado resultados</h1>");
                out.write("<div>");
                out.write("<td><a href=\"menu.jsp\">\n" +  
                                      "<button>Volver al menú</button>\n" +
                                      "</a></td>");
                
                out.write("<td><a href=\"buscarImagen.jsp\">\n" +  
                                      "<button>Realizar otra búsqueda</button>\n" +
                                      "</a></td>");
                out.write("</div>");
            }
            
            
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loginsql aux = new loginsql();    
        ResultSet rs = aux.Busqueda(request.getParameter("titulo"),  request.getParameter("keywords"), request.getParameter("autor"), request.getParameter("creador") , request.getParameter("fechaC"), request.getParameter("fechaA"), request.getParameter("nombreF"));
            
        processRequest(request, response, rs);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
