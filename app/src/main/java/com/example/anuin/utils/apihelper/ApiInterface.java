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
    @POST("member-login-social-media")
    Call<ResponseBody> loginSocialMedia(@Header("APP_TOKEN") String token,
                                        @Field("provider") String provider,
                                        @Field("provider_id") String provide_id,
                                        @Field("email") String email);

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

    @FormUrlEncoded
    @POST("member-update")
    Call<ResponseBody> updateProfile(@Header("APP_TOKEN") String tokenApp,
                                     @Header("USER_TOKEN") String tokenUser,
                                     @Field("member_id") int idMember,
                                     @Field("name") String name,
                                     @Field("username") String username,
                                     @Field("email") String email,
                                     @Field("phone_number") String phone_number,
                                     @Field("member_image") String member_image);

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

    @FormUrlEncoded
    @POST("forgot-password")
    Call<ResponseBody> getPassword (@Header("APP_TOKEN") String token,
                                    @Field("email") String email,
                                    @Field("reset_date") String date);

    @FormUrlEncoded
    @POST("member-address-add")
    Call<ResponseBody> addAddress (@Header("APP_TOKEN") String token,
                                   @Header("USER_TOKEN") String uTOken,
                                   @Field("member_id") int id,
                                   @Field("alamat") String alamat,
                                   //lokasi maps
                                   @Field("city") String city,
                                   @Field("kecamatan") String kec,
                                   @Field("kelurahan") String kel,
                                   @Field("kode_post") String pos,
                                   @Field("property") String properti

    );

    @GET("provinsi-list")
    Call<ResponseBody> getProvinsi(@Header("APP_TOKEN") String token
                                   );

    @GET("kabupaten-list/{id}")
    Call<ResponseBody> getKabupaten (@Header("APP_TOKEN") String token,
                                     @Path("id") int id
    );

    @GET("kecamatan-list/13")
    Call<ResponseBody> getKecamatan( @Header("APP_TOKEN") String token
    );
}
