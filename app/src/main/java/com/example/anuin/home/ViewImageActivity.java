package com.example.anuin.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.anuin.R;

import java.net.URI;

public class ViewImageActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        imageView = findViewById(R.id.imageView);
        Intent intent = getIntent();
        String uri = intent.getStringExtra("URI");
        imageView.setImageURI(Uri.parse(uri));
    }
}
