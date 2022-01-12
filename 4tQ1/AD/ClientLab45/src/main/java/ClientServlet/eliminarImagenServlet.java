/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
@WebServlet(name = "eliminarImagenServlet", urlPatterns = {"/eliminarImagenServlet"})
public class eliminarImagenServlet extends HttpServlet {

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
            out.println("<title>Servlet eliminarImagenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet eliminarImagenServlet at " + request.getContextPath() + "</h1>");
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
            
            Image im = SearchbyId(id);
            
            
            if(im.getCreator().equals(misesion.getAttribute("username"))){
            
                String aux = "http://localhost:8080/lab4AD/resources/javaee8/delete";

                URL url = new URL(aux);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
                String params = "id=" + id;
                writer.write(params);

                writer.flush();
                writer.close();
                os.close();


                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);

                String output;

                if(conn.getResponseCode() == 200){

                    while((output=br.readLine()) != null){

                        if(output.equals("OK")) response.sendRedirect("menuServlet.jsp");

                        else if(output.equals("KO")) response.sendRedirect("errorServlet.jsp");



                    }

                }
            }
            else response.sendRedirect("errorServlet.jsp");
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
    
    
    /*#############################################################*/
    
    private Image SearchbyId(int id){
        try{
                    
            String aux = "http://localhost:8080/lab4AD/resources/javaee8/searchID/"+id;

            URL url = new URL(aux);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
                //conn.setRequestProperty("Accept","");
                
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            Image im = new Image();
            String output;
            String aux2 = new String();
                
                if(conn.getResponseCode() == 200){
                
                   while((output=br.readLine()) != null){
                       if(output.length()>0){
                            aux2+=output;
                        }
                   }
                    
                        
                  Gson gson = new Gson();
                  im = gson.fromJson(aux2, Image.class);      
                  
                  
                    
                    
                } 
                
                
                
                
                
            conn.disconnect();
            return im;
            
        }catch(Exception e){
        
        }
        
        return null;
    }
    
    

}
