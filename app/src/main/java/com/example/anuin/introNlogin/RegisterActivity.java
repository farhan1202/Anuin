package com.example.anuin.introNlogin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.anuin.Modal.LoginDialog;
import com.example.anuin.R;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Context context;
    ApiInterface apiInterface;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etPasswordrt)
    EditText etPasswordrt;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    LoginDialog loginDialog;

    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        loginDialog = new LoginDialog(this);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        context = this;
        apiInterface = UtilsApi.getApiService();

        String regexUsername = ".{6,}";
        String regexPassword = ".{8,}";

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.etName, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.etUsername, regexUsername,R.string.usernameerror);
        awesomeValidation.addValidation(this, R.id.etEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.etPassword, regexPassword, R.string.passworderror);
        awesomeValidation.addValidation(this,R.id.etPasswordrt, R.id.etPassword, R.string.passworderror2);

    }
    private void requestLogin() {
        if (awesomeValidation.validate()){
            apiInterface.registerRequest(UtilsApi.APP_TOKEN,
                    etName.getText().toString(),
                    etUsername.getText().toString(),
                    etEmail.getText().toString(),
                    etPassword.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    if (jsonObject.getString("STATUS").equals("200")) {
                                        Toast.makeText(context, "Please, check your email to verify", Toast.LENGTH_SHORT).show();
                                        loginDialog.dismissLoadingDialog();
                                        startActivity(new Intent(context, LoginActivity.class));
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else{
                                try {
                                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                    Toast.makeText(context, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(context, "Periksa koneksi!", Toast.LENGTH_SHORT).show();
                            loginDialog.dismissLoadingDialog();
                        }
                    });

        }else{
            loginDialog.dismissLoadingDialog();
        }

    }

    public void Login(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnRegister)
    public void onViewClicked() {
        loginDialog.startLoadingDialog();
        requestLogin();
    }
}
