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
@WebServlet(name = "eliminarImagenRemot", urlPatterns = {"/eliminarImagenRemot"})
public class eliminarImagenRemot extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
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
                int control = DeleteImage(id);
                
                if (control != 0){
                    
                    response.sendRedirect("menuRemot.jsp");
                }
            }
            else {
                
                response.sendRedirect("errorRemot.jsp");
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
    
    private Image SearchbyId(int id){
        
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
            
            return port.searchbyId(id);
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return null;
    }
    private int DeleteImage(int id){
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
            
            // TODO process result here
            int result = port.deleteImage(id);
            return result;
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return 0;
    }

}
