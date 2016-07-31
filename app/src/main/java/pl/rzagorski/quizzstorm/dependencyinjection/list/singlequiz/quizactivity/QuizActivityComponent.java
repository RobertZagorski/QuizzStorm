package pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity;

import dagger.Subcomponent;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizActivity;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@ActivityScope
@Subcomponent(
        modules = QuizActivityModule.class
)
public interface QuizActivityComponent {

    QuizActivity inject(QuizActivity quizActivity);
}