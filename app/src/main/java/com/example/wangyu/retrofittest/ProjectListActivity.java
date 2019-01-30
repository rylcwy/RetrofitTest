package com.example.wangyu.retrofittest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.commons.lang3.StringUtils;
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

public class ProjectListActivity extends AppCompatActivity implements View.OnClickListener{
    public String html;
    public static List<String> versions_id_list=new ArrayList<String>();
    public static List<String> versionsinfo_list=new ArrayList<String>();
    public static List<String> versions_name_list=new ArrayList<String>();
    public static List<String> versions_code_list=new ArrayList<String>();
    public static List<String> versions_size_list=new ArrayList<String>();
    public static List<String> versions_force_list=new ArrayList<String>();
    public static List<String> versions_publisher_list=new ArrayList<String>();
    public static List<String> versions_date_list=new ArrayList<String>();
    public static List<String> update_detail_list=new ArrayList<String>();


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
        switch (v.getId()) {
            case R.id.TapTap_Beta:
                if (CheckLogin.getLoginState()) {
                    Call<ResponseBody> getList = MainActivity.res.getTapTapBeta(2);
                    getList.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            try {
                                String html = response.body().string();

                                if (html.contains("登录你的账户")) {
                                    Intent intent = new Intent();
                                    intent.setAction("android.intent.action.login");
                                    startActivity(intent);
                                    MainActivity.getToken(new TokenCallbacks() {
                                        @Override
                                        public void onSuccess(@android.support.annotation.NonNull String token) {

                                        }

                                        @Override
                                        public void onFailure(@android.support.annotation.NonNull Throwable throwable) {

                                        }
                                    });
                                }

                                else {
                                    Document doc = Jsoup.parse(html);
                                    Elements update_detail_e = doc.select("table.table.table-hover tr:nth-child(even)");
                                    Elements versions_info_e = doc.select("table.table.table-hover tr:nth-child(odd) td");
                                    update_detail_list = update_detail_e.eachText();
                                    versionsinfo_list = versions_info_e.eachText();
//                            for (int i=0;i<update_detail.size();i++){
//                                Log.d("xmlparse", "updatedetail: "+update_detail.get(i));
//                            }

                                    for (int i = 0; i < versionsinfo_list.size(); i = i + 7) {
                                        versions_id_list.add(versionsinfo_list.get(i));
                                    }

                                    for (int i = 1; i < versionsinfo_list.size(); i = i + 7) {
                                        versions_code_list.add(StringUtils.substringBefore(versionsinfo_list.get(i), " "));
                                        Log.d("xmlparse", "code: " + versions_code_list);

                                    }


                                    for (int i = 1; i < versionsinfo_list.size(); i = i + 7) {
                                        versions_name_list.add(StringUtils.substringBetween(versionsinfo_list.get(i), "(", ")"));
                                        //Log.d("xmlparse", "code: "+versions_name_list);

                                    }
                                    Log.d("xmlparse", "code: " + versions_name_list);


                                    for (int i = 2; i < versionsinfo_list.size(); i = i + 7) {
                                        versions_size_list.add(versionsinfo_list.get(i));
                                        Log.d("xmlparse", "code: " + versions_size_list);

                                    }


                                    for (int i = 3; i < versionsinfo_list.size(); i = i + 7) {
                                        versions_force_list.add(versionsinfo_list.get(i));
                                        Log.d("xmlparse", "code: " + versions_force_list);

                                    }


                                    for (int i = 4; i < versionsinfo_list.size(); i = i + 7) {
                                        versions_publisher_list.add(versionsinfo_list.get(i));
                                        Log.d("xmlparse", "code: " + versions_publisher_list);

                                    }


                                    for (int i = 5; i < versionsinfo_list.size(); i = i + 7) {
                                        versions_date_list.add(versionsinfo_list.get(i));
                                        Log.d("xmlparse", "code: " + versions_date_list);

                                    }
                                    BetaList.actionStart(ProjectListActivity.this, versions_name_list, versions_code_list, versions_force_list, versions_publisher_list, versions_date_list, update_detail_list);

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
}
