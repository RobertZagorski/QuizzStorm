package pl.rzagorski.quizzstorm.dependencyinjection.splash;


import dagger.Subcomponent;
import pl.rzagorski.quizzstorm.dependencyinjection.ActivityScope;

/**
 * Created by Robert Zagórski on 07.06.2016.
 */
@ActivityScope
@Subcomponent(
        modules = TestSplashActivityModule.class
)
public interface TestSplashActivityComponent extends SplashActivityComponent {
}