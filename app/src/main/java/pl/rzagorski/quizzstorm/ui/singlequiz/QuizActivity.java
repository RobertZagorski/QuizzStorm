package pl.rzagorski.quizzstorm.ui.singlequiz;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.MembersInjector;
import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.QuizComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity.QuizActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity.QuizActivityModule;
import pl.rzagorski.quizzstorm.ui.menu.MenuActivity;
import pl.rzagorski.quizzstorm.utils.interfaces.ComponentCreator;

public class QuizActivity extends MenuActivity implements ComponentCreator<QuizActivityComponent> {
    QuizActivityComponent quizActivityComponent;

    @Inject QuizPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_quiz;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public QuizActivityComponent getComponent() {
        if (quizActivityComponent != null) {
            return null;
        }
        quizActivityComponent = getQizComponent()
                .provide(new QuizActivityModule(this));
        return quizActivityComponent;
    }
}
