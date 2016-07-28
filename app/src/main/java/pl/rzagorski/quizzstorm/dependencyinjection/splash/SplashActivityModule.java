package pl.rzagorski.quizzstorm.dependencyinjection.splash;


import dagger.Module;
import dagger.Provides;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.ui.splash.SplashActivity;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@Module
public class SplashActivityModule {
    private SplashActivity splashActivity;

    public SplashActivityModule(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    @Provides
    @ActivityScope
    SplashActivity provideSplashActivity() {
        return splashActivity;
    }
}