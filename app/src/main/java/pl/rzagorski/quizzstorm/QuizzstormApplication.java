package pl.rzagorski.quizzstorm;

import android.app.Application;
import android.support.annotation.Nullable;

import com.orhanobut.logger.AndroidLogTool;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import pl.rzagorski.quizzstorm.dependencyinjection.ApplicationComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.ApplicationModule;
import pl.rzagorski.quizzstorm.dependencyinjection.DaggerApplicationComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.DataModule;
import pl.rzagorski.quizzstorm.dependencyinjection.list.ListActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.list.singlequiz.QuizComponent;


/**
 * Created by Robert Zagórski on 2016-01-08.
 */
public class QuizzstormApplication extends Application {
    ApplicationComponent mApplicationComponent;
    QuizComponent mQuizComponent;
    ListActivityComponent mListComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        enableLogger();
        createComponents();
    }

    private void enableLogger() {
        Logger.init(getString(R.string.app_name))
                .methodCount(1)
                .logLevel(BuildConfig.DEBUG_BUILD ? LogLevel.FULL : LogLevel.NONE)
                .methodOffset(0)
                .logTool(new AndroidLogTool());
    }

    public void createComponents() {
        if (mApplicationComponent != null) {
            return;
        }
        ApplicationModule applicationModule = new ApplicationModule(this);
        DataModule dataModule = new DataModule(this);
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(applicationModule)
                .dataModule(dataModule)
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            createComponents();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    @Nullable
    public QuizComponent getQuizComponent() {
        return mQuizComponent;
    }

    public void setComponent(QuizComponent quizComponent) {
        mQuizComponent = quizComponent;
    }

    @Nullable
    public ListActivityComponent getListComponent() {
        return mListComponent;
    }

    public void setListComponent(ListActivityComponent listComponent) {
        mListComponent = listComponent;
    }
}
