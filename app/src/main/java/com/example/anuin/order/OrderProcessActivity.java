package com.example.anuin.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.anuin.Modal.WorkDone;
import com.example.anuin.R;

public class OrderProcessActivity extends AppCompatActivity {
Button btnOrderDone,btnOrderCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_process);
        btnOrderDone = findViewById(R.id.btnOrderDone);
        btnOrderCancel = findViewById(R.id.btnOrderCancel);
        btnOrderDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkDone done = new WorkDone();
                done.show(getSupportFragmentManager(),"WorkDone");
            }
        });
        btnOrderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
