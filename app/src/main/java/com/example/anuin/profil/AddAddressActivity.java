package com.example.anuin.profil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anuin.MainActivity;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressActivity extends AppCompatActivity {
    Button btnSimpan;
    Spinner spinnerProv,spinnerKabKota, spinnerKec, spinnerKel;
    EditText editLokasi,editAlamat,editPos,editProperti;
    Toolbar toolbar;
    ApiInterface apiHelper;
    String idProv,idKab,idKec,idKel; //idkel sama properti masih kosong

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        toolbar = findViewById(R.id.toolbarFormAddress);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSimpan = findViewById(R.id.btnSimpan);
        spinnerProv = findViewById(R.id.spinnerProvinsi);
        spinnerKabKota = findViewById(R.id.spinnerKabKota);
        spinnerKec = findViewById(R.id.spinnerKecamatan);
        spinnerKel = findViewById(R.id.spinnerKelurahan);
        editProperti = findViewById(R.id.editProperti);
        editLokasi = findViewById(R.id.editLokasi);
        editAlamat = findViewById(R.id.editAlamat);
        editPos = findViewById(R.id.editPos);

        apiHelper = UtilsApi.getApiService();

        //Ambil data dari API ke Spinner
        fetchProv();

        editLokasi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= editLokasi.getRight() - editLokasi.getTotalPaddingRight()) {

                        return true;
                    }
                }
                return false;
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();

            }

            private void saveAddress() {
                PrefManager manager = new PrefManager(getApplicationContext());
                apiHelper.addAddress(UtilsApi.APP_TOKEN,
                        manager.getTokenUser(),
                        manager.getId(),
                        editAlamat.getText().toString(),
                        editLokasi.getText().toString(),
                        idProv,
                        idKab,
                        idKec,
                        idKel,
                        editPos.getText().toString(),
                        editProperti.getText().toString()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")){
                                    Toast.makeText(AddAddressActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                                    Intent intent = getIntent();
                                    int flag = intent.getIntExtra("CODE", 0);
                                    if (flag == 1){
                                        Intent intent1 = new Intent();
                                        setResult(RESULT_OK, intent1);
                                        finish();
                                    }else{
                                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                                        intent1.putExtra("FLAGPAGE", 2);
                                        startActivity(intent1);
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
                                Toast.makeText(AddAddressActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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
        });
    }//end onCreate

    private void fetchProv() {
        ArrayList<String> prov = new ArrayList<>();
        apiHelper.getProvinsi(UtilsApi.APP_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.get("STATUS").equals("200")) {
                            JSONArray array = object.getJSONArray("DATA");
                            for (int i = 0; i <array.length() ; i++) {
                                prov.add(array.getJSONObject(i).getString("name"));
                            }

                            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_align,prov);
                            adapter.setDropDownViewResource(R.layout.spinner_align);
                            spinnerProv.setAdapter(adapter);

                            spinnerProv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    try {
                                        idProv = array.getJSONObject(position).getString("id");     //untuk addAddress
                                        fetchKabKota(Integer.parseInt(idProv));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

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
    private void fetchKabKota(int id) {
        ArrayList<String> kab = new ArrayList<>();
        apiHelper.getKabupaten(UtilsApi.APP_TOKEN,id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("STATUS").equals("200")){
                            JSONArray array = object.getJSONArray("DATA");
                            for (int i = 0; i < array.length(); i++) {
                                kab.add(array.getJSONObject(i).getString("name"));
                            }

                            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_align,kab);
                            adapter.setDropDownViewResource(R.layout.spinner_align);
                            spinnerKabKota.setAdapter(adapter);

                            spinnerKabKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    try {
                                        idKab = array.getJSONObject(position).getString("id");
//                                        Toast.makeText(AddAddress.this, ""+idKab, Toast.LENGTH_SHORT).show();
                                        fetchKecamatan(Integer.parseInt(idKab));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

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

    private void fetchKecamatan(int id) {
            ArrayList<String> kec = new ArrayList<>();
            apiHelper.getKecamatan(UtilsApi.APP_TOKEN, id).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
                            if (jsonObject.getString("STATUS").equals("200")){
                                JSONArray array = jsonObject.getJSONArray("DATA");
                                for (int i = 0; i < array.length(); i++) {
                                    kec.add(array.getJSONObject(i).getString("name"));
                                }

                                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_align,kec);
                                adapter.setDropDownViewResource(R.layout.spinner_align);
                                spinnerKec.setAdapter(adapter);

                                spinnerKec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        try {
                                            idKec = array.getJSONObject(i).getString("id");
                                            fetchKelurangan(Integer.parseInt(idKec));
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
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
    }

    private void fetchKelurangan(int idKec) {
        ArrayList<String> kel = new ArrayList<>();
        apiHelper.getKelurahan(UtilsApi.APP_TOKEN, idKec).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            JSONArray array = jsonObject.getJSONArray("DATA");
                            for (int i = 0; i < array.length(); i++) {
                                kel.add(array.getJSONObject(i).getString("name"));
                            }

                            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_align,kel);
                            adapter.setDropDownViewResource(R.layout.spinner_align);
                            spinnerKel.setAdapter(adapter);

                            spinnerKel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    try {
                                        idKel = array.getJSONObject(i).getString("id");
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
