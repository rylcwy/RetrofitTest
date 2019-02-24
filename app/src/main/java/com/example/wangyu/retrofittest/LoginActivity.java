package com.example.wangyu.retrofittest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
//todo activity.this ?this?
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    public static EditText inputemail;
    public static EditText inputpassword;
    public static CheckBox rememberMe;
    public static String CheckBoxState;
    public static final String TAG="LoginActivity";
    private LoginActivity loginActivity;
    MyApplication myApplication;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.d("TAG","LoginActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputemail=(EditText)findViewById(R.id.Email);
        inputpassword=(EditText)findViewById(R.id.password);
        rememberMe=(CheckBox)findViewById(R.id.remenberme);
        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
        mContext=MyApplication.getContext();
        addActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                LoginComponent loginComponent=new LoginComponent();
                loginComponent.getToken(mContext);
                if(rememberMe.isChecked()){
                    CheckBoxState="on";
                }
                else {
                    CheckBoxState="off";
                }
                break;
            default:
                break;
        }
            }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG, "LoginActivity onPause");
        removeActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG, "LoginActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "LoginActivity onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG, "LoginActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG, "LoginActivity onResume");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TAG, "LoginActivity onRestart");
    }

    public void addActivity() {
        ((MyApplication)getApplication()).addActivity_(this);// 调用myApplication的添加Activity方法
    }

    public void removeActivity(){
        ((MyApplication)getApplication()).removeActivity_(this);
    }

}



