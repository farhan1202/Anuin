package com.example.anuin.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuin.Adapter.AdapterViewPager;
import com.example.anuin.R;
import com.example.anuin.home.adapter.CategoryAdapter;
import com.example.anuin.home.model.Banner;
import com.example.anuin.home.model.Category;
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
public class HomeFrag extends Fragment {
/*MekanikAdapter mekanikAdapter;
RecyclerView recyclerMekanik, recyclerDekor;*/
Context context;

RecyclerView recyclerView;
CategoryAdapter categoryAdapter;
List<Category.DATABean> dataBeans;

ViewPager viewPager;
AdapterViewPager adapterViewPager;
ArrayList<Banner.DATABean> banners;

ApiInterface apiHelper;

    public HomeFrag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
/*        recyclerMekanik = view.findViewById(R.id.recyclerElektronik);
        recyclerDekor = view.findViewById(R.id.recyclerDekor);*/
        context = view.getContext();
        apiHelper = UtilsApi.getApiService();
        viewPager = view.findViewById(R.id.viewPager);
        recyclerView = view.findViewById(R.id.recyclerGroup);

        FetchBanner();
        fetchCategory();
/*
        mekanikAdapter = new MekanikAdapter(context);
        recyclerMekanik.setAdapter(mekanikAdapter);
        recyclerMekanik.setLayoutManager(new GridLayoutManager(context, 2));

        recyclerDekor.setAdapter(mekanikAdapter);
        recyclerDekor.setLayoutManager(new GridLayoutManager(context, 2));*/



        return view;
    }

    private void fetchCategory() {
        apiHelper.getCategory(UtilsApi.APP_TOKEN).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("STATUS").equals("200")){
                            JSONArray jsonArray = jsonObject.getJSONArray("DATA");

                            dataBeans = new ArrayList<>();
                            Gson gson = new Gson();
                            for (int i = 0; i < jsonArray.length(); i++){
                                Category.DATABean dataBean = gson.fromJson(jsonArray.getJSONObject(i).toString(), Category.DATABean.class);
                                dataBeans.add(dataBean);
                            }

                            categoryAdapter = new CategoryAdapter(context, dataBeans);
                            recyclerView.setAdapter(categoryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setHasFixedSize(true);
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

    private void FetchBanner() {
        apiHelper.getBanner("clabsLar4dm1n!@#$4Nu1n4Pp$").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if (object.getString("STATUS").equals("200")){
                            JSONArray array = object.getJSONArray("DATA");

                            banners = new ArrayList<>();

                            Gson gson = new Gson();
                            for (int i = 0; i < array.length(); i++) {
                                Banner.DATABean banner = gson.fromJson(array.getJSONObject(i).toString(),Banner.DATABean.class);
                                banners.add(banner);
                            }

                            adapterViewPager = new AdapterViewPager(context,banners);

                            viewPager.setAdapter(adapterViewPager);
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

}
