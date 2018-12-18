package com.example.wangyu.retrofittest;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface SendRequest {
    @GET("/login")
    Call<ResponseBody> getCookie();



    @POST("/login")
    @FormUrlEncoded
    Call<ResponseBody> getApps(@Field("email") String email, @Field("password") String password, @Field("_token") String token);

    @GET("/")
    Call<ResponseBody> getApps_islogin();

    @GET("/application/582aaa0455554/releases?page=1")
    Call<ResponseBody> getTapTapBeta();




}
