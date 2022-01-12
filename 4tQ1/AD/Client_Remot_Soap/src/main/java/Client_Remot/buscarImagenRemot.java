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
import java.util.ArrayList;
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
@WebServlet(name = "buscarImagenRemot", urlPatterns = {"/buscarImagenRemot"})
public class buscarImagenRemot extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, List list)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession misesion = (HttpSession) request.getSession(false);
            
            if (misesion != null){
            
                if(misesion.getAttribute("username") != null){
                    String username = (String) misesion.getAttribute("username");
                
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

                        out.write("<h1>La búsqueda no ha encontrado resultados</h1>");
                        out.write("<div>");
                        out.write("<td><a href=\"menuRemot.jsp\">\n" +  
                                              "<button>Volver al menú</button>\n" +
                                              "</a></td>");

                        out.write("<td><a href=\"buscarImagenRemot.jsp\">\n" +  
                                              "<button>Realizar otra búsqueda</button>\n" +
                                              "</a></td>");
                        out.write("</div>");
                    }
             
                }
                else{
                    out.write("<h1>Es necesario iniciar sesion</h1>");
                    out.write("<div>");
                    out.write("<td><a href=\"loginRemot.jsp\">\n" +  
                                "<button>Iniciar sesion</button>\n" +
                                "</a></td>");
                    out.write("</div>");
                }
            }
            else {
            
                out.write("<h1>Error, vuelva a iniciar sesion</h1>");
                out.write("<div>");
                out.write("<td><a href=\"loginRemot.jsp\">\n" +  
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
         List<Image> list = new ArrayList();
       
        if (request.getParameter("ident") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            Image image = SearchbyId(id);
            if (image.getTitle()!=null) list.add(image);
        }
        else if (request.getParameter("title") != null){
            list = SearchbyTitle(request.getParameter("titulo2"));
        }
        else if (request.getParameter("fecha") != null){
            list = SearchbyCreaDate(request.getParameter("fechaC2"));
        }
        else if (request.getParameter("key") != null){
            list = SearchbyKeywords(request.getParameter("keywords2"));
        }
        else if (request.getParameter("aut") != null){
            list = SearchbyAuthor(request.getParameter("autor2"));
        }
        processRequest(request, response, list);
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
    
    
    private Image SearchbyId(int id){
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
          
            // TODO process result here
            Image result = port.searchbyId(id);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    
    private List<Image> SearchbyTitle(String title){
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
          
            // TODO process result here
            return port.searchbyTitle(title);
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    
     private List SearchbyCreaDate(String creaDate){
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
          
            // TODO process result here
            return port.searchbyCreaDate(creaDate);
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
     
     private List SearchbyKeywords(String keywords){
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
          
            // TODO process result here
            return port.searchbyKeywords(keywords);
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
     
     private List SearchbyAuthor(String author){
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
          
            // TODO process result here
            return port.searchbyAuthor(author);
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    
}
