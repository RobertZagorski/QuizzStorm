package pl.rzagorski.quizzstorm.ui.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import pl.rzagorski.quizzstorm.QuizzstormApplication;
import pl.rzagorski.quizzstorm.dependencyinjection.ApplicationComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.ListActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.QuizComponent;

/**
 * Created by Robert Zagórski on 2016-06-28.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected QuizzstormApplication getQuizApplication() {
        return ((QuizzstormApplication) getApplicationContext());
    }

    protected ApplicationComponent getApplicationComponent() {
        return getQuizApplication().getApplicationComponent();
    }

    protected ApplicationComponent getUserComponent() {
        return getQuizApplication().getApplicationComponent();
    }

    protected ListActivityComponent getListComponent() {
        return getQuizApplication().getListComponent();
    }

    protected QuizComponent getQizComponent() {
        return getQuizApplication().getQuizComponent();
    }

    protected Fragment getCurrentFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() < 1) {
            return null;
        }
        String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        Fragment currentFragment = fragmentManager.findFragmentByTag(fragmentTag);
        return currentFragment;
    }

    protected Fragment getPreviousFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() < 2) {
            return null;
        }
        String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 2).getName();
        Fragment previousFragment = fragmentManager.findFragmentByTag(fragmentTag);
        return previousFragment;
    }

    protected <T> T findFragmentByTag(Class<T> fragmentClass) {
        return (T) getFragmentManager().findFragmentByTag(fragmentClass.getSimpleName());
    }
}
