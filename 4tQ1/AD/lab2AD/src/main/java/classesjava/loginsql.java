/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 *
 * @author alumne
 */
public class loginsql {
    
    public boolean ComprovaCredencials(String username, String passwd){
        
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
            statement.setString(2, passwd);
            
            ResultSet rs = statement.executeQuery();
    
            
            result = rs.next();
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        
        return result;
       
    }
    
    public void AfegirEntrada(String titulo, String descripcion, String keywords, String autor, String creador, String fechaC, String fechaA, String nombreF){
        
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
    
            
            
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        
        
    }
    
    
    public ResultSet Busqueda(String titulo, String keywords, String autor, String creador, String fechaC, String fechaA, String nombreF){
        
        String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs = null;
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE where TITLE like ? and KEYWORDS like ? and AUTHOR like ? and CREATOR like ?  and CAPTURE_DATE like ? and STORAGE_DATE like ? and FILENAME like ?");
            statement.setString(1, "%" + titulo + "%");
            statement.setString(2, "%" + keywords + "%");
            statement.setString(3, "%" + autor + "%");
            statement.setString(4, "%" + creador + "%");
            statement.setString(5, "%" + fechaC + "%");
            statement.setString(6, "%" + fechaA + "%");
            statement.setString(7, "%" + nombreF + "%");
            rs = statement.executeQuery();
            

            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }      
        
    
        return rs;
    
    }
    
    
    public ResultSet Lista(){
    
        String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs = null;
       
        
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE");
            rs = statement.executeQuery();
            
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
            
        return rs;
    }
    
    public String Eliminar(String id){
        
        String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs = null;
       
        String filename = new String();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE WHERE ID = ?");
            statement.setString(1, id);
            rs = statement.executeQuery();
            
            rs.next();
            filename = rs.getString("FILENAME");
            
            statement = connection.prepareStatement("DELETE FROM IMAGE WHERE ID = ?");
            statement.setString(1, id);
            statement.executeUpdate();
            
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
            
        return filename;
        
    }
    
    public String idtofilename  (String id){
    
        String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs = null;
        String name = new String();
       
        String filename = new String();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE WHERE ID = ?");
            statement.setString(1, id);
            rs = statement.executeQuery();
            
            if(rs.next()) name = rs.getString("FILENAME"); 
            else name = "empty";
            
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        
        return name;
    }
    
    public ResultSet idtoentry(String id){
    
        String query;
        PreparedStatement statement;
        Connection connection = null;
        ResultSet rs = null;
        
      
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/login;user=login;password=login");
            
            statement = connection.prepareStatement("SELECT * FROM IMAGE WHERE ID = ?");
            statement.setString(1, id);
            rs = statement.executeQuery();
            

            
            
        }catch(Exception e1){
            System.err.println(e1.getMessage());
        }
        
        
        return rs;
    }
    
}
