package com.example.anuin.home;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.R;
import com.example.anuin.home.adapter.TakePhotoAdapter;
import com.example.anuin.profil.AddAddressActivity;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPesananActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    @BindView(R.id.recyclerPhoto)
    RecyclerView recyclerPhoto;
    @BindView(R.id.btnClear)
    ImageButton btnClear;
    @BindView(R.id.btnProses)
    Button btnProses;
    @BindView(R.id.txtDetailLokasi)
    EditText txtDetailLokasi;
    @BindView(R.id.txtDeskripsiPekerjaan)
    EditText txtDeskripsiPekerjaan;
    private Uri mImageUri;

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
    String kode_post;
    @BindView(R.id.txtPesananTitles)
    TextView txtPesananTitles;
    @BindView(R.id.txtCategory)
    TextView txtCategory;
    @BindView(R.id.takePickture)
    ImageView takePickture;
    //    @BindView(R.id.picktureResult)
//    ImageView picktureResult;
    ArrayList<String> list;
    TakePhotoAdapter takePhotoAdapter;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pesanan);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbarFormPemesanan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();

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

        takePickture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() < 3) {
                    openFileChooser();
                } else {
                    Toast.makeText(mContext, "Maksimal 3 gambar", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                takePhotoAdapter = new TakePhotoAdapter(mContext, list);
                recyclerPhoto.setAdapter(takePhotoAdapter);
                btnClear.setVisibility(View.GONE);
            }
        });

        /*picktureResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lagi = "";
                for (int i = 0; i < list.size() ; i++){
                    lagi += list.get(i) + ", ";
                }
                Intent intent = new Intent(getApplicationContext(), ViewImageActivity.class);
                intent.putExtra("URI", mImageUri.toString());
                Toast.makeText(mContext, "" + lagi, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });*/


    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MediaType.parse("text/plain"), descriptionString);
    }

    private void initProsesOrder(int ids) {
        Map<String, String> map = new HashMap<>();
        map.put("APP_TOKEN", UtilsApi.APP_TOKEN);
        map.put("USER_TOKEN", prefManager.getTokenUser());
        Map<String, RequestBody> bodyMap = new HashMap<>();
        bodyMap.put("member_id", createPartFromString(prefManager.getId() + ""));
        bodyMap.put("product_jasa_id", createPartFromString(ids+ ""));
        bodyMap.put("member_address_id", createPartFromString("26"));
        bodyMap.put("provinsi", createPartFromString(id1+ ""));
        bodyMap.put("city", createPartFromString(id2 + ""));
        bodyMap.put("kecamatan", createPartFromString(id3 + ""));
        bodyMap.put("kelurahan", createPartFromString(id4 + ""));
        bodyMap.put("kode_post", createPartFromString(kode_post + ""));
        bodyMap.put("work_date",  createPartFromString("2020-01-20 10:10:10"));
        bodyMap.put("detail_pekerjaan", createPartFromString(txtDeskripsiPekerjaan.getText().toString()));
        bodyMap.put("detail_lokasi", createPartFromString(txtDetailLokasi.getText().toString()));
        bodyMap.put("biaya_panggil", createPartFromString("100000"));
        bodyMap.put("biaya_layanan", createPartFromString("10000"));
        bodyMap.put("total_tagihan", createPartFromString("110000"));
        bodyMap.put("payment_method",  createPartFromString("1"));
        bodyMap.put("payment_driver", createPartFromString("Gopay"));
        /*RequestBody member_id = RequestBody.create(MediaType.parse("text/plain"), prefManager.getId() + "");
        RequestBody product_jasa_id = RequestBody.create(MediaType.parse("text/plain"), ids + "");
        RequestBody member_address_id = RequestBody.create(MediaType.parse("text/plain"), ids + "26");
        RequestBody provinsi = RequestBody.create(MediaType.parse("text/plain"), id1 + "");
        RequestBody city = RequestBody.create(MediaType.parse("text/plain"), id2 + "");
        RequestBody kecamatan = RequestBody.create(MediaType.parse("text/plain"), id3 + "");
        RequestBody kelurahan = RequestBody.create(MediaType.parse("text/plain"), id4 + "");
        RequestBody kode_post1 = RequestBody.create(MediaType.parse("text/plain"), kode_post);
        RequestBody work_date = RequestBody.create(MediaType.parse("text/plain"), "2020-01-20");
        RequestBody detail_pekerjaan = RequestBody.create(MediaType.parse("text/plain"), txtDeskripsiPekerjaan.getText().toString() );
        RequestBody detail_lokasi = RequestBody.create(MediaType.parse("text/plain"), txtDetailLokasi.getText().toString() );
        RequestBody biaya_panggil = RequestBody.create(MediaType.parse("text/plain"), "100000" );
        RequestBody biaya_layanan = RequestBody.create(MediaType.parse("text/plain"), "10000" );
        RequestBody total_tagihan = RequestBody.create(MediaType.parse("text/plain"), "110000" );
        RequestBody payment_method = RequestBody.create(MediaType.parse("text/plain"), "1" );
        RequestBody payment_driver = RequestBody.create(MediaType.parse("text/plain"), "Gopay" );*/

        File file = new File(mImageUri.getPath());
        RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"),
                file);
        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("booking_image[]",
                file.getName(),
                propertyImage);

        apiInterface.bookingOrder(map,
                bodyMap,
                propertyImagePart
                ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            Toast.makeText(mContext, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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

            }
        });


    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            mImageUri = data.getData();
            /*picktureResult.setImageURI(mImageUri);
            picktureResult.setVisibility(View.VISIBLE);
            list.add(flag + "");
            flag++;*/
            list.add(mImageUri.toString());
            takePhotoAdapter = new TakePhotoAdapter(mContext, list);
            recyclerPhoto.setAdapter(takePhotoAdapter);
            recyclerPhoto.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            btnClear.setVisibility(View.VISIBLE);
        }

        if (requestCode == 27) {
            if (resultCode == RESULT_OK) {
                fetchLokasi();
            }
        }
    }

    private void fetchTitle() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id1", 0);
        int ids = intent.getIntExtra("id", 0);
        apiInterface.getProductJasa(UtilsApi.APP_TOKEN, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");
                            txtCategory.setText(jsonArray.getJSONObject(0).getString("category_title"));
                            JSONArray jsonArray1 = new JSONArray(jsonArray.getJSONObject(0).getString("product_jasa"));
                            btnProses.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    initProsesOrder(id);
                                }
                            });
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                if (Integer.parseInt(jsonArray1.getJSONObject(i).getString("id")) == ids) {
                                    txtPesananTitles.setText(jsonArray1.getJSONObject(i).getString("product_jasa_title"));
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
                            if (!datas.isEmpty()) {
                                datas.add("Tambah Alamat");
                            } else {
                                showAlertDialog();

                            }

                            ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, datas);
                            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinnerLokasi.setAdapter(spinnerAdapter);

                            spinnerLokasi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    try {
                                        if (i == datas.size() - 1) {
                                            Intent intent = new Intent(getApplicationContext(), AddAddressActivity.class);
                                            intent.putExtra("CODE", 1);
                                            startActivityForResult(intent, 27);
                                        } else {
                                            id1 = Integer.parseInt(jsonArray.getJSONObject(i).getString("provinsi"));
                                            id2 = Integer.parseInt(jsonArray.getJSONObject(i).getString("city"));
                                            id3 = Integer.parseInt(jsonArray.getJSONObject(i).getString("kecamatan"));
                                            id4 = Integer.parseInt(jsonArray.getJSONObject(i).getString("kelurahan"));
                                            kode_post = jsonArray.getJSONObject(i).getString("kode_post");
                                            fetchWilayah();
                                        }


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

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Please add your address first")
                .setCancelable(true)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getApplicationContext(), AddAddressActivity.class);
                        intent.putExtra("CODE", 1);
                        startActivityForResult(intent, 27);
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
