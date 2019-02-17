package com.example.wangyu.retrofittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent();

        if (CheckLogin.getLoginState()) {
            intent.setAction("android.intent.action.projectlist");
            startActivity(intent);
        }
        else {
            intent.setAction("android.intent.action.login");
            startActivity(intent);
        }
    }

}
