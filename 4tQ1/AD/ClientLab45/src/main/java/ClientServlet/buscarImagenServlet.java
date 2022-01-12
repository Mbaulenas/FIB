/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientServlet;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 *
 * @author alumne
 */
@WebServlet(name = "buscarImagenServlet", urlPatterns = {"/buscarImagenServlet"})
public class buscarImagenServlet extends HttpServlet {


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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, List list)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession misesion = (HttpSession) request.getSession(false);
            
            if (misesion != null){
            
                if(misesion.getAttribute("username") != null){
                    String username = (String) misesion.getAttribute("username");
                
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
                            out.write("<th>Modificable</th>");
                            out.write("<th>Eliminable</th>");

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
                                

                                if(username.equals(i.creator)){

                                    out.write("<td><a href=\"modificarImagenServlet.jsp?param="+i.id+"\">\n" +  //S HA DE CANVIAR EL LINK
                                              "<button>Modificar</button>\n" +
                                              "</a></td>");

                                    out.write("<td><a href=\"eliminarImagenServlet.jsp?param="+i.id+"\">\n" +  //S HA DE CANVIAR EL LINK
                                              "<button>Eliminar</button>\n" +
                                              "</a></td>");
                                }
                            out.write("</tr>");
                        }
                    out.write("</table>");

                    }
                    else{

                        out.write("<h1>La búsqueda no ha encontrado resultados</h1>");
                        out.write("<div>");
                        out.write("<td><a href=\"menuServlet.jsp\">\n" +  
                                              "<button>Volver al menú</button>\n" +
                                              "</a></td>");

                        out.write("<td><a href=\"buscarImagenServlet.jsp\">\n" +  
                                              "<button>Realizar otra búsqueda</button>\n" +
                                              "</a></td>");
                        out.write("</div>");
                    }
             
                }
                else{
                    out.write("<h1>Es necesario iniciar sesion</h1>");
                    out.write("<div>");
                    out.write("<td><a href=\"loginServlet.jsp\">\n" +  
                                "<button>Iniciar sesion</button>\n" +
                                "</a></td>");
                    out.write("</div>");
                }
            }
            else {
            
                out.write("<h1>Error, vuelva a iniciar sesion</h1>");
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
        List<Image> list = new ArrayList();
        
        if (request.getParameter("ident") != null){
            int id = Integer.parseInt(request.getParameter("id"));
            Image image = SearchbyId(id);
            if (image.getTitulo()!=null) list.add(image);
        }
        else if (request.getParameter("title") != null){
            list = SearchbyTitle(request.getParameter("titulo2"));
        }
        else if (request.getParameter("fecha") != null){
            list = SearchbyCreaDate(request.getParameter("fechaC2"));
        }
        else if (request.getParameter("key") != null){
            list = SearchbyKeywords(request.getParameter("keywords2"));
        }
        else if (request.getParameter("aut") != null){
            list = SearchbyAuthor(request.getParameter("autor2"));
        }
        processRequest(request, response, list);
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
    }// </editor-fold>

    
    /*##############################################################*/
    
    
    
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
    

    private List SearchbyTitle(String title){
        
        try{
                    
            String aux = "http://localhost:8080/lab4AD/resources/javaee8/searchTitle/"+title;

            URL url = new URL(aux);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                //conn.setRequestProperty("Accept","");
                
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                List<Image> list = new ArrayList<Image>();
                String output;
                String aux2 = new String();
                
                if(conn.getResponseCode() == 200){
                
                   while((output=br.readLine()) != null){
                       if(output.length()>0){
                            aux2+=output;
                        }
                   }
                    
                  Type tipus = new TypeToken<ArrayList<Image>>(){}.getType();
                  Gson gson = new Gson();
                  list = gson.fromJson(aux2, tipus);      
                  
                
                    
                } 
                
                
                
                
                
            conn.disconnect();
            return list;
            
        }catch(Exception e){
        
        }
        return null;
    }
    
    
    private List SearchbyCreaDate(String CreaDate){
        
        try{
                    
            String aux = "http://localhost:8080/lab4AD/resources/javaee8/searchCreationDate/"+CreaDate;

            URL url = new URL(aux);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept","");
                
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            List<Image> list = new ArrayList<Image>();
            String output;
            String aux2 = new String();
                
                if(conn.getResponseCode() == 200){
                
                   while((output=br.readLine()) != null){
                       if(output.length()>0){
                            aux2+=output;
                        }
                   }
                    
                  Type tipus = new TypeToken<ArrayList<Image>>(){}.getType();
                  Gson gson = new Gson();
                  list = gson.fromJson(aux2, tipus);      
                  
                
                    
                } 
                
                
                
                
                
            conn.disconnect();
            return list;
            
        }catch(Exception e){
        
        }
        return null;
    }
    
    
    private List SearchbyKeywords(String keywords){
        
        try{
                    
            String aux = "http://localhost:8080/lab4AD/resources/javaee8/searchKeywords/"+keywords;

            URL url = new URL(aux);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept","");
                
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            List<Image> list = new ArrayList<Image>();
            String output;
            String aux2 = new String();
                
                if(conn.getResponseCode() == 200){
                
                   while((output=br.readLine()) != null){
                       if(output.length()>0){
                            aux2+=output;
                        }
                   }
                    
                  Type tipus = new TypeToken<ArrayList<Image>>(){}.getType();
                  Gson gson = new Gson();
                  list = gson.fromJson(aux2, tipus);      
                  
                
                    
                } 
                
            conn.disconnect();
            return list;
            
        }catch(Exception e){
        
        }
        return null;
    }
    
    
    private List SearchbyAuthor(String author){
        
        try{
                    
            String aux = "http://localhost:8080/lab4AD/resources/javaee8/searchAuthor/"+author;

            URL url = new URL(aux);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            //conn.setRequestProperty("Accept","");
                
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            List<Image> list = new ArrayList<Image>();
            String output;
            String aux2 = new String();
                
                if(conn.getResponseCode() == 200){
                
                   while((output=br.readLine()) != null){
                       if(output.length()>0){
                            aux2+=output;
                        }
                   }
                    
                  Type tipus = new TypeToken<ArrayList<Image>>(){}.getType();
                  Gson gson = new Gson();
                  list = gson.fromJson(aux2, tipus);      
                  
                
                    
                } 
                
            conn.disconnect();
            return list;
            
        }catch(Exception e){
        
        }
    
        return null;
    }

    }
