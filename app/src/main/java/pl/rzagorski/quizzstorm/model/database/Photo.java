package pl.rzagorski.quizzstorm.model.database;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "PHOTO".
 */
public class Photo {

    private String id;
    private String author;
    private Long width;
    private Long height;
    private Long mediaId;
    private String source;
    private String title;
    private String url;
    private String localUrl;

    public Photo() {
    }

    public Photo(String id) {
        this.id = id;
    }

    public Photo(String id, String author, Long width, Long height, Long mediaId, String source, String title, String url, String localUrl) {
        this.id = id;
        this.author = author;
        this.width = width;
        this.height = height;
        this.mediaId = mediaId;
        this.source = source;
        this.title = title;
        this.url = url;
        this.localUrl = localUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

}
