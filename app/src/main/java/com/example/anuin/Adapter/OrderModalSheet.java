package com.example.anuin.Adapter;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anuin.R;
import com.example.anuin.home.FormPesananActivity;
import com.example.anuin.utils.apihelper.ApiInterface;
import com.example.anuin.utils.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderModalSheet extends BottomSheetDialogFragment {
    @BindView(R.id.modalJasaName)
    TextView modalJasaName;
    @BindView(R.id.modalJasaPrice)
    TextView modalJasaPrice;
    @BindView(R.id.modalJasaDesc)
    TextView modalJasaDesc;
    @BindView(R.id.modalJasaWarning)
    TextView modalJasaWarning;
    @BindView(R.id.btnCancelOrder)
    Button btnCancelOrder;
    @BindView(R.id.btnAcceptOrder)
    Button btnAcceptOrder;
    Unbinder unbinder;
    ApiInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_bottom_sheet, container, false);
        Bundle bundle = getArguments();
        apiInterface = UtilsApi.getApiService();
        int id = bundle.getInt("id", 0);
        componentDidMount(id);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void componentDidMount(int id) {
        apiInterface.getProductJasaDetail(UtilsApi.APP_TOKEN, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")) {
                            JSONObject jsonObject1 = new JSONObject(jsonObject.getString("DATA"));
                                modalJasaName.setText(jsonObject1.getString("product_jasa_title"));
                                modalJasaPrice.setText("" + NumberFormat.getCurrencyInstance(new Locale("in", "ID")).format(jsonObject1.getInt("product_jasa_harga")));
                                modalJasaDesc.setText(jsonObject1.getString("product_jasa_desc"));
                                modalJasaWarning.setText(Html.fromHtml(jsonObject1.getString("product_jasa_warning")));
                                int id_category = jsonObject1.getInt("category_id");
//                            Toast.makeText(getContext(), "" + id_category, Toast.LENGTH_SHORT).show();

                            btnCancelOrder.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dismiss();
                                }
                            });

                            btnAcceptOrder.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Intent intent = new Intent(view.getContext(), FormPesananActivity.class);
                                        intent.putExtra("id", id);
                                        intent.putExtra("id1", id_category);
                                        startActivity(intent);
                                }
                            });
                        }else {
                            try {
                                JSONObject jsonObject1 = new JSONObject(response.errorBody().string());
                                Toast.makeText(getContext(), "" + jsonObject1.getString("MESSAGE"), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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
                Toast.makeText(getContext(), "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
