package pl.rzagorski.quizzstorm.model.api;

import java.util.List;

import pl.rzagorski.quizzstorm.model.database.Question;
import pl.rzagorski.quizzstorm.utils.interfaces.LayerTransformer;

/**
 * The model of quiz question.
 *
 * Created by Robert Zagórski on 27.07.2016.
 */
public class ApiQuestion implements LayerTransformer<Question> {
    private ApiPhoto image;
    private List<ApiAnswer> answers;
    private String text;
    private String answer;
    private String type;
    private Long order;

    public List<ApiAnswer> getAnswers() {
        return answers;
    }

    @Override
    public Question transform() {
        Question question = new Question();
        question.setPhotoRef(image.transform());
        question.setText(text);
        question.setAnswer(answer);
        question.setType(type);
        question.setOrder(order);
        return question;
    }
}
