package com.mycompany.lab4ad.resources;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lab4AD.*;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response.ResponseBuilder;



/**
 *
 * @author 
 */
@Path("javaee8")
public class JavaEE8Resource {
    
    public funcionsBD funcionsBD = new funcionsBD();
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
    
    
        /**
    * POST method to upload an image sisi
     * @param file    
     * @param filename    
    * @return*/
    @Path("upload")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String upload (@FormParam("file")String file, @FormParam("filename")String filename){
    
            
            int aux = funcionsBD.upload(file, filename);
            
            if(aux!=0) return "OK";
            else return "KO";
    }
    
    
    @GET
    @Path("/download/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") int id) {
        Image im = funcionsBD.SearchbyId(id);
        String filename = im.getFilename();
        File fileDownload = new File("/home2/users/alumnes/1240358/NetBeansProjects/lab4AD/src/main/resources/imagenes/"+filename);
        ResponseBuilder response = Response.ok((Object) fileDownload);
        response.header("Content-Disposition", "attachment;filename=" + filename);
        return response.build();
    }
    
    @GET
    @Path("/filenameCheck/{filename}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public String filenameCheck(@PathParam("filename") String filename) {
        //aquest codi no va aqui
        boolean fnc = funcionsBD.filenameCheck(filename);
        int fncaux = 0;

        while(!fnc){
            fncaux++;
            fnc = funcionsBD.filenameCheck(fncaux + filename);
        }
        if(fncaux!=0) filename = fncaux + filename;
        
        return filename;
    }
    
    /*
* POST method to register a new image
* @param title
* @param description
* @param keywords
* @param author
* @param creator
* @param capt_date
* @return
*/
@Path("register")
@POST
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_HTML)
public String registerImage (@FormParam("title") String title, @FormParam("description") String description, @FormParam("keywords") String keywords, @FormParam("author") String author, @FormParam("creator") String creator, @FormParam("capture") String capt_date ,String file, @FormParam("filename") String filename){

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        
        //el segon stordate ha de ser captdate quan sapiguem ferho
        int aux = funcionsBD.RegisterImage(title, description, keywords, author, creator, capt_date, date, filename, file);
        
        
        if(aux==1) return "OK";
        else return "KO";
        
       
}

    /**
    * POST method to modify an existing image
    * @param title
    * @param description
    * @param keywords
    * @param author
    * @param creator
    * @param capt_date
    * @return
    */
    @Path("modify")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String modifyImage (@FormParam("id") String id,
    @FormParam("title") String title,
    @FormParam("description") String description,
    @FormParam("keywords") String keywords,
    @FormParam("author") String author,
    @FormParam("creator") String creator,
    @FormParam("capture") String capt_date){

            

            Image im= funcionsBD.SearchbyId(Integer.parseInt(id));
            
            im.setId(Integer.parseInt(id));
            im.setTitulo(title);
            im.setDescripcion(description);
            im.setKeywords(keywords);
            im.setAutor(author);
            im.setCreator(creator);
            im.setFechaC(capt_date);
            
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            
            im.setFechaA(date);

            int aux = funcionsBD.ModifyImage(im);

            if(aux!=0) return "OK";
            else return "KO";

    }
    
    /**
    * POST method to delete an existing image
    * @param id
    * @return*/
    @Path("delete")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String deleteImage (@FormParam("id") String id){
    
            Image im = funcionsBD.SearchbyId(Integer.parseInt(id));
            String filename = im.getFilename();
            
            int aux = funcionsBD.DeleteImage(Integer.parseInt(id));
            
            if(aux!=0){
                
                
                try {
                    Files.delete(Paths.get("/home/alumne/NetBeansProjects/lab4AD/src/main/resources/imagenes/"+filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "OK";
            }
            
            
            
            
            
            return "KO";
    }
    
    /**
    * GET method to list images
    * @return
    */
    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String listImages (){
    
            String test = "OK";
            
            List<Image> list = funcionsBD.ListImages();
            
            Gson gson = new Gson();
            
            String res = gson.toJson(list);
            
            return res;
            
            
    }
    
    /**
    * GET method to search images by id
    * @param id
    * @return
    */
    @Path("searchID/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String searchByID (@PathParam("id") int id){
    
            
            
            Image im = funcionsBD.SearchbyId(id);
            
            Gson gson = new Gson();
            
            String res = gson.toJson(im);
            
            return res;
            
            
    }
    
    /**
    * GET method to search images by title
    * @param title
    * @return
    */
    @Path("searchTitle/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String searchByTitle (@PathParam("title") String title){
    
            List<Image> list = funcionsBD.SearchbyTitle(title);
            Gson gson = new Gson();
            
            String res = gson.toJson(list);
            
            return res;
        
    }
    
    /**
    * GET method to search images by creation date. Date format should be
    * yyyy-mm-dd
    * @param date
    * @return
    */
    @Path("searchCreationDate/{date}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String searchByCreationDate (@PathParam("date") String date){
        
            List<Image> list = funcionsBD.SearchbyCreaDate(date);
            
            Gson gson = new Gson();
            
            String res = gson.toJson(list);
            
            return res;
    }
    
    /**
    * GET method to search images by author
    * @param author
    * @return
    */
    @Path("searchAuthor/{author}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String searchByAuthor (@PathParam("author") String author){
    
            List<Image> list = funcionsBD.SearchbyAuthor(author);
            
            Gson gson = new Gson();
            
            String res = gson.toJson(list);
            
            return res;
    }
    
    /*** GET method to search images by keyword
    * @param keywords
    * @return
    */
    @Path("searchKeywords/{keywords}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String searchByKeywords (@PathParam("keywords") String keywords){
    
            List<Image> list = funcionsBD.SearchbyKeywords(keywords);
            
            Gson gson = new Gson();
            
            String res = gson.toJson(list);
            
            return res;
    }
    
    @Path("comprovaCredencials/{username}/{password}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String ComprovaCredencials (@PathParam("username") String username, @PathParam("password") String password){
    
           
            boolean res = funcionsBD.ComprovarCredencials(username, password);
            
            if(res) return "OK";
            else return "KO";
            
            
    }


    @Path("registerUser")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registrarUser (@FormParam("username") String username, @FormParam("passwd") String passwd){


            

            //el segon stordate ha de ser captdate quan sapiguem ferho
            boolean aux = funcionsBD.AfegirUsuari(username, passwd);



            if(aux) return "OK";
            else return "KO";


    }
    
    


}
