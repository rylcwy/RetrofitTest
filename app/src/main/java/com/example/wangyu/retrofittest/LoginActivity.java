package com.example.wangyu.retrofittest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Time;
import java.util.List;
import java.util.Timer;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Utf8;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import javax.net.ssl.HostnameVerifier;
import com.example.wangyu.retrofittest.MainActivity.*;

import static com.example.wangyu.retrofittest.MainActivity.realtoken;
import static com.example.wangyu.retrofittest.MainActivity.token;
import static com.example.wangyu.retrofittest.saveCookie.cookieStr;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static String cookieStr22="";
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
    public EditText inputemail;
    public EditText inputpassword;
    public static String useremail;
    public static String userpassword;
    public static CheckBox remember;
    public static String CheckBoxState;
    public CheckLogin loginStateUpdate=new CheckLogin(LoginActivity.this);


    public static OkHttpClient client1=new OkHttpClient.Builder()
            .hostnameVerifier(new TrustAllHostnameVerifier())
            .addInterceptor(new setCookie())
            .sslSocketFactory(createSSLSocketFactory())
            .followRedirects(false)
            .build();


    public static Retrofit retrofitLogin1=new Retrofit.Builder()
            .baseUrl("https://versions.xmxdev.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client1)
            .build();

    public static SendRequest res1=retrofitLogin1.create(SendRequest.class);


    public static OkHttpClient client2=new OkHttpClient.Builder()
            .hostnameVerifier(new TrustAllHostnameVerifier())
            .addInterceptor(new setCookie2())
            .sslSocketFactory(createSSLSocketFactory())
            .followRedirects(false)
            .build();


    public static Retrofit retrofitLogin2=new Retrofit.Builder()
            .baseUrl("https://versions.xmxdev.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client2)
            .build();

    public static SendRequest res2=retrofitLogin2.create(SendRequest.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputemail=(EditText)findViewById(R.id.Email);
        inputpassword=(EditText)findViewById(R.id.password);
        remember=(CheckBox)findViewById(R.id.remenberme);
        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                useremail= inputemail.getText().toString();
                userpassword=inputpassword.getText().toString();
                if(remember.isChecked()){
                    CheckBoxState="on";
                }
                else {
                    CheckBoxState="off";
                }


                final Call<ResponseBody> logincall=res1.getApps(useremail,userpassword,token);

                logincall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response1) {
                        try{
                            List<String> cookies=response1.headers().values("set-cookie");
                            if (cookies != null && cookies.size() > 0) {
                                for (int i = 0; i < cookies.size(); i++) {
                                    String cookie=cookies.get(i).substring(0,cookies.get(i).indexOf(";"))+";"+" ";
                                    cookieStr22+=cookie;

                                }
                                cookieStr22=cookieStr22.substring(0,cookieStr22.lastIndexOf(";"));
                                Log.d("cookie", "点击登陆之后: "+cookieStr22);
                                final Call<ResponseBody> logincall2=res2.getApps_islogin();
                                logincall2.enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        try{
                                            Log.d("nb", "onResponse: "+response.body().string());
                                        }
                                        catch (IOException e){

                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });

                            }
                            realtoken=token;
                            loginStateUpdate.setLoginState(realtoken,cookieStr);
                        }
                        catch (Exception e){
                            Log.d("LoginActivity", "Exception: "+e);

                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Log.d("Login", "onFailure: "+t);

                    }
                });


                break;
            default:
                break;
        }


            }
}

