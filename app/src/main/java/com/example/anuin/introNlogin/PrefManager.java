package com.example.anuin.introNlogin;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context mcontext;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "introslide";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTime";

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

}
