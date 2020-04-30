package com.example.anuin.other;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anuin.Adapter.NotifAdapter;
import com.example.anuin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class NotifActivity extends AppCompatActivity {
RecyclerView rvNotif;
NotifAdapter adapter;
Toolbar toolbar;
String TAG = "TAG";
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

        getToken();
    }

    private void getToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()){
                    Log.w("getInstanceFailed",task.getException());
                    return;
                }

                //get new InstanceID Token
                String token = task.getResult().getToken();
//                String getMessage = getString(R.string.msg_token_fmt,token);
                Log.e(TAG, token);
                Toast.makeText(NotifActivity.this, token, Toast.LENGTH_SHORT).show();
            }
        });
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
