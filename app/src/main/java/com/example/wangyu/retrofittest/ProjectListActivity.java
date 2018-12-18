package com.example.wangyu.retrofittest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectListActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        Button Beta=(Button) findViewById(R.id.TapTap_Beta);
        Button TapTap=(Button)findViewById(R.id.TapTap);
        Button Pad=(Button)findViewById(R.id.TapTap_Pad);
        Beta.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.TapTap_Beta:
                Call<ResponseBody> getList=MainActivity.res.getTapTapBeta();
                getList.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d("list", "onResponse: "+response.toString());
                        Intent intent=new Intent();
                        intent.setAction("android.intent.action.BetaList");
                        startActivity(intent);


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


        }
    }
}
