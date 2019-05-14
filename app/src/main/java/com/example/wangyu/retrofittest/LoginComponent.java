package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.wangyu.retrofittest.LoginActivity.inputemail;
import static com.example.wangyu.retrofittest.LoginActivity.inputpassword;

public class LoginComponent {
    private static String logintoken;
    private static String logouttoken;


    class User{
        private  String userEmail;
        private  String userPassword;
        private  String userToken;

        public  void setUserEmail(){
            this.userEmail=inputemail.getText().toString();
        }

        public  void setUserPassword(){
            this.userPassword=inputpassword.getText().toString();
        }

        public void setUserToken(String userToken){
            this.userToken=userToken;
        }

    }

    public void getToken(final Context context) {
        final User user=new User();
        Call<ResponseBody> getTokenCall = RetrofitCommunication.getRes().getCookie();
        getTokenCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    try {
                        String TokenStr = response.body().string();
                        int begin_index = TokenStr.indexOf("\"_token\" value=");
                        int end_index = TokenStr.lastIndexOf("<!-- Email -->");
                        String test2 = TokenStr.substring(begin_index, end_index);
                        Pattern p = Pattern.compile("=\"(.*)\"");
                        Matcher m = p.matcher(test2);
                        while (m.find()) {
                            logintoken = m.group(1);
                            user.setUserToken(logintoken);
                            user.setUserEmail();
                            user.setUserPassword();
                            logIn(context,user);
                        }
                    } catch (Exception e) {
                        Log.d("MainActivity", "Error" + e);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                LogUtil.e("getToken", "get token request error!");
            }
        });
    }


    public  void logIn(final Context context, User user){
        Call<ResponseBody> loginCall=RetrofitCommunication.getRes().login(user.userEmail,user.userPassword,user.userToken);
        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response1) {
                try{
                    Call<ResponseBody> loginRedirectCall=RetrofitCommunication.getRes().getAppsRedirect();
                    loginRedirectCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String html=response.body().string();
                                if (html.contains("登录你的账户")||html.contains("记住我")){
                                    Toast.makeText(MyApplication.getContext(),"登录失败,请重新登录",Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Document doc=Jsoup.parse(html);
                                    Elements elements=doc.select("#logout-form");
                                    logouttoken= StringUtils.substringBetween(elements.toString(),"value=\"","\"");
                                    Intent intent=new Intent();
                                    intent.setAction("android.intent.action.projectlist");
                                    context.startActivity(intent);

                                }

                            }
                            catch (IOException e ){
                                Toast.makeText(MyApplication.getContext(),"登录错误"+response.code(),Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            LogUtil.d("LoginActivity", "onFailure: "+t);
                        }
                    });
                }
                catch (Exception e){
                    LogUtil.d("LoginActivity", "Exception: "+e);

                }

            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                LogUtil.d("LoginActivity", "onFailure: "+t);

            }
        });
    }



    public static String getLogouttoken(){
        return logouttoken;
    }



}
