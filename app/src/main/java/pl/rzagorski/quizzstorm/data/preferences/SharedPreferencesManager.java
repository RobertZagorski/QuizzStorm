package pl.rzagorski.quizzstorm.data.preferences;

import android.content.Context;

import javax.inject.Inject;

import pl.rzagorski.quizzstorm.dependencyinjection.ApplicationContext;

/**
 * Created by Robert Zagórski on 2016-02-03.
 */
public class SharedPreferencesManager {

    private SharedPreferencesUtil mSharedPreferencesUtil;
    private Context mContext;

    @Inject
    public SharedPreferencesManager(@ApplicationContext Context context, SharedPreferencesUtil sharedPreferencesUtil) {
        mContext = context;
        mSharedPreferencesUtil = sharedPreferencesUtil;
    }
}
