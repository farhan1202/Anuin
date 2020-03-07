package com.example.anuin.introNlogin.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("walkthrough")
    Call<ResponseBody> getWalkthrough(@Header("APP_TOKEN") String token);

    @GET("banner")
    Call<ResponseBody> getBanner(@Header("APP_TOKEN") String token);

    @FormUrlEncoded
    @POST("member-register")
    Call<ResponseBody> registerRequest(@Header("APP_TOKEN") String token,
                                       @Field("name") String name,
                                       @Field("username") String username,
                                       @Field("email") String email,
                                       @Field("password") String password);
}
