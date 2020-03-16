package com.example.anuin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.annotation.NonNull;

import com.example.anuin.introNlogin.ApiLoginActivity;
import com.example.anuin.utils.PrefManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anuin.home.HomeFrag;
import com.example.anuin.order.OrderFrag;
import com.example.anuin.other.OtherFrag;
import com.example.anuin.profil.ProfileFrag;

public class MainActivity extends AppCompatActivity {
    private boolean doubleBack;
    private Toast backToast;

    PrefManager prefManager;

    /*private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(listener);

        prefManager = new PrefManager(getApplicationContext());
        if (prefManager.getGuest()){
            bottomNav.getMenu().getItem(1).setEnabled(false);
            bottomNav.getMenu().getItem(2).setEnabled(false);
            bottomNav.getMenu().getItem(3).setEnabled(false);
        }
        /*gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();*/


    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selecFrag = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selecFrag = new HomeFrag();
                            break;
                        case R.id.nav_order:
                            selecFrag = new OrderFrag();
                            break;
                        case R.id.nav_profil:
                            selecFrag = new ProfileFrag();
                            break;
                        case R.id.nav_other:
                            selecFrag = new OtherFrag();
                            break;
                            default:
                                selecFrag = new HomeFrag();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selecFrag).commit();

                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        if (doubleBack){
            backToast.cancel();
            super.onBackPressed();
            moveTaskToBack(true);
        }else{
            backToast = Toast.makeText(this, "Press back againt to exit ", Toast.LENGTH_SHORT);
            backToast.show();
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
    protected void onDestroy() {
        super.onDestroy();
        prefManager.removeQuest();
    }

    /*private void handleResult(GoogleSignInResult result){
        if (result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            //get Data here
        }else{
            MoveToLogin();
        }
    }

    private void MoveToLogin() {
        startActivity(new Intent(getApplicationContext(), ApiLoginActivity.class));;
    }

    @Override
    public void onStart() {
        super.onStart();
        int method = sharedPreferences.getInt("METHOD", 1);
        if (method == 2){
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
            if (opr.isDone()){
                GoogleSignInResult result = opr.get();
                handleResult(result);
            }else{
                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(@NonNull GoogleSignInResult result) {
                        handleResult(result);
                    }
                });
            }
        }

    }*/



}


