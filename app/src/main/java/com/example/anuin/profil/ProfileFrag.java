package com.example.anuin.profil;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuin.Modal.AccountDialog;
import com.example.anuin.Modal.PasswordDialog;
import com.example.anuin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment {
    CardView cardNama,cardEmail,cardTelp, cardAddAlamat,cardPassword;

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
        cardEmail = view.findViewById(R.id.cardEmail);
        cardTelp = view.findViewById(R.id.cardTelepon);
        cardPassword = view.findViewById(R.id.cardPassword);


        cardNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDialog dialog = new AccountDialog();
                dialog.show(getFragmentManager(),"AccountDialog");
            }
        });
        cardEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDialog dialog = new AccountDialog();
                dialog.show(getFragmentManager(),"AccountDialog");
            }
        });
        cardTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountDialog dialog = new AccountDialog();
                dialog.show(getFragmentManager(),"AccountDialog");
            }
        });
        cardPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordDialog dialog = new PasswordDialog();
                dialog.show(getFragmentManager(),"PasswordDialog");
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
