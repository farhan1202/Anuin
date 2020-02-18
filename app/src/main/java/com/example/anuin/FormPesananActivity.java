package com.example.anuin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class FormPesananActivity extends AppCompatActivity {
Toolbar toolbar;
EditText txtFormDate, txtFormTime;
Calendar calendar, calender1;
DatePickerDialog dialog;
Time time;
TimePickerDialog dialog1;
Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pesanan);
        toolbar = findViewById(R.id.toolbarFormPemesanan);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtFormTime = findViewById(R.id.txtFormTime);
        txtFormDate = findViewById(R.id.txtFormDate);
        txtFormDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH + 1);
                final int year = calendar.get(Calendar.YEAR);

                dialog = new DatePickerDialog(FormPesananActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txtFormDate.setText(i2 + "/" + i1 + "/" + i);
                    }
                },year, month, day);
                dialog.show();
            }
        });

        txtFormTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calender1 = Calendar.getInstance();
                final int hour = calender1.get(Calendar.HOUR_OF_DAY);
                final int minute = calender1.get(Calendar.MINUTE);

                dialog1 = new TimePickerDialog(FormPesananActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        txtFormTime.setText(i + ":" + i1 + " WIB");
                    }
                }, hour, minute, android.text.format.DateFormat.is24HourFormat(mContext));
                dialog1.show();
            }
        });
    }
}
