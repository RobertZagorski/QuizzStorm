package pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity;


import dagger.Module;
import dagger.Provides;
import pl.rzagorski.quizzstorm.data.api.ApiManager;
import pl.rzagorski.quizzstorm.data.database.DatabaseManager;
import pl.rzagorski.quizzstorm.data.interactor.GetQuizDetailsUseCase;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.QuizScope;
import pl.rzagorski.quizzstorm.model.database.Quiz;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizActivity;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizPresenter;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@Module
public class QuizActivityModule {
    private QuizActivity quizActivity;

    public QuizActivityModule(QuizActivity quizActivity) {
        this.quizActivity = quizActivity;
    }

    @Provides
    @ActivityScope
    QuizActivity provideListActivity() {
        return quizActivity;
    }

    @Provides
    @ActivityScope
    GetQuizDetailsUseCase provideQuizDetailsUseCase(DatabaseManager databaseManager,
                                                    ApiManager apiManager) {
        return new GetQuizDetailsUseCase(databaseManager, apiManager);
    }

    @Provides
    @ActivityScope
    QuizPresenter provideQuizPresenter(@QuizScope Quiz quiz,
                                       GetQuizDetailsUseCase getQuizDetailsUseCase) {
        return new QuizPresenter(quiz, getQuizDetailsUseCase);
    }
}