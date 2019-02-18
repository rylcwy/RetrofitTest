package com.example.wangyu.retrofittest;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginComponent {
    private static String logintoken;

    public static String getToken() {
        Call<ResponseBody> call = RetrofitCommunication.getRes().getCookie();
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
                            logintoken=m.group(1);
                        }
                    } catch (Exception e) {
                        Log.d("MainActivity", "Error" + e);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                LogUtil.e("getToken","get token request error!");
            }
        });
        return logintoken;
    }

}