package com.example.anuin.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anuin.Modal.WorkDone;
import com.example.anuin.R;
import com.example.anuin.order.adapter.PhotoListAdapter;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderProcessActivity extends AppCompatActivity {

    Toolbar toolbar;
    @BindView(R.id.toolbarOrderWaiting)
    Toolbar toolbarOrderWaiting;
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
    @BindView(R.id.txtBiayaPanggil)
    TextView txtBiayaPanggil;
    @BindView(R.id.txtBiayaLayanan)
    TextView txtBiayaLayanan;
    @BindView(R.id.txtTotalTagihan)
    TextView txtTotalTagihan;
    @BindView(R.id.metodeBayar)
    TextView metodeBayar;
    @BindView(R.id.btnOrderCancel)
    Button btnOrderCancel;
    @BindView(R.id.btnOrderDone)
    Button btnOrderDone;
    @BindView(R.id.relativeBottom)
    RelativeLayout relativeBottom;
    @BindView(R.id.imageMerchant)
    ImageView imageMerchant;
    @BindView(R.id.recyclerPhotoOrder)
    RecyclerView recyclerPhotoOrder;

    ApiInterface apiInterface;
    PrefManager prefManager;
    Context context;
    @BindView(R.id.btnChat)
    RelativeLayout btnChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_process);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarOrderWaiting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerPhotoOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        apiInterface = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        context = this;
        componentDidMount();
        btnOrderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void componentDidMount() {
        Intent intent = getIntent();
        apiInterface.getBookingDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(), intent.getIntExtra("IDORDER", 0))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")) {

                                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));

                                    txtBiayaPanggil.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonObject1.getInt("biaya_panggil")) + ""));
                                    txtBiayaLayanan.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonObject1.getInt("biaya_layanan")) + ""));
                                    txtTotalTagihan.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonObject1.getInt("total_tagihan")) + ""));

                                    getLokasiUser(jsonObject1.getInt("member_address_id"));

                                    JSONObject jsonBooking = new JSONObject(jsonObject1.getString("booking_code"));
                                    txtCodeBooking.setText(jsonBooking.getString("code_name"));

                                    JSONObject jsonProductJasa = new JSONObject(jsonObject1.getString("product_jasa"));
                                    txtDetailJasa.setText(jsonProductJasa.getString("product_jasa_title"));

                                    JSONObject jsonCategory = new JSONObject(jsonProductJasa.getString("category"));
                                    txtCategory.setText(jsonCategory.getString("category_title"));

                                    JSONObject jsonMerchant = new JSONObject(jsonObject1.getString("merchant"));
                                    namaPemberiJasa.setText(jsonMerchant.getString("name"));
                                    ratingPemberiJasa.setText(jsonMerchant.getString("rating"));
                                    Glide
                                            .with(context)
                                            .load(jsonMerchant.getString("merchant_image"))
                                            .into(imageMerchant);

                                    JSONObject jsonPayment = new JSONObject(jsonObject1.getString("booking_payment"));
                                    String payment_type_title = jsonPayment.getString("payment_type_title");
                                    String payment_method_title = jsonPayment.getString("payment_method_title");

                                    metodeBayar.setText(payment_type_title + " - " + payment_method_title);


                                    orderDate.setText(jsonObject1.getString("work_date").substring(0, 11));
                                    orderTime.setText(jsonObject1.getString("work_date").substring(11));
                                    txtDetailAlamat.setText(jsonObject1.getString("detail_lokasi"));

                                    JSONArray jsonArray = jsonObject1.getJSONArray("booking_image");
                                    ArrayList<String> strings = new ArrayList<>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        strings.add(jsonArray.getJSONObject(i).getString("image_name"));
                                    }

                                    PhotoListAdapter photoListAdapter = new PhotoListAdapter(context, strings);
                                    recyclerPhotoOrder.setAdapter(photoListAdapter);

                                    btnOrderDone.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            try {
                                                WorkDone done = new WorkDone();
                                                Bundle bundle = new Bundle();
                                                bundle.putInt("booking_id", jsonObject1.getInt("id"));
                                                done.setArguments(bundle);
                                                done.show(getSupportFragmentManager(), "WorkDone");
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                    btnChat.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(getApplicationContext(), OrderChatActivity.class));
                                        }
                                    });

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Toast.makeText(context, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                        Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
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
