package com.example.wangyu.retrofittest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import static com.example.wangyu.retrofittest.saveCookie.cookieStr;


public class MainActivity extends AppCompatActivity {

    public static String realtoken;
    public static String token;
    public static String cookieStr="";
    public static CheckLogin checkLogin;

    @SuppressLint("TrulyRandom")
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()},
                    new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return sSLSocketFactory;
    }

    private static class TrustAllManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static OkHttpClient client=new OkHttpClient.Builder()
            .hostnameVerifier(new TrustAllHostnameVerifier())
            .sslSocketFactory(createSSLSocketFactory())
            .addInterceptor(new saveCookie())
            .build();


    public static Retrofit retrofitLogin=new Retrofit.Builder()
            .baseUrl("https://versions.xmxdev.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    public static SendRequest res=retrofitLogin.create(SendRequest.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkLogin=new CheckLogin(MainActivity.this);
        if (checkLogin.getLoginState()) {
            Toast.makeText(MainActivity.this, "进入列表页", Toast.LENGTH_LONG).show();

        }
        else {
            Intent intent=new Intent();
            intent.setAction("android.intent.action.login");
            startActivity(intent);
            getToken(new TokenCallbacks() {
                @Override
                public void onSuccess(@NonNull String token) {


                }

                @Override
                public void onFailure(@NonNull Throwable throwable) {

                }
            });
        }


    }

    public void getToken(final TokenCallbacks callbacks) {

        Call<ResponseBody> call = res.getCookie();
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse (@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                    if ( response.body()!= null) {
                        try {
                            String getToken=response.body().string();
                            int begin_index=getToken.indexOf("\"_token\" value=");
                            int end_index=getToken.lastIndexOf("<!-- Email -->");
                            String test2=getToken.substring(begin_index,end_index);
                            Pattern p = Pattern.compile("=\"(.*)\"");
                            Matcher m =p.matcher(test2);
                            while(m.find()){
                                token=m.group(1);
                                Log.d("MainActivity", "token: "+token);
                                callbacks.onSuccess(token);
                            }
                        } catch (Exception e) {
                            Log.d("MainActivity", "Error" + e);
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                    Log.d("MainActivity", "onFailure: " + t);

                }
            });



    }
}
