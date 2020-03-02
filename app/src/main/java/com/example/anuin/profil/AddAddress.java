package com.example.anuin.profil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anuin.R;

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
}
