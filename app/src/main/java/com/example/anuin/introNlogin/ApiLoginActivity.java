package com.example.anuin.introNlogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anuin.MainActivity;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;

public class ApiLoginActivity extends AppCompatActivity {
    TextView tvBtn;
    private Boolean doubleBack = false;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_login);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        tvBtn=findViewById(R.id.tvBtn);
    }

    public void signin(View view) {
        tvBtn.setTextColor(Color.RED);
        Intent home=new Intent(ApiLoginActivity.this, LoginActivity.class);
        startActivity(home);
    }

    @Override
    public void onBackPressed() {
        if (doubleBack){
            toast.cancel();
            super.onBackPressed();
        }else{
            toast = Toast.makeText(this, "Press back againt to exit", Toast.LENGTH_SHORT);
            toast.show();
            doubleBack = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBack=false;
                }
            }, 2000);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*SessionManagement sessionManagement = new SessionManagement(this);
        boolean userId = sessionManagement.getSession();*/

        PrefManager prefManager = new PrefManager(this);
        boolean userId = prefManager.getSession();
        if (userId){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }
}
