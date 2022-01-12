/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import lab3ad.ServeiWeb_Service;

/**
 *
 * @author alumne
 */
@WebServlet(name = "downloadImageServlet", urlPatterns = {"/downloadImageServlet"})
public class downloadImageServlet extends HttpServlet {

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
                String filename = image.getFilename();
                Image im = DownloadImage(id);
                byte[] decode = Base64.getDecoder().decode(im.getFile());
                try{
                    FileOutputStream os = new FileOutputStream("/home/alumne/NetBeansProjects/ClientServletLab3/src/main/webapp/imagenes/" + filename);
                    os.write(decode);
                    response.sendRedirect("menuServlet.jsp");
                }catch (Exception e){
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
    //##################################################################// </editor-fold>
    //##################################################################
    
    
    public Image DownloadImage(int id){
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            // TODO process result here
            Image result = port.downloadImage(id);
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


}
