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
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author alumne
 */
@WebServlet(name = "registrarImagenRemot", urlPatterns = {"/registrarImagenRemot"})
@MultipartConfig
public class registrarImagenRemot extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/10.10.73.48_8080/Practica0503/Practica03WS.wsdl")
    private Practica03WS_Service service;
    //private static Logger logger = Logger.getLogger(registrarImagenRemot.class);

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
        try(PrintWriter out = response.getWriter()){
                HttpSession misesion = (HttpSession) request.getSession(true);
                if (misesion != null){
 
                    
                if(misesion.getAttribute("username") != null){
                    Part filePart = request.getPart("file");
                    String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    InputStream fileContent = filePart.getInputStream();
                    byte[] imageBytes = new byte[(int)filePart.getSize()];
                    fileContent.read(imageBytes, 0, imageBytes.length);
                    fileContent.close();
                    String s = Base64.getEncoder().encodeToString(imageBytes);
                    
                    String username = (String) misesion.getAttribute("username");
                    String pattern = "yyyy/MM/dd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String date = simpleDateFormat.format(new Date());

                    Image image = new Image();
                    image.setTitle(request.getParameter("titulo"));
                    image.setDescription(request.getParameter("descripcion"));
                    image.setKeywords(request.getParameter("keywords"));
                    image.setAuthor(request.getParameter("autor"));
                    image.setCreator(username);
                    image.setCaptureDate(request.getParameter("fechaC"));
                    image.setStorageDate(date);
                    image.setFilename(filename);
                    //image.setFile(s);


                    int control = RegisterImage(image);
                    int control2 = UploadImage(s, filename);

                    if (control != 0 && control2 != 0){

                        out.write("<h2> Imagen registrada correctamente</h2>");

                        out.write("<a href='registrarImagenRemot.jsp'>");
                        out.write("<button>Registrar otra imagen</button></a>");

                        out.write("<a href='menuRemot.jsp'>");
                        out.write("<button>Volver al menú</button></a>");
                    }
                    else {
                        out.write("<h2>Ha habido un error subiendo la imagen</h2>");

                        out.write("<a href='registrarImagenRemot.jsp'>");
                        out.write("<button>Registrar otra imagen</button></a>");

                        out.write("<a href='menuRemot.jsp'>");
                        out.write("<button>Volver al menú</button></a>");
                    }
                }
                else {
                    out.write("<h2>Para poder subir una imagen se tiene que iniciar sesion</h2>");

                    out.write("<a href='loginRemot.jsp'>");
                    out.write("<button>Iniciar sesion</button></a>");
                }
            }
            else{
                out.write("<h2>Para poder subir una imagen se tiene que iniciar sesion</h2>");

                out.write("<a href='loginRemot.jsp'>");
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
    
    
    private int RegisterImage(Image image){
        
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
            // TODO process result here
            int result = port.registerImage(image);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return 0;
    }
    
    private int UploadImage (String data, String filename){
        try { // Call Web Service Operation
            Practica03WS port = service.getPractica03WSPort();
            // TODO process result here
            int result = port.uploadImage(data,filename);
            return result;
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        return 0;
    }
    

}
