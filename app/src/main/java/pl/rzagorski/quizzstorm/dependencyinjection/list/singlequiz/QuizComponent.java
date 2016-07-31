package pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz;

import dagger.Subcomponent;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity.QuizActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity.QuizActivityModule;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@QuizScope
@Subcomponent(
        modules = QuizModule.class
)
public interface QuizComponent {

    QuizActivityComponent provide(QuizActivityModule quizActivityModule);
}