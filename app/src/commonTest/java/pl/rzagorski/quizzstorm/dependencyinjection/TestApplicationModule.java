package pl.rzagorski.quizzstorm.dependencyinjection;;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level test dependencies (Overriden from {@link ApplicationModule}).
 */
@Module
public class TestApplicationModule {
    protected final Application mApplication;

    public TestApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideTestApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    /*************
     * MOCKS
     *************/
}