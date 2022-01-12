/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
        
            /* TODO output your page here. You may use following sample code. */
            
            
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
        
        try(PrintWriter out = response.getWriter()){
               
               //Fem el login
               //correcte = ComprovarCredencials(request.getParameter("username"), request.getParameter("passwd"));
                
                if(request.getParameter("login")!=null){
                    String aux = "http://localhost:8080/lab4AD/resources/javaee8/comprovaCredencials/" +  request.getParameter("username") + "/" + request.getParameter("passwd");
                
                    URL url = new URL(aux);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    //conn.setRequestProperty("Accept","");

                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);

                    String output;

                    if(conn.getResponseCode() == 200){

                        while((output=br.readLine()) != null){

                            if(output.equals("OK")) {
                                HttpSession misesion = request.getSession(true);
                                misesion.setAttribute("username", request.getParameter("username"));
                                response.sendRedirect("menuServlet.jsp");
                            }
                            else if(output.equals("KO")) response.sendRedirect("errorLoginServlet.jsp");


                        }

                    } 

                    else out.println("DEAD");



                    conn.disconnect();


            
                }
                else{
                    
                    String aux = "http://localhost:8080/lab4AD/resources/javaee8/registerUser";
                
                    URL url = new URL(aux);

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    String params = "username="+request.getParameter("username")+
                                    "&passwd="+request.getParameter("passwd");
                    
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
                    writer.write(params);

                    writer.flush();
                    writer.close();
                    os.close();
                    

                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);

                    String output;

                    if(conn.getResponseCode() == 200){

                        while((output=br.readLine()) != null){

                            if(output.equals("OK")) response.sendRedirect("loginServlet.jsp");
                            else response.sendRedirect("errorUsuariOcupatServlet.jsp");


                        }

                    } 

                    else out.println("DEAD");
                
                
                
                    conn.disconnect();


            
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
    }// </editor-fold>// </editor-fold>
    
    
    
    /*####################################################*/

        
    
    
    
    
}
