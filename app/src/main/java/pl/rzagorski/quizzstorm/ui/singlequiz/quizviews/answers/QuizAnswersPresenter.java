package pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.answers;

import java.util.List;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.model.database.Answer;
import pl.rzagorski.quizzstorm.model.database.Question;
import pl.rzagorski.quizzstorm.ui.base.BasePresenter;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizPresenter;

/**
 * Created by Robert Zagórski on 31.07.2016.
 */
public class QuizAnswersPresenter extends BasePresenter<QuizAnswersView> {
    QuizPresenter mQuizPresenter;

    Thread timerThread;
    Runnable timerRunnable;

    List<Question> questionList;

    @Inject
    public QuizAnswersPresenter(QuizPresenter quizPresenter) {
        mQuizPresenter = quizPresenter;
    }

    @Override
    public void attachView(QuizAnswersView mvcView) {
        super.attachView(mvcView);
        questionList = mQuizPresenter.getQuiz().getQuestionsRef();
        getView().setQuestions(questionList);
        initTimer();
    }

    @Override
    public void detachView() {
        super.detachView();
        ((Timer)timerRunnable).stop();
        timerThread = null;
    }

    public void onQuestionAnswered(Long order, int answerId) {
        Question question = questionList.get((int) order.longValue());
        Answer answer = question.getAnswersRef().get(answerId);
        if (answer.getIsCorrect()) {
            getView().showCorrectAnswerView();
        } else {
            getView().showIncorrectAnswerView();
        }
        getView().showNextQuestion();
    }

    private void initTimer() {
        /*timerRunnable = new Timer();
        timerThread = new Thread(timerRunnable);
        timerThread.start();*/
    }


    public class Timer implements Runnable {
        boolean isStopped = false;
        Long quizTime = 0L;

        @Override
        public void run() {
            while (!isStopped) {
                getView().updateTime(quizTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void stop() {
            isStopped = true;
        }
    }
}
