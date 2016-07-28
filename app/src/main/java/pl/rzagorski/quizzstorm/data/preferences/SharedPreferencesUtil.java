package pl.rzagorski.quizzstorm.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by Maciej Reichwald on 11.02.14.
 */
public class SharedPreferencesUtil {
    public static final String DEFAULT_STRING_VALUE = "";
    public static final boolean DEFAULT_BOOLEAN_VALUE = false;
    public static final long DEFAULT_LONG_VALUE = -1;
    public static final int DEFAULT_INT_VALUE = -1;

    private static final String SHARED_PREFERENCES_STORAGE = "SharedPreferencesStorage";

    @Inject
    public SharedPreferencesUtil() {
    }

    public void clearPreference(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        sharedPreferences.edit().remove(key).apply();
    }

    /**
     * Loads String value from Shared Preferences storage
     *
     * @param context Activity context to connect Shared Preferences storage
     * @param key     Loaded value key
     * @return
     */
    public String loadString(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String value = sharedPreferences.getString(key, DEFAULT_STRING_VALUE);
        return value;
    }

    /**
     * Loads String set values from Shared Preferences storage
     *
     * @param context Activity context to connect Shared Preferences storage
     * @param key     Loaded value key
     * @return
     */
    public Set<String> loadStringSet(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        Set<String> values = sharedPreferences.getStringSet(key, new HashSet<String>());
        return values;
    }

    /**
     * Loads boolean value from Shared Preferences storage
     *
     * @param context Activity context needed to connect to Shared Preferences storage
     * @param key     Loaded value key
     * @return
     */
    public boolean loadBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        boolean value = sharedPreferences.getBoolean(key, DEFAULT_BOOLEAN_VALUE);
        return value;
    }

    /**
     * Loads boolean values from Shared Preferences storage
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean loadBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        boolean value = sharedPreferences.getBoolean(key, defaultValue);
        return value;
    }

    /**
     * Loads Integer value from Shared preferences storage
     *
     * @param context
     * @param key
     * @return
     */
    public int loadInt(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        int value = sharedPreferences.getInt(key, DEFAULT_INT_VALUE);
        return value;
    }

    /**
     * Loads Long value from Shared preferences storage
     *
     * @param context
     * @param key
     * @return
     */
    public long loadLong(Context context, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        long value = sharedPreferences.getLong(key, DEFAULT_LONG_VALUE);
        return value;
    }

    /**
     * Saves boolean value at given key in Shared Preferences storage
     *
     * @param context Application context needed to connect to Shared Preferences storage
     * @param key     Saved value key
     * @param value
     */
    public void savePreference(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Saves String value at given key in Shared Preferences storage
     *
     * @param context Application context needed to connect to Shared Preferences storage
     * @param key     Saved value key
     * @param value
     */
    public void savePreference(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Saves Integer values at given key in Shared Preferences storage
     *
     * @param context
     * @param key     Saved value key
     * @param value
     */
    public void savePreference(Context context, String key, int value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Saves long value at given key in Shared Preferences storage
     *
     * @param context Application context needed to connect to Shared Preferences storage
     * @param key     Saved value key
     * @param value
     */
    public void savePreference(Context context, String key, long value) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Saves a {@link Set} of {@link String Strings}
     *
     * @param context
     * @param key
     * @param valuesList
     */
    public void savePreference(Context context, String key, Set<String> valuesList) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, valuesList);
        editor.commit();
    }

    private SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_STORAGE, Context.MODE_PRIVATE);
    }

}
