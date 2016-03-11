package com.nikhil.intelimentapp.Utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nikhil on 09-03-2016.
 */
public class PreferencesManger {
    public static final String PREFS_ID = "APP_PREFS";

    public static String getStringFields(Context context, String key) {
        SharedPreferences settings;
        String value;
        settings = context.getSharedPreferences(PREFS_ID, Context.MODE_PRIVATE);
        value = settings.getString(key, "");
        return value;
    }

    public static void addStringFields(Context context, String key, String value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_ID, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(key, value);

        editor.commit();
    }

    public static boolean getBooleanFields(Context context, String key) {
        SharedPreferences settings;
        boolean value;
        settings = context.getSharedPreferences(PREFS_ID, Context.MODE_PRIVATE);
        value = settings.getBoolean(key, false);
        return value;
    }

    public static void addBooleanFields(Context context, String key, boolean value) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = context.getSharedPreferences(PREFS_ID, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putBoolean(key, value);

        editor.commit();
    }

}
