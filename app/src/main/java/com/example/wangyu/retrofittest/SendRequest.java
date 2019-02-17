package com.example.wangyu.retrofittest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface SendRequest {
    @GET("/login")
    Call<ResponseBody> getCookie();

    @POST("/login")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("email") String email, @Field("password") String password, @Field("_token") String token);

    @GET("/")
    Call<ResponseBody> getAppsRedirect();

    @GET("/application/582aaa0455554/releases?")
    Call<ResponseBody> getTapTapBeta(@Query("page") int page);

    @GET("/application/582aaa0455554/releases?")
    Call<ResponseBody> getTapTapRelease(@Query("page") int page);




}
