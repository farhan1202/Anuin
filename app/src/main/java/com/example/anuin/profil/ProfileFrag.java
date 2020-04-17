package com.example.anuin.profil;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.Modal.EmailDialog;
import com.example.anuin.Modal.LoginDialog;
import com.example.anuin.Modal.NameDialog;
import com.example.anuin.Modal.PasswordDialog;
import com.example.anuin.Modal.PhoneDialog;
import com.example.anuin.Modal.UsernameDialog;
import com.example.anuin.R;
import com.example.anuin.introNlogin.ApiLoginActivity;
import com.example.anuin.profil.adapter.AddressAdapter;
import com.example.anuin.profil.model.Address;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    CardView cardNama, cardEmail, cardTelp, cardAddAlamat, cardPassword, cardUsername;
    @BindView(R.id.detailNama)
    TextView detailNama;
    @BindView(R.id.detailMail)
    TextView detailMail;
    @BindView(R.id.detailTelp)
    TextView detailTelp;
    @BindView(R.id.detailUsername)
    TextView detailUsername;

    ApiInterface apiInterface;
    PrefManager prefManager;
    @BindView(R.id.recyclerAddress)
    RecyclerView recyclerAddress;

    LoginDialog loginDialog;
    Context context;

    public ProfileFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        cardNama = view.findViewById(R.id.cardNama);
        cardAddAlamat = view.findViewById(R.id.cardAddAlamat);
        cardEmail = view.findViewById(R.id.cardEmail);
        cardTelp = view.findViewById(R.id.cardTelepon);
        cardPassword = view.findViewById(R.id.cardPassword);
        cardUsername = view.findViewById(R.id.cardUsername);

        prefManager = new PrefManager(view.getContext());

        apiInterface = UtilsApi.getApiService();
        context = view.getContext();
        loginDialog = new LoginDialog(context);


        if (prefManager.getSession()) {
            FetchProfile();
            fetchAddress();
        }

        cardPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefManager.getSessionSosmed()) {
                    Toast.makeText(getContext(), "Can't Edit Password", Toast.LENGTH_SHORT).show();
                } else {
                    PasswordDialog dialog = new PasswordDialog();
                    dialog.show(getFragmentManager(), "PasswordDialog");
                }
            }
        });

        cardAddAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddAddressActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void fetchAddress() {
        PrefManager prefManager = new PrefManager(getContext());
        List<Address.DATABean> dataBeans = new ArrayList<>();
        apiInterface.getAddressUser(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");
                            Gson gson = new Gson();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Address.DATABean dataBean = gson.fromJson(jsonArray.getJSONObject(i).toString(), Address.DATABean.class);
                                dataBeans.add(dataBean);
                            }
                            AddressAdapter addressAdapter = new AddressAdapter(getContext(), dataBeans);
                            recyclerAddress.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerAddress.setAdapter(addressAdapter);
                            recyclerAddress.setHasFixedSize(true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Check your connections", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void FetchProfile() {
        PrefManager prefManager = new PrefManager(getContext());
        loginDialog.startLoadingDialog();
        apiInterface.requestProfile(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("STATUS").equals("200")) {
                            JSONObject object1 = object.getJSONObject("DATA");
                            loginDialog.dismissLoadingDialog();
                            detailNama.setText(object1.getString("name"));
                            detailMail.setText(object1.getString("email"));
                            detailTelp.setText(object1.getString("phone_number"));
                            detailUsername.setText(object1.getString("username"));

                            cardNama.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    NameDialog dialog = new NameDialog();
                                    /*Bundle bundle = new Bundle();
                                    bundle.putString("nama", detailNama.getText().toString());
                                    dialog.setArguments(bundle);*/
                                    dialog.show(getFragmentManager(), "AccountDialog");
                                }
                            });
                            cardEmail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (prefManager.getSessionSosmed()) {
                                        Toast.makeText(getContext(), "Can't Edit Email", Toast.LENGTH_SHORT).show();
                                    } else {
                                        EmailDialog dialogE = new EmailDialog();
                                    /*Bundle bundle1 = new Bundle();
                                    bundle1.putString("email",detailMail.getText().toString());
                                    dialogE.setArguments(bundle1);*/
                                        dialogE.show(getFragmentManager(), "AccountDialog");
                                    }

                                }
                            });
                            cardTelp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    PhoneDialog dialogP = new PhoneDialog();
                                    /*Bundle bundle = new Bundle();
                                    bundle.putString("phone",detailTelp.getText().toString());
                                    dialogP.setArguments(bundle);*/
                                    dialogP.show(getFragmentManager(), "AccountDialog");
                                }
                            });
                            cardUsername.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    UsernameDialog dialogUN = new UsernameDialog();
                                    /*Bundle bundle = new Bundle();
                                    bundle.putString("username",detailUsername.getText().toString());
                                    dialogUN.setArguments(bundle);*/
                                    dialogUN.show(getFragmentManager(), "AccountDialog");
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    loginDialog.dismissLoadingDialog();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("Sesi Login Berakhir")
                            .setCancelable(false)
                            .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent1 = new Intent(getContext(), ApiLoginActivity.class);
                                    intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent1);
                                }
                            });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    prefManager.removeSession();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
                loginDialog.dismissLoadingDialog();
            }
        });
    }
}
