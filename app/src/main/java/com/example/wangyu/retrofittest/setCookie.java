package com.example.wangyu.retrofittest;

import android.util.Log;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.example.wangyu.retrofittest.LoginActivity.CheckBoxState;
import static com.example.wangyu.retrofittest.LoginActivity.useremail;
import static com.example.wangyu.retrofittest.LoginActivity.userpassword;
import static com.example.wangyu.retrofittest.MainActivity.checkLogin;
import static com.example.wangyu.retrofittest.MainActivity.token;
import static com.example.wangyu.retrofittest.saveCookie.cookieStr;


public class setCookie implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request updateRequest=originalRequest.newBuilder()
                .addHeader("cookie",cookieStr)
                .addHeader("content-type","application/x-www-form-urlencoded")
                .addHeader("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                .build();
        Log.d("cookies", "intercept: "+cookieStr);
        return chain.proceed(updateRequest);

    }
}
