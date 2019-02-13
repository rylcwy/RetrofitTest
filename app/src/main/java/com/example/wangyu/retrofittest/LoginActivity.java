package com.example.wangyu.retrofittest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.wangyu.retrofittest.MainActivity.realtoken;
import static com.example.wangyu.retrofittest.MainActivity.token;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText inputemail;
    public EditText inputpassword;
    public static String useremail;
    public static String userpassword;
    public static CheckBox remember;
    public static String CheckBoxState;
    public static int responseCode;

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


    public static Retrofit retrofitLogin1=new Retrofit.Builder()
            .baseUrl("https://versions.xmxdev.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(MainActivity.client)
            .build();


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


    public int logIn(LoginCallbacks loginCallbacks){

        final Call<ResponseBody> login=MainActivity.res.login(useremail,userpassword,token);

        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response1) {
                try{
                    final Call<ResponseBody> loginRedirect=MainActivity.res.getAppsRedirect();
                    loginRedirect.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            responseCode=response.code();
                            try {
                                if (response.body().string().contains("登录你的账户")||response.body().string().contains("记住我")){
                                    Toast.makeText(MyApplication.getContext(),"登陆失败",Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Intent intent=new Intent();
                                    intent.setAction("android.intent.action.projectlist");
                                    startActivity(intent);
                                }

                            }
                            catch (IOException e ){
                                Toast.makeText(MyApplication.getContext(),"登陆错误"+response.code(),Toast.LENGTH_SHORT).show();

                            }



                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });


                    realtoken=token;
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
        return responseCode;
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
                logIn(new LoginCallbacks() {
                    @Override
                    public void getProjectList(int responseCode) {
                        if (responseCode==200){


                        }
                        else onFailure();
                    }

                    @Override
                    public void onFailure() {

                    }
                });
                break;
            default:
                break;
        }
            }
}

