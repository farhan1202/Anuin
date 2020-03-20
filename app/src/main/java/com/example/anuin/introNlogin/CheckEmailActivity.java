package com.example.anuin.introNlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anuin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckEmailActivity extends AppCompatActivity {


    @BindView(R.id.btnBackToLogin)
    TextView btnBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_email);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnBackToLogin)
    public void onViewClicked() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
