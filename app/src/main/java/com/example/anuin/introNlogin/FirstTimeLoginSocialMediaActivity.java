package com.example.anuin.introNlogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.anuin.MainActivity;
import com.example.anuin.Modal.LoginDialog;
import com.example.anuin.R;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstTimeLoginSocialMediaActivity extends AppCompatActivity {

    @BindView(R.id.txtNewNameUser)
    EditText txtNewNameUser;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    ApiInterface apiInterface;
    PrefManager prefManager;
    LoginDialog loginDialog;

    GoogleSignInClient mGoogleSignClient;
    @BindView(R.id.txtNewUsername)
    EditText txtNewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_login_social_media);
        ButterKnife.bind(this);
        apiInterface = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        loginDialog = new LoginDialog(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignClient = GoogleSignIn.getClient(getApplicationContext(), gso);
        componentDIdMount();
    }

    private void componentDIdMount() {


        apiInterface.requestProfile(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("DATA");
                            String email = jsonObject1.getString("email");
                            String username = jsonObject1.getString("username");
                            String phone_number = jsonObject1.getString("phone_number");
                            String member_image = jsonObject1.getString("member_image");
                            btnConfirm.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (TextUtils.isEmpty(txtNewUsername.getText().toString())) {
                                        txtNewUsername.setError("Field can't be blank");
                                    } else if(TextUtils.isEmpty(txtNewNameUser.getText().toString())){
                                        txtNewNameUser.setError("Field can't be blank");
                                    } else{
                                        updateProfile(email, phone_number, member_image);
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
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(FirstTimeLoginSocialMediaActivity.this, t.getLocalizedMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProfile(String email, String phone_number, String member_image) {
        apiInterface.updateProfile(UtilsApi.APP_TOKEN,
                prefManager.getTokenUser(),
                prefManager.getId(),
                txtNewNameUser.getText().toString(),
                txtNewUsername.getText().toString(),
                email,
                phone_number,
                member_image)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")) {
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    finish();
                                    prefManager.saveSession();
                                    prefManager.saveSessionSosmed();
                                    loginDialog.dismissLoadingDialog();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Toast.makeText(FirstTimeLoginSocialMediaActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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
                    }
                });
    }
}
