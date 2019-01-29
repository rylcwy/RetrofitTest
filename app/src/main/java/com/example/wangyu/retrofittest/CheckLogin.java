package com.example.wangyu.retrofittest;

import static com.example.wangyu.retrofittest.PersistentCookieStore.loginstatusPrefs;


public class CheckLogin{


    public  static  boolean getLoginState(){
        return loginstatusPrefs.getBoolean("isLogin",false);
    }





}
