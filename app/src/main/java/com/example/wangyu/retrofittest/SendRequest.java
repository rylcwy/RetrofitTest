package com.example.wangyu.retrofittest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface SendRequest {
    @GET("/login")
    Call<ResponseBody> getCookie();

    @POST("/login")
    @FormUrlEncoded
    Call<ResponseBody> getApps(@Field("email") String email, @Field("password") String password, @Field("_token") String token);

    @GET("/")
    Call<ResponseBody> getApps_islogin();

    @GET("/application/582aaa0455554/releases")
    Call<ResponseBody> getTapTapBeta();




}
