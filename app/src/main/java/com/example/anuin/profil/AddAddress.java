package com.example.anuin.profil;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anuin.MainActivity;
import com.example.anuin.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

        //permission gps
        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        editLokasi.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= editLokasi.getRight() - editLokasi.getTotalPaddingRight()) {
                            getLocation();
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

    private void getLocation() {
        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //Write Function To enable gps
            OnGPS();
        }else{
            //GPS is already On then
            getLocations();
        }
    }

    private void getLocations() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }else{
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=lat;
                longitude=longi;

                convertWithGeoCoder(latitude, longitude);

            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=lat;
                longitude=longi;

                convertWithGeoCoder(latitude, longitude);
            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=lat;
                longitude=longi;

                convertWithGeoCoder(latitude, longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void convertWithGeoCoder(double latitude, double longitude) {
        Geocoder geocoder;
        geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            Log.i("TAG", "convertWithGeoCoder: " + city + " " + state + " " + country + " " + postalCode + " " + knownName);
            editLokasi.setText(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
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
