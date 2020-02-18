package com.example.anuin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anuin.Adapter.NotifAdapter;

public class NotifActivity extends AppCompatActivity {
RecyclerView rvNotif;
NotifAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        rvNotif = findViewById(R.id.rvNotif);
        rvNotif.setHasFixedSize(true);

        rvNotif.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotifAdapter();
        rvNotif.setAdapter(adapter);

    }
}
