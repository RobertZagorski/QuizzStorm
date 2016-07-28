package pl.rzagorski.quizzstorm.model.api;

import android.support.annotation.VisibleForTesting;

import pl.rzagorski.quizzstorm.model.database.Category;
import pl.rzagorski.quizzstorm.utils.interfaces.LayerTransformer;

/**
 * The model of quiz category fetched from the API
 * <p/>
 * Created by Robert Zagórski on 27.07.2016.
 */
public class ApiCategory implements LayerTransformer<Category> {
    private Long uid;
    private Long id;
    private String name;
    private String type;

    @VisibleForTesting
    void setUid(Long uid) {
        this.uid = uid;
    }

    @VisibleForTesting
    void setId(Long id) {
        this.id = id;
    }

    @VisibleForTesting
    void setName(String name) {
        this.name = name;
    }

    @VisibleForTesting
    void setType(String type) {
        this.type = type;
    }

    @Override
    public Category transform() {
        Category category = new Category();
        category.setId(id);
        category.setUid(uid);
        category.setName(name);
        category.setType(type);
        return category;
    }
}
