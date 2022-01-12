/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4AD;

import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Base64;


/**
 *
 * @author alumne
 */
public class funcionsBD {
    
    public int RegisterImage(String titulo, String descripcion, String keywords, String autor, String creador, String fechaC, String fechaA, String filename, String file){
        
        String query;
        PreparedStatement statement;
        Connection connection = null;
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            query = "INSERT INTO IMAGE (TITLE, DESCRIPTION, KEYWORDS, AUTHOR, CREATOR, CAPTURE_DATE, STORAGE_DATE, FILENAME) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            
            
            statement = connection.prepareStatement(query);
            statement.setString(1, titulo);
            statement.setString(2, descripcion);
            statement.setString(3, keywords);
            statement.setString(4, autor);
            statement.setString(5, creador);
            statement.setString(6, fechaC);
            statement.setString(7, fechaA);
            statement.setString(8, filename);
            

            
            statement.executeUpdate();
            
    
            return 1;
            
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
        return 0;
    }
    
    
    public int DeleteImage(int id){
    
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs = null;
       
        String filename = new String();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
          
            statement = connection.prepareStatement("DELETE FROM IMAGE WHERE ID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            return id;
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
            
        return 0;
        
    }
    
    
    public List ListImages(){
    
        //String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs;
        List<Image> lista = new ArrayList<Image>();
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE");
            rs = statement.executeQuery();
            
            while (rs.next()){
                Image im = new Image();
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                im.setFilename(rs.getString("FILENAME")) ;
                im.setKeywords(rs.getString("KEYWORDS"));
                im.setTitulo(rs.getString("TITLE"));
                im.setId(rs.getInt("ID"));
                lista.add(im);
            }
            return lista;
           
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
            
        return lista;
    }
    
    
    public Image SearchbyId(int id){
       
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs;
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE where ID = ?");
            statement.setInt(1,id);          
            rs = statement.executeQuery();
            
            Image im = new Image();
            while (rs.next()){
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                im.setKeywords(rs.getString("KEYWORDS"));
                im.setFilename(rs.getString("FILENAME")) ;
                im.setTitulo(rs.getString("TITLE"));
                im.setId(rs.getInt("ID"));
                
            }
            
            
            
            return im;
         
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
        
    
        return null;
    
    }
    
    
    public List SearchbyTitle(String title){
    
        //String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs;
        List<Image> lista = new ArrayList<Image>();
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE WHERE TITLE like ?");
            statement.setString(1,"%" + title + "%");  
            rs = statement.executeQuery();
            
            while (rs.next()){
                Image im = new Image();
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                im.setFilename(rs.getString("FILENAME")) ;
                im.setKeywords(rs.getString("KEYWORDS"));
                im.setTitulo(rs.getString("TITLE"));
                im.setId(rs.getInt("ID"));
                lista.add(im);
            }
           
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
            
        return lista;
    }
    
    
    public List SearchbyCreaDate(String creaDate){
    
        //String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs;
        List<Image> lista = new ArrayList<Image>();
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE WHERE STORAGE_DATE like ?");
            statement.setString(1,"%"+creaDate+"%");  
            rs = statement.executeQuery();
            
            while (rs.next()){
                Image im = new Image();
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                im.setKeywords(rs.getString("KEYWORDS"));
                im.setFilename(rs.getString("FILENAME")) ;
                im.setTitulo(rs.getString("TITLE"));
                im.setId(rs.getInt("ID"));
                lista.add(im);
            }
           
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
            
        return lista;
    }
    
    
    public List SearchbyKeywords(String keywords){
    
        //String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs;
        List<Image> lista = new ArrayList<Image>();
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE WHERE KEYWORDS like ?");
            statement.setString(1,"%" + keywords + "%");  
            rs = statement.executeQuery();
            
            while (rs.next()){
                Image im = new Image();
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                im.setFilename(rs.getString("FILENAME")) ;
                im.setKeywords(rs.getString("KEYWORDS"));
                im.setTitulo(rs.getString("TITLE"));
                im.setId(rs.getInt("ID"));
                lista.add(im);
            }
           
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
            
        return lista;
    }
    
    
    public List SearchbyAuthor(String author){
    
        //String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs;
        List<Image> lista = new ArrayList<Image>();
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE WHERE AUTHOR like ?");
            statement.setString(1,"%" + author + "%");  
            rs = statement.executeQuery();
            
            
            while (rs.next()){
                Image im = new Image();
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                im.setFilename(rs.getString("FILENAME")) ;
                im.setKeywords(rs.getString("KEYWORDS"));
                im.setTitulo(rs.getString("TITLE"));
                im.setId(rs.getInt("ID"));
                lista.add(im);
            }
           
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
            
        return lista;
    }
    
    
    public int ModifyImage(Image image){
        int control = DeleteImage(image.getId());
        if (control != 0){
            RegisterImage(image.getTitulo(), image.getDescripcion(), image.getKeywords(), image.getAutor(), image.getCreator(), image.getFechaC(), image.getFechaA(), image.getFilename(), image.getFilename());
            return image.getId();
        }
        return 0;
    }
    
    
    public boolean ComprovarCredencials(String username, String password){
        String query;
        PreparedStatement statement;
        Connection connection = null;
        boolean result = false;
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            query = "select * from usuarios where ID_USUARIO = ? and PASSWORD = ?";
            
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet rs = statement.executeQuery();
   
            result = rs.next();
            
            return result;
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
        
        
        return result;
    }
    
    
    public boolean AfegirUsuari(String username, String password){
        String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs;
        boolean result = true;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            query = "SELECT * FROM USUARIOS WHERE ID_USUARIO = ?";
            
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            rs = statement.executeQuery();
            
            if (rs.next()== false){
                 query = "INSERT INTO USUARIOS (ID_USUARIO, PASSWORD)"
                    + "VALUES (?, ?)";
            
                statement = connection.prepareStatement(query);
                statement.setString(1, username);
                statement.setString(2, password);

                statement.executeUpdate();
            }
            else {
                result = false;
            }
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
        return result;
    }
    
    
    public List SearchComplete(String titulo, String descripcion, String keywords, String autor, String creador, String fechaC, String fechaA, String nombreF ){
        String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs;
        List<Image> lista = new ArrayList<Image>();
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE where TITLE like ? and DESCRIPTION like  ? and KEYWORDS like ? and AUTHOR like ? and CREATOR like ?  and CAPTURE_DATE like ? and STORAGE_DATE like ? and FILENAME like ?");
            statement.setString(1, "%" + titulo + "%");
            statement.setString(2, "%" + descripcion + "%");
            statement.setString(3, "%" + keywords + "%");
            statement.setString(4, "%" + autor + "%");
            statement.setString(5, "%" + creador + "%");
            statement.setString(6, "%" + fechaC + "%");
            statement.setString(7, "%" + fechaA + "%");
            
            rs = statement.executeQuery();
             
            while (rs.next()){
                Image im = new Image();
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                
                im.setKeywords(rs.getString("KEYWORDS"));
                im.setTitulo(rs.getString("TITLE"));
                im.setId(rs.getInt("ID"));
                lista.add(im);
            }
            return lista;
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        finally{
            if (connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                    //response.sendRedirect("error.jsp");
                }
            }
        }
        
    
        return lista;
    
    }
    
    public int upload(String file, String filename){
    
            
        byte[] decodedBytes = Base64.getUrlDecoder().decode(file);
         
        try
        {
            //FileUtils.writeByteArrayToFile(new File(path), decodedBytes);
            FileOutputStream os = new FileOutputStream("/home2/users/alumnes/1240358/NetBeansProjects/lab4AD/src/main/resources/imagenes/"+filename);
            os.write(decodedBytes);
        }
        catch(Exception e){
        }
        
        return 1;
            
    }
    
    public boolean filenameCheck(String filename){
    
        String query;
        PreparedStatement statement;
        Connection connection = null;
        boolean result = false;
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            query = "select * from IMAGE where FILENAME = ?";
            
            statement = connection.prepareStatement(query);
            statement.setString(1, filename);
            
            
            ResultSet rs = statement.executeQuery();
   
            result = rs.next();
            
            return !result;
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        return false;
    }
}
