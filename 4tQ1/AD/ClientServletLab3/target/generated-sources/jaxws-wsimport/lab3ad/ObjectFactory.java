
package lab3ad;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the lab3ad package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListImagesResponse_QNAME = new QName("http://lab3AD/", "ListImagesResponse");
    private final static QName _SearchbyId_QNAME = new QName("http://lab3AD/", "SearchbyId");
    private final static QName _AfegirUsuari_QNAME = new QName("http://lab3AD/", "AfegirUsuari");
    private final static QName _AfegirUsuariResponse_QNAME = new QName("http://lab3AD/", "AfegirUsuariResponse");
    private final static QName _SearchCompleteResponse_QNAME = new QName("http://lab3AD/", "SearchCompleteResponse");
    private final static QName _SearchbyKeywords_QNAME = new QName("http://lab3AD/", "SearchbyKeywords");
    private final static QName _SearchbyAuthorResponse_QNAME = new QName("http://lab3AD/", "SearchbyAuthorResponse");
    private final static QName _SearchComplete_QNAME = new QName("http://lab3AD/", "SearchComplete");
    private final static QName _SearchbyTitle_QNAME = new QName("http://lab3AD/", "SearchbyTitle");
    private final static QName _ModifyImageResponse_QNAME = new QName("http://lab3AD/", "ModifyImageResponse");
    private final static QName _SearchbyKeywordsResponse_QNAME = new QName("http://lab3AD/", "SearchbyKeywordsResponse");
    private final static QName _SearchbyCreaDateResponse_QNAME = new QName("http://lab3AD/", "SearchbyCreaDateResponse");
    private final static QName _ComprovarCredencials_QNAME = new QName("http://lab3AD/", "ComprovarCredencials");
    private final static QName _DeleteImage_QNAME = new QName("http://lab3AD/", "DeleteImage");
    private final static QName _RegisterImageResponse_QNAME = new QName("http://lab3AD/", "RegisterImageResponse");
    private final static QName _ModifyImage_QNAME = new QName("http://lab3AD/", "ModifyImage");
    private final static QName _SearchbyAuthor_QNAME = new QName("http://lab3AD/", "SearchbyAuthor");
    private final static QName _DeleteImageResponse_QNAME = new QName("http://lab3AD/", "DeleteImageResponse");
    private final static QName _SearchbyCreaDate_QNAME = new QName("http://lab3AD/", "SearchbyCreaDate");
    private final static QName _ListImages_QNAME = new QName("http://lab3AD/", "ListImages");
    private final static QName _DownloadImageResponse_QNAME = new QName("http://lab3AD/", "DownloadImageResponse");
    private final static QName _RegisterImage_QNAME = new QName("http://lab3AD/", "RegisterImage");
    private final static QName _SearchbyIdResponse_QNAME = new QName("http://lab3AD/", "SearchbyIdResponse");
    private final static QName _ComprovarCredencialsResponse_QNAME = new QName("http://lab3AD/", "ComprovarCredencialsResponse");
    private final static QName _UploadImage_QNAME = new QName("http://lab3AD/", "uploadImage");
    private final static QName _UploadImageResponse_QNAME = new QName("http://lab3AD/", "uploadImageResponse");
    private final static QName _DownloadImage_QNAME = new QName("http://lab3AD/", "DownloadImage");
    private final static QName _SearchbyTitleResponse_QNAME = new QName("http://lab3AD/", "SearchbyTitleResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: lab3ad
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ComprovarCredencials }
     * 
     */
    public ComprovarCredencials createComprovarCredencials() {
        return new ComprovarCredencials();
    }

    /**
     * Create an instance of {@link DeleteImage }
     * 
     */
    public DeleteImage createDeleteImage() {
        return new DeleteImage();
    }

    /**
     * Create an instance of {@link RegisterImageResponse }
     * 
     */
    public RegisterImageResponse createRegisterImageResponse() {
        return new RegisterImageResponse();
    }

    /**
     * Create an instance of {@link SearchbyAuthor }
     * 
     */
    public SearchbyAuthor createSearchbyAuthor() {
        return new SearchbyAuthor();
    }

    /**
     * Create an instance of {@link ModifyImage }
     * 
     */
    public ModifyImage createModifyImage() {
        return new ModifyImage();
    }

    /**
     * Create an instance of {@link DeleteImageResponse }
     * 
     */
    public DeleteImageResponse createDeleteImageResponse() {
        return new DeleteImageResponse();
    }

    /**
     * Create an instance of {@link SearchbyCreaDate }
     * 
     */
    public SearchbyCreaDate createSearchbyCreaDate() {
        return new SearchbyCreaDate();
    }

    /**
     * Create an instance of {@link ListImages }
     * 
     */
    public ListImages createListImages() {
        return new ListImages();
    }

    /**
     * Create an instance of {@link DownloadImageResponse }
     * 
     */
    public DownloadImageResponse createDownloadImageResponse() {
        return new DownloadImageResponse();
    }

    /**
     * Create an instance of {@link RegisterImage }
     * 
     */
    public RegisterImage createRegisterImage() {
        return new RegisterImage();
    }

    /**
     * Create an instance of {@link ComprovarCredencialsResponse }
     * 
     */
    public ComprovarCredencialsResponse createComprovarCredencialsResponse() {
        return new ComprovarCredencialsResponse();
    }

    /**
     * Create an instance of {@link SearchbyIdResponse }
     * 
     */
    public SearchbyIdResponse createSearchbyIdResponse() {
        return new SearchbyIdResponse();
    }

    /**
     * Create an instance of {@link UploadImage }
     * 
     */
    public UploadImage createUploadImage() {
        return new UploadImage();
    }

    /**
     * Create an instance of {@link UploadImageResponse }
     * 
     */
    public UploadImageResponse createUploadImageResponse() {
        return new UploadImageResponse();
    }

    /**
     * Create an instance of {@link DownloadImage }
     * 
     */
    public DownloadImage createDownloadImage() {
        return new DownloadImage();
    }

    /**
     * Create an instance of {@link SearchbyTitleResponse }
     * 
     */
    public SearchbyTitleResponse createSearchbyTitleResponse() {
        return new SearchbyTitleResponse();
    }

    /**
     * Create an instance of {@link AfegirUsuari }
     * 
     */
    public AfegirUsuari createAfegirUsuari() {
        return new AfegirUsuari();
    }

    /**
     * Create an instance of {@link ListImagesResponse }
     * 
     */
    public ListImagesResponse createListImagesResponse() {
        return new ListImagesResponse();
    }

    /**
     * Create an instance of {@link SearchbyId }
     * 
     */
    public SearchbyId createSearchbyId() {
        return new SearchbyId();
    }

    /**
     * Create an instance of {@link AfegirUsuariResponse }
     * 
     */
    public AfegirUsuariResponse createAfegirUsuariResponse() {
        return new AfegirUsuariResponse();
    }

    /**
     * Create an instance of {@link SearchCompleteResponse }
     * 
     */
    public SearchCompleteResponse createSearchCompleteResponse() {
        return new SearchCompleteResponse();
    }

    /**
     * Create an instance of {@link SearchbyAuthorResponse }
     * 
     */
    public SearchbyAuthorResponse createSearchbyAuthorResponse() {
        return new SearchbyAuthorResponse();
    }

    /**
     * Create an instance of {@link SearchbyKeywords }
     * 
     */
    public SearchbyKeywords createSearchbyKeywords() {
        return new SearchbyKeywords();
    }

    /**
     * Create an instance of {@link SearchComplete }
     * 
     */
    public SearchComplete createSearchComplete() {
        return new SearchComplete();
    }

    /**
     * Create an instance of {@link SearchbyTitle }
     * 
     */
    public SearchbyTitle createSearchbyTitle() {
        return new SearchbyTitle();
    }

    /**
     * Create an instance of {@link ModifyImageResponse }
     * 
     */
    public ModifyImageResponse createModifyImageResponse() {
        return new ModifyImageResponse();
    }

    /**
     * Create an instance of {@link SearchbyKeywordsResponse }
     * 
     */
    public SearchbyKeywordsResponse createSearchbyKeywordsResponse() {
        return new SearchbyKeywordsResponse();
    }

    /**
     * Create an instance of {@link SearchbyCreaDateResponse }
     * 
     */
    public SearchbyCreaDateResponse createSearchbyCreaDateResponse() {
        return new SearchbyCreaDateResponse();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "ListImagesResponse")
    public JAXBElement<ListImagesResponse> createListImagesResponse(ListImagesResponse value) {
        return new JAXBElement<ListImagesResponse>(_ListImagesResponse_QNAME, ListImagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyId")
    public JAXBElement<SearchbyId> createSearchbyId(SearchbyId value) {
        return new JAXBElement<SearchbyId>(_SearchbyId_QNAME, SearchbyId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfegirUsuari }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "AfegirUsuari")
    public JAXBElement<AfegirUsuari> createAfegirUsuari(AfegirUsuari value) {
        return new JAXBElement<AfegirUsuari>(_AfegirUsuari_QNAME, AfegirUsuari.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AfegirUsuariResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "AfegirUsuariResponse")
    public JAXBElement<AfegirUsuariResponse> createAfegirUsuariResponse(AfegirUsuariResponse value) {
        return new JAXBElement<AfegirUsuariResponse>(_AfegirUsuariResponse_QNAME, AfegirUsuariResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCompleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchCompleteResponse")
    public JAXBElement<SearchCompleteResponse> createSearchCompleteResponse(SearchCompleteResponse value) {
        return new JAXBElement<SearchCompleteResponse>(_SearchCompleteResponse_QNAME, SearchCompleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyKeywords")
    public JAXBElement<SearchbyKeywords> createSearchbyKeywords(SearchbyKeywords value) {
        return new JAXBElement<SearchbyKeywords>(_SearchbyKeywords_QNAME, SearchbyKeywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyAuthorResponse")
    public JAXBElement<SearchbyAuthorResponse> createSearchbyAuthorResponse(SearchbyAuthorResponse value) {
        return new JAXBElement<SearchbyAuthorResponse>(_SearchbyAuthorResponse_QNAME, SearchbyAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchComplete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchComplete")
    public JAXBElement<SearchComplete> createSearchComplete(SearchComplete value) {
        return new JAXBElement<SearchComplete>(_SearchComplete_QNAME, SearchComplete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyTitle")
    public JAXBElement<SearchbyTitle> createSearchbyTitle(SearchbyTitle value) {
        return new JAXBElement<SearchbyTitle>(_SearchbyTitle_QNAME, SearchbyTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "ModifyImageResponse")
    public JAXBElement<ModifyImageResponse> createModifyImageResponse(ModifyImageResponse value) {
        return new JAXBElement<ModifyImageResponse>(_ModifyImageResponse_QNAME, ModifyImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyKeywordsResponse")
    public JAXBElement<SearchbyKeywordsResponse> createSearchbyKeywordsResponse(SearchbyKeywordsResponse value) {
        return new JAXBElement<SearchbyKeywordsResponse>(_SearchbyKeywordsResponse_QNAME, SearchbyKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreaDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyCreaDateResponse")
    public JAXBElement<SearchbyCreaDateResponse> createSearchbyCreaDateResponse(SearchbyCreaDateResponse value) {
        return new JAXBElement<SearchbyCreaDateResponse>(_SearchbyCreaDateResponse_QNAME, SearchbyCreaDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprovarCredencials }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "ComprovarCredencials")
    public JAXBElement<ComprovarCredencials> createComprovarCredencials(ComprovarCredencials value) {
        return new JAXBElement<ComprovarCredencials>(_ComprovarCredencials_QNAME, ComprovarCredencials.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "DeleteImage")
    public JAXBElement<DeleteImage> createDeleteImage(DeleteImage value) {
        return new JAXBElement<DeleteImage>(_DeleteImage_QNAME, DeleteImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "RegisterImageResponse")
    public JAXBElement<RegisterImageResponse> createRegisterImageResponse(RegisterImageResponse value) {
        return new JAXBElement<RegisterImageResponse>(_RegisterImageResponse_QNAME, RegisterImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "ModifyImage")
    public JAXBElement<ModifyImage> createModifyImage(ModifyImage value) {
        return new JAXBElement<ModifyImage>(_ModifyImage_QNAME, ModifyImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyAuthor")
    public JAXBElement<SearchbyAuthor> createSearchbyAuthor(SearchbyAuthor value) {
        return new JAXBElement<SearchbyAuthor>(_SearchbyAuthor_QNAME, SearchbyAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "DeleteImageResponse")
    public JAXBElement<DeleteImageResponse> createDeleteImageResponse(DeleteImageResponse value) {
        return new JAXBElement<DeleteImageResponse>(_DeleteImageResponse_QNAME, DeleteImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreaDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyCreaDate")
    public JAXBElement<SearchbyCreaDate> createSearchbyCreaDate(SearchbyCreaDate value) {
        return new JAXBElement<SearchbyCreaDate>(_SearchbyCreaDate_QNAME, SearchbyCreaDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "ListImages")
    public JAXBElement<ListImages> createListImages(ListImages value) {
        return new JAXBElement<ListImages>(_ListImages_QNAME, ListImages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "DownloadImageResponse")
    public JAXBElement<DownloadImageResponse> createDownloadImageResponse(DownloadImageResponse value) {
        return new JAXBElement<DownloadImageResponse>(_DownloadImageResponse_QNAME, DownloadImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "RegisterImage")
    public JAXBElement<RegisterImage> createRegisterImage(RegisterImage value) {
        return new JAXBElement<RegisterImage>(_RegisterImage_QNAME, RegisterImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyIdResponse")
    public JAXBElement<SearchbyIdResponse> createSearchbyIdResponse(SearchbyIdResponse value) {
        return new JAXBElement<SearchbyIdResponse>(_SearchbyIdResponse_QNAME, SearchbyIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComprovarCredencialsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "ComprovarCredencialsResponse")
    public JAXBElement<ComprovarCredencialsResponse> createComprovarCredencialsResponse(ComprovarCredencialsResponse value) {
        return new JAXBElement<ComprovarCredencialsResponse>(_ComprovarCredencialsResponse_QNAME, ComprovarCredencialsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "uploadImage")
    public JAXBElement<UploadImage> createUploadImage(UploadImage value) {
        return new JAXBElement<UploadImage>(_UploadImage_QNAME, UploadImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "uploadImageResponse")
    public JAXBElement<UploadImageResponse> createUploadImageResponse(UploadImageResponse value) {
        return new JAXBElement<UploadImageResponse>(_UploadImageResponse_QNAME, UploadImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "DownloadImage")
    public JAXBElement<DownloadImage> createDownloadImage(DownloadImage value) {
        return new JAXBElement<DownloadImage>(_DownloadImage_QNAME, DownloadImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab3AD/", name = "SearchbyTitleResponse")
    public JAXBElement<SearchbyTitleResponse> createSearchbyTitleResponse(SearchbyTitleResponse value) {
        return new JAXBElement<SearchbyTitleResponse>(_SearchbyTitleResponse_QNAME, SearchbyTitleResponse.class, null, value);
    }

}
