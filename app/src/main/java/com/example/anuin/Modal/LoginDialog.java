package com.example.anuin.Modal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.anuin.R;

public class LoginDialog {
    private Context context;
    private AlertDialog alertDialog;

    public LoginDialog(Context context) {
        this.context = context;
    }

    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(LayoutInflater.from(context).inflate(R.layout.login_dialog, null));

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismissLoadingDialog(){
        alertDialog.dismiss();
    }
}
