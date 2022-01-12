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
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, boolean correcte, boolean log, boolean control )
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            if (log){
                if (correcte){
                    HttpSession misesion= request.getSession(true);
                    misesion.setAttribute("username", request.getParameter("username"));
                    response.sendRedirect("menuServlet.jsp");
                }
                else response.sendRedirect("errorLoginServlet.jsp");
            }
            else if (control){
                response.sendRedirect("loginServlet.jsp");
            }
            else {
                response.sendRedirect("errorUsuariOcupatServlet.jsp");
            }
        }
        catch (Exception e) {
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
        
            boolean log = true;
            boolean correcte = true;
            boolean control = false;

           if (request.getParameter("login") != null){
               //Fem el login
                correcte = ComprovarCredencials(request.getParameter("username"), request.getParameter("passwd"));

            }
           else if (request.getParameter("regist") != null){
               log = false;
               control = AfegirUsuari(request.getParameter("username"),request.getParameter("passwd"));
           }
           processRequest(request, response, correcte, log, control);
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
    }// </editor-fold>// </editor-fold>
    
    
    
    /*####################################################*/

        
    private boolean ComprovarCredencials(String username, String password){
        
        boolean result = false;
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
  
            // TODO process result here
            result = port.comprovarCredencials(username, password);
            return result;
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return result;
    }
    
    private boolean AfegirUsuari(String username, String password){
        boolean result = false;
        try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
  
            // TODO process result here
            result = port.afegirUsuari(username, password);
            return result;
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return result;
    }
    
    
    
}
