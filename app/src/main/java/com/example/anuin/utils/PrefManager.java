package com.example.anuin.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context mcontext;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "introslide";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTime";
    private static final String SESSION_KEY = "SESSION_USER";


    public PrefManager(Context context){
        this.mcontext = context;
        pref = mcontext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch (boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch(){
        return  pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    //SESSION LOGIN PREF
    public void saveSession(){
        editor.putBoolean(SESSION_KEY, true);
        editor.commit();
    }

    public boolean getSession(){
        return pref.getBoolean(SESSION_KEY, false);
    }

    public void removeSession(){
        editor.putBoolean(SESSION_KEY, false);
        editor.commit();
    }

}
