package  com.example.wangyu.retrofittest;

import android.content.Context;

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
        cookieStore.persist(cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
       return cookieStore.load();
    }
}
