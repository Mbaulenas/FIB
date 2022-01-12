/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3AD;

import java.io.FileOutputStream;
import java.util.Base64;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.util.List;
import javax.activation.DataHandler;

/**
 *
 * @author alumne
 */
@WebService(serviceName = "ServeiWeb")
public class ServeiWeb {
    public funcionsBD aux = new funcionsBD();
   /* Registrar una nueva imagen */
    
    public int uploadImage(String filename, String Picture){
        return aux.uploadImage(filename, Picture);
    }
 
    public int RegisterImage (Image image){
        
        return aux.RegisterImage(image.getTitulo(), image.getDescripcion(), image.getKeywords(), image.getAutor(), image.getCreator(), image.getFechaC(), image.getFechaA(), image.getFilename());
    }
    
    public Image DownloadImage(int id){
        return aux.downloadImage(id);
    }

    /* Modificar una imagen existente */ 

    public int ModifyImage (Image image){
        return aux.ModifyImage(image);
    }
    /* Borrar una imagen existente */ 

    public int DeleteImage (int id){
        return aux.DeleteImage(id);
    } 

    /* Listar las imágenes en el Sistema */ 

    public List ListImages (){
        return aux.ListImages();
    }

    /* Devuelve la imagen identificada por id */ 

    public Image SearchbyId (int id){
        return aux.SearchbyId(id);
    } 

    /* Devuelve la lista de imágenes que contienen title en su título */ 

    public List SearchbyTitle (String title){
        return aux.SearchbyTitle(title);
    } 

    /*  Devuelve  la  lista  de  imágenes  de  una  fecha  determinada  (puede  ser  
    dd/mm/aaaa, mm/aaaa, dd/mm/aaaa). Podéis elegir entre la fecha de creación o 
    la de captura. Si es la de captura, cambiad la cabecera y el parámetro */ 

    public List SearchbyCreaDate (String creaDate){
        return aux.SearchbyCreaDate(creaDate);
    } 

    /*  Devuelve la  lista de  imágenes que  contienen  keywords  entre  sus  palabras  
    clave */ 

    public List SearchbyKeywords (String keywords){
        return aux.SearchbyKeywords(keywords);
    } 

    /* Devuelve la lista de imágenes con autor (o creador) igual al parámetro que 
    se pasa a la operación. Si lo hacéis por creador, cambiad la cabecera y el 
    parámetro */ 

    public List SearchbyAuthor (String author){
        return aux.SearchbyAuthor(author);
    }
    
    
    /*############################################################*/
    
    
    public boolean ComprovarCredencials(String username, String password){
        return aux.ComprovarCredencials(username, password);
    }
    
    
    public boolean AfegirUsuari(String username, String password){
        return aux.AfegirUsuari(username, password);
    }
    
   
    public List SearchComplete(String titulo, String descripcion, String keywords, String autor, String creador, String fechaC, String fechaA, String nombreF ){
        return aux.SearchComplete(titulo, descripcion, keywords, autor, creador, fechaC, fechaA, nombreF);
    }
}
