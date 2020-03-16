package com.example.anuin.other;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anuin.R;
import com.example.anuin.introNlogin.ApiLoginActivity;
import com.example.anuin.utils.PrefManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFrag extends Fragment {
    TextView notifikasi, keluar, kontak;

    /*private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;*/

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

        /*gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(view.getContext()).enableAutoManage((FragmentActivity) view.getContext(), new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();*/

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
                /*SessionManagement sessionManagement = new SessionManagement(view.getContext());
                sessionManagement.removeSession();*/
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

//                                Auth.GoogleSignInApi.signOut(googleApiClient);

                                PrefManager prefManager = new PrefManager(view.getContext());
                                prefManager.removeSession();

                                Intent intent = new Intent(view.getContext(), ApiLoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


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
