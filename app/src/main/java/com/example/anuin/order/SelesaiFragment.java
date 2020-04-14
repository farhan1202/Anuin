package com.example.anuin.order;


import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuin.R;
import com.example.anuin.order.adapter.OrderDoneAdapter;
import com.example.anuin.order.adapter.WaitingOrderAdapter;
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
public class SelesaiFragment extends Fragment {
    RecyclerView rvOrderSelesai;
    OrderDoneAdapter orderDoneAdapter;
    Context context;
    List<OrderList.DATABean> orderList;
    ApiInterface apiInterface;


    public SelesaiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_selesai, container, false);
        rvOrderSelesai=view.findViewById(R.id.rvOrderSelesai);
        context=view.getContext();
        apiInterface = UtilsApi.getApiService();

        PrefManager manager = new PrefManager(context);
        apiInterface.getOrderComplete(UtilsApi.APP_TOKEN, manager.getTokenUser(),manager.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

                            orderList = new ArrayList<>();
                            Gson gson = new Gson();
                            for (int i = jsonArray.length() - 1; i>=0; i--){
                                OrderList.DATABean dataBean = gson.fromJson(jsonArray.getJSONObject(i).toString(), OrderList.DATABean.class);
                                orderList.add(dataBean);
                            }

                            orderDoneAdapter = new OrderDoneAdapter(context, orderList);
                            rvOrderSelesai.setLayoutManager(new LinearLayoutManager(context));
                            rvOrderSelesai.setAdapter(orderDoneAdapter);
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


        return view;
    }

}
