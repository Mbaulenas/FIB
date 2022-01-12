/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author alumne
 */
@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {


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
        int control = 0;
        try(PrintWriter out = response.getWriter()){
            HttpSession misesion = (HttpSession) request.getSession(true);
            if (misesion != null){
                
                if(misesion.getAttribute("username") != null){
                    
                    String username = (String) misesion.getAttribute("username");
                    URL url = new URL("http://10.10.73.48:8080/Practica04_REST_WS/resources/javaee8/register");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    
                    Part filePart = request.getPart("file");
                    String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    InputStream fileContent = filePart.getInputStream();
                    byte[] imageBytes = new byte[(int)filePart.getSize()];
                    fileContent.read(imageBytes, 0, imageBytes.length);
                    fileContent.close();
                    Upload(imageBytes, filename);
                    
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
                    
                    String params = "title="+request.getParameter("title")+
                                    "&description="+request.getParameter("description")+
                                    "&keywords="+request.getParameter("keywords")+
                                    "&author="+request.getParameter("author")+
                                    "&creator="+username+
                                    "&capture="+request.getParameter("capture")+
                                    "&filename="+filename;
                    
                        writer.write(params);
                        writer.flush();
                        writer.close();
                        os.close();
                    

                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);

                    String output;
                    
                    if(conn.getResponseCode() == 200){

                        while((output=br.readLine()) != null){

                            if(output.equals("okey")) control = 1;
                            


                        }

                    } 

                    else out.println("DEAD");
                
                
                
                    conn.disconnect();
                    
                    //upload iamge
                    
                    
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
    
    public void Upload(byte[] imageBytes, String filename){
    
            try{
                URL url = new URL("http://10.10.73.48:8080/Practica04_REST_WS/resources/javaee8/upload");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                
                
                String encoded = Base64.getUrlEncoder().encodeToString(imageBytes);
                
                
//                OutputStream os = conn.getOutputStream();
//                os.write(encoded.getBytes());
//                os.flush();
                
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
                String params = "file="+encoded+"&filename="+filename;
                writer.write(params);

                writer.flush();
                writer.close();
                os.close();
                
                
                
                
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);

                String output;

                if(conn.getResponseCode() == 200){

                    while((output=br.readLine()) != null){

                        if(output.equals("OK")){

                           
                        }



                    }

                } 

                    
                    in.close();
                
                
                    conn.disconnect();
                    
            }catch(Exception e){
            
            }
            
    }
    
    public String filenameCheck(String filename){
    
        try{
        String aux = "http://localhost:8080/lab4AD/resources/javaee8/filenameCheck/"+filename;

        URL url = new URL(aux);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept","");

        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
       
        String output;
        String aux2 = new String();

            if(conn.getResponseCode() == 200){

               while((output=br.readLine()) != null){
                   if(output.length()>0){
                        aux2+=output;
                    }
               }
            }
        return aux2;
        }catch(Exception e){
            
        }
        return null;
    }
    
}
