package com.example.anuin.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anuin.Modal.LoginDialog;
import com.example.anuin.R;
import com.example.anuin.order.adapter.PaymentBankAdapter;
import com.example.anuin.order.interfaces.PaymentBankInterface;
import com.example.anuin.order.model.Payment;
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

public class PaymentListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerPayBank)
    RecyclerView recyclerPayBank;
    @BindView(R.id.btnBayar)
    Button btnBayar;
    @BindView(R.id.tvBank)
    TextView tvBank;

    PrefManager prefManager;
    ApiInterface apiInterface;
    Context context;
    LoginDialog loginDialog;

    boolean flags = false;
    int idPaymentBank;
    int metode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_bank);
        ButterKnife.bind(this);

        apiInterface = UtilsApi.getApiService();
        context = this;
        loginDialog = new LoginDialog(context);
        prefManager = new PrefManager(context);

        recyclerPayBank.setLayoutManager(new LinearLayoutManager(context));

        componenDidMount();

    }

    private void componenDidMount() {
        Intent intent = getIntent();
        metode = intent.getIntExtra("id", 0);
        loginDialog.startLoadingDialog();
        apiInterface.getMethodPaymentDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), metode)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")){
                                    loginDialog.dismissLoadingDialog();
                                    JSONObject jsonObject1 = new JSONObject(jsonObject.getJSONObject("DATA").toString());

                                    tvBank.setText(jsonObject1.getString("title"));

                                    JSONArray jsonArray = jsonObject1.getJSONArray("payment_method");
                                    List<Payment.DATABean.PaymentMethodBean> paymentMethodBeans = new ArrayList<>();
                                    Gson gson = new Gson();
                                    for (int i = 0 ; i < jsonArray.length() ; i++){
                                        Payment.DATABean.PaymentMethodBean paymentMethodBean = gson.fromJson(jsonArray.getString(i), Payment.DATABean.PaymentMethodBean.class);
                                        paymentMethodBeans.add(paymentMethodBean);
                                    }

                                    PaymentBankAdapter paymentBankAdapter = new PaymentBankAdapter(context, paymentMethodBeans, new PaymentBankInterface() {
                                        @Override
                                        public void onItemClick(int id, boolean flag) {
                                            if (id != -1){
                                                idPaymentBank = id;
                                                flags = flag;
                                            }
                                        }
                                    });
                                    recyclerPayBank.setAdapter(paymentBankAdapter);

                                    btnBayar.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (flags){
                                                Intent intent = new Intent();
                                                intent.putExtra("id", idPaymentBank);
                                                intent.putExtra("metode", metode);
                                                setResult(RESULT_OK, intent);
                                                finish();
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
                        Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
                        loginDialog.dismissLoadingDialog();
                    }
                });
    }
}
