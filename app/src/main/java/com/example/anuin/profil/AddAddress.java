package com.example.anuin.profil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.provider.Settings;
import android.util.Log;
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
    Spinner spinnerKabKota, spinnerKec, spinnerKel, spinnerProp;
    EditText editLokasi;
    String KK[] = {
            "Pilih Kabupaten / Kota",
            "Bandung"
    };
    String Kec[] = {
            "Pilih Kecamatan",
            "Regol"
    };
    String Kel[] = {
            "Pilih Kelurahan",
    };
    String Prop[] = {
            "Pilih Properti",
    };

    private static  final int REQUEST_LOCATION=1;
    LocationManager locationManager;
    double latitude, longitude;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        toolbar = findViewById(R.id.toolbarFormAddress);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnSimpan = findViewById(R.id.btnSimpan);
        spinnerKabKota = findViewById(R.id.spinnerKabKota);
        spinnerKec = findViewById(R.id.spinnerKecamatan);
        spinnerKel = findViewById(R.id.spinnerKelurahan);
        spinnerProp = findViewById(R.id.spinnerProperti);
        editLokasi = findViewById(R.id.editLokasi);

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

        ArrayAdapter adapterKK = new ArrayAdapter(this, R.layout.spinner_align, KK);
        adapterKK.setDropDownViewResource(R.layout.spinner_align);
        spinnerKabKota.setAdapter(adapterKK);

        ArrayAdapter adapterKec = new ArrayAdapter(this, R.layout.spinner_align, Kec);
        adapterKec.setDropDownViewResource(R.layout.spinner_align);
        spinnerKec.setAdapter(adapterKec);

        ArrayAdapter adapterKel = new ArrayAdapter(this, R.layout.spinner_align, Kel);
        adapterKel.setDropDownViewResource(R.layout.spinner_align);
        spinnerKel.setAdapter(adapterKel);

        ArrayAdapter adapterProp = new ArrayAdapter(this, R.layout.spinner_align, Prop);
        adapterProp.setDropDownViewResource(R.layout.spinner_align);
        spinnerProp.setAdapter(adapterProp);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
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
