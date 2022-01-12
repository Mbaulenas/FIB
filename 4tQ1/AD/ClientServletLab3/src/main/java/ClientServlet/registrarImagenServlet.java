/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;

import org.apache.log4j.Logger; 
/**
 *
 * @author alumne
 */
@WebServlet(name = "registrarImagenServlet", urlPatterns = {"/registrarImagenServlet"})
@MultipartConfig
public class registrarImagenServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(registrarImagenServlet.class);
    
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
      
    }
    /*
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

  }*/

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
        
        
        
            try(PrintWriter out = response.getWriter()){
                HttpSession misesion = (HttpSession) request.getSession(true);
                if (misesion != null){
 
                   
                    //FileOutputStream os = new FileOutputStream("/home/alumne/NetBeansProjects/lab2AD/src/main/webapp/imagenes/" + filename);
                    
                         
                    
                
                
                if(misesion.getAttribute("username") != null){
                    Part filePart = request.getPart("file");
                    String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    InputStream fileContent = filePart.getInputStream();
                    byte[] imageBytes = new byte[(int)filePart.getSize()];
                    fileContent.read(imageBytes, 0, imageBytes.length);
                    fileContent.close();
                    String s = Base64.getEncoder().encodeToString(imageBytes);
                    
                    String username = (String) misesion.getAttribute("username");
                    String pattern = "dd/MM/yyyy";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String date = simpleDateFormat.format(new Date());

                    Image image = new Image();
                    image.setTitulo(request.getParameter("titulo"));
                    image.setDescripcion(request.getParameter("descripcion"));
                    image.setKeywords(request.getParameter("keywords"));
                    image.setAutor(request.getParameter("autor"));
                    image.setCreator(username);
                    image.setFechaC(request.getParameter("fechaC"));
                    image.setFechaA(date);
                    image.setFilename(filename);
                    //image.setFile(s);


                    int control = RegisterImage(image);
                    int control2 = UploadImage(filename, s);

                    if (control != 0){

                        out.write("<h2> Imagen registrada correctamente</h2>");

                        out.write("<a href='registrarImagenServlet.jsp'>");
                        out.write("<button>Registrar otra imagen</button></a>");

                        out.write("<a href='menuServlet.jsp'>");
                        out.write("<button>Volver al menú</button></a>");
                    }
                    else {
                        out.write("<h2>Ha habido un error subiendo la imagen</h2>");

                        out.write("<a href='registrarImagenServlet.jsp'>");
                        out.write("<button>Registrar otra imagen</button></a>");

                        out.write("<a href='menuServlet.jsp'>");
                        out.write("<button>Volver al menú</button></a>");
                    }
                }
                else {
                    out.write("<h2>Para poder subir una imagen se tiene que iniciar sesion</h2>");

                    out.write("<a href='loginServlet.jsp'>");
                    out.write("<button>Iniciar sesion</button></a>");
                }
            }
            else{
                out.write("<h2>Para poder subir una imagen se tiene que iniciar sesion</h2>");

                out.write("<a href='loginServlet.jsp'>");
                out.write("<button>Iniciar sesion</button></a>");
            
            }
        }
        catch(Exception e){
            response.sendRedirect("errorServlet.jsp");
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
    
    private int UploadImage(String filename, String Picture){
    
        
       try { // Call Web Service Operation
            ClientServlet.ServeiWeb port = service.getServeiWebPort();
            
            // TODO process result here
            int result = port.uploadImage(filename,Picture);
            return result;
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return 0;

    }
}
