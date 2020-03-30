package com.example.anuin.other.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.R;
import com.example.anuin.other.adapter.HelpAdapter;
import com.example.anuin.other.model.HelpModel;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelpActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
   /* private ExpandableListView expandableListView;

    private ExpandableListAdapter expandableListAdapter;*/

    private List<HelpModel.DATABean> listHelp;
    private HelpAdapter helpAdapter;


    /*private HashMap<Integer, List<HelpModel.DATABean>> listDataChild;*/

    ApiInterface apiInterface;
    Context context;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbarSetting);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        context = this;
        apiInterface = UtilsApi.getApiService();

        initListData();
    }


    private void initListData() {
        apiInterface.getHelp(UtilsApi.APP_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

                            listHelp = new ArrayList<>();
                            Gson gson = new Gson();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                HelpModel.DATABean dataBean = gson.fromJson(jsonArray.getJSONObject(i).toString(), HelpModel.DATABean.class);
                                listHelp.add(dataBean);


                                helpAdapter = new HelpAdapter(listHelp,context);
                                recyclerView.setAdapter(helpAdapter);
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
