package com.example.anuin.profil;

import androidx.appcompat.app.AppCompatActivity;
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

public class AddAddress extends AppCompatActivity {
    Button btnSimpan;
    Spinner spinnerProv,spinnerKabKota, spinnerKec, spinnerKel;
    EditText editLokasi,editAlamat,editPos,editProperti;
    Toolbar toolbar;
    ApiInterface apiHelper;
    String idProv,idKab,idKec,idKel,idProperti; //idkel sama properti masih kosong

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
        fetchKec();

        editLokasi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= editLokasi.getRight() - editLokasi.getTotalPaddingRight()) {
                        Toast.makeText(AddAddress.this, "Drawable Clicked", Toast.LENGTH_SHORT).show();
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
                        idKab,idKec,idKel,
                        editPos.getText().toString(),idProperti
                        ).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject object = new JSONObject(response.body().string());
                                if (object.getString("STATUS").equals("200")){
                                    Toast.makeText(AddAddress.this, object.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(AddAddress.this,object.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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

    private void fetchKec() {
        apiHelper.getKecamatan(UtilsApi.APP_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("STATUS").equals("200")){
                            Toast.makeText(AddAddress.this, "" + object.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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
