package com.example.anuin.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anuin.R;
import com.example.anuin.order.adapter.OrderDoneAdapter;
import com.example.anuin.order.adapter.PhotoListAdapter;
import com.example.anuin.order.model.OrderList;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderCompleteDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbarOrderWaiting)
    Toolbar toolbarOrderWaiting;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tvSelesai)
    TextView tvSelesai;
    @BindView(R.id.imageMerchant)
    ImageView imageMerchant;
    @BindView(R.id.namaPemberiJasa)
    TextView namaPemberiJasa;
    @BindView(R.id.ratingPemberiJasa)
    TextView ratingPemberiJasa;
    @BindView(R.id.txtCodeBooking)
    TextView txtCodeBooking;
    @BindView(R.id.orderDate)
    TextView orderDate;
    @BindView(R.id.orderTime)
    TextView orderTime;
    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.txtDetailAlamat)
    TextView txtDetailAlamat;
    @BindView(R.id.txtCategory)
    TextView txtCategory;
    @BindView(R.id.txtDetailJasa)
    TextView txtDetailJasa;
    @BindView(R.id.recyclerPhotoOrder)
    RecyclerView recyclerPhotoOrder;
    @BindView(R.id.txtBiayaPanggil)
    TextView txtBiayaPanggil;
    @BindView(R.id.txtBiayaLayanan)
    TextView txtBiayaLayanan;
    @BindView(R.id.txtTotalTagihan)
    TextView txtTotalTagihan;
    @BindView(R.id.metodeBayar)
    TextView metodeBayar;

    ApiInterface apiInterface;
    PrefManager prefManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarOrderWaiting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerPhotoOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        apiInterface = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        context = this;

        apiInterface.getOrderComplete(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");
                            Intent intent = getIntent();
                            int id = intent.getIntExtra("id", 0);
                            int position = 0;
                            for (int i = 0 ; i < jsonArray.length() ; i++ ){
                                if (id == jsonArray.getJSONObject(i).getInt("id")){
                                    position = i;
                                }
                            }

                            txtBiayaPanggil.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonArray.getJSONObject(position).getInt("biaya_panggil")) + ""));
                            txtBiayaLayanan.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonArray.getJSONObject(position).getInt("biaya_layanan")) + ""));
                            txtTotalTagihan.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonArray.getJSONObject(position).getInt("total_tagihan")) + ""));

                            getLokasiUser(jsonArray.getJSONObject(position).getInt("member_address_id"));

                            JSONObject jsonBooking = new JSONObject(jsonArray.getJSONObject(position).getString("booking_code"));
                            txtCodeBooking.setText("#"+jsonBooking.getString("code_name"));

                            JSONObject jsonProductJasa = new JSONObject(jsonArray.getJSONObject(position).getString("product_jasa"));
                            txtDetailJasa.setText(jsonProductJasa.getString("product_jasa_title"));

                            JSONObject jsonCategory = new JSONObject(jsonProductJasa.getString("category"));
                            txtCategory.setText(jsonCategory.getString("category_title"));

                            JSONObject jsonMerchant = new JSONObject(jsonArray.getJSONObject(position).getString("merchant"));
                            namaPemberiJasa.setText(jsonMerchant.getString("name"));
                            ratingPemberiJasa.setText(jsonMerchant.getString("rating"));
                            Glide
                                    .with(context)
                                    .load(jsonMerchant.getString("merchant_image"))
                                    .into(imageMerchant);

                            JSONObject jsonPayment = new JSONObject(jsonArray.getJSONObject(position).getString("booking_payment"));
                            String payment_type_title = jsonPayment.getString("payment_type_title");
                            String payment_method_title = jsonPayment.getString("payment_method_title");

                            metodeBayar.setText(payment_type_title+" - "+payment_method_title);

                            String date1 = jsonArray.getJSONObject(position).getString("work_date").substring(0, 11);
                            try {
                                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                                SimpleDateFormat formatter1=new SimpleDateFormat("dd MMM yyy");
                                orderDate.setText(formatter1.format(date));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            orderTime.setText(jsonArray.getJSONObject(position).getString("work_date").substring(11));
                            txtDetailAlamat.setText(jsonArray.getJSONObject(position).getString("detail_lokasi"));

                            JSONArray jsonArray12 = jsonArray.getJSONObject(position).getJSONArray("booking_image");

                            ArrayList<String> strings = new ArrayList<>();
                            for (int i = 0; i < jsonArray12.length(); i++) {
                                strings.add(jsonArray12.getJSONObject(i).getString("image_name"));
                            }
                            PhotoListAdapter photoListAdapter = new PhotoListAdapter(context, strings);
                            recyclerPhotoOrder.setAdapter(photoListAdapter);
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

    private void getLokasiUser(int member_address_id) {
        apiInterface.getAddressUserDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(), member_address_id)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")) {
                                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                                    txtAlamat.setText(jsonObject1.getString("lokasi_maps"));
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
