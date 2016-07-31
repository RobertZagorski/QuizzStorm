package pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz;


import dagger.Module;
import dagger.Provides;
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
    @ActivityScope
    Quiz provideQuiz() {
        return quiz;
    }

    @Provides
    @ActivityScope
    QuizPresenter provideQuizPresenter(Quiz quiz) {
        return new QuizPresenter(quiz);
    }
}