package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;

public class PersistentCookieStore {
    private static final String DOMAIN = "versions.xmxdev.com";

    private final String COOKIE_PREFS = "Cookies_Prefs";
    private SharedPreferences cookiePrefs;
    public Map<String, String> cookies;

    public PersistentCookieStore(Context context) {
        cookies = new HashMap<>();
        cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        Map<String, ?> prefsMap = cookiePrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            String content = "";
            content += (entry.getKey() + ":" + entry.getValue());
            System.out.print("content" + content);
        }
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
                SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
                prefsWriter.putString(cookie.name(), cookie.value());
                prefsWriter.apply();
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