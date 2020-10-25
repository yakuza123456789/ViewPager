package com.example.android3_lesosn3.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    private static SharedPreferences sharedPreferences;

    private final static String APP_NAME = "key";
    private final static String USER = "user";


    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences(APP_NAME, context.MODE_PRIVATE);
    }

    public static void saveUser(String user) {
        sharedPreferences.edit().putString(USER, user).apply();
    }

    public static String getUser(){
        return sharedPreferences.getString(USER, "");
    }
}
