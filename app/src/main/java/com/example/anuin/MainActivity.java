package com.example.anuin;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFrag()).commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(listener);
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
            backToast = Toast.makeText(this, "Press back againt to exit", Toast.LENGTH_SHORT);
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
}


