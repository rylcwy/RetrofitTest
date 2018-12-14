package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.content.SharedPreferences;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;

public class PersistentCookieStore {
    private final String COOKIE_PREFS = "Cookies_Prefs";
    private static final String COOKIE_NAME_PREFIX = "cookie_";
    private SharedPreferences cookiePrefs;
    public Map<String, String> cookies;

    public PersistentCookieStore(Context context) {
        cookies=new HashMap<>();
        cookiePrefs =context.getSharedPreferences(COOKIE_PREFS, 0);
        Map<String, ?> prefsMap = cookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            String content="";
            content+=(entry.getKey()+":"+entry.getValue());
            System.out.print("content"+content);
            }
        }



    protected String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public void add(Cookie cookie){
        String name = getCookieToken(cookie);
        if(!cookie.persistent()){
            cookies.put(cookie.name(),cookie.value());

        }
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();

            prefsWriter.putString(cookie.domain();
            prefsWriter.apply();


    }

    public List<Cookie> getCookie() {
        ArrayList<Cookie> ret = new ArrayList<Cookie>();
        cookiePrefs.
        if (cookies.containsKey("versions.xmxdev.com"))
            for (String key:cookies.keySet()){
                ret.addAll(cookies.get(key).values())
            }

        return ret;

    }

}