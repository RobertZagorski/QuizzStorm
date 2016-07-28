package pl.rzagorski.quizzstorm.dependencyinjection;

import android.app.Application;

import dagger.Module;

/**
 * Created by Robert Zagórski on 2016-06-28.
 */
@Module
public class TestDataModule extends DataModule {

    public TestDataModule(Application application) {
        super(application);
    }
}
