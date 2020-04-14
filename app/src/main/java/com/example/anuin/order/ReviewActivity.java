package com.example.anuin.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.anuin.MainActivity;
import com.example.anuin.Modal.LoginDialog;
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

public class ReviewActivity extends AppCompatActivity {

    @BindView(R.id.imageMerchant)
    ImageView imageMerchant;
    @BindView(R.id.txtNamaMerchant)
    TextView txtNamaMerchant;
    @BindView(R.id.rating)
    RatingBar rating;
    @BindView(R.id.editUlasan)
    EditText editUlasan;
    @BindView(R.id.btnDone)
    Button btnDone;

    ApiInterface apiInterface;
    PrefManager prefManager;
    LoginDialog loginDialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);

        context = this;
        apiInterface = UtilsApi.getApiService();
        prefManager = new PrefManager(this);
        loginDialog = new LoginDialog(this);

        componentDidMount();

    }

    private void componentDidMount() {
        Intent intent = getIntent();
        apiInterface.getBookingDetail(UtilsApi.APP_TOKEN, prefManager.getTokenUser(), prefManager.getId(), intent.getIntExtra("booking_id", 0))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("STATUS").equals("200")){
                                    JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                                    int booking_id = jsonObject1.getInt("id");

                                    JSONObject jsonMerchant = new JSONObject(jsonObject1.getString("merchant"));
                                    int merchant_id = jsonMerchant.getInt("id");
                                    txtNamaMerchant.setText(jsonMerchant.getString("name"));
                                    Glide
                                            .with(context)
                                            .load(jsonMerchant.getString("merchant_image"))
                                            .into(imageMerchant);



                                    btnDone.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            bookingApprove(booking_id, merchant_id, (int) rating.getRating(), editUlasan.getText().toString());
                                        }
                                    });

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                Toast.makeText(context, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("TAG", "onFailure: " + t.getMessage());
                        Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    private void bookingApprove(int booking_id, int merchant_id, int rating, String toString) {
        apiInterface.bookingApprove(UtilsApi.APP_TOKEN, prefManager.getTokenUser(),prefManager.getId(),booking_id,merchant_id,toString, rating).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            Toast.makeText(ReviewActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
                            intent.putExtra("FLAGPAGE", 1);
                            intent.putExtra("FLAGPAGER", 1);
                            startActivity(intent);
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
                        Toast.makeText(ReviewActivity.this, "" + jsonObject.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(ReviewActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
