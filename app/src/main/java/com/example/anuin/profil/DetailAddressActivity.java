package com.example.anuin.profil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.anuin.MainActivity;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAddressActivity extends AppCompatActivity {

    @BindView(R.id.toolbarDetailAddress)
    Toolbar toolbarDetailAddress;
    @BindView(R.id.txtAlamat)
    TextView txtAlamat;
    @BindView(R.id.txtLocaksiMaps)
    TextView txtLocaksiMaps;
    @BindView(R.id.txtWilaya)
    TextView txtWilaya;
    @BindView(R.id.txtKodePost)
    TextView txtKodePost;
    @BindView(R.id.txtProperty)
    TextView txtProperty;
    @BindView(R.id.btnHapus)
    Button btnHapus;
    @BindView(R.id.btnEdit)
    Button btnEdit;
    int id1, id2, id3, id4;
    int idAddress;
    String kel = "", kec = "", kab = "", prov = "";
    ApiInterface apiInterface;
    PrefManager prefManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_address);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarDetailAddress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        apiInterface = UtilsApi.getApiService();
        context = this;
        prefManager = new PrefManager(context);

        Intent intent = getIntent();

        idAddress = intent.getIntExtra("eID", 0);
        fetchDetailAddress();

    }

    private void fetchDetailAddress() {

        apiInterface.getAddressUserDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(), idAddress).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            txtAlamat.setText(jsonObject1.getString("alamat"));
                            txtLocaksiMaps.setText(jsonObject1.getString("lokasi_maps"));
                            txtKodePost.setText(jsonObject1.getString("kode_post"));
                            txtProperty.setText(jsonObject1.getString("property"));
                            id1 = Integer.parseInt(jsonObject1.getString("provinsi"));
                            id2 = Integer.parseInt(jsonObject1.getString("city"));
                            id3 = Integer.parseInt(jsonObject1.getString("kecamatan"));
                            id4 = Integer.parseInt(jsonObject1.getString("kelurahan"));
                            fetchWilayah();

                            btnEdit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try {
                                        int eIdAddress = jsonObject1.getInt("id");
                                        Intent intent = new Intent(getApplicationContext(), UpdateAddressActivity.class);
                                        intent.putExtra("eIDADDRESS", eIdAddress);
                                        startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                            btnHapus.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Are you sure?")
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    deleteAddress();
                                                }
                                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });

                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
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
                        Toast.makeText(DetailAddressActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailAddressActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteAddress() {
        apiInterface.deleteAddressUser(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(), idAddress).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            Toast.makeText(DetailAddressActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
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

    private void fetchWilayah() {
        apiInterface.getDetailProvinsi(UtilsApi.APP_TOKEN, id1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            prov = jsonObject1.getString("name");
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
                        Toast.makeText(DetailAddressActivity.this, "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailAddressActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
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
                            kab = jsonObject1.getString("name");
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
                        Toast.makeText(DetailAddressActivity.this, "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailAddressActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
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
                            kec = jsonObject1.getString("name");
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
                        Toast.makeText(DetailAddressActivity.this, "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailAddressActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
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
                            kel = jsonObject1.getString("name");
                            txtWilaya.setText(kel + ", " + kec + ", " + kab + ", " + prov);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject jsonObject1 = new JSONObject(response.errorBody().string());
                        Toast.makeText(DetailAddressActivity.this, "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailAddressActivity.this, "Check your connections", Toast.LENGTH_SHORT).show();
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
