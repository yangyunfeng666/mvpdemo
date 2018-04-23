package com.kye.basemodule.shareperfence;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Map;

/**
 * Created by yyf on 2018-04-13 17:42.
 */

public class Preference {
    private static Context mContext;
    private static String mFileName;

    public static void init(Context context,String fileName){
        mContext = context;
        mFileName = fileName;
    }

    public static <T> T get(@NonNull String key, T fallback) throws
            UnsupportedOperationException {
        SharedPreferences sp = getSharedPreferences();
        Object result;
        if (fallback instanceof Boolean) {
            result = sp.getBoolean(key, (Boolean) fallback);
        } else if (fallback instanceof String) {
            result = sp.getString(key, (String) fallback);
        } else if (fallback instanceof Integer) {
            result = sp.getInt(key, (Integer) fallback);
        } else if (fallback instanceof Float) {
            result = sp.getFloat(key, (Float) fallback);
        } else if (fallback instanceof Long) {
            result = sp.getLong(key, (Long) fallback);
        } else {
            throw new UnsupportedOperationException("Type not supported: " + fallback.getClass()
                    .getSimpleName());
        }
        return (T) result;
    }

    public static <T> void set(@NonNull String key, @NonNull T value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            throw new UnsupportedOperationException("Type not supported: " + value.getClass()
                    .getSimpleName());
        }
        editor.apply();
    }

    public static void remove(String key) {
        getSharedPreferences().edit().remove(key).apply();
    }
    /**
     * Retrieve a String value from the preferences, default is an empty string.
     * 从偏好检索字符串值，默认为空字符串。
     */
    public static String getString(@NonNull String key) {
        return get(key, "");
    }

    /**
     * Retrieve a long value from the preferences, default is <code>0</code>.
     */
    public static long getLong(@NonNull String key) {
        return get(key, 0L);
    }

    /**
     * Retrieve a integer value from the preferences, default is <code>0</code>.
     */
    public static int getInt(@NonNull String key) {
        return get(key, 0);
    }

    /**
     * Retrieve a boolean value from the preferences, default is <code>false</code>.
     */
    public static boolean getBoolean(@NonNull String key) {
        return get(key, false);
    }


    private static SharedPreferences getSharedPreferences() {
        checkInitiatedOrThrow();
        // FIXME: 17/3/29
        return mContext.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
    }



    public static Map<String, ?> getAll() {
        return getSharedPreferences().getAll();
    }

    private static void checkInitiatedOrThrow() {
        if (mContext == null || TextUtils.isEmpty(mFileName)) {
            throw new IllegalStateException("The Prefs class is not initialized correctly.");
        }
    }
}
