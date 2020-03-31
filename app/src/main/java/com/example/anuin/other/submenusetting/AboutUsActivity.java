package com.example.anuin.other.submenusetting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anuin.R;
import com.example.anuin.introNlogin.LoginActivity;
import com.example.anuin.other.SettingActivity;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutUsActivity extends AppCompatActivity {
    @BindView(R.id.backAbout)
    ImageView btnBack;
    @BindView(R.id.imgAbout)
    ImageView imgAbout;
    @BindView(R.id.txtAbout)
    TextView txtAbout;
    @BindView(R.id.descAbout)
    TextView descAbout;

    ApiInterface apiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        apiHelper = UtilsApi.getApiService();

        //fetchAbout();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
        super.onBackPressed();
    }
}
