package com.example.wangyu.retrofittest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//todo activity.this ?this?
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static EditText inputemail;
    public static EditText inputpassword;
    public static CheckBox rememberMe;
    public static String CheckBoxState;
    public static final String TAG = "LoginActivity";
    private long clickTime=0;
    private LoginActivity loginActivity;
    MyApplication myApplication;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityUtils.addActivity(LoginActivity.this);
        LogUtil.d("TAG", "LoginActivity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputemail = (EditText) findViewById(R.id.Email);
        inputpassword = (EditText) findViewById(R.id.password);
        rememberMe = (CheckBox) findViewById(R.id.remenberme);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        mContext = MyApplication.getContext();
        addActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                LoginComponent loginComponent = new LoginComponent();
                loginComponent.getToken(mContext);
                if (rememberMe.isChecked()) {
                    CheckBoxState = "on";
                } else {
                    CheckBoxState = "off";
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
        ActivityUtils.removeActivity(LoginActivity.this);
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
        ((MyApplication) getApplication()).addActivity_(this);// 调用myApplication的添加Activity方法
    }

    public void removeActivity() {
        ((MyApplication) getApplication()).removeActivity_(this);
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            onBackPressed();
            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }

    }

    private void exit(){
        if((System.currentTimeMillis()-clickTime)>2000){
            Toast.makeText(MyApplication.getContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
            clickTime=System.currentTimeMillis();
        }

        else {
            ActivityUtils.finishAllActivity();
        }
    }
}



