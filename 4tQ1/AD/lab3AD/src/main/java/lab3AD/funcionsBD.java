/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3AD;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    
    public int uploadImage(String filename, String Picture){
        
       byte[] decode = Base64.getDecoder().decode(Picture);
        try{
            FileOutputStream os = new FileOutputStream("/home2/users/alumnes/1240358/NetBeansProjects/lab3AD/src/main/webapp/imagenes/" + filename);
            os.write(decode);
            return 1;
        }catch (Exception e){
        }
        return 0;
    }
    
    public Image downloadImage(int id){
        
        Image image = SearchbyId(id);
        //String path = "/home/alumne/NetBeansProjects/lab3AD/src/main/webapp/imagenes/" + image.getFilename();
        File file = new File ("/home2/users/alumnes/1240358/NetBeansProjects/lab3AD/src/main/webapp/imagenes/" + image.getFilename() );
        
        try{
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String s = Base64.getEncoder().encodeToString(fileContent);
            image.setFile(s);
            return image;
        }catch(Exception e){
        }
        return null;
    }
    
    public int RegisterImage(String titulo, String descripcion, String keywords, String autor, String creador, String fechaC, String fechaA, String nombreF){
        
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
            statement.setString(8, nombreF);

            
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
        Image image = SearchbyId(id);
        File del = new File ("/home2/users/alumnes/1240358/NetBeansProjects/lab3AD/src/main/webapp/imagenes/" + image.getFilename());
        del.delete();
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
                im.setFilename(rs.getString("FILENAME"));
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
                im.setFilename(rs.getString("FILENAME"));
                im.setKeywords(rs.getString("KEYWORDS"));
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
                im.setFilename(rs.getString("FILENAME"));
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
            statement.setString(1,"%" + creaDate + "%");  
            rs = statement.executeQuery();
            
            while (rs.next()){
                Image im = new Image();
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                im.setFilename(rs.getString("FILENAME"));
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
                im.setFilename(rs.getString("FILENAME"));
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
                im.setFilename(rs.getString("FILENAME"));
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
        
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs = null;
        String query;
        //En principi crec que haure de fer un select dels camps de la imatge a modificar per quedar-me el filename
        //Despres haure d'eliminar aquesta entrada de la base de dades
        //Per ultim afegir la nova entrada amb el filename de l'antiga pero amb tots els camps nous.
        //crec qeu amb aixo ja estara pero ja ho veurem dema
        
        Image im = SearchbyId(image.getId());
        image.setFilename(im.getFilename());
        
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
          
            statement = connection.prepareStatement("DELETE FROM IMAGE WHERE ID = ?");
            statement.setInt(1, im.getId());
            statement.executeUpdate();
            
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            query = "INSERT INTO IMAGE (TITLE, DESCRIPTION, KEYWORDS, AUTHOR, CREATOR, CAPTURE_DATE, STORAGE_DATE, FILENAME) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            
            
            statement = connection.prepareStatement(query);
            statement.setString(1, image.getTitulo());
            statement.setString(2, image.getDescripcion());
            statement.setString(3, image.getKeywords());
            statement.setString(4, image.getAutor());
            statement.setString(5, image.getCreator());
            statement.setString(6, image.getFechaC());
            statement.setString(7, image.getFechaA());
            statement.setString(8, image.getFilename());

            
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
        
        
        //int control = DeleteImage(image.getId());
        
        
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
            statement.setString(8, "%" + nombreF + "%");
            rs = statement.executeQuery();
             
            while (rs.next()){
                Image im = new Image();
                im.setAutor(rs.getString("AUTHOR"));
                im.setCreator(rs.getString("CREATOR"));
                im.setDescripcion(rs.getString("DESCRIPTION"));
                im.setFechaA(rs.getString("STORAGE_DATE"));
                im.setFechaC(rs.getString("CAPTURE_DATE")) ;
                im.setFilename(rs.getString("FILENAME"));
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
}
