package com.example.anuin.Modal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anuin.MainActivity;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordDialog extends BottomSheetDialogFragment {


    @BindView(R.id.txtData)
    TextView txtData;
    @BindView(R.id.txtNewPassword)
    EditText txtNewPassword;
    @BindView(R.id.txtInputLayout)
    TextInputLayout txtInputLayout;
    @BindView(R.id.txtNewPasswordConfirm)
    EditText txtNewPasswordConfirm;
    @BindView(R.id.txtInputLayout2)
    TextInputLayout txtInputLayout2;
    @BindView(R.id.btnSave)
    Button btnSave;
    PrefManager prefManager;
    ApiInterface apiInterface;
    LoginDialog loginDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.password_layout, container, false);
        ButterKnife.bind(this, view);
        prefManager = new PrefManager(view.getContext());
        apiInterface = UtilsApi.getApiService();
        loginDialog = new LoginDialog(view.getContext());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNewPassword.getText().toString().length() < 6) {
                    txtNewPassword.setError("Password must be more than 6 letters long");
                } else if (txtNewPasswordConfirm.getText().toString().length() < 6) {
                    txtNewPasswordConfirm.setError("Password must be more than 6 letters long");
                } else {
                    loginDialog.startLoadingDialog();
                    initProses();
                }
            }
        });

        return view;
    }

    private void initProses() {
        apiInterface.changePassword(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(),
                txtNewPassword.getText().toString(), txtNewPasswordConfirm.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            Toast.makeText(getContext(), "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            loginDialog.dismissLoadingDialog();
                            Intent intent1 = new Intent(getContext(), MainActivity.class);
                            intent1.putExtra("FLAGPAGE", 2);
                            startActivity(intent1);
                            getActivity().finish();
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
                        loginDialog.dismissLoadingDialog();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loginDialog.dismissLoadingDialog();
                Toast.makeText(getContext(), "Koneksi bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
