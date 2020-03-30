package com.example.anuin.profil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAddressActivity extends AppCompatActivity {

    @BindView(R.id.toolbarFormUpdateAddress)
    Toolbar toolbarFormUpdateAddress;
    @BindView(R.id.editAlamat)
    EditText editAlamat;
    @BindView(R.id.editLokasi)
    EditText editLokasi;
    @BindView(R.id.spinnerProvinsi)
    Spinner spinnerProvinsi;
    @BindView(R.id.spinnerKabKota)
    Spinner spinnerKabKota;
    @BindView(R.id.spinnerKecamatan)
    Spinner spinnerKecamatan;
    @BindView(R.id.spinnerKelurahan)
    Spinner spinnerKelurahan;
    @BindView(R.id.editPos)
    EditText editPos;
    @BindView(R.id.editProperti)
    EditText editProperti;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    ApiInterface apiHelper;
    String idProvs,idKab,idKec,idKel;
    String provs, kabs, kecs, kels;
    PrefManager prefManager;
    int idAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarFormUpdateAddress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        idAddress = intent.getIntExtra("eIDADDRESS", 0);

        apiHelper = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        fetchDetailAddress();
    }

    private void fetchDetailAddress() {
        apiHelper.getAddressUserDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(), idAddress).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            editAlamat.setText(jsonObject1.getString("alamat"));
                            editLokasi.setText(jsonObject1.getString("lokasi_maps"));
                            editPos.setText(jsonObject1.getString("kode_post"));
                            editProperti.setText(jsonObject1.getString("property"));
                            provs = jsonObject1.getString("provinsi");
                            kabs = jsonObject1.getString("city");
                            kecs = jsonObject1.getString("kecamatan");
                            kels = jsonObject1.getString("kelurahan");
                            fetchProv();
                            btnUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    initUpdate();
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
                        Toast.makeText(UpdateAddressActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(UpdateAddressActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUpdate() {
        apiHelper.updateAddress(UtilsApi.APP_TOKEN,
                prefManager.getTokenUser(),
                prefManager.getId(),
                idAddress,
                editAlamat.getText().toString(),
                editLokasi.getText().toString(),
                idProvs,
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
                            Toast.makeText(UpdateAddressActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("FLAGPAGE", 2);
                            startActivity(intent);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(UpdateAddressActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(UpdateAddressActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });
    }

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
                            int posisition = -1;
                            for (int i = 0; i <array.length() ; i++) {
                                if (array.getJSONObject(i).getString("id").equals(provs)){
                                    posisition = i;
                                }
                                prov.add(array.getJSONObject(i).getString("name"));
                            }

                            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_align,prov);
                            adapter.setDropDownViewResource(R.layout.spinner_align);
                            spinnerProvinsi.setAdapter(adapter);

                            if (posisition != -1)
                                spinnerProvinsi.setSelection(posisition);


                            spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    try {
                                        idProvs = array.getJSONObject(position).getString("id");     //untuk addAddress
                                        fetchKabKota(Integer.parseInt(idProvs));
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
                            int posisition = -1;
                            for (int i = 0; i < array.length(); i++) {
                                if (array.getJSONObject(i).getString("id").equals(kabs)){
                                    posisition = i;
                                }
                                kab.add(array.getJSONObject(i).getString("name"));
                            }

                            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_align,kab);
                            adapter.setDropDownViewResource(R.layout.spinner_align);
                            spinnerKabKota.setAdapter(adapter);

                            if (posisition != -1)
                                spinnerKabKota.setSelection(posisition);

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
                            int posisition = -1;
                            for (int i = 0; i < array.length(); i++) {
                                if (array.getJSONObject(i).getString("id").equals(kecs)){
                                    posisition = i;
                                }
                                kec.add(array.getJSONObject(i).getString("name"));
                            }

                            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_align,kec);
                            adapter.setDropDownViewResource(R.layout.spinner_align);
                            spinnerKecamatan.setAdapter(adapter);

                            if (posisition != -1)
                                spinnerKecamatan.setSelection(posisition);

                            spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                            int posisition = -1;
                            for (int i = 0; i < array.length(); i++) {
                                if (array.getJSONObject(i).getString("id").equals(kels)){
                                    posisition = i;
                                }
                                kel.add(array.getJSONObject(i).getString("name"));
                            }

                            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_align,kel);
                            adapter.setDropDownViewResource(R.layout.spinner_align);
                            spinnerKelurahan.setAdapter(adapter);

                            if (posisition != -1)
                                spinnerKelurahan.setSelection(posisition);

                            spinnerKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
