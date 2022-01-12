/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

import java.util.List;

/**
 *
 * @author alumne
 */
@WebServlet(name = "testServlet", urlPatterns = {"/testServlet"})
public class testServlet extends HttpServlet {

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
            
            
            
            int testnum = Integer.parseInt(request.getParameter("testnum"));
            
            switch(testnum){
                
                case 1: 
                    Image image = new Image();
                    image.setAutor(request.getParameter("autor"));
                    image.setCreator(request.getParameter("creator"));
                    image.setDescripcion(request.getParameter("descripcion"));
                    image.setFechaA(request.getParameter("fechaA"));
                    image.setFechaC(request.getParameter("fechaC"));
                    image.setKeywords(request.getParameter("keywords"));
                    image.setTitulo(request.getParameter("titulo"));
                    image.setFilename(request.getParameter("filename"));
                    
                    int test1 = RegisterImage(image);
                    
                    if(test1==1) out.write("Test 1 (Registrar imatge): correcte");
                    else out.write("Test 1 (Registrar imatge): incorrecte");
                    
                break;
                
                case 2:
                    int test2 = DeleteImage(Integer.parseInt(request.getParameter("id")));
                    if(test2!=0) out.write("Test 2 (Esborrar imatge): correcte");
                    else out.write("Test 2 (Esborrar imatge): incorrecte");
                    
                break;
                    
                case 3:
                    List test3 = ListImages();
                    for(Iterator<Image> it = test3.iterator(); it.hasNext();){
                    Image i = it.next();
                    System.out.println(i.getTitulo());
                break;
                    
                    
        }
                    
                 
                   
            }
            
            
        }
        
    }
    
    private int RegisterImage(Image image){
        
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            
            // TODO process result here
            int result = port.registerImage(image);
            return result;
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return 0;
    }
    
    
     private int DeleteImage(int id){
        
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            
            // TODO process result here
            int result = port.deleteImage(id);
            return result;
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return 0;
    }
     
    
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
        processRequest(request, response);
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
