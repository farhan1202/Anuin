package com.example.anuin.other.setting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anuin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailChildActivity extends AppCompatActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvContent)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_child);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("TITLE"));
        tvContent.setText(intent.getStringExtra("CONTENT"));
    }
}
