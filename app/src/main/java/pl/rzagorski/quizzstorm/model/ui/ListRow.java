package pl.rzagorski.quizzstorm.model.ui;

import android.support.annotation.ColorRes;

/**
 * Created by Robert Zagórski on 28.07.2016.
 */
public class ListRow extends RecyclerViewModel<ListRow> {
    Long id;
    String title;
    Long questionCount;
    String imageUrl;
    String categoryName;
    @ColorRes int cardBackground;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Long questionCount) {
        this.questionCount = questionCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCardBackground() {
        return cardBackground;
    }

    public void setCardBackground(int cardBackground) {
        this.cardBackground = cardBackground;
    }
}
