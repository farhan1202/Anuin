package com.example.anuin.order;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.anuin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewOrderPhotoActivity extends AppCompatActivity {

    @BindView(R.id.photoView)
    ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_photo);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Glide
                .with(this)
                .load(intent.getStringExtra("URIPHOTO"))
                .into(photoView);
    }
}
