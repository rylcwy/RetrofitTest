package com.example.wangyu.retrofittest;

import android.content.Context;
import android.util.Log;

import java.net.CookieStore;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieManager implements CookieJar{
    public static final String logtag="cookies";
    private PersistentCookieStore cookieStore;
    public CookieManager(Context context){
        cookieStore=new PersistentCookieStore(context);
    }
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        for(int i=0;i<cookies.size();i++){
            Log.d(logtag, "saveFromResponse: "+cookies.get(i));
            cookieStore.add(cookies.get(i));

        }


    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return cookieStore.getCookie();


    }
}
