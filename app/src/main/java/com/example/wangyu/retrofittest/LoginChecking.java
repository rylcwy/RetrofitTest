package com.example.wangyu.retrofittest;

import static com.example.wangyu.retrofittest.PersistentCookieStore.loginstatusPrefs;


public class LoginChecking {

    public  static  boolean getLoginState(){
        return loginstatusPrefs.getBoolean("isLogin",false);
    }





}
