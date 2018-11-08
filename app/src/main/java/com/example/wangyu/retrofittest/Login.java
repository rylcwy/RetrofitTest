//package com.example.wangyu.retrofittest;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.util.Log;
//import android.widget.CheckBox;
//
//import java.io.IOException;
//
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import static com.example.wangyu.retrofittest.MainActivity.*;
//import static com.example.wangyu.retrofittest.LoginActivity.CheckBoxState;
//import static com.example.wangyu.retrofittest.LoginActivity.useremail;
//import static com.example.wangyu.retrofittest.LoginActivity.userpassword;
//import com.example.wangyu.retrofittest.LoginActivity;
//
//public class Login {
//
//    public static void userLogin(){
//        final Call<ResponseBody> logincall=res.getApps(useremail,userpassword, CheckBoxState,token);
//        logincall.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
//                try{
//
//
//                    Log.d("Login", "没登陆，登陆完毕拿请求 "+response.body().string());
//                }
//                catch (IOException e){
//                    Log.d("Login", "onResponse: "+e);
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
//                Log.d("Login", "onFailure: "+t);
//
//            }
//        });
//    }
//
//
//
//
//}
