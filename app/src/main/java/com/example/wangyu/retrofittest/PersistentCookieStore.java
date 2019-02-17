package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;

public class PersistentCookieStore {
    private static final String DOMAIN = "versions.xmxdev.com";

    private final String COOKIE_PREFS = "Cookies_Prefs";
    private final String LOGIN_STATUS = "Login_Status";
    public static SharedPreferences.Editor prefsWriter;
    public static SharedPreferences.Editor loginstatueWriter;
    public static SharedPreferences cookiePrefs;
    public static SharedPreferences loginstatusPrefs;


    public PersistentCookieStore(Context context) {

        context=MyApplication.getContext();

        cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        loginstatusPrefs=context.getSharedPreferences(LOGIN_STATUS,0);

        Map<String, ?> prefsMap = cookiePrefs.getAll();
    }

    protected String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    /**
     *
     * write cookie into file
     *
     * @param cookies
     */
    public void persist(List<Cookie> cookies){
        for (Cookie cookie: cookies) {
            if (cookie.persistent() && cookie.domain() != null && cookie.domain().endsWith(DOMAIN)) {
                prefsWriter = cookiePrefs.edit();
                prefsWriter.putString(cookie.name(), cookie.value());
                prefsWriter.apply();

                loginstatueWriter=loginstatusPrefs.edit();
                loginstatueWriter.putBoolean("isLogin",true);
                loginstatueWriter.apply();
            }
        }
    }

    /**
     * load cookie from file
     *
     * @return
     */
    public List<Cookie> load() {
        List<Cookie> result = new ArrayList<>();

        Map<String, ?> allCookie = cookiePrefs.getAll();
        for (Map.Entry<String, ?> entry: allCookie.entrySet()) {
            Cookie.Builder builder = new Cookie.Builder();

            builder.domain(DOMAIN);
            builder.name(entry.getKey());
            builder.value((String) entry.getValue());

            result.add(builder.build());
        }


        return result;
    }

}