package com.example.anuin.introNlogin.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("walkthrough")
    Call<ResponseBody> getWalkthrough();
}
