package com.example.anuin.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anuin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderChatActivity extends AppCompatActivity {


    @BindView(R.id.btnBacks)
    ImageView btnBacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_chat);
        ButterKnife.bind(this);
        btnBacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}
