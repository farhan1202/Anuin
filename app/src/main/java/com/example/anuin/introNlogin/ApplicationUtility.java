package com.example.anuin.introNlogin;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ApplicationUtility {
    ConnectivityManager connectivityManager;
    NetworkInfo info;

    public boolean checkConnection(Context context){
        boolean flag = false;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            info = connectivityManager.getActiveNetworkInfo();

            if (info.getType()==ConnectivityManager.TYPE_WIFI || info.getType()==ConnectivityManager.TYPE_MOBILE){
                System.out.println(info.getTypeName());
                return true;
            }
        }catch (Exception e){
            System.out.println("Exception at network connection ..." + e.getStackTrace());
        }

        return flag;
    }
}
