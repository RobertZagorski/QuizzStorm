package pl.rzagorski.quizzstorm.model.api;

import android.support.annotation.VisibleForTesting;

import java.util.Date;
import java.util.List;

import pl.rzagorski.quizzstorm.model.api.request.GetQuizListRequest;
import pl.rzagorski.quizzstorm.model.database.Category;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.utils.interfaces.LayerTransformer;

/**
 * The model of the quiz (basic composer of the app).
 * This model is retrieved from the API in
 * {@link GetQuizListRequest}
 *
 * <br>
 * Created by Robert Zagórski on 27.07.2016.
 */
public class ApiQuiz implements LayerTransformer<Quiz> {
    private String buttonStart;
    private String shareTitle;
    private Long questions;
    private Date createdAt;
    private Boolean sponsored;
    private Long id;
    private List<ApiCategory> categories;
    private String type;
    private String title;
    private String content;
    private ApiPhoto mainPhoto;
    private ApiCategory category;

    @VisibleForTesting
    void setButtonStart(String buttonStart) {
        this.buttonStart = buttonStart;
    }

    @VisibleForTesting
    void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    @VisibleForTesting
    void setQuestions(Long questions) {
        this.questions = questions;
    }

    @VisibleForTesting
    void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @VisibleForTesting
    void setSponsored(Boolean sponsored) {
        this.sponsored = sponsored;
    }

    @VisibleForTesting
    void setId(Long id) {
        this.id = id;
    }

    @VisibleForTesting
    void setCategories(List<ApiCategory> categories) {
        this.categories = categories;
    }

    @VisibleForTesting
    void setType(String type) {
        this.type = type;
    }

    @VisibleForTesting
    void setTitle(String title) {
        this.title = title;
    }

    @VisibleForTesting
    void setContent(String content) {
        this.content = content;
    }

    @VisibleForTesting
    void setMainPhoto(ApiPhoto mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    @VisibleForTesting
    void setCategory(ApiCategory category) {
        this.category = category;
    }

    @Override
    public Quiz transform() {
        Quiz quiz = new Quiz();
        quiz.setId(id);
        quiz.setButtonStart(buttonStart);
        quiz.setShareTitle(shareTitle);
        quiz.setQuestions(questions);
        quiz.setCreatedAt(createdAt);
        quiz.setSponsored(sponsored);
        quiz.setCategoryRef(createCategory());
        quiz.setType(type);
        quiz.setTitle(title);
        quiz.setContent(content);
        quiz.setPhotoRef(mainPhoto.transform());
        return quiz;
    }

    private Category createCategory() {
        Category category = ApiQuiz.this.category.transform();
        if (categories == null || categories.size() <= 0) {
            return category;
        }
        Category secondCategory = categories.get(0).transform();
        category.setNameEng(secondCategory.getName());
        category.setType(secondCategory.getType());
        category.setUid(secondCategory.getUid());
        return category;
    }
}
