package pl.rzagorski.quizzstorm.dependencyinjection;

import javax.inject.Singleton;

import dagger.Component;
import pl.rzagorski.quizzstorm.dependencyinjection.splash.SplashActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.splash.SplashActivityModule;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class
        }
)
public interface ApplicationComponent {

    SplashActivityComponent provide(SplashActivityModule splashActivityModule);
}
