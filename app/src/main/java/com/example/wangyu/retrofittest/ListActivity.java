package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListActivity extends AppCompatActivity implements LoadListView.IloadListener {
    static List<Versions> versionsList=new ArrayList<>();
    private static List<String> versionsInfoList = new ArrayList<>();
    private static List<String> sublist = new ArrayList<>();
    private static List<String> updateDetailList = new ArrayList<>();
    private ArrayList<VersionInfo> Versionss=new ArrayList<>();
    private ArrayList<VersionInfo> versionInfoArrayList1;
    private ViewPager mViewPager;
    private static LoadListView loadListView;
    private TabLayout mTabLayout;
    private int count=0;
    private static int pages=2;
    private String listNames;


    public static void actionStart(Context context, ArrayList versionInfoArrayList,String listName){
        Intent intent=new Intent(context,ListActivity.class);
        intent.putExtra("version",(Serializable) versionInfoArrayList);
        intent.putExtra("listname",listName);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_list);
        Intent intent=getIntent();
        versionInfoArrayList1=(ArrayList<VersionInfo>)getIntent().getSerializableExtra("version");
        listNames=getIntent().getStringExtra("listname");
        initVersions();
        VersionAdapter adapter=new VersionAdapter(ListActivity.this,R.layout.versions_item,versionsList);
        loadListView=(LoadListView)findViewById(R.id.beta_list);
        loadListView.setAdapter(adapter);
        loadListView.setInterface(this);

    }

    private void initVersions(){
        for (int i=0;i<versionInfoArrayList1.size();i++){
            Versions version1=new Versions(versionInfoArrayList1.get(i).getVersionCode(),versionInfoArrayList1.get(i).getVersionCode(),versionInfoArrayList1.get(i).getVersionDetail(),versionInfoArrayList1.get(i).getVersonDate(),
                    versionInfoArrayList1.get(i).getVersionPublisher(),versionInfoArrayList1.get(i).getVersionForce());
            versionsList.add(version1);
        }
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getBetaList(2);

                }
            }, 2000);

    }

    private void getBetaList (int pageNumber){
        Call<ResponseBody> getBetaListCall=null;
        if (listNames=="TapTap"){
            getBetaListCall = RetrofitCommunication.getRes().getTapTapRelease(pageNumber);
        }

        else {
            getBetaListCall = RetrofitCommunication.getRes().getTapTapBeta(pageNumber);
        }

        getBetaListCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    String html = response.body().string();
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
                            versionInfo.setVersionId(sublist.get(0));
                            versionInfo.setVersionCode(sublist.get(1));
                            versionInfo.setVersionName(sublist.get(1));
                            versionInfo.setVersionSize(sublist.get(2));
                            versionInfo.setVersionPublisher(sublist.get(4));
                            versionInfo.setVersonDate(sublist.get(5));
                            versionInfo.setVersionDetail(updateDetailList.get(detailIndex));
                            detailIndex++;
                            Versionss.add(versionInfo);
                        }
                    }
                    pages++;

                    processLoadShow();
                } catch (IOException e) {
                    System.out.print(e);

                }
                catch (Exception es){
                    System.out.print(es);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("betalist", "Error " +t);

            }
        });

    }

    private void processLoadShow() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<Versionss.size();i++){
                    Versions version2=new Versions(Versionss.get(i).getVersionCode(),Versionss.get(i).getVersionCode(),Versionss.get(i).getVersionDetail(),Versionss.get(i).getVersonDate(),
                            Versionss.get(i).getVersionPublisher(),Versionss.get(i).getVersionForce());
                    versionsList.add(version2);
                    count++;

                } // 通知listview加载完毕
                loadListView.loadComplete();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Beta", "onDestroy: ");
        versionsList.clear();
    }
}
