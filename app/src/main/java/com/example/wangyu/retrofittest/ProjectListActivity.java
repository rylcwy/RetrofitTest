package com.example.wangyu.retrofittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectListActivity extends AppCompatActivity implements View.OnClickListener {
    public String html;
    public static List<String> versionsInfoList = new ArrayList<String>();
    public static List<String> sublist = new ArrayList<String>();
    public static List<String> updateDetailList = new ArrayList<String>();
    public ArrayList<VersionInfo> Versions=new ArrayList<>();


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
        switch (v.getId()) {
            case R.id.TapTap_Beta:
                if (CheckLogin.getLoginState()) {
                    Call<ResponseBody> getList = MainActivity.res.getTapTapBeta(1);
                    getList.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            try {
                                String html = response.body().string();

                                if (html.contains("登录你的账户")) {
                                    Toast.makeText(MyApplication.getContext(), "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setAction("android.intent.action.login");
                                    MyApplication.getContext().startActivity(intent);
                                    MainActivity.getToken(new TokenCallbacks() {
                                        @Override
                                        public void onSuccess(@android.support.annotation.NonNull String token) {

                                        }

                                        @Override
                                        public void onFailure(@android.support.annotation.NonNull Throwable throwable) {

                                        }
                                    });
                                } else {
                                    Document doc = Jsoup.parse(html);
                                    Elements versionDetailElements = doc.select("table.table.table-hover tr:nth-child(even)");
                                    Elements versionsInfoElements = doc.select("table.table.table-hover tr:nth-child(odd) td");
                                    updateDetailList = versionDetailElements.eachText();
                                    versionsInfoList = versionsInfoElements.eachText();
                                    int count=versionsInfoList.size()/7;
                                    int remainder=versionsInfoList.size()%7;

                                    if (remainder==0){
                                        int detailIndex=0;
                                        for (int i=0;i<count;i++){
                                            VersionInfo versionInfo=new VersionInfo();
                                            sublist=versionsInfoList.subList(i*7,i*7+6);
                                            //VersionInfo versionInfo=new VersionInfo();
                                                versionInfo.setVersionId(sublist.get(0));
                                                versionInfo.setVersionCode(sublist.get(1));
                                                versionInfo.setVersionName(sublist.get(1));
                                                versionInfo.setVersionSize(sublist.get(2));
                                                versionInfo.setVersionForce(sublist.get(3));
                                                versionInfo.setVersonDate(sublist.get(4));
                                                versionInfo.setVersionDetail(updateDetailList.get(detailIndex));
                                                detailIndex++;
                                                Versions.add(versionInfo);
                                        }

                                    }
                                    BetaList.actionStart(MyApplication.getContext(), Versions);

                                }


                            } catch (IOException e) {

                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("loginstatus1", "loginstats " + CheckLogin.getLoginState());

                        }
                    });

                }
                break;
             default:
                 break;
        }

    }


    public void getBetaList (int pageNumber){
        Call<ResponseBody> getList = MainActivity.res.getTapTapBeta(2);
        getList.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    String html = response.body().string();
                    Log.d("test", "onResponse: "+html);

                    if (html.contains("登录你的账户")) {
                        Toast.makeText(MyApplication.getContext(), "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.login");
                        MyApplication.getContext().startActivity(intent);
                        MainActivity.getToken(new TokenCallbacks() {
                            @Override
                            public void onSuccess(@android.support.annotation.NonNull String token) {

                            }

                            @Override
                            public void onFailure(@android.support.annotation.NonNull Throwable throwable) {

                            }
                        });
                    } else {
                        Document doc = Jsoup.parse(html);
                        Elements update_detail_e = doc.select("table.table.table-hover tr:nth-child(even)");
                        Elements versions_info_e = doc.select("table.table.table-hover tr:nth-child(odd) td");
                        updateDetailList = update_detail_e.eachText();
                        versionsInfoList = versions_info_e.eachText();
                        int count=versionsInfoList.size()/7;
                        int remainder=versionsInfoList.size()%7;

                        if (remainder==0){
                            int detailIndex=0;
                            for (int i=0;i<count;i++){
                                VersionInfo versionInfo=new VersionInfo();
                                sublist=versionsInfoList.subList(i*7,i*7+6);
                                //VersionInfo versionInfo=new VersionInfo();
                                versionInfo.setVersionId(sublist.get(0));
                                versionInfo.setVersionCode(sublist.get(1));
                                versionInfo.setVersionName(sublist.get(1));
                                versionInfo.setVersionSize(sublist.get(2));
                                versionInfo.setVersionForce(sublist.get(3));
                                versionInfo.setVersonDate(sublist.get(4));
                                versionInfo.setVersionDetail(updateDetailList.get(detailIndex));
                                detailIndex++;
                                Versions.add(versionInfo);
                            }

                        }
                        }


                } catch (IOException e) {

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("betalist", "Error " +t);

            }
        });

    }
}


