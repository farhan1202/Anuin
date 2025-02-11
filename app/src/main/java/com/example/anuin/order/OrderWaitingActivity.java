package com.example.anuin.order;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderWaitingActivity extends AppCompatActivity {
    public static int METODE_PAYMENT = 27;
    int metode = -1;
    String payment_driver = "";

    Toolbar toolbar;
    @BindView(R.id.txtCoundown)
    TextView txtCoundown;
    ApiInterface apiInterface;
    PrefManager prefManager;
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
    @BindView(R.id.btnPaymentMethode)
    Button btnPaymentMethode;
    @BindView(R.id.relativeBottom)
    RelativeLayout relativeBottom;
    @BindView(R.id.txtBiayaPanggil)
    TextView txtBiayaPanggil;
    @BindView(R.id.txtBiayaLayanan)
    TextView txtBiayaLayanan;
    @BindView(R.id.txtTotalTagihan)
    TextView txtTotalTagihan;
    @BindView(R.id.recyclerPhotoOrder)
    RecyclerView recyclerPhotoOrder;
    Context context;
    @BindView(R.id.btnBatal)
    Button btnBatal;
    @BindView(R.id.pembayaran)
    TextView pembayaran;
    @BindView(R.id.btnSelesai)
    Button btnSelesai;

    LoginDialog loginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_waiting);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbarOrderWaiting);
        recyclerPhotoOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiInterface = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        context = this;
        loginDialog = new LoginDialog(context);
        componentDidMount();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == METODE_PAYMENT) {
            if (resultCode == RESULT_OK) {
                fetchPembayaran(data);
            }
        }
    }

    private void fetchPembayaran(Intent data) {
        loginDialog.startLoadingDialog();
        apiInterface.getMethodPaymentDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), data.getIntExtra("metode", 0))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")) {
                                    loginDialog.dismissLoadingDialog();
                                    JSONObject jsonObject1 = new JSONObject(jsonObject.getJSONObject("DATA").toString());
                                    JSONArray jsonArray = jsonObject1.getJSONArray("payment_method");
//                                    Toast.makeText(context, "" + jsonArray.getJSONObject(0).getInt("id"), Toast.LENGTH_SHORT).show();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        if (data.getIntExtra("id", 0) == jsonArray.getJSONObject(i).getInt("id")) {
                                            pembayaran.setText(jsonArray.getJSONObject(i).getString("title"));
                                            pembayaran.setVisibility(View.VISIBLE);
                                            btnPaymentMethode.setText("UBAH PEMBAYARAN");
                                            metode = data.getIntExtra("metode", -1);
                                            payment_driver = jsonArray.getJSONObject(i).getString("variable_method");
                                        }
                                    }
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
                        Toast.makeText(context, "Connection Error ", Toast.LENGTH_SHORT).show();
                    }
                });
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

                                    String hour = jsonObject1.getString("created_at").substring(11, 13);
                                    String menit = jsonObject1.getString("created_at").substring(14, 16);
                                    String detik = jsonObject1.getString("created_at").substring(17);
                                    int totalDetikOrder = (Integer.parseInt(hour) * 3600) + (Integer.parseInt(menit) * 60) + Integer.parseInt(detik);
                                        countdownPayment(totalDetikOrder);

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

                                    btnBatal.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            onBackPressed();
                                        }
                                    });

                                    btnSelesai.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (metode == -1) {
                                                Toast.makeText(context, "Harap Pilih Metode Pembayaran", Toast.LENGTH_SHORT).show();
                                            } else {
                                                try {
                                                    initPayment(jsonObject1.getInt("id"), jsonObject1.getInt("biaya_panggil"), jsonObject1.getInt("biaya_layanan"),
                                                            jsonObject1.getInt("total_tagihan"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    });

                                    btnPaymentMethode.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            try {
                                                Intent intent = new Intent(getApplicationContext(), MetodePembayaranActivity.class);
                                                intent.putExtra("total_tagihan", jsonObject1.getInt("total_tagihan"));
                                                intent.putExtra("product_jasa_title", jsonProductJasa.getString("product_jasa_title"));
                                                intent.putExtra("category_title", jsonCategory.getString("category_title"));
                                                startActivityForResult(intent, METODE_PAYMENT);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }

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
                                loginDialog.dismissLoadingDialog();
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Toast.makeText(OrderWaitingActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(OrderWaitingActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initPayment(int id, int biaya_panggil, int biaya_layanan, int total_tagihan) {
        apiInterface.bookingPayment(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(),
                id,
                biaya_panggil,
                biaya_layanan,
                total_tagihan,
                metode + "",
                payment_driver).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            Toast.makeText(context, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(OrderWaitingActivity.this, MainActivity.class);
//                            intent.putExtra("FLAGPAGE", 1);
//                            startActivity(intent);
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getJSONObject("DATA").toString());
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(jsonObject1.getString("redirect_url")));
                            startActivity(browserIntent);
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

    private void countdownPayment(int totalDetikOrder) {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = simpleDateFormat.format(calendar.getTime());
        String hour1 = date.substring(0, 2);
        String menit1 = date.substring(3, 5);
        String detik1 = date.substring(6);
        int totalDetikOrderNow = (Integer.parseInt(hour1) * 3600) + (Integer.parseInt(menit1) * 60) + Integer.parseInt(detik1);
        if (totalDetikOrderNow < totalDetikOrder) {
            totalDetikOrderNow += 86400;
        }
        int total = (totalDetikOrderNow - totalDetikOrder);

        new CountDownTimer(2100000 - (total * 1000), 1000) {

            public void onTick(long millisUntilFinished) {

                long i = millisUntilFinished;
                updateTime(i);
                //here you can have your logic to set text to edittext
            }

            private void updateTime(long i) {
                int minute = (int) i / 60000;
                int secons = (int) i % 60000 / 1000;

                String timerLeft = "" + minute;
                timerLeft += ":";
                if (secons < 10) timerLeft += "0";
                timerLeft += secons;
                txtCoundown.setText(timerLeft);

            }

            public void onFinish() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }

        }.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPayment();
    }

    private void checkPayment() {
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
                                    if (jsonObject1.getString("booking_status").equals("1")) {
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
                                Toast.makeText(OrderWaitingActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(OrderWaitingActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
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
