package com.example.anuin.introNlogin.apihelper;

public class UtilsApi {
    public static final String BASE_URL = "http://104.248.149.236/anuin/gateway/public/api/";
    public static ApiInterface getApiService(){
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }

}
