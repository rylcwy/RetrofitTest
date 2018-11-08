package com.example.wangyu.retrofittest;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.wangyu.retrofittest.LoginActivity.cookieStr22;
import static com.example.wangyu.retrofittest.LoginActivity.useremail;
import static com.example.wangyu.retrofittest.LoginActivity.userpassword;
import static com.example.wangyu.retrofittest.MainActivity.token;


public class setCookie2 implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request updateRequest=originalRequest.newBuilder()
                .addHeader("cookie",cookieStr22)
                .addHeader("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
                .build();
        Log.d("cookie", "登陆请求的cookie"+cookieStr22);

        return   chain.proceed(updateRequest);
    }
}
