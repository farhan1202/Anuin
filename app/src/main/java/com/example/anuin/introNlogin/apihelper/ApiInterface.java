package com.example.anuin.introNlogin.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiInterface {
    @GET("walkthrough")
    Call<ResponseBody> getWalkthrough(@Header("APP_TOKEN") String token);

    @GET("banner")
    Call<ResponseBody> getBanner(@Header("APP_TOKEN") String token);
}
