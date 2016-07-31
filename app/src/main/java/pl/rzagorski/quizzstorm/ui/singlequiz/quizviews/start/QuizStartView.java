package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.start;

import pl.rzagorski.quizzstorm.ui.base.MvpView;

/**
 * Created by Robert Zagórski on 31.07.2016.
 */
public interface QuizStartView extends MvpView {
    void setTitle(String title);
    void setDescription(String content);
}
