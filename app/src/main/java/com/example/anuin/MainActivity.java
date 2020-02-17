package com.example.anuin;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selecFrag).commit();

                    return true;
                }
            };
}


