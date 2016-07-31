package pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import pl.rzagorski.quizzstorm.data.api.ApiManager;
import pl.rzagorski.quizzstorm.data.database.DatabaseManager;
import pl.rzagorski.quizzstorm.data.interactor.GetQuizDetailsUseCase;
import pl.rzagorski.quizzstorm.data.interactor.UseCase;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizPresenter;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@Module
public class QuizModule {
    private Quiz quiz;

    public QuizModule(Quiz quiz) {
        this.quiz = quiz;
    }

    @Provides
    @QuizScope
    Quiz provideQuiz() {
        return quiz;
    }
}