package com.example.anuin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddAddress extends AppCompatActivity {
Button btnSimpan;
Spinner spinnerKabKota;
String KK[] = {
  "Pilih Kabupaten / Kota",
        "Bandung"
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        btnSimpan = findViewById(R.id.btnSimpan);
        spinnerKabKota = findViewById(R.id.spinnerKabKota);

        /*btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAddress.this, ProfileFrag.class);
                startActivity(intent);
            }
        });*/

        ArrayAdapter adapterKK = new ArrayAdapter(this,android.R.layout.simple_spinner_item,KK);
        adapterKK.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKabKota.setAdapter(adapterKK);
    }
}
