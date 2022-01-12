
package ClientServlet;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ServeiWeb", targetNamespace = "http://lab3AD/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServeiWeb {


    /**
     * 
     * @param arg0
     * @return
     *     returns ClientServlet.Image
     */
    @WebMethod(operationName = "DownloadImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "DownloadImage", targetNamespace = "http://lab3AD/", className = "ClientServlet.DownloadImage")
    @ResponseWrapper(localName = "DownloadImageResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.DownloadImageResponse")
    @Action(input = "http://lab3AD/ServeiWeb/DownloadImageRequest", output = "http://lab3AD/ServeiWeb/DownloadImageResponse")
    public Image downloadImage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "uploadImage", targetNamespace = "http://lab3AD/", className = "ClientServlet.UploadImage")
    @ResponseWrapper(localName = "uploadImageResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.UploadImageResponse")
    @Action(input = "http://lab3AD/ServeiWeb/uploadImageRequest", output = "http://lab3AD/ServeiWeb/uploadImageResponse")
    public int uploadImage(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "ComprovarCredencials")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ComprovarCredencials", targetNamespace = "http://lab3AD/", className = "ClientServlet.ComprovarCredencials")
    @ResponseWrapper(localName = "ComprovarCredencialsResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.ComprovarCredencialsResponse")
    @Action(input = "http://lab3AD/ServeiWeb/ComprovarCredencialsRequest", output = "http://lab3AD/ServeiWeb/ComprovarCredencialsResponse")
    public boolean comprovarCredencials(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg7
     * @param arg6
     * @return
     *     returns java.util.List<java.lang.Object>
     */
    @WebMethod(operationName = "SearchComplete")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchComplete", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchComplete")
    @ResponseWrapper(localName = "SearchCompleteResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchCompleteResponse")
    @Action(input = "http://lab3AD/ServeiWeb/SearchCompleteRequest", output = "http://lab3AD/ServeiWeb/SearchCompleteResponse")
    public List<Object> searchComplete(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        String arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        String arg6,
        @WebParam(name = "arg7", targetNamespace = "")
        String arg7);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "AfegirUsuari")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "AfegirUsuari", targetNamespace = "http://lab3AD/", className = "ClientServlet.AfegirUsuari")
    @ResponseWrapper(localName = "AfegirUsuariResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.AfegirUsuariResponse")
    @Action(input = "http://lab3AD/ServeiWeb/AfegirUsuariRequest", output = "http://lab3AD/ServeiWeb/AfegirUsuariResponse")
    public boolean afegirUsuari(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.Object>
     */
    @WebMethod(operationName = "SearchbyKeywords")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyKeywords", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyKeywords")
    @ResponseWrapper(localName = "SearchbyKeywordsResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyKeywordsResponse")
    @Action(input = "http://lab3AD/ServeiWeb/SearchbyKeywordsRequest", output = "http://lab3AD/ServeiWeb/SearchbyKeywordsResponse")
    public List<Object> searchbyKeywords(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod(operationName = "DeleteImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "DeleteImage", targetNamespace = "http://lab3AD/", className = "ClientServlet.DeleteImage")
    @ResponseWrapper(localName = "DeleteImageResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.DeleteImageResponse")
    @Action(input = "http://lab3AD/ServeiWeb/DeleteImageRequest", output = "http://lab3AD/ServeiWeb/DeleteImageResponse")
    public int deleteImage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.Object>
     */
    @WebMethod(operationName = "SearchbyCreaDate")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyCreaDate", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyCreaDate")
    @ResponseWrapper(localName = "SearchbyCreaDateResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyCreaDateResponse")
    @Action(input = "http://lab3AD/ServeiWeb/SearchbyCreaDateRequest", output = "http://lab3AD/ServeiWeb/SearchbyCreaDateResponse")
    public List<Object> searchbyCreaDate(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.Object>
     */
    @WebMethod(operationName = "ListImages")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ListImages", targetNamespace = "http://lab3AD/", className = "ClientServlet.ListImages")
    @ResponseWrapper(localName = "ListImagesResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.ListImagesResponse")
    @Action(input = "http://lab3AD/ServeiWeb/ListImagesRequest", output = "http://lab3AD/ServeiWeb/ListImagesResponse")
    public List<Object> listImages();

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod(operationName = "RegisterImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "RegisterImage", targetNamespace = "http://lab3AD/", className = "ClientServlet.RegisterImage")
    @ResponseWrapper(localName = "RegisterImageResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.RegisterImageResponse")
    @Action(input = "http://lab3AD/ServeiWeb/RegisterImageRequest", output = "http://lab3AD/ServeiWeb/RegisterImageResponse")
    public int registerImage(
        @WebParam(name = "arg0", targetNamespace = "")
        Image arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.Object>
     */
    @WebMethod(operationName = "SearchbyTitle")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyTitle", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyTitle")
    @ResponseWrapper(localName = "SearchbyTitleResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyTitleResponse")
    @Action(input = "http://lab3AD/ServeiWeb/SearchbyTitleRequest", output = "http://lab3AD/ServeiWeb/SearchbyTitleResponse")
    public List<Object> searchbyTitle(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.Object>
     */
    @WebMethod(operationName = "SearchbyAuthor")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyAuthor", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyAuthor")
    @ResponseWrapper(localName = "SearchbyAuthorResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyAuthorResponse")
    @Action(input = "http://lab3AD/ServeiWeb/SearchbyAuthorRequest", output = "http://lab3AD/ServeiWeb/SearchbyAuthorResponse")
    public List<Object> searchbyAuthor(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod(operationName = "ModifyImage")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "ModifyImage", targetNamespace = "http://lab3AD/", className = "ClientServlet.ModifyImage")
    @ResponseWrapper(localName = "ModifyImageResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.ModifyImageResponse")
    @Action(input = "http://lab3AD/ServeiWeb/ModifyImageRequest", output = "http://lab3AD/ServeiWeb/ModifyImageResponse")
    public int modifyImage(
        @WebParam(name = "arg0", targetNamespace = "")
        Image arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns ClientServlet.Image
     */
    @WebMethod(operationName = "SearchbyId")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "SearchbyId", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyId")
    @ResponseWrapper(localName = "SearchbyIdResponse", targetNamespace = "http://lab3AD/", className = "ClientServlet.SearchbyIdResponse")
    @Action(input = "http://lab3AD/ServeiWeb/SearchbyIdRequest", output = "http://lab3AD/ServeiWeb/SearchbyIdResponse")
    public Image searchbyId(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}