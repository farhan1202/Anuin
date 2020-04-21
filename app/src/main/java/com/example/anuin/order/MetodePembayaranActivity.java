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
    @BindView(R.id.tvMotode)
    TextView tvMotode;
    @BindView(R.id.tvTipe)
    TextView tvTipe;
    @BindView(R.id.tvTunai)
    TextView tvTunai;
    @BindView(R.id.cvTunai)
    CardView cvTunai;
    @BindView(R.id.tvBank)
    TextView tvBank;
    @BindView(R.id.cvBank)
    CardView cvBank;
    @BindView(R.id.tvWalet)
    TextView tvWalet;
    @BindView(R.id.cvEWalet)
    CardView cvEWalet;

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
        cvBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), PaymentBankActivity.class);
                intent1.putExtra("id", 2);
                startActivityForResult(intent1, 2);
            }
        });

        cvEWalet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.putExtra("metode", 3);
                setResult(RESULT_OK, intent1);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            if (resultCode == RESULT_OK){
                setResult(RESULT_OK, data);
                finish();
            }
        }

    }
}
