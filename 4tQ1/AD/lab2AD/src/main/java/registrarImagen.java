/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.SimpleDateFormat;


import org.apache.log4j.Logger;
import classesjava.loginsql;

/**
 *
 * @author alumne
 */
@WebServlet(urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {
    private static Logger logger = Logger.getLogger(registrarImagen.class);



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
    
    private String getFileName(Part part) {
    String partHeader = part.getHeader("content-disposition");
    logger.info("Part Header = " + partHeader);
    for (String cd : part.getHeader("content-disposition").split(";")) {
      if (cd.trim().startsWith("filename")) {
        return cd.substring(cd.indexOf('=') + 1).trim()
            .replace("\"", "");
      }
    }
    return null;

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
         String nombreF = new String();
        int aux2 = 1;
         for (Part part : request.getParts()) {
            logger.info(part.getName());
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            logger.info("Length : " + b.length);
            String fileName = getFileName(part);
            
            logger.info("File name : " + fileName);
            FileOutputStream os = new FileOutputStream("/home2/users/alumnes/1240358/Desktop/imagenes/" + fileName); //
            
            if(aux2==1){
                nombreF = fileName;
                aux2=0;
            }
            
            os.write(b);
            is.close();
          }
        //response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            loginsql aux = new loginsql();
            
            HttpSession misesion = (HttpSession) request.getSession();
            String username = (String) misesion.getAttribute("username");
            
            
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            
            aux.AfegirEntrada(request.getParameter("titulo"), request.getParameter("descripcion"), request.getParameter("keywords"), request.getParameter("autor"), username , request.getParameter("fechaC"), date, nombreF);
            //response.sendRedirect("menu.jsp");
            
            
            out.write("<h2> Imagen registrada correctamente</h2>");
            
            
            out.write("<a href='registrarImagen.jsp'>");
            out.write("<button>Registrar otra imagen</button></a>");
            
            out.write("<a href='menu.jsp'>");
            out.write("<button>Volver al menú</button></a>");
            
        }catch(Exception e){
            response.sendRedirect("error.jsp");
        }
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

}
