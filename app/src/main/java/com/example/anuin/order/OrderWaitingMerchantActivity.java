package com.example.anuin.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.MainActivity;
import com.example.anuin.Modal.LoginDialog;
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

public class OrderWaitingMerchantActivity extends AppCompatActivity {

    @BindView(R.id.toolbarOrderWaiting)
    Toolbar toolbarOrderWaiting;
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
    @BindView(R.id.txtProdukJasaName)
    TextView txtProdukJasaName;
    @BindView(R.id.recyclerPhotoOrder)
    RecyclerView recyclerPhotoOrder;
    @BindView(R.id.txtBiayaPanggil)
    TextView txtBiayaPanggil;
    @BindView(R.id.txtBiayaLayanan)
    TextView txtBiayaLayanan;
    @BindView(R.id.txtTotalTagihan)
    TextView txtTotalTagihan;
    @BindView(R.id.pembayaran)
    TextView pembayaran;

    ApiInterface apiInterface;
    PrefManager prefManager;
    LoginDialog loginDialog;
    Context context;

    Handler handler = new Handler();
    int apiDelayed = 5 * 1000;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_waiting_merchant);
        ButterKnife.bind(this);
        recyclerPhotoOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        setSupportActionBar(toolbarOrderWaiting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiInterface = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        context = this;
        loginDialog = new LoginDialog(context);
        componentDidMount();

    }

    private void componentDidMount() {
        Intent intent = getIntent();
        loginDialog.startLoadingDialog();
        apiInterface.getBookingDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(), intent.getIntExtra("IDORDER", 0))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")) {
                                    loginDialog.dismissLoadingDialog();

                                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));

                                    txtBiayaPanggil.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonObject1.getInt("biaya_panggil")) + ""));
                                    txtBiayaLayanan.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonObject1.getInt("biaya_layanan")) + ""));
                                    txtTotalTagihan.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonObject1.getInt("total_tagihan")) + ""));

                                    getLokasiUser(jsonObject1.getInt("member_address_id"));

                                    JSONObject jsonBooking = new JSONObject(jsonObject1.getString("booking_code"));
                                    txtCodeBooking.setText("#"+jsonBooking.getString("code_name"));

                                    JSONObject jsonProductJasa = new JSONObject(jsonObject1.getString("product_jasa"));
                                    txtProdukJasaName.setText(jsonProductJasa.getString("product_jasa_title"));

                                    JSONObject jsonCategory = new JSONObject(jsonProductJasa.getString("category"));
                                    txtCategory.setText(jsonCategory.getString("category_title"));

                                    String date1 = jsonObject1.getString("work_date").substring(0, 11);
                                    try {
                                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                                        SimpleDateFormat formatter1=new SimpleDateFormat("dd MMM yyy");
                                        orderDate.setText(formatter1.format(date));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    orderTime.setText(jsonObject1.getString("work_date").substring(11));
                                    txtDetailAlamat.setText(jsonObject1.getString("detail_lokasi"));

                                    JSONArray jsonArray = jsonObject1.getJSONArray("booking_image");
                                    ArrayList<String> strings = new ArrayList<>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        strings.add(jsonArray.getJSONObject(i).getString("image_name"));
                                    }

                                    PhotoListAdapter photoListAdapter = new PhotoListAdapter(context, strings);
                                    recyclerPhotoOrder.setAdapter(photoListAdapter);

                                    JSONObject jsonPayment = new JSONObject(jsonObject1.getString("booking_payment"));
                                    String payment_type_title = jsonPayment.getString("payment_type_title");
                                    String payment_method_title = jsonPayment.getString("payment_method_title");

                                    pembayaran.setText(payment_type_title + " - " + payment_method_title);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                loginDialog.dismissLoadingDialog();
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
                        loginDialog.dismissLoadingDialog();
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "onFailure: " + t.getMessage());
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                //do your function;
                checkMerchhant();
                handler.postDelayed(runnable, apiDelayed);
            }
        }, apiDelayed);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable); //stop handler when activity not visible
    }

    private void checkMerchhant() {
        Intent intent = getIntent();
        apiInterface.getBookingDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(), intent.getIntExtra("IDORDER", 0))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")) {
                                    loginDialog.dismissLoadingDialog();
                                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                                    if (jsonObject1.getString("booking_status").equals("2")) {
                                        loginDialog.startLoadingDialog();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("FLAGPAGE", 1);
                                        startActivity(intent);
                                        finish();
                                    }

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
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
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
