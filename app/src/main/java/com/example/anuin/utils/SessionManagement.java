package com.example.anuin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.anuin.utils.model.Users;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String SHARED_PREF_NAME = "SESSION";
    public static String SESSION_KEY = "SESSION_USER";

    public SessionManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession() {
        editor.putBoolean(SESSION_KEY, true);
        editor.commit();
    }

    public boolean getSession(){
        return sharedPreferences.getBoolean(SESSION_KEY, false);
    }

    public void removeSession(){
        editor.putBoolean(SESSION_KEY, false);
        editor.commit();
    }
}
