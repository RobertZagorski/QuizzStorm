package pl.rzagorski.quizzstorm.data;

import android.app.Application;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.QuizzstormApplication;

/**
 * Created by Robert Zagórski on 2016-07-22.
 */
public class ScopeManager {
    private QuizzstormApplication mApplication;

    @Inject
    public ScopeManager(Application application) {
        mApplication = (QuizzstormApplication) application;
    }
}
