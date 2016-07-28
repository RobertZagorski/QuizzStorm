package pl.rzagorski.quizzstorm.dependencyinjection.splash;

import dagger.Subcomponent;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;
import pl.rzagorski.quizzstorm.ui.splash.SplashActivity;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@ActivityScope
@Subcomponent(
        modules = SplashActivityModule.class
)
public interface SplashActivityComponent {

    SplashActivity inject(SplashActivity splashActivity);

}