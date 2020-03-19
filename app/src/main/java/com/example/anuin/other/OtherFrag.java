package com.example.anuin.other;


import android.app.Activity;
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
import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFrag extends Fragment {
    TextView notifikasi, keluar, kontak;
    private ShareDialog shareDialog;
    public OtherFrag() {
        // Required empty public constructor
    }

    GoogleSignInClient mGoogleSignClient;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_other, container, false);
        notifikasi = view.findViewById(R.id.notifikasi);
        keluar = view.findViewById(R.id.keluar);
        kontak = view.findViewById(R.id.kontakkami);
        shareDialog = new ShareDialog(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignClient = GoogleSignIn.getClient(view.getContext(), gso);

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
                                mGoogleSignClient.signOut()
                                        .addOnCompleteListener((Activity) view.getContext(), new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                // ...
                                            }
                                        });

                                LoginManager.getInstance().logOut();


                                PrefManager prefManager = new PrefManager(view.getContext());
                                prefManager.removeSession();
                                prefManager.spString(PrefManager.SP_TOKEN_USER, "");
                                prefManager.spInt(PrefManager.SP_ID, -1);

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
