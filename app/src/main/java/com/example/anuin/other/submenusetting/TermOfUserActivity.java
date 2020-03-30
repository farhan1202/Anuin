package com.example.anuin.other.submenusetting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.anuin.R;
import com.example.anuin.other.model.TermUse;
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

public class TermOfUserActivity extends AppCompatActivity {
@BindView(R.id.recyclerTerm)
    RecyclerView recyclerTerm;

Context context;
rowTermAdapter rowTermAdapter;
List<TermUse.DATABean> dataBeanList;

ApiInterface apiHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_of_user);
        ButterKnife.bind(this);

        context = getApplicationContext();
        apiHelper = UtilsApi.getApiService();



        fetchTerm();
    }

    private void fetchTerm() {
        apiHelper.termOfUse(UtilsApi.APP_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("STATUS").equals("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            dataBeanList = new ArrayList<>();
                            Gson gson = new Gson();
                            for (int i = 0; i <array.length() ; i++) {
                                TermUse.DATABean dataBean = gson.fromJson(array.getJSONObject(i).toString(),TermUse.DATABean.class);
                                dataBeanList.add(dataBean);
                            }


                            rowTermAdapter = new rowTermAdapter(context,dataBeanList);
                            recyclerTerm.setAdapter(rowTermAdapter);
                            recyclerTerm.setLayoutManager(new LinearLayoutManager(context));
                            recyclerTerm.setHasFixedSize(true);


                        }else{
                            Toast.makeText(context, "Respon 400", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Toast.makeText(context, ""+object.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
