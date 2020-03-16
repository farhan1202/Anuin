package com.example.anuin.profil;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.anuin.Modal.EmailDialog;
import com.example.anuin.Modal.NameDialog;
import com.example.anuin.Modal.PasswordDialog;
import com.example.anuin.Modal.PhoneDialog;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFrag extends Fragment {
    CardView cardNama, cardEmail, cardTelp, cardAddAlamat, cardPassword;
    ApiInterface apiHelper;
    @BindView(R.id.detailNama)
    TextView detailNama;
    @BindView(R.id.detailMail)
    TextView detailMail;
    @BindView(R.id.detailTelp)
    TextView detailTelp;


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
        ButterKnife.bind(this, view);

        apiHelper = UtilsApi.getApiService();

        FetchProfile();



        cardPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordDialog dialog = new PasswordDialog();
                dialog.show(getFragmentManager(), "PasswordDialog");
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

    private void FetchProfile() {
        PrefManager prefManager = new PrefManager(getContext());
        apiHelper.requestProfile(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("STATUS").equals("200")) {
                            JSONObject object1 = object.getJSONObject("DATA");
                            detailNama.setText(object1.getString("name"));
                            detailMail.setText(object1.getString("email"));
                            detailTelp.setText(object1.getString("phone_number"));

                            cardNama.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    NameDialog dialog = new NameDialog();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("nama", detailNama.getText().toString());
                                    dialog.setArguments(bundle);
                                    dialog.show(getFragmentManager(), "AccountDialog");
                                }
                            });
                            cardEmail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    EmailDialog dialogE = new EmailDialog();
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putString("email",detailMail.getText().toString());
                                    dialogE.setArguments(bundle1);
                                    dialogE.show(getFragmentManager(), "AccountDialog");
                                }
                            });
                            cardTelp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PhoneDialog dialogP = new PhoneDialog();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("phone",detailTelp.getText().toString());
                                    dialogP.setArguments(bundle);
                                    dialogP.show(getFragmentManager(), "AccountDialog");
                                }
                            });
                        }



                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
