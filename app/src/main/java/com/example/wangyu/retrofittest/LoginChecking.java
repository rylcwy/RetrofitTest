package com.example.wangyu.retrofittest;


public class LoginChecking {

    public  static  boolean getLoginState(){
        return PersistentCookieStore.getLoginstatusPrefs().getBoolean("isLogin",false);
    }

}
