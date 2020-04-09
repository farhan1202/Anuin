package com.example.anuin.order;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.example.anuin.R;

public class MetodePembayaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motode_pembayaran);
        CardView cvTunai=findViewById(R.id.cvTunai);

        cvTunai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MetodePembayaranActivity.this, BayarActivity.class);
                startActivityForResult(intent, OrderWaitingActivity.METODE_PAYMENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OrderWaitingActivity.METODE_PAYMENT){
            if (resultCode == RESULT_OK){
                setResult(RESULT_OK, data);
                finish();
            }
        }

    }
}
