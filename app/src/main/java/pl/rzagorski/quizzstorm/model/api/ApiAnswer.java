package pl.rzagorski.quizzstorm.model.api;

import android.support.annotation.VisibleForTesting;

import pl.rzagorski.quizzstorm.model.database.Answer;
import pl.rzagorski.quizzstorm.utils.interfaces.LayerTransformer;

/**
 * The model of question answer fetched from the API
 * <p/>
 * Created by Robert Zagórski on 28.07.2016.
 */
public class ApiAnswer implements LayerTransformer<Answer> {
    private ApiPhoto image;
    private Long order;
    private String text;

    @VisibleForTesting
    void setImage(ApiPhoto image) {
        this.image = image;
    }

    @VisibleForTesting
    void setOrder(Long order) {
        this.order = order;
    }

    @VisibleForTesting
    void setText(String text) {
        this.text = text;
    }

    @Override
    public Answer transform() {
        Answer answer = new Answer();
        answer.setOrder(order);
        answer.setText(text);
        return answer;
    }
}
