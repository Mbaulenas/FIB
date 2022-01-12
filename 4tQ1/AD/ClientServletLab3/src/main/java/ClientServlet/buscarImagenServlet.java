/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;




import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author alumne
 */
@WebServlet(name = "buscarImagenServlet", urlPatterns = {"/buscarImagenServlet"})
public class buscarImagenServlet extends HttpServlet {


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

                        out.write("<h1>La búsqueda no ha encontrado resultados</h1>");
                        out.write("<div>");
                        out.write("<td><a href=\"menuServlet.jsp\">\n" +  
                                              "<button>Volver al menú</button>\n" +
                                              "</a></td>");

                        out.write("<td><a href=\"buscarImagenServlet.jsp\">\n" +  
                                              "<button>Realizar otra búsqueda</button>\n" +
                                              "</a></td>");
                        out.write("</div>");
                    }
             
                }
                else{
                    out.write("<h1>Es necesario iniciar sesion</h1>");
                    out.write("<div>");
                    out.write("<td><a href=\"loginServlet.jsp\">\n" +  
                                "<button>Iniciar sesion</button>\n" +
                                "</a></td>");
                    out.write("</div>");
                }
            }
            else {
            
                out.write("<h1>Error, vuelva a iniciar sesion</h1>");
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
        List<Image> list = new ArrayList();
        if (request.getParameter("completa") != null){
            list = SearchComplete(request.getParameter("titulo"), request.getParameter("descripcion"),  request.getParameter("keywords"), request.getParameter("autor"), request.getParameter("creador") , request.getParameter("fechaC"), request.getParameter("fechaA"), request.getParameter("nombreF"));
            
        }
        else if (request.getParameter("ident") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            Image image = SearchbyId(id);
            if (image.getTitulo()!=null) list.add(image);
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

    
    /*##############################################################*/
    
    private List SearchComplete(String titulo, String descripcion, String keywords, String autor, String creador, String fechaC, String fechaA, String nombreF){
      
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            // TODO initialize WS operation arguments here
            
            // TODO process result here
            List result = port.searchComplete(titulo,descripcion,keywords,autor,creador,fechaC,fechaA,nombreF);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    
    private Image SearchbyId(int id){
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            // TODO process result here
            Image result = port.searchbyId(id);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    

    private List SearchbyTitle(String title){
        
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            // TODO process result here
            List result = port.searchbyTitle(title);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    
    
    private List SearchbyCreaDate(String CreaDate){
        
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            // TODO process result here
            List result = port.searchbyCreaDate(CreaDate);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    
    
    private List SearchbyKeywords(String keywords){
        
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            // TODO process result here
            List result = port.searchbyKeywords(keywords);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    
    
    private List SearchbyAuthor(String author){
        
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            // TODO process result here
            List result = port.searchbyAuthor(author);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    
    
}
