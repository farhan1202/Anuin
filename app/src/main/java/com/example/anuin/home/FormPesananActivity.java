package com.example.anuin.home;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPesananActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText txtFormDate, txtFormTime;
    Calendar calendar, calender1;
    DatePickerDialog dialog;
    Time time;
    TimePickerDialog dialog1;
    Context mContext = this;
    @BindView(R.id.spinnerLokasi)
    Spinner spinnerLokasi;
    @BindView(R.id.fieldProvinsi)
    TextView fieldProvinsi;
    @BindView(R.id.fieldKabupaten)
    TextView fieldKabupaten;
    @BindView(R.id.fieldKecamatan)
    TextView fieldKecamatan;
    @BindView(R.id.fieldKelurahan)
    TextView fieldKelurahan;

    ApiInterface apiInterface;
    PrefManager prefManager;
    int id1, id2, id3, id4;
    @BindView(R.id.txtPesananTitles)
    TextView txtPesananTitles;
    @BindView(R.id.txtCategory)
    TextView txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pesanan);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbarFormPemesanan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        fetchLokasi();
        fetchTitle();

        txtFormTime = findViewById(R.id.txtFormTime);
        txtFormDate = findViewById(R.id.txtFormDate);
        txtFormDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH + 1);
                final int year = calendar.get(Calendar.YEAR);

                dialog = new DatePickerDialog(FormPesananActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtFormDate.setText(i2 + "/" + i1 + "/" + i);
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        txtFormTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calender1 = Calendar.getInstance();
                final int hour = calender1.get(Calendar.HOUR_OF_DAY);
                final int minute = calender1.get(Calendar.MINUTE);

                dialog1 = new TimePickerDialog(FormPesananActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        txtFormTime.setText(i + ":" + i1);
                    }
                }, hour, minute, DateFormat.is24HourFormat(mContext));
                dialog1.show();
            }
        });

    }

    private void fetchTitle() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id1", 0);
        int ids = intent.getIntExtra("id", 0);
        apiInterface.getProductJasa(UtilsApi.APP_TOKEN, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");
                            txtCategory.setText(jsonArray.getJSONObject(0).getString("category_title"));
                            JSONArray jsonArray1 = new JSONArray(jsonArray.getJSONObject(0).getString("product_jasa"));

                            for (int i = 0 ; i < jsonArray1.length() ; i++){
                                if (Integer.parseInt(jsonArray1.getJSONObject(i).getString("id")) == ids){
                                    txtPesananTitles.setText(jsonArray1.getJSONObject(i).getString("product_jasa_title"));
                                }
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(mContext, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FormPesananActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchLokasi() {
        apiInterface.getAddressUser(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");
                            ArrayList<String> datas = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                datas.add(jsonArray.getJSONObject(i).getString("alamat"));
                            }
                            ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, datas);
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerLokasi.setAdapter(spinnerAdapter);

                            spinnerLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    try {
                                        id1 = Integer.parseInt(jsonArray.getJSONObject(i).getString("provinsi"));
                                        id2 = Integer.parseInt(jsonArray.getJSONObject(i).getString("city"));
                                        id3 = Integer.parseInt(jsonArray.getJSONObject(i).getString("kecamatan"));
                                        id4 = Integer.parseInt(jsonArray.getJSONObject(i).getString("kelurahan"));
                                        fetchWilayah();

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

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
                        Toast.makeText(mContext, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchWilayah() {
        apiInterface.getDetailProvinsi(UtilsApi.APP_TOKEN, id1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            fieldProvinsi.setText(jsonObject1.getString("name"));
                            kabupaten();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.errorBody().string());
                        Toast.makeText(FormPesananActivity.this, "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FormPesananActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void kabupaten() {
        apiInterface.getDetailKabupaten(UtilsApi.APP_TOKEN, id2).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            fieldKabupaten.setText(jsonObject1.getString("name"));
                            kecamatan();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.errorBody().string());
                        Toast.makeText(FormPesananActivity.this, "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FormPesananActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void kecamatan() {
        apiInterface.getDetailKecamatan(UtilsApi.APP_TOKEN, id3).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            fieldKecamatan.setText(jsonObject1.getString("name"));
                            kelurahan();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.errorBody().string());
                        Toast.makeText(FormPesananActivity.this, "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FormPesananActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void kelurahan() {
        apiInterface.getDetailKelurahan(UtilsApi.APP_TOKEN, id4).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            fieldKelurahan.setText(jsonObject1.getString("name"));

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.errorBody().string());
                        Toast.makeText(FormPesananActivity.this, "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FormPesananActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
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
