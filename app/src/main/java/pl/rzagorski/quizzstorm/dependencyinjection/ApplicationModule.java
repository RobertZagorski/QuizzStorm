package pl.rzagorski.quizzstorm.dependencyinjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.rzagorski.quizzstorm.data.ScopeManager;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    public Application provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public ScopeManager provideScopeCreator(Application application) {
        return new ScopeManager(application);
    }
}
