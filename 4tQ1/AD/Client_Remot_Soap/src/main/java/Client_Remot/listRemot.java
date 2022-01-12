/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client_Remot;

import com.mycompany.practica03.Image;
import com.mycompany.practica03.Practica03WS;
import com.mycompany.practica03.Practica03WS_Service;
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
@WebServlet(name = "listRemot", urlPatterns = {"/listRemot"})
public class listRemot extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/10.10.73.48_8080/Practica0503/Practica03WS.wsdl")
    private Practica03WS_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, List<Image> list)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession misesion = (HttpSession) request.getSession(false);
            
            if (misesion != null){
                String username = (String) misesion.getAttribute("username");
                
                if (misesion.getAttribute("username") != null){
                    
                    if (!list.isEmpty()){
                
                        out.write("<div>");  
                        out.write("<a href=\"menuRemot.jsp\">");
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
                                out.write("<td>" + i.getTitle() + "</td>");
                                out.write("<td>" + i.getDescription() + "</td>");
                                out.write("<td>" + i.getKeywords() + "</td>");
                                out.write("<td>" + i.getAuthor() + "</td>");
                                out.write("<td>" + i.getCreator() + "</td>");
                                out.write("<td>" + i.getCaptureDate() + "</td>");
                                out.write("<td>" + i.getStorageDate() + "</td>");
                                out.write("<td>" + i.getFilename() + "</td>");

                                if(username.equals(i.getCreator())){

                                    out.write("<td><a href=\"modificarImagenRemot.jsp?id="+i.getId()+"&filename="+i.getFilename()+"&title="+i.getTitle()+"&keywords="+i.getKeywords()+"&description="+i.getDescription()+"&fechaC="+i.getCaptureDate()+"&autor="+i.getAuthor()+"\">\n" +  //S HA DE CANVIAR EL LINK
                                              "<button>Modificar</button>\n" +
                                              "</a></td>");

                                    out.write("<td><a href=\"eliminarImagenRemot.jsp?id="+i.getId()+"&filename="+i.getFilename()+"\">\n"+  //S HA DE CANVIAR EL LINK
                                              "<button>Eliminar</button>\n" +
                                              "</a></td>");
                                    out.write("<td><a href=\"downloadImagenRemot.jsp?id="+i.getId()+"&filename="+i.getFilename()+"\">\n" +  //S HA DE CANVIAR EL LINK
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
            response.sendRedirect("registrarImagenRemot.jsp");
        }
        else if (request.getParameter("buscar") != null){
            response.sendRedirect("buscarImagenRemot.jsp");
        }
        else if(request.getParameter("list") != null){
            
            //control de sesions igual hauria d'estr aqui per bloqueja si no ets user
            
            List list = ListImage();
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
    
    private List<Image> ListImage(){
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
            // TODO process result here
            java.util.List<Image> result = port.listImage();
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }

}
