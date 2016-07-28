package pl.rzagorski.quizzstorm.model.api.request;

/**
 * A model of request used to get all quizes.</br>
 * Mainly one parameter is used:
 * <ul>
 *     <li> quizId - the ID of the quiz to be fetched
 * </ul>
 *
 * Created by Robert Zagórski on 28.07.2016.
 */
public class GetQuizDetailsRequest {
    private Long quizId;
    private Long count;

    public GetQuizDetailsRequest() {
    }

    public GetQuizDetailsRequest(Long quizId, Long count) {
        this.quizId = quizId;
        this.count = count;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
