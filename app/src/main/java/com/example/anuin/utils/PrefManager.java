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
    private static final String SESSION_KEY_SOSMED = "SESSION_SOSMED";

    private static final String SESSION_GUEST = "SESSION_GUEST";

    public static final String SP_ID = "spID";
    public static final String SP_TOKEN_USER = "spTokenUser";


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

    //SESSION GUEST
    public void saveGuest(){
        editor.putBoolean(SESSION_GUEST, true);
        editor.commit();
    }

    public boolean getGuest(){
        return pref.getBoolean(SESSION_GUEST, false);
    }

    public void removeGuest(){
        editor.putBoolean(SESSION_GUEST, false);
        editor.commit();
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

    //session login sosmed
    public void saveSessionSosmed(){
        editor.putBoolean(SESSION_KEY_SOSMED, true);
        editor.commit();
    }

    public boolean getSessionSosmed(){
        return pref.getBoolean(SESSION_KEY_SOSMED, false);
    }

    public void removeSessionSosmed(){
        editor.putBoolean(SESSION_KEY_SOSMED, false);
        editor.commit();
    }

    public void spInt(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }

    public void spString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public int getId(){
        return pref.getInt(SP_ID, 0);
    }

    public String getTokenUser(){
        return pref.getString(SP_TOKEN_USER, "");
    }


}
