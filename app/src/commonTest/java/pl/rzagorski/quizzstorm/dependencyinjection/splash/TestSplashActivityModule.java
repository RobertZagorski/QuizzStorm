package pl.rzagorski.quizzstorm.dependencyinjection.splash;


import dagger.Module;
import pl.rzagorski.quizzstorm.ui.splash.SplashActivity;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@Module
public class TestSplashActivityModule extends SplashActivityModule {

    public TestSplashActivityModule(SplashActivity splashActivity) {
        super(splashActivity);
    }
}