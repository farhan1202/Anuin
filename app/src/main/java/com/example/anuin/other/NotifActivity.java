package com.example.anuin.other;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.example.anuin.Adapter.NotifAdapter;
import com.example.anuin.R;

public class NotifActivity extends AppCompatActivity {
RecyclerView rvNotif;
NotifAdapter adapter;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        toolbar = findViewById(R.id.toolbarNotification);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvNotif = findViewById(R.id.rvNotif);
        rvNotif.setHasFixedSize(true);

        rvNotif.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotifAdapter();
        rvNotif.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
