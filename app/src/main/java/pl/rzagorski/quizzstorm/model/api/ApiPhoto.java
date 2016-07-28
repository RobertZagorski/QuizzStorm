package pl.rzagorski.quizzstorm.model.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.rzagorski.quizzstorm.model.database.Photo;
import pl.rzagorski.quizzstorm.utils.interfaces.LayerTransformer;

/**
 * The model of a photo, It contains the url that need to be
 * used to fetch the photo.
 *
 * Created by Robert Zagórski on 27.07.2016.
 */
public class ApiPhoto implements LayerTransformer<Photo> {
    private String author;
    private Long width;
    private Long height;
    private Long mediaId;
    private String source;
    private String title;
    private String url;

    @Override
    public Photo transform() {
        Photo photo = new Photo();
        photo.setId(getId(url));
        photo.setAuthor(author);
        photo.setWidth(width);
        photo.setHeight(height);
        photo.setSource(source);
        photo.setMediaId(mediaId);
        photo.setTitle(title);
        photo.setUrl(url);
        return photo;
    }

    private String getId(String url) {
        Pattern mPattern = Pattern.compile("[\\d]+-[\\d]+");
        Matcher matcher = mPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
