package com.example.anuin.introNlogin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anuin.MainActivity;
import com.example.anuin.Modal.LoginDialog;
import com.example.anuin.R;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;
import com.example.anuin.utils.PrefManager;
import com.example.anuin.utils.model.Users;
import com.google.gson.Gson;

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

public class LoginActivity extends AppCompatActivity {

    LoginDialog loginDialog;

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.FPass)
    TextView FPass;

    ApiInterface apiInterface;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginDialog = new LoginDialog(this);

        apiInterface = UtilsApi.getApiService();
        context = this;

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


    }

    public void Register(View view) {
        Intent home = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(home);
    }

    @OnClick({R.id.btnLogin, R.id.FPass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                btnLogin();
                break;
            case R.id.FPass:
                break;
        }
    }

    private void btnLogin() {
        if (TextUtils.isEmpty(etEmail.getText().toString())){
            etEmail.setError("Masukkan Email");
            return;
        }else if(TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError("Masukkan Password");
            return;
        }else{
            loginDialog.startLoadingDialog();
            performLogin();
        }
    }

    private void performLogin() {
        apiInterface.getLogin(UtilsApi.APP_TOKEN, etEmail.getText().toString(), etPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            JSONObject data= jsonObject.getJSONObject("DATA");

                            Gson gson = new Gson();
                            Users user = gson.fromJson(data+"", Users.class);

                            /*SessionManagement sessionManagement = new SessionManagement(context);
                            sessionManagement.saveSession();*/

                            PrefManager prefManager = new PrefManager(context);
                            prefManager.saveSession();

                            moveToMain();
                        }else{
                            Toast.makeText(context, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_LONG).show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loginDialog.dismissLoadingDialog();
                                }
                            }, 1000);
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

    private void moveToMain() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loginDialog.dismissLoadingDialog();
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }


}
