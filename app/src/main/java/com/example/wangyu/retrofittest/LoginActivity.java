package com.example.wangyu.retrofittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText inputemail;
    public EditText inputpassword;
    private static String userEmail;
    private static String userPassword;
    private static String userToken;
    public static CheckBox rememberMe;
    public static String CheckBoxState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputemail=(EditText)findViewById(R.id.Email);
        inputpassword=(EditText)findViewById(R.id.password);
        rememberMe=(CheckBox)findViewById(R.id.remenberme);
        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
        userToken=LoginComponent.getToken();

    }


    public void logIn(){

        Call<ResponseBody> loginCall=RetrofitCommunication.getRes().login(userEmail,userPassword,LoginComponent.getToken());

        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response1) {
                try{
                    Call<ResponseBody> loginRedirectCall=RetrofitCommunication.getRes().getAppsRedirect();
                    loginRedirectCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                if (response.body().string().contains("登录你的账户")||response.body().string().contains("记住我")){
                                    Toast.makeText(MyApplication.getContext(),"登录失败,请重新登录",Toast.LENGTH_SHORT).show();
                                }

                                else {
                                    Intent intent=new Intent();
                                    intent.setAction("android.intent.action.projectlist");
                                    startActivity(intent);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                userEmail= inputemail.getText().toString();
                userPassword=inputpassword.getText().toString();
                if(rememberMe.isChecked()){
                    CheckBoxState="on";
                }
                else {
                    CheckBoxState="off";
                }

                logIn();
                break;
            default:
                break;
        }
            }
}

