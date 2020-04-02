package com.example.anuin.other.submenusetting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

        fetchAbout();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void fetchAbout() {
        apiHelper.getAbout(UtilsApi.APP_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("STATUS").equals("200")) {
                            JSONArray array = object.getJSONArray("DATA");

                            for (int i = 0; i < array.length(); i++) {
                                Glide.with(getApplicationContext())
                                        .load(array.getJSONObject(i).getString("about_image"))
                                        .centerCrop()
                                        .into(imgAbout);
                                txtAbout.setText(array.getJSONObject(i).getString("about_title"));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    descAbout.setText(Html.fromHtml(array.getJSONObject(i).getString("content"), Html.FROM_HTML_MODE_COMPACT) );
                                }else{
                                    descAbout.setText(Html.fromHtml(array.getJSONObject(i).getString("content")));
                                }
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
        super.onBackPressed();
    }
}
