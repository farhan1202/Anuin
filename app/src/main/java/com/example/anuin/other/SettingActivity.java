package com.example.anuin.other;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.anuin.R;
import com.example.anuin.other.setting.HelpActivity;
import com.example.anuin.other.setting.PrivacyPolicyActivity;
import com.example.anuin.other.submenusetting.AboutUsActivity;
import com.example.anuin.other.submenusetting.TermOfUserActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends AppCompatActivity {


    Toolbar toolbar;
    @BindView(R.id.toolbarSetting)
    Toolbar toolbarSetting;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.appS1)
    Switch appS1;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tvHelp)
    TextView tvHelp;
    @BindView(R.id.tvTerm)
    TextView tvTerm;
    @BindView(R.id.tvprivacy)
    TextView tvprivacy;
    @BindView(R.id.tv7)
    TextView tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbarSetting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Setting");
        tvHelp = findViewById(R.id.tvHelp);

        tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HelpActivity.class));
            }
        });

        tvprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrivacyPolicyActivity.class));
            }
        });

        tvTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TermOfUserActivity.class);
                startActivity(intent);
            }
        });
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                startActivity(intent);
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

