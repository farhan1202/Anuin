package com.example.anuin.home;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.MainActivity;
import com.example.anuin.Modal.LoginDialog;
import com.example.anuin.R;
import com.example.anuin.home.adapter.TakePhotoAdapter;
import com.example.anuin.profil.AddAddressActivity;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
    @BindView(R.id.txtTotalTagihan)
    TextView txtTotalTagihan;
    private int STORAGE_PERMISSION_CODE = 1;

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
    Uri mImageUri;


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
    LoginDialog loginDialog;
    ApiInterface apiInterface;
    PrefManager prefManager;
    Context context;

    int id1, id2, id3, id4;
    int product_jasa_harga, product_jasa_harga_layanan, total_tagihan;

    String kode_post, lokasi_maps;
    @BindView(R.id.txtPesananTitles)
    TextView txtPesananTitles;
    @BindView(R.id.txtCategory)
    TextView txtCategory;
    @BindView(R.id.takePickture)
    ImageView takePickture;
    //    @BindView(R.id.picktureResult)
//    ImageView picktureResult;
    ArrayList<String> list;
    ArrayList<String> imagePath;
    ArrayList<String> datas;
    TakePhotoAdapter takePhotoAdapter;
    int flag = 0;
    String currentImagePath = null;

    Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pesanan);
        ButterKnife.bind(this);
        toolbar = findViewById(R.id.toolbarFormPemesanan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = new ArrayList<>();
        imagePath = new ArrayList<>();

        apiInterface = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        loginDialog = new LoginDialog(this);
        context =this;
        fetchLokasi();
        fetchTitle();

        txtFormTime = findViewById(R.id.txtFormTime);
        txtFormDate = findViewById(R.id.txtFormDate);
        txtFormDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                final int year = calendar.get(Calendar.YEAR);

                dialog = new DatePickerDialog(FormPesananActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String bulan = "" + i1;
                        String tgl = "" + i2;
                        if (i1 < 10) {
                            bulan = "0" + (i1 + 1);
                        }
                        if (i2 < 10) {
                            tgl = "0" + i2;
                        }
                        txtFormDate.setText(i + "-" + bulan + "-" + tgl);
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
                        String jam = i + "";
                        String menit = i1 + "";
                        if (i < 10) {
                            jam = "0" + i;
                        }
                        if (i1 < 10) {
                            menit = "0" + i1;
                        }
                        txtFormTime.setText(jam + ":" + menit);
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            mImageUri = data.getData();

            /*picktureResult.setImageURI(mImageUri);
            picktureResult.setVisibility(View.VISIBLE);*/
            Toast.makeText(getApplicationContext(), "" + getRealPathFromUri(mImageUri), Toast.LENGTH_SHORT).show();

            list.add(mImageUri.toString());
            takePhotoAdapter = new TakePhotoAdapter(mContext, list);
            recyclerPhoto.setAdapter(takePhotoAdapter);
            recyclerPhoto.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            btnClear.setVisibility(View.VISIBLE);


            imagePath.add(getRealPathFromUri(mImageUri));
        }else if(requestCode == 2 && resultCode == RESULT_OK){
            Toast.makeText(mContext, "" + currentImagePath, Toast.LENGTH_SHORT).show();
            list.add(String.valueOf(Uri.fromFile(new File(currentImagePath))));
            takePhotoAdapter = new TakePhotoAdapter(mContext, list);
            recyclerPhoto.setAdapter(takePhotoAdapter);
            recyclerPhoto.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            btnClear.setVisibility(View.VISIBLE);

            imagePath.add(currentImagePath);
        }

        if (requestCode == 27) {
            if (resultCode == RESULT_OK) {
                fetchLokasi();
            }
        }
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
        bodyMap.put("product_jasa_id", createPartFromString(ids + ""));
        bodyMap.put("member_address_id", createPartFromString(lokasi_maps));
        bodyMap.put("provinsi", createPartFromString(id1 + ""));
        bodyMap.put("city", createPartFromString(id2 + ""));
        bodyMap.put("kecamatan", createPartFromString(id3 + ""));
        bodyMap.put("kelurahan", createPartFromString(id4 + ""));
        bodyMap.put("kode_post", createPartFromString(kode_post + ""));
        bodyMap.put("work_date", createPartFromString(txtFormDate.getText().toString() + " " + txtFormTime.getText().toString() + ":00"));
        bodyMap.put("detail_pekerjaan", createPartFromString(txtDeskripsiPekerjaan.getText().toString()));
        bodyMap.put("detail_lokasi", createPartFromString(txtDetailLokasi.getText().toString()));
        bodyMap.put("biaya_panggil", createPartFromString(product_jasa_harga + ""));
        bodyMap.put("biaya_layanan", createPartFromString(product_jasa_harga_layanan + ""));
        bodyMap.put("total_tagihan", createPartFromString(total_tagihan + ""));
        /*bodyMap.put("payment_method", createPartFromString("0"));
        bodyMap.put("payment_driver", createPartFromString("Gopay"));*/


        MultipartBody.Part[] propertyImagePart = new MultipartBody.Part[imagePath.size()];
        for (int i = 0; i < imagePath.size(); i++) {
            File file = new File(imagePath.get(i));
            RequestBody propertyImage = RequestBody.create(MediaType.parse("multipart/from-data"), file);
            propertyImagePart[i] = MultipartBody.Part.createFormData("booking_image[]",
                    file.getName(),
                    propertyImage);
        }


        apiInterface.bookingOrder(map,
                bodyMap,
                propertyImagePart
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            Toast.makeText(mContext, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            loginDialog.dismissLoadingDialog();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("FLAGPAGE", 1);
                            startActivity(intent);
                            finish();
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
                        loginDialog.dismissLoadingDialog();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ERROR", String.valueOf(t));
                loginDialog.dismissLoadingDialog();
                Toast.makeText(FormPesananActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void openFileChooser() {
        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);*/

        /*Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);*/

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePicture.resolveActivity(getPackageManager()) != null){
                        File imageFile = null;
                        try {
                            imageFile = getImageFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (imageFile != null){
                            Uri imageUri = FileProvider.getUriForFile(context, "com.example.android.fileprovider", imageFile);
                            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                            startActivityForResult(takePicture, 2);
                        }
                    }



                } else if (options[item].equals("Choose from Gallery")) {
                    Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                    photoPickerIntent.setType("image/*");
                    startActivityForResult(photoPickerIntent, PICK_IMAGE_REQUEST);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private File getImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageName = "jpg_"+timeStamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(imageName,".jpg",storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;

    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_idx);
        cursor.close();
        return result;
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(FormPesananActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    private void
    fetchTitle() {
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
                                    if (ContextCompat.checkSelfPermission(getApplicationContext(),
                                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                                        requestStoragePermission();
                                    } else {
                                        if (TextUtils.isEmpty(txtDetailLokasi.getText().toString())) {
                                            txtDetailLokasi.setError("Field cant be blank");
                                        } else if (TextUtils.isEmpty(txtDeskripsiPekerjaan.getText().toString())) {
                                            txtDeskripsiPekerjaan.setError("Field cant be blank");
                                        } else if (TextUtils.isEmpty(txtFormDate.getText().toString())) {
                                            txtFormDate.setError("Please enter a work date");
                                        } else if (TextUtils.isEmpty(txtFormTime.getText().toString())) {
                                            txtFormTime.setError("Please enter a work time");
                                        } else if (list == null) {
                                            Toast.makeText(mContext, "Please add a photo", Toast.LENGTH_SHORT).show();
                                        } else if(datas.isEmpty()){
                                            Toast.makeText(mContext, "Mohon tambahkan alamat", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            initProsesOrder(ids);
                                            loginDialog.startLoadingDialog();
                                        }
                                    }
                                }
                            });
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                if (Integer.parseInt(jsonArray1.getJSONObject(i).getString("id")) == ids) {
                                    txtPesananTitles.setText(jsonArray1.getJSONObject(i).getString("product_jasa_title"));
                                    product_jasa_harga = jsonArray1.getJSONObject(i).getInt("product_jasa_harga");
                                    product_jasa_harga_layanan = (int) (0.05 * product_jasa_harga);
                                    total_tagihan = product_jasa_harga + product_jasa_harga_layanan;
                                    txtTotalTagihan.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(total_tagihan) + ""));
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
                            datas = new ArrayList<>();
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
                                            lokasi_maps = jsonArray.getJSONObject(i).getString("id");
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
