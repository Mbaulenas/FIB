/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author alumne
 */
@WebServlet(name = "list", urlPatterns = {"/list"})
public class list extends HttpServlet {

   

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,List list )
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession misesion = (HttpSession) request.getSession(false);
            
            if (misesion != null){
                String username = (String) misesion.getAttribute("username");
                
                if (misesion.getAttribute("username") != null){
                    
                    if (!list.isEmpty()){
                
                        out.write("<div>");  
                        out.write("<a href=\"menuServlet.jsp\">");
                        out.write("<button>Volver al menú</button>"); 
                        out.write(" </a>");
                        out.write("</div>");

                        out.write("<table>");
                        out.write("<tr>");

                            out.write("<th>Titulo</th>");
                            out.write("<th>Descripcion</th>");
                            out.write("<th>Keywords</th>");
                            out.write("<th>Autor</th>");
                            out.write("<th>Creador</th>");
                            out.write("<th>Fecha creacion</th>");
                            out.write("<th>Fecha subida</th>");
                            out.write("<th>Filename</th>");
                            out.write("<th>Modificable</th>");
                            out.write("<th>Eliminable</th>");
                            out.write("<th>Descargar imagen</th>");

                        out.write("</tr>");

                        for(Iterator<Image> it = list.iterator(); it.hasNext();){
                            Image i = it.next();

                            out.write("<tr>");
                                out.write("<td>" + i.getTitulo() + "</td>");
                                out.write("<td>" + i.getDescripcion() + "</td>");
                                out.write("<td>" + i.getKeywords() + "</td>");
                                out.write("<td>" + i.getAutor() + "</td>");
                                out.write("<td>" + i.getCreator() + "</td>");
                                out.write("<td>" + i.getFechaC() + "</td>");
                                out.write("<td>" + i.getFechaA() + "</td>");
                                out.write("<td>" + i.getFilename() + "</td>");

                                if(username.equals(i.creator)){

                                    out.write("<td><a href=\"modificarImagen.jsp?param="+i.id+"\">\n" +  //S HA DE CANVIAR EL LINK
                                              "<button>Modificar</button>\n" +
                                              "</a></td>");

                                    out.write("<td><a href=\"eliminarImagen.jsp?param="+i.id+"\">\n" +  //S HA DE CANVIAR EL LINK
                                              "<button>Eliminar</button>\n" +
                                              "</a></td>");
                                    
                                    out.write("<td><a href=\"http://10.10.73.48:8080/Practica04_REST_WS/resources/javaee8/download/"+i.id+"\">\n" +  //S HA DE CANVIAR EL LINK
                                              "<button>Descargar</button>\n" +
                                              "</a></td>");
                                }
                            out.write("</tr>");
                        }
                    out.write("</table>");

                    }
                    else{

                        out.write("<h1>No hay imagenes para mostrar</h1>");
                        out.write("<div>");
                        out.write("<td><a href=\"menuServlet.jsp\">\n" +  
                                              "<button>Volver al menú</button>\n" +
                                              "</a></td>");
                        out.write("</div>");
                    }

                }
                else {
                    out.write("<h1>Es necesario iniciar sesion</h1>");
                    out.write("<div>");
                    out.write("<td><a href=\"loginServlet.jsp\">\n" +  
                                "<button>Iniciar sesion</button>\n" +
                                "</a></td>");
                    out.write("</div>");
                }
             
            }
            else {
            
                out.write("<h1>Error, vulve a iniciar se</h1>");
                out.write("<div>");
                out.write("<td><a href=\"loginServlet.jsp\">\n" +  
                                "<button>Iniciar sesion</button>\n" +
                                "</a></td>");
                out.write("</div>");
            }
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
        //processRequest(request, response);
        try{
                    
            String aux = "http://10.10.73.48:8080/Practica04_REST_WS/resources/javaee8/list";

            URL url = new URL(aux);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
                //conn.setRequestProperty("Accept","");
                
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String content = null;
            StringBuilder sb = new StringBuilder();
            while((content = in.readLine()) != null) sb.append(content);
            String aux2 = sb.toString();
                  
            JSONArray array = new JSONArray();
            JSONObject obj = new JSONObject(aux2);
            array = obj.getJSONArray("llistaimatges"); 
            List<Image> list = new ArrayList<Image>();
                  
                  
                  
          for(int i=0; i<array.length(); i++){
              Image im = new Image();
              JSONObject object = array.getJSONObject(i);
              im.setTitulo(object.getString("Title"));
              im.setDescripcion(object.getString("Description"));
              im.setKeywords(object.getString("Keywords"));
              im.setAutor(object.getString("Author"));
              im.setCreator(object.getString("Creator"));
              im.setFechaC(object.getString("Capture date"));
              im.setFechaA(object.getString("Storage date"));
              im.setFilename(object.getString("Filename"));
              im.setId(Integer.parseInt(object.getString("Id")));
              list.add(im);
          }   
              conn.disconnect();
              
              processRequest(request,response,list);
            
        }catch(Exception e){
        
        }
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
        
            
            //control de sesions igual hauria d'estr aqui per bloqueja si no ets user
            
            //List list = ListImages();
            
            
            
        
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
