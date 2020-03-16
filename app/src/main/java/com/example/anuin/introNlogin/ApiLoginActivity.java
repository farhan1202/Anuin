package com.example.anuin.introNlogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anuin.MainActivity;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApiLoginActivity extends AppCompatActivity {
    TextView tvBtn;
    @BindView(R.id.btnFB)
    Button btnFB;
    @BindView(R.id.btnGmail)
    Button btnGmail;
    @BindView(R.id.btnGuest)
    Button btnGuest;
    private Boolean doubleBack = false;
    private Toast toast;

    /*private GoogleApiClient googleApiClient;
    private static final int SIGN_IN = 9001;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_login);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        tvBtn = findViewById(R.id.tvBtn);

        //google login
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        btnGmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);
            }
        });*/
    }

    public void signin(View view) {
        tvBtn.setTextColor(Color.RED);
        Intent home = new Intent(ApiLoginActivity.this, LoginActivity.class);
        startActivity(home);
    }

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            toast.cancel();
            super.onBackPressed();
        } else {
            toast = Toast.makeText(this, "Press back againt to exit", Toast.LENGTH_SHORT);
            toast.show();
            doubleBack = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBack = false;
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
        if (userId) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

    @OnClick(R.id.btnGuest)
    public void onViewClicked() {
        PrefManager prefManager = new PrefManager(this);
        prefManager.saveGuest();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()){
                PrefManager prefManager = new PrefManager(this);
                prefManager.saveSession(2);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Login Cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
