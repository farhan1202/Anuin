package com.example.anuin.Modal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anuin.MainActivity;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailDialog extends BottomSheetDialogFragment {
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.btnSaveEmail)
    Button btnSaveEmail;

    PrefManager prefManager;
    ApiInterface apiInterface;
    LoginDialog loginDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.email_layout, container, false);
        ButterKnife.bind(this, view);
        /*String email = getArguments().getString("email");
        editEmail.setText(email);*/
        prefManager = new PrefManager(view.getContext());
        apiInterface = UtilsApi.getApiService();
        loginDialog = new LoginDialog(view.getContext());

        componentDIdMount();
        return view;
    }

    private void componentDIdMount() {
        apiInterface.requestProfile(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                            editEmail.setText(jsonObject1.getString("email"));
                            String username = jsonObject1.getString("username");
                            String name = jsonObject1.getString("name");
                            String phone_number = jsonObject1.getString("phone_number");
                            String member_image = jsonObject1.getString("member_image");
                            btnSaveEmail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (TextUtils.isEmpty(editEmail.getText().toString())){
                                        editEmail.setError("Field tidak boleh kosong");
                                    }else{
                                        updateEmail(name, username, phone_number, member_image);
                                        loginDialog.startLoadingDialog();
                                    }
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void updateEmail(String name, String username, String phone_number, String member_image) {
        apiInterface.updateProfile(UtilsApi.APP_TOKEN, prefManager.getTokenUser(),
                prefManager.getId(),
                name,
                username,
                editEmail.getText().toString(),
                phone_number,
                member_image).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            Toast.makeText(getContext(), "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            loginDialog.dismissLoadingDialog();
                            Intent intent1 = new Intent(getContext(), MainActivity.class);
                            intent1.putExtra("FLAGPAGE", 2);
                            startActivity(intent1);
                            getActivity().finish();
                        }else{
                            loginDialog.dismissLoadingDialog();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    loginDialog.dismissLoadingDialog();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Koneksi bermasalah", Toast.LENGTH_SHORT).show();
                loginDialog.dismissLoadingDialog();
            }
        });
    }
}
