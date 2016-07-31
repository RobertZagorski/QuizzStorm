package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.end;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.ui.base.BasePresenter;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizPresenter;
import pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.answers.QuizAnswersView;

/**
 * Created by Robert Zagórski on 31.07.2016.
 */
public class QuizEndPresenter extends BasePresenter<QuizEndView> {
    QuizPresenter mQuizPresenter;

    @Inject
    public QuizEndPresenter(QuizPresenter quizPresenter) {
        mQuizPresenter = quizPresenter;
    }
}
