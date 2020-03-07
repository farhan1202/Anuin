package com.example.anuin.other;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anuin.R;
import com.example.anuin.introNlogin.ApiLoginActivity;
import com.example.anuin.utils.SessionManagement;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFrag extends Fragment {
    TextView notifikasi, keluar, kontak;

    public OtherFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_other, container, false);
        notifikasi = view.findViewById(R.id.notifikasi);
        keluar = view.findViewById(R.id.keluar);
        kontak = view.findViewById(R.id.kontakkami);

        notifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), NotifActivity.class);
                startActivity(intent);
            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionManagement sessionManagement = new SessionManagement(view.getContext());
                sessionManagement.removeSession();
                Intent intent = new Intent(view.getContext(), ApiLoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        kontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "02242831923"));
                startActivity(intent);
            }
        });

        return view;
    }

}
