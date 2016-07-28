package pl.rzagorski.quizzstorm.model.api.response;

import java.util.List;

import pl.rzagorski.quizzstorm.model.api.ApiQuiz;

/**
 * Created by Robert on 27.07.2016.
 */
public class GetQuizListResponse {
    private Long count;
    private List<ApiQuiz> items;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<ApiQuiz> getItems() {
        return items;
    }

    public void setItems(List<ApiQuiz> items) {
        this.items = items;
    }
}
