package pl.rzagorski.quizzstorm.data;

import android.app.Application;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.QuizzstormApplication;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.QuizComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.QuizModule;
import pl.rzagorski.quizzstorm.model.database.Quiz;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
public class ScopeManager {
    private QuizzstormApplication mApplication;

    @Inject
    public ScopeManager(Application application) {
        mApplication = (QuizzstormApplication) application;
    }

    public QuizComponent createQuizComponent(Quiz quiz) {
        QuizModule quizModule = new QuizModule(quiz);
        QuizComponent quizComponent = mApplication.getListComponent().provide(quizModule);
        mApplication.setComponent(quizComponent);
        return quizComponent;
    }
}
