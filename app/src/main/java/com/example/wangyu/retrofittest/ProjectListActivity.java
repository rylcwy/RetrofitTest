package com.example.wangyu.retrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ProjectListActivity extends AppCompatActivity implements View.OnClickListener {
    private String html;
    private List<String> versionsInfoList = new ArrayList<String>();
    private List<String> sublist = new ArrayList<String>();
    private List<String> updateDetailList = new ArrayList<String>();
    private ArrayList<VersionInfo> Versions=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        Button Beta = (Button) findViewById(R.id.TapTap_Beta);
        Button TapTap = (Button) findViewById(R.id.TapTap);
        Button Pad = (Button) findViewById(R.id.TapTap_Pad);
        Beta.setOnClickListener(this);



    }

    @Override
    public void onClick(final View v) {
        ProjectHandler projectHandler = new ProjectHandler();
        switch (v.getId()) {
            case R.id.TapTap_Beta:
                projectHandler.onClick(new ProjectResponseFetcher() {
                    @Override
                    public Call<ResponseBody> getCallableResponse() {
                        return RetrofitCommunication.getRes().getTapTapBeta(1);
                    }
                });
                break;

            case R.id.TapTap:
                TapTapProjectFetcher tapTapProjectFetcher = new TapTapProjectFetcher();
                projectHandler.onClick(tapTapProjectFetcher);
                break;

            case R.id.TapTap_Pad:
                break;

            default:
                break;
        }

    }

}


