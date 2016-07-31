package pl.rzagorski.quizzstorm.model.api;

import java.util.Date;
import java.util.List;

import pl.rzagorski.quizzstorm.model.database.Category;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.utils.interfaces.LayerTransformer;

/**
 * The detailed model of a quiz.
 * It contains for example:
 * <ul>
 *     <li> questions - to be shown to the user
 *     <li> rates - how well the user solved the quiz
 *     <li> lastestResults - the last results of other users
 *     <li> avgResult - the average score for this quiz
 * </ul>
 * <br>
 * Created by Robert Zagórski on 27.07.2016.
 */
public class ApiQuizExtendedModel implements LayerTransformer<Quiz> {
    private List<ApiRate> rates;
    private List<ApiQuestion> questions;
    private Date createdAt;
    private Boolean sponsored;
    private String type;
    private String title;
    private String content;
    private String buttonStart;
    private String shareTitle;
    private Long id;
    private List<ApiCategory> categories;
    private String scripts;
    private ApiPhoto mainPhoto;
    private ApiCategory category;
    private Boolean isBattle;
    private Long created;
    private List<ApiLatestResult> latestResultss;
    private Double avgResult;
    private Long resultCount;
    private Double cityAvg;
    private Double cityTime;
    private Double cityCount;
    private Boolean userBattleDone;

    public Long getId() {
        return id;
    }

    public List<ApiRate> getRates() {
        return rates;
    }

    public List<ApiQuestion> getQuestions() {
        return questions;
    }

    public List<ApiLatestResult> getLatestResults() {
        return latestResultss;
    }

    @Override
    public Quiz transform() {
        Quiz quiz = new Quiz();
        quiz.setId(id);
        quiz.setCreatedAt(createdAt);
        quiz.setSponsored(sponsored);
        quiz.setType(type);
        quiz.setTitle(title);
        quiz.setContent(content);
        quiz.setButtonStart(buttonStart);
        quiz.setShareTitle(shareTitle);
        quiz.setCategoryRef(createCategory());
        quiz.setScripts(scripts);
        quiz.setPhotoRef(mainPhoto.transform());
        quiz.setIsBattle(isBattle);
        quiz.setCreated(created);
        quiz.setAverageResult(avgResult);
        quiz.setCityAverage(cityAvg);
        quiz.setCityTime(cityTime);
        quiz.setCityCount(cityCount);
        quiz.setUserBattleDone(userBattleDone);
        return quiz;
    }

    private Category createCategory() {
        Category category = ApiQuizExtendedModel.this.category.transform();
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
