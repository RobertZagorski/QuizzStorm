package pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity;


import dagger.Module;
import dagger.Provides;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizActivity;

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
}