package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.SharedPreferences;

public class CheckLogin{
    public Context context;


    public CheckLogin(Context context){
        this.context=context;
    }

    public void setLoginState(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("LoginStatus",0);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean("isLogin",true);
        editor.apply();

    }

    public boolean getLoginState(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("LoginStatus",0);
        return sharedPreferences.getBoolean("isLogin",false);
    }





}
