package com.example.anuin.other;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.anuin.R;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUsActivity extends AppCompatActivity {
    @BindView(R.id.contact_image)
    ImageView contact_image;
    @BindView(R.id.txtAddress)
    TextView txtAddress;
    @BindView(R.id.operational)
    TextView operational;
    @BindView(R.id.closed)
    TextView closed;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.notel)
    TextView nomor;
    @BindView(R.id.phoneCard)
    CardView phoneCard;
    @BindView(R.id.mailCard)
    CardView mailCard;
    @BindView(R.id.faxCard)
    CardView faxCard;

    ApiInterface apiHelper;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        toolbar = findViewById(R.id.toolbarContactUs);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ButterKnife.bind(this);
        apiHelper = UtilsApi.getApiService();

        fetchContactUs();
    }



    private void fetchContactUs() {
        apiHelper.aboutUs(UtilsApi.APP_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("STATUS").equals("200")) {
                            JSONObject data = object.getJSONArray("DATA").getJSONObject(0);
                            txtAddress.setText(data.getString("address"));
                            operational.setText(data.getString("operational"));
                            closed.setText(data.getString("closed"));
                            email.setText(data.getString("email"));
                            nomor.setText(data.getString("phone"));
                            Glide.with(getApplicationContext())
                                    .load(data.getString("contact_image"))
                                    .centerCrop()
                                    .into(contact_image);
                            phoneCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        String tlp = data.getString("phone");
                                        Intent telp = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + tlp));
                                        startActivity(telp);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            mailCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String to = null;
                                    try {
                                        to = data.getString("email");
                                        String[] recipient = to.split(",");

                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        intent.putExtra(Intent.EXTRA_EMAIL, recipient);
                                        intent.setType("text/plain");
                                        startActivity(Intent.createChooser(intent, "Choose an email app"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                            email.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        String to = data.getString("email");
                                        String[] recipient = to.split(",");

                                        Intent intent = new Intent(Intent.ACTION_SEND);
                                        intent.putExtra(Intent.EXTRA_EMAIL,recipient);
                                        intent.setType("text/plain");
                                        startActivity(Intent.createChooser(intent,"Choose an email app"));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            faxCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    try {
                                        String fax = data.getString("fax");
                                        Intent faxs = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + fax));
                                        startActivity(faxs);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
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

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
