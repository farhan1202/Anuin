package com.example.anuin.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anuin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BayarActivity extends AppCompatActivity {

    @BindView(R.id.tvTunai)
    TextView tvTunai;
    @BindView(R.id.chkTunai)
    CheckBox chkTunai;
    @BindView(R.id.btnBayar)
    Button btnBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
        ButterKnife.bind(this);
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!chkTunai.isChecked()){
                    Toast.makeText(BayarActivity.this, "Harap Centang Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("metode", 0);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
