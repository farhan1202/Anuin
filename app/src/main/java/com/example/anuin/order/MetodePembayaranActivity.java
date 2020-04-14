package com.example.anuin.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.anuin.R;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MetodePembayaranActivity extends AppCompatActivity {

    @BindView(R.id.txtTotalTagihan)
    TextView txtTotalTagihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motode_pembayaran);
        ButterKnife.bind(this);
        CardView cvTunai = findViewById(R.id.cvTunai);
        Intent intent = getIntent();
        int total_tagihan = intent.getIntExtra("total_tagihan", 0);
        txtTotalTagihan.setText((NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(total_tagihan) + ""));

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
        if (requestCode == OrderWaitingActivity.METODE_PAYMENT) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK, data);
                finish();
            }
        }

    }
}
