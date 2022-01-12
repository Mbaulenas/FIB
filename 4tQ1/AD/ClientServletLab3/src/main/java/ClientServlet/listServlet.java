/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author alumne
 */
@WebServlet(name = "listServlet", urlPatterns = {"/listServlet"})
public class listServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/lab3AD/ServeiWeb.wsdl")
    private ClientServlet.ServeiWeb_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,List list )
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession misesion = (HttpSession) request.getSession(false);
            
            if (misesion != null){
                String username = (String) misesion.getAttribute("username");
                
                if (misesion.getAttribute("username") != null){
                    
                    if (!list.isEmpty()){
                
                        out.write("<div>");  
                        out.write("<a href=\"menuServlet.jsp\">");
                        out.write("<button>Volver al menú</button>"); 
                        out.write(" </a>");
                        out.write("</div>");

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
                            out.write("<th>Descargable</th>");

                        out.write("</tr>");

                        for(Iterator<Image> it = list.iterator(); it.hasNext();){
                            Image i = it.next();

                            out.write("<tr>");
                                out.write("<td>" + i.getTitulo() + "</td>");
                                out.write("<td>" + i.getDescripcion() + "</td>");
                                out.write("<td>" + i.getKeywords() + "</td>");
                                out.write("<td>" + i.getAutor() + "</td>");
                                out.write("<td>" + i.getCreator() + "</td>");
                                out.write("<td>" + i.getFechaC() + "</td>");
                                out.write("<td>" + i.getFechaA() + "</td>");
                                out.write("<td>" + i.getFilename() + "</td>");

                                if(username.equals(i.creator)){

                                    out.write("<td><a href=\"modificarImagenServlet.jsp?id="+i.id+"&filename="+i.filename+"&title="+i.titulo+"&keywords="+i.keywords+"&description="+i.descripcion+"&fechaC="+i.fechaC+"&autor="+i.autor+"\">\n" +  //S HA DE CANVIAR EL LINK
                                              "<button>Modificar</button>\n" +
                                              "</a></td>");

                                    out.write("<td><a href=\"eliminarImagenServlet.jsp?id="+i.id+"&filename="+i.filename+"\">\n"+  //S HA DE CANVIAR EL LINK
                                              "<button>Eliminar</button>\n" +
                                              "</a></td>");
                                    out.write("<td><a href=\"downloadServlet.jsp?id="+i.id+"&filename="+i.filename+"\">\n" +  //S HA DE CANVIAR EL LINK
                                              "<button>Descargar</button>\n" +
                                              "</a></td>");
                                }
                            out.write("</tr>");
                        }
                    out.write("</table>");

                    }
                    else{

                        out.write("<h1>No hay imagenes para mostrar</h1>");
                        out.write("<div>");
                        out.write("<td><a href=\"menuServlet.jsp\">\n" +  
                                              "<button>Volver al menú</button>\n" +
                                              "</a></td>");
                        out.write("</div>");
                    }

                }
                else {
                    out.write("<h1>Es necesario iniciar sesion</h1>");
                    out.write("<div>");
                    out.write("<td><a href=\"loginServlet.jsp\">\n" +  
                                "<button>Iniciar sesion</button>\n" +
                                "</a></td>");
                    out.write("</div>");
                }
             
            }
            else {
            
                out.write("<h1>Error, vulve a iniciar se</h1>");
                out.write("<div>");
                out.write("<td><a href=\"loginServlet.jsp\">\n" +  
                                "<button>Iniciar sesion</button>\n" +
                                "</a></td>");
                out.write("</div>");
            }
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
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("regist") != null){
            response.sendRedirect("registrarImagenServlet.jsp");
        }
        else if (request.getParameter("buscar") != null){
            response.sendRedirect("buscarImagenServlet.jsp");
        }
        else if(request.getParameter("list") != null){
            
            //control de sesions igual hauria d'estr aqui per bloqueja si no ets user
            
            List list = ListImages();
            processRequest(request,response,list);
        }
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
    
    private List ListImages(){
    
        
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            // TODO process result here
            List result = port.listImages();
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;

    }

}
