package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.start;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.ui.base.BasePresenter;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizPresenter;
import pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.answers.QuizAnswersView;

/**
 * Created by Robert Zagórski on 31.07.2016.
 */
public class QuizStartPresenter extends BasePresenter<QuizStartView> {
    QuizPresenter mQuizPresenter;

    @Inject
    public QuizStartPresenter(QuizPresenter quizPresenter) {
        mQuizPresenter = quizPresenter;
    }

    @Override
    public void attachView(QuizStartView mvcView) {
        super.attachView(mvcView);
        getView().setTitle(mQuizPresenter.getQuiz().getTitle());
        getView().setDescription(mQuizPresenter.getQuiz().getContent());
    }

    public void onStart() {

    }
}
