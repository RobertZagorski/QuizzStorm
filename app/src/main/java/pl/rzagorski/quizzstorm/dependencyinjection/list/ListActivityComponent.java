package pl.rzagorski.quizzstorm.dependencyinjection.list;

import dagger.Subcomponent;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.QuizComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.QuizModule;
import pl.rzagorski.quizzstorm.ui.list.ListActivity;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@ActivityScope
@Subcomponent(
        modules = ListActivityModule.class
)
public interface ListActivityComponent {

    ListActivity inject(ListActivity listActivity);

    QuizComponent provide(QuizModule quizModule);
}