package pl.rzagorski.quizzstorm.dependencyinjection;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pl.rzagorski.quizzstorm.dependencyinjection.splash.TestSplashActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.splash.TestSplashActivityModule;

@Singleton
@Component(
        modules = {
                TestApplicationModule.class,
                TestDataModule.class
        }
)
public interface TestApplicationComponent extends ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    TestSplashActivityComponent provide(TestSplashActivityModule splashActivityModule);
}
