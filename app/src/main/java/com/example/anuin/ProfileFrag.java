package com.example.anuin;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuin.Modal.AccountDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment {
    CardView cardNama, cardAddAlamat;

    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        cardNama = view.findViewById(R.id.cardNama);
        cardAddAlamat = view.findViewById(R.id.cardAddAlamat);

        cardNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDialog dialog = new AccountDialog();
                dialog.show(getFragmentManager(),"AccountDialog");
            }
        });

        cardAddAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddAddress.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
