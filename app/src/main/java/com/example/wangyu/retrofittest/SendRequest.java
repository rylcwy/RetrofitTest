package com.example.wangyu.retrofittest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;


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

    @HTTP(method = "GET",path="/application/582aaa0455554/releases?page=id",hasBody = false)
    Call<ResponseBody> getTapTapBeta1(@Path("id")int id);




}
