package com.example.anuin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.anuin.Adapter.OrderModalSheet;

public class DetailJasaActivity extends AppCompatActivity {
Button btnPesan;
Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jasa);

        btnPesan = findViewById(R.id.buttonPesan);
        toolbar = findViewById(R.id.toolbarDetailJasa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderModalSheet orderModalSheet = new OrderModalSheet();
                orderModalSheet.show(getSupportFragmentManager(), "");
            }
        });

    }
}
