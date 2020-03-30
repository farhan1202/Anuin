package com.example.anuin.other.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import com.example.anuin.R;
import com.example.anuin.other.adapter.PrivacyPolicyAdapter;
import com.example.anuin.other.model.PrivacyPolicyModel;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyPolicyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    List<PrivacyPolicyModel.DATABean> beanList;
    ApiInterface apiHelper;
    PrivacyPolicyAdapter privacyPolicyAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        toolbar = findViewById(R.id.toolbarSetting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pfivacy Plolicy");
        context = this;
        apiHelper = UtilsApi.getApiService();
        recyclerView = findViewById(R.id.rvPrivacyPolicy);

        initPrivacy();

    }

    private void initPrivacy() {
        apiHelper.getPrivacyPolicy(UtilsApi.APP_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

                            beanList = new ArrayList<>();
                            Gson gson = new Gson();
                            for (int i = 0 ; i< jsonArray.length(); i++){
                                PrivacyPolicyModel.DATABean datalist = gson.fromJson(jsonArray.getJSONObject(i).toString(), PrivacyPolicyModel.DATABean.class);
                                beanList.add(datalist);
                            }

                            privacyPolicyAdapter = new PrivacyPolicyAdapter(context, beanList);
                            recyclerView.setAdapter(privacyPolicyAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setHasFixedSize(true);

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
