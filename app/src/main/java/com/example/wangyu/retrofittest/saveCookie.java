package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class saveCookie implements Interceptor {

    public static String cookieStr = "";
    @Override
    public  Response intercept(Chain chain) throws IOException {
        Response resp = chain.proceed(chain.request());
        List<String> cookies = resp.headers("set-cookie");

        if (cookies != null && cookies.size() > 0) {
            for (int i = 0; i < cookies.size(); i++) {
                String cookie=cookies.get(i).substring(0,cookies.get(i).indexOf(";"))+";"+" ";
                cookieStr+=cookie;

            }
            cookieStr=cookieStr.substring(0,cookieStr.lastIndexOf(";"));
        }
        return resp;


    }

}
