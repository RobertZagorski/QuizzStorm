package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.answers;

import java.util.List;

import pl.rzagorski.quizzstorm.model.database.Question;
import pl.rzagorski.quizzstorm.ui.base.MvpView;

/**
 * Created by Robert Zagórski on 31.07.2016.
 */
public interface QuizAnswersView extends MvpView {
    void setQuestions(List<Question> questionList);

    void updateTime(Long seconds);

    void showCorrectAnswerView();

    void showIncorrectAnswerView();

    void showNextQuestion();
}
