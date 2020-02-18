package com.example.anuin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.anuin.Adapter.OrderModalSheet;

public class DetailJasaActivity extends AppCompatActivity {
Button btnPesan;
Toolbar toolbar;
RadioButton rB1, rB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jasa);

        btnPesan = findViewById(R.id.buttonPesan);
        toolbar = findViewById(R.id.toolbarDetailJasa);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rB1 = findViewById(R.id.radioPerbaikan);
        rB2 = findViewById(R.id.radioSurvey);

        if (rB2.isChecked() || rB2.isSelected()){
            Toast.makeText(this, "asd", Toast.LENGTH_SHORT).show();
        }



        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderModalSheet orderModalSheet = new OrderModalSheet();
                orderModalSheet.show(getSupportFragmentManager(), "");
            }
        });

    }
}
