package pl.rzagorski.quizzstorm.ui.singlequiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity.QuizActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.quizactivity.QuizActivityModule;
import pl.rzagorski.quizzstorm.ui.menu.MenuActivity;
import pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.answers.QuizAnswersFragment;
import pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.end.QuizEndFragment;
import pl.rzagorski.quizzstorm.ui.singlequiz.quizviews.start.QuizStartFragment;
import pl.rzagorski.quizzstorm.utils.interfaces.ComponentCreator;

public class QuizActivity extends MenuActivity implements ComponentCreator<QuizActivityComponent>, QuizActivityView {
    QuizActivityComponent quizActivityComponent;

    @Inject QuizPresenter mPresenter;

    @Inject QuizStartFragment startFragment;
    @Inject QuizAnswersFragment anwersFragment;
    @Inject QuizEndFragment endFragment;

    private int progress;
    private int activeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        mPresenter.attachView(this);
        if (savedInstanceState == null) {
            replaceFragment(startFragment, R.id.quiz_fragment);
        } else {
            restoreFragments();
        }
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
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.saveState();
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

    private void restoreFragments() {
        Fragment restoredFragment = findFragmentByTag(QuizStartFragment.class);
        if (restoredFragment != null) {
            startFragment = (QuizStartFragment) restoredFragment;
        }
        restoredFragment = findFragmentByTag(QuizEndFragment.class);
        if (restoredFragment != null) {
            endFragment = (QuizEndFragment) restoredFragment;
        }
        restoredFragment = findFragmentByTag(QuizAnswersFragment.class);
        if (restoredFragment != null) {
            anwersFragment = (QuizAnswersFragment) restoredFragment;
        }
    }

    public void goToStartFragment() {
        replaceFragment(startFragment, R.id.quiz_fragment);
        activeFragment = 0;
    }

    public void goToAnswersFragment() {
        replaceFragment(anwersFragment, R.id.quiz_fragment);
        activeFragment = 1;
    }

    public void goToEndFragmnet() {
        replaceFragment(endFragment, R.id.quiz_fragment);
        activeFragment = 2;
    }

    protected void replaceFragment(Fragment fragmentToReplace, int fragmentId) {
        FragmentManager fragMan = getSupportFragmentManager();
        if (fragMan.findFragmentByTag(fragmentToReplace.getClass().getSimpleName()) != null) {
            openFragment(fragmentToReplace, fragmentId);
            return;
        }
        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        fragTransaction.replace(fragmentId, fragmentToReplace, fragmentToReplace.getClass().getSimpleName());
        fragTransaction.addToBackStack(fragmentToReplace.getClass().getSimpleName());
        fragTransaction.commit();
    }

    protected void openFragment(Fragment fragmentToOpen, int fragmentId) {
        FragmentManager fragMan = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        fragTransaction.replace(fragmentId, fragmentToOpen, fragmentToOpen.getClass().getSimpleName());
        fragTransaction.addToBackStack(fragmentToOpen.getClass().getSimpleName());
        fragTransaction.commit();
    }

    protected <T> T findFragmentByTag(Class<T> fragmentClass) {
        return (T) getFragmentManager().findFragmentByTag(fragmentClass.getSimpleName());
    }
}
