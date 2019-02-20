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

    public static void getToken(final LoginActivity.User user) {
        Call<ResponseBody> getTokenCall = RetrofitCommunication.getRes().getCookie();
        getTokenCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse (@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if ( response.body()!= null) {
                    try {
                        String TokenStr=response.body().string();
                        int begin_index=TokenStr.indexOf("\"_token\" value=");
                        int end_index=TokenStr.lastIndexOf("<!-- Email -->");
                        String test2=TokenStr.substring(begin_index,end_index);
                        Pattern p = Pattern.compile("=\"(.*)\"");
                        Matcher m =p.matcher(test2);
                        while(m.find()){
                            logintoken=m.group(1);
                            user.setUserToken(logintoken);
                            LoginActivity loginActivity=new LoginActivity();
                            loginActivity.logIn(user);
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
    }




}
