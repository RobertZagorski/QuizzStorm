package pl.rzagorski.quizzstorm.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import pl.rzagorski.quizzstorm.QuizzstormApplication;
import pl.rzagorski.quizzstorm.R;
import pl.rzagorski.quizzstorm.dependencyinjection.splash.SplashActivityComponent;
import pl.rzagorski.quizzstorm.dependencyinjection.splash.SplashActivityModule;
import pl.rzagorski.quizzstorm.ui.base.BaseActivity;
import pl.rzagorski.quizzstorm.ui.list.ListActivity;
import pl.rzagorski.quizzstorm.utils.interfaces.ComponentCreator;

public class SplashActivity extends BaseActivity implements ComponentCreator<SplashActivityComponent> {
    SplashActivityComponent component;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_SplashTheme);
        super.onCreate(savedInstanceState);
        getComponent();
        setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    public SplashActivityComponent getComponent() {
        if (component == null) {
            component = ((QuizzstormApplication) getApplicationContext()).getApplicationComponent().provide(new SplashActivityModule(this));
            component.inject(this);
        }
        return component;
    }
}
