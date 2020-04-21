package com.example.anuin.order;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anuin.Modal.LoginDialog;
import com.example.anuin.order.adapter.WaitingOrderAdapter;
import com.example.anuin.R;
import com.example.anuin.order.model.OrderList;
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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AktifFragment extends Fragment {
    RecyclerView rvOrderAktif;
    WaitingOrderAdapter waitingOrderAdapter;
    Context context;
    LoginDialog loginDialog;
    List<OrderList.DATABean> orderList;
    ApiInterface apiInterface;



    public AktifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_aktif, container, false);
        rvOrderAktif=view.findViewById(R.id.rvOrderAktif);
        context=view.getContext();
        apiInterface = UtilsApi.getApiService();
        loginDialog = new LoginDialog(context);
        loginDialog.startLoadingDialog();

        PrefManager manager = new PrefManager(context);
        apiInterface.getBookingLIst(UtilsApi.APP_TOKEN, manager.getTokenUser(),manager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            loginDialog.dismissLoadingDialog();
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

                            orderList = new ArrayList<>();
                            Gson gson = new Gson();
                            for (int i = jsonArray.length() - 1; i>=0; i--){
                                OrderList.DATABean dataBean = gson.fromJson(jsonArray.getJSONObject(i).toString(), OrderList.DATABean.class);
                                orderList.add(dataBean);
                            }

                            waitingOrderAdapter= new WaitingOrderAdapter(context, orderList);
                            rvOrderAktif.setLayoutManager(new LinearLayoutManager(context));
                            rvOrderAktif.setAdapter(waitingOrderAdapter);
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
                loginDialog.dismissLoadingDialog();
                Log.d("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }


}
