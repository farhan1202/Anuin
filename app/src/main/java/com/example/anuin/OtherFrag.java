package com.example.anuin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anuin.introNlogin.ApiLoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFrag extends Fragment {
TextView notifikasi,keluar;

    public OtherFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_other, container, false);
        notifikasi = view.findViewById(R.id.notifikasi);
        keluar = view.findViewById(R.id.keluar);
        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),NotifActivity.class);
                startActivity(intent);
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(view.getContext(), ApiLoginActivity.class);
               startActivity(intent);
            }
        });

        return view;
    }

}
