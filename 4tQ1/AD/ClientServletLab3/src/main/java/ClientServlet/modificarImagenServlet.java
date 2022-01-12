/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "modificarImagenServlet", urlPatterns = {"/modificarImagenServlet"})
public class modificarImagenServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet modificarImagenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet modificarImagenServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        
        try (PrintWriter out = response.getWriter()) {
            
            HttpSession misesion = request.getSession(true);
            
            int id = (int) misesion.getAttribute("idfoto");
            
            Image image = SearchbyId(id);
            
            String nomuser = (String) misesion.getAttribute("username");
                    
            if(nomuser.equals(image.getCreator())){
                Image Mimage = new Image();
                Mimage.setTitulo(request.getParameter("titulo"));
                Mimage.setDescripcion(request.getParameter("descripcion"));
                Mimage.setKeywords(request.getParameter("keywords"));
                Mimage.setAutor(request.getParameter("autor"));
                Mimage.setFechaC(request.getParameter("fechaC"));
                Mimage.setCreator(image.getCreator());
                Mimage.setFechaA(image.getFechaA());
                Mimage.setId(image.getId());
                
                int control = ModifyImage(Mimage);
                
                if (control != 0){
                    
                    response.sendRedirect("menuServlet.jsp");
                }
            }
            else {
                
                response.sendRedirect("errorServlet.jsp");
            }
           
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
    
    /*################################################################*/
    
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
    
    private int ModifyImage(Image image){
        
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            
            // TODO process result here
            int result = port.modifyImage(image);
            return result;
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return 0;
    }

}
