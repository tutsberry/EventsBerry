package com.tutsberry.eventsberry.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by saquib on 11/05/15.
 */
public class Setting {

    public static final String PREF_NAME = "APP_SETTINGS";
    private static SharedPreferences settings;
    private static SharedPreferences.Editor editor;

    public Setting(Context context) {
        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getString(String key, String default_value) {
        return settings.getString(key, default_value);
    }

    public void setString(String key, String value) {
        settings.edit().putString(key, value).apply();
    }

    public void removeString(String key) {
        settings.edit().remove(key).apply();
    }

    public int getInt(String key, int default_value) {
        return settings.getInt(key, default_value);
    }

    public void setInt(String key, int value) {
        settings.edit().putInt(key, value).apply();
    }

    public boolean getBoolean(String key, boolean default_value) {
        return settings.getBoolean(key, default_value);
    }

    public void setBoolean(String key, boolean value) {
        settings.edit().putBoolean(key, value).apply();
    }
}
