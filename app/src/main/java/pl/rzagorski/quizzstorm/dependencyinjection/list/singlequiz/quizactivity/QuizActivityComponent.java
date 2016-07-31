package pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity;

import dagger.Subcomponent;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.ui.singlequiz.QuizActivity;
import pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.answers.QuizAnswersFragment;
import pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.end.QuizEndFragment;
import pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.start.QuizStartFragment;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@ActivityScope
@Subcomponent(
        modules = QuizActivityModule.class
)
public interface QuizActivityComponent {

    QuizActivity inject(QuizActivity quizActivity);

    void inject(QuizAnswersFragment quizAnswersFragment);
    void inject(QuizEndFragment quizEndFragment);
    void inject(QuizStartFragment quizStartFragment);
}