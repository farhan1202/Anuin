package com.example.anuin.utils.apihelper;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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

    @FormUrlEncoded
    @POST("change-password-token")
    Call<ResponseBody> changePassword(@Header("APP_TOKEN") String tokenApp,
                                      @Header("USER_TOKEN") String tokenUser,
                                      @Field("id") int idMember,
                                      @Field("new_password") String newPass,
                                      @Field("confirm_password") String newPassConfirm);

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
    Call<ResponseBody> getPassword(@Header("APP_TOKEN") String token,
                                   @Field("email") String email,
                                   @Field("reset_date") String date);

    @FormUrlEncoded
    @POST("member-address-add")
    Call<ResponseBody> addAddress(@Header("APP_TOKEN") String tokenApp,
                                  @Header("USER_TOKEN") String tokenUser,
                                  @Field("member_id") int idMember,
                                  @Field("alamat") String alamat,
                                  @Field("lokasi_maps") String lokasi,
                                  @Field("provinsi") String provinsi,
                                  @Field("city") String city,
                                  @Field("kecamatan") String kecamatan,
                                  @Field("kelurahan") String kelurahan,
                                  @Field("kode_post") String kodePost,
                                  @Field("property") String property);

    @FormUrlEncoded
    @POST("member-address-update")
    Call<ResponseBody> updateAddress(@Header("APP_TOKEN") String tokenApp,
                                     @Header("USER_TOKEN") String tokenUser,
                                     @Field("member_id") int idMember,
                                     @Field("member_address_id") int addressId,
                                     @Field("alamat") String alamat,
                                     @Field("lokasi_maps") String lokasi,
                                     @Field("provinsi") String provinsi,
                                     @Field("city") String city,
                                     @Field("kecamatan") String kecamatan,
                                     @Field("kelurahan") String kelurahan,
                                     @Field("kode_post") String kodePost,
                                     @Field("property") String property);

    @FormUrlEncoded
    @POST("member-address")
    Call<ResponseBody> getAddressUser(@Header("APP_TOKEN") String tokenApp,
                                      @Header("USER_TOKEN") String tokenUser,
                                      @Field("member_id") int idMember);

    @FormUrlEncoded
    @POST("member-address-detail")
    Call<ResponseBody> getAddressUserDetail(@Header("APP_TOKEN") String tokenApp,
                                            @Header("USER_TOKEN") String tokenUser,
                                            @Field("member_id") int idMember,
                                            @Field("member_address_id") int idAddressMember);

    @FormUrlEncoded
    @POST("member-address-delete")
    Call<ResponseBody> deleteAddressUser(@Header("APP_TOKEN") String tokenApp,
                                         @Header("USER_TOKEN") String tokenUser,
                                         @Field("member_id") int idMember,
                                         @Field("member_address_id") int idAddressMember);

    @GET("payment-method/{id}")
    Call<ResponseBody> getMethodPaymentDetail (@Header("APP_TOKEN") String tokenApp,
                                               @Header("USER_TOKEN") String tokenUser,
                                               @Path("id") int id);

    @Multipart
    @POST("booking-order")
    Call<ResponseBody> bookingOrder(@HeaderMap Map<String, String> stringStringMap,
                                    @PartMap Map<String, RequestBody> stringBody,
                                    @Part MultipartBody.Part[] filePart
    );

    @FormUrlEncoded
    @POST("booking-payment")
    Call<ResponseBody> bookingPayment(@Header("APP_TOKEN") String tokenApp,
                                      @Header("USER_TOKEN") String tokenUser,
                                      @Field("member_id") int idMember,
                                      @Field("booking_id") int bookingId,
                                      @Field("biaya_panggil") int biayaPanggil,
                                      @Field("biaya_layanan") int biayaLayanan,
                                      @Field("total_tagihan") int totalTagihan,
                                      @Field("payment_method") String paymentMethod,
                                      @Field("payment_driver") String payment_driver);

    @FormUrlEncoded
    @POST("booking-approve")
    Call<ResponseBody> bookingApprove(@Header("APP_TOKEN") String tokenApp,
                                      @Header("USER_TOKEN") String tokenUser,
                                      @Field("member_id") int idMember,
                                      @Field("booking_id") int bookingId,
                                      @Field("merchant_id") int merchant_id,
                                      @Field("feedback") String feedback,
                                      @Field("rating") int rating
                                      );

    @GET("provinsi-list")
    Call<ResponseBody> getProvinsi(@Header("APP_TOKEN") String token
    );



    @GET("kabupaten-list/{id}")
    Call<ResponseBody> getKabupaten(@Header("APP_TOKEN") String token,
                                    @Path("id") int id
    );

    @GET("kecamatan-list/{id}")
    Call<ResponseBody> getKecamatan(@Header("APP_TOKEN") String token,
                                    @Path("id") int id
    );

    @GET("kelurahan-list/{id}")
    Call<ResponseBody> getKelurahan(@Header("APP_TOKEN") String token,
                                    @Path("id") int id
    );

    @GET("provinsi-detail/{id}")
    Call<ResponseBody> getDetailProvinsi(@Header("APP_TOKEN") String token,
                                         @Path("id") int id);

    @GET("kabupaten-detail/{id}")
    Call<ResponseBody> getDetailKabupaten(@Header("APP_TOKEN") String token,
                                          @Path("id") int id);

    @GET("kecamatan-detail/{id}")
    Call<ResponseBody> getDetailKecamatan(@Header("APP_TOKEN") String token,
                                          @Path("id") int id);

    @GET("kelurahan-detail/{id}")
    Call<ResponseBody> getDetailKelurahan(@Header("APP_TOKEN") String token,
                                          @Path("id") int id);

    @GET("privacy-policy")
    Call<ResponseBody> getPrivacyPolicy(@Header("APP_TOKEN") String token
    );

    @GET("helps-group")
    Call<ResponseBody> getHelps(@Header("APP_TOKEN") String token
    );

    @POST("contact")
    Call<ResponseBody> aboutUs(@Header("APP_TOKEN") String token);


    @FormUrlEncoded
    @POST("booking-list")
    Call<ResponseBody> getBookingLIst(@Header("APP_TOKEN") String token,
                                      @Header("USER_TOKEN") String userToken,
                                      @Field("member_id") int mID
    );

    @FormUrlEncoded
    @POST("booking-detail")
    Call<ResponseBody> getBookingDetail(@Header("APP_TOKEN") String token,
                                      @Header("USER_TOKEN") String userToken,
                                      @Field("member_id") int mID,
                                      @Field("booking_id") int idBooking
    );

    @FormUrlEncoded
    @POST("booking-complete-list")
    Call<ResponseBody> getOrderComplete (@Header("APP_TOKEN") String token,
                                           @Header("USER_TOKEN") String userToken,
                                           @Field("member_id") int mID
    );

    @GET("terms-of-use")
    Call<ResponseBody> termOfUse(@Header("APP_TOKEN") String token
    );

    @GET("about")
    Call<ResponseBody> getAbout(@Header("APP_TOKEN") String token

    );

}
