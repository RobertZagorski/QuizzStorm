package pl.rzagorski.quizzstorm.ui.singlequiz;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.ui.base.BasePresenter;

/**
 * Created by Robert Zagórski on 31.07.2016.
 */
public class QuizPresenter extends BasePresenter<QuizActivityView> {
    Quiz mQuiz;

    @Inject

    public QuizPresenter(Quiz quiz) {
        this.mQuiz = quiz;
    }
}
