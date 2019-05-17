package com.example.wangyu.retrofittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    Intent intent=new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityUtils.addActivity(MainActivity.this);
        RetrofitCommunication retrofitCommunication=new RetrofitCommunication();
        super.onCreate(savedInstanceState);
        Log.d("Mainactiviy", "onCreate: ");
        setContentView(R.layout.activity_main);
        if (LoginChecking.getLoginState()) {
            intent.setAction("android.intent.action.projectlist");
            startActivity(intent);
        }
        else {
            intent.setAction("android.intent.action.login");
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("Mainactiviy", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("Mainactiviy", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(MainActivity.this);
        LogUtil.d("Mainactiviy", "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d("Mainactiviy", "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("Mainactiviy", "onResume: ");
    }
}
