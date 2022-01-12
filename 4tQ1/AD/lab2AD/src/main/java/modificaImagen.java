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

import classesjava.loginsql;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;



/**
 *
 * @author alumne
 */
@WebServlet(urlPatterns = {"/modificaImagen"})
public class modificaImagen extends HttpServlet {

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
           
        HttpSession misesion = request.getSession(true);
            
        String id =(String) misesion.getAttribute("idfoto");
           
        ResultSet rs = aux.idtoentry(id);
        try {
            rs.next();
        } catch (Exception e){
            response.sendRedirect("error.jsp");
        }
        
        
        doPost(request, response, aux, id, rs);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, loginsql aux, String id, ResultSet rs)
            throws ServletException, IOException {
        
        try {
            aux.Eliminar(id);
           
            aux.AfegirEntrada(request.getParameter("titulo"), request.getParameter("descripcion"), request.getParameter("keywords"), request.getParameter("autor"), rs.getString("CREATOR"), request.getParameter("fechaC"), rs.getString("STORAGE_DATE"), rs.getString("FILENAME"));
           
            response.sendRedirect("list.jsp");
        }catch(Exception e1){
           response.sendRedirect("error.jsp");
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

}
