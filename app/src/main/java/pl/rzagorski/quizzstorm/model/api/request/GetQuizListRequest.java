package pl.rzagorski.quizzstorm.model.api.request;

/**
 * A model of request used to get all quizes.</br>
 * It is composed of two parameters:
 * <ul>
 *     <li> startIndex - the beginning of the quiz
 *     <li> endIndex - the end index to be fetched
 * </ul>
 * The number of quizes is the difference between those values
 *
 * Created by Robert Zagórski on 28.07.2016.
 */
public class GetQuizListRequest {
    private Long startIndex;
    private Long endIndex;

    public GetQuizListRequest() {
    }

    public GetQuizListRequest(Long startIndex, Long endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Long getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Long endIndex) {
        this.endIndex = endIndex;
    }
}
