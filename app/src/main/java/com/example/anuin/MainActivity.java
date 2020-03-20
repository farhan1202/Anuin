package com.example.anuin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anuin.home.HomeFrag;
import com.example.anuin.order.OrderFrag;
import com.example.anuin.other.OtherFrag;
import com.example.anuin.profil.ProfileFrag;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

public class MainActivity extends AppCompatActivity {
    private boolean doubleBack;
    private Toast backToast;

    PrefManager prefManager;

    BottomNavigationView bottomNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
        bottomNav = findViewById(R.id.bottom_navigation);
        //bottomNav.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

//        bottomNav.setOnNavigationItemSelectedListener(listener);

        initBottomView();

        prefManager = new PrefManager(getApplicationContext());
        if (prefManager.getGuest()){
            bottomNav.getMenu().getItem(1).setEnabled(false);
            bottomNav.getMenu().getItem(2).setEnabled(false);
            bottomNav.getMenu().getItem(3).setEnabled(false);
        }

    }

    private void initBottomView() {
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        changeFragment(new HomeFrag(), HomeFrag.class
                                .getSimpleName());
                        break;
                    case R.id.nav_order:
                        changeFragment(new OrderFrag(), OrderFrag.class
                                .getSimpleName());
                        break;
                    case R.id.nav_profil:
                        changeFragment(new ProfileFrag(), ProfileFrag.class
                                .getSimpleName());
                        break;
                    case R.id.nav_other:
                        changeFragment(new OtherFrag(), OtherFrag.class
                                .getSimpleName());
                        break;
                }
                return true;
            }
        });
        changeFragment(new HomeFrag(), HomeFrag.class
                .getSimpleName());
    }

    public void changeFragment(Fragment fragment, String tagFragmentName) {

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.fragment_container, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    /*private BottomNavigationView.OnNavigationItemSelectedListener listener =
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
            };*/


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
        prefManager.removeGuest();
    }

}


