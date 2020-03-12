package com.example.anuin.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anuin.Adapter.OrderModalSheet;
import com.example.anuin.R;
import com.example.anuin.home.adapter.JasaAdapter;
import com.example.anuin.home.interfaces.ProductJasaInterface;
import com.example.anuin.home.model.ProductJasa;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailJasaActivity extends AppCompatActivity {

    Button btnPesan;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiInterface apiInterface;
    Context context;
    TextView txtDetailJasa, tPrice;
    ImageView imageView;

    boolean flags = false;
    int idJasaSelected;

    List<ProductJasa.DATABean.ProductJasaBean> dataBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jasa);
        recyclerView = findViewById(R.id.recyclerJasa);
        btnPesan = findViewById(R.id.buttonPesan);
        toolbar = findViewById(R.id.toolbarDetailJasa);
        txtDetailJasa = findViewById(R.id.txtDetailJasa);
        imageView = findViewById(R.id.imgDetailJasa);
        tPrice = findViewById(R.id.tPrice);
        context = this;
        apiInterface = UtilsApi.getApiService();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        fetchProductJasa(intent);
        /*btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderModalSheet orderModalSheet = new OrderModalSheet();
                orderModalSheet.show(getSupportFragmentManager(), "");
            }
        });*/

    }

    private void fetchProductJasa(Intent intent) {
        int id = intent.getIntExtra("eId", 1);
        apiInterface.getProductJasa(UtilsApi.APP_TOKEN, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

                            final JSONArray jsonArray1 = new JSONArray(jsonArray.getJSONObject(0).getString("product_jasa"));

                            dataBeans = new ArrayList<>();
                            Gson gson = new Gson();
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                ProductJasa.DATABean.ProductJasaBean bean = gson.fromJson(jsonArray1.get(i).toString(), ProductJasa.DATABean.ProductJasaBean.class);
                                dataBeans.add(bean);
                            }

                            txtDetailJasa.setText(jsonArray.getJSONObject(0).getString("category_title"));
                            Glide.with(context)
                                    .load(jsonArray.getJSONObject(0).getString("category_banner"))
                                    .placeholder(new ColorDrawable(Color.parseColor("#FB8E03")))
                                    .centerCrop()
                                    .into(imageView);


                            JasaAdapter jasaAdapter = new JasaAdapter(context, dataBeans, new ProductJasaInterface() {
                                @Override
                                public void onItemClick(int id, boolean flag) {
                                    if (id != -1) {
                                        tPrice.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(dataBeans.get(id).getProduct_jasa_harga()) + ""));
                                        idJasaSelected = dataBeans.get(id).getId();
                                        flags = flag;
                                    }
                                }
                            });
                            recyclerView.setAdapter(jasaAdapter);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setNestedScrollingEnabled(false);

                            btnPesan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (flags) {
                                        OrderModalSheet orderModalSheet = new OrderModalSheet();
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("id", idJasaSelected);
                                        orderModalSheet.setArguments(bundle);
                                        orderModalSheet.show(getSupportFragmentManager(), "");
                                    } else {
                                        Toast.makeText(DetailJasaActivity.this, "Product jasa tidak ada", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
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
