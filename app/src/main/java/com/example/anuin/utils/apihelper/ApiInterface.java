package com.example.anuin.utils.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("walkthrough")
    Call<ResponseBody> getWalkthrough(@Header("APP_TOKEN") String token);

    @FormUrlEncoded
    @POST("member-login")
    Call<ResponseBody> getLogin(@Header("APP_TOKEN") String token,
                                @Field("email") String email,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("member-register")
    Call<ResponseBody> registerRequest(@Header("APP_TOKEN") String token,
                                       @Field("name") String name,
                                       @Field("username") String username,
                                       @Field("email") String email,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("member-profile")
    Call<ResponseBody> requestProfile(@Header("APP_TOKEN") String tokenApp,
                                      @Header("USER_TOKEN") String tokenUser,
                                      @Field("member_id") int idMember);

    @GET("banner")
    Call<ResponseBody> getBanner(@Header("APP_TOKEN") String token);

    @GET("category")
    Call<ResponseBody> getCategory(@Header("APP_TOKEN") String token);

    @GET("product-jasa/{id}")
    Call<ResponseBody> getProductJasa(@Header("APP_TOKEN") String token,
                                      @Path("id") int id);

    @GET("product-jasa-detail/{id}")
    Call<ResponseBody> getProductJasaDetail(@Header("APP_TOKEN") String token,
                                            @Path("id") int id);
}
