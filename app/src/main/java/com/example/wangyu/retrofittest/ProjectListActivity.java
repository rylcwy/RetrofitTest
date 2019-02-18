package com.example.wangyu.retrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ProjectListActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        Button betaButton = (Button) findViewById(R.id.TapTap_Beta);
        Button releaseButton = (Button) findViewById(R.id.TapTap);
        Button padButton = (Button) findViewById(R.id.TapTap_Pad);
        Button internationalButton = (Button) findViewById(R.id.TapTap_international);
        betaButton.setOnClickListener(this);
        releaseButton.setOnClickListener(this);
        padButton.setOnClickListener(this);
        internationalButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ProjectHandler projectHandler = new ProjectHandler();
        switch (v.getId()) {
            case R.id.TapTap_Beta:
                projectHandler.getList(new ProjectResponseFetcher() {
                    @Override
                    public Call<ResponseBody> getCallableResponse() {
                        return RetrofitCommunication.getRes().getTapTapBeta(1);
                    }
                });
                break;

            case R.id.TapTap:
                projectHandler.getList(new ProjectResponseFetcher(){
                    @Override
                    public Call<ResponseBody> getCallableResponse() {
                        return RetrofitCommunication.getRes().getTapTapRelease(1);
                    }
                });
                break;

            case R.id.TapTap_Pad:
                projectHandler.getList(new ProjectResponseFetcher(){
                    @Override
                    public Call<ResponseBody> getCallableResponse() {
                        return RetrofitCommunication.getRes().getTapTapHD(1);
                    }
                });
                break;

            case R.id.TapTap_international:
                projectHandler.getList(new ProjectResponseFetcher(){
                    @Override
                    public Call<ResponseBody> getCallableResponse() {
                        return RetrofitCommunication.getRes().getTapTapInternational(1);
                    }
                });
                break;

            default:
                break;
        }

    }

}


