package pl.rzagorski.quizzstorm.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import pl.rzagorski.quizzstorm.model.database.QuizResult;
import pl.rzagorski.quizzstorm.utils.interfaces.LayerTransformer;

/**
 * The model of the latest results of the quiz made by other users
 *
 * Created by Robert Zagórski on 27.07.2016.
 */
public class ApiLatestResult implements LayerTransformer<QuizResult> {
    private Long city;
    @SerializedName("end_date") private Date endDate;
    private Float result;
    private Long resolveTime;


    @Override
    public QuizResult transform() {
        ApiCategory category = new ApiCategory();
        QuizResult quizResult = new QuizResult();
        quizResult.setCity(city);
        quizResult.setEndDate(endDate);
        quizResult.setResult(result);
        quizResult.setResolvedTime(resolveTime);
        return quizResult;
    }
}
