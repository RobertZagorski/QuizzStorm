package pl.rzagorski.quizzstorm.dependencyinjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.rzagorski.quizzstorm.data.api.ApiFactory;
import pl.rzagorski.quizzstorm.data.api.ApiRequests;
import pl.rzagorski.quizzstorm.data.database.DatabaseManager;
import pl.rzagorski.quizzstorm.data.database.DatabaseUtil;
import pl.rzagorski.quizzstorm.data.preferences.SharedPreferencesManager;
import pl.rzagorski.quizzstorm.data.preferences.SharedPreferencesUtil;
import retrofit2.CallAdapter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Robert Zagórski on 2016-06-28.
 */
@Module
public class DataModule {
    protected final Application mApplication;

    public DataModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public CallAdapter.Factory provideRxErrorHandlingCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    @Singleton
    public ApiRequests provideApiRequests(CallAdapter.Factory callFactory) {
        return ApiFactory.makeApiManager(callFactory);
    }

    @Provides
    @Singleton
    public DatabaseUtil provideDatabaseUtil() {
        return new DatabaseUtil();
    }

    @Provides
    @Singleton
    public DatabaseManager provideDatabaseManager(DatabaseUtil databaseUtil) {
        return new DatabaseManager(mApplication, databaseUtil);
    }

    @Provides
    @Singleton
    public SharedPreferencesUtil sharedPreferencesUtil() {
        return new SharedPreferencesUtil();
    }

    @Provides
    @Singleton
    public SharedPreferencesManager provideSharedPreferencesManager(SharedPreferencesUtil sharedPreferencesUtil) {
        return new SharedPreferencesManager(mApplication, sharedPreferencesUtil);
    }
}
