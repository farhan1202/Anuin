package com.example.anuin.utils.apihelper;

public class UtilsApi {
    public static final String BASE_URL = "http://104.248.149.236/anuin/gateway/public/api/";
//    public static final String BASE_URL = "http://gateway.fixid-dev.online/api/";
    public static final String APP_TOKEN = "clabsLar4dm1n!@#$4Nu1n4Pp$";

    public static ApiInterface getApiService(){
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }

}
