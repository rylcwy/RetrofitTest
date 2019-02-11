package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

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


public class BetaList extends AppCompatActivity implements LoadListView.IloadListener {
    static List<Versions> versionsList=new ArrayList<>();
    public static List<String> versionsInfoList = new ArrayList<>();
    public static List<String> sublist = new ArrayList<>();
    public static List<String> updateDetailList = new ArrayList<>();
    private ArrayList<VersionInfo> Versionss=new ArrayList<>();
    private ArrayList<VersionInfo> versionInfoArrayList1;
    private ViewPager mViewPager;
    private LoadListView loadListView;
    private TabLayout mTabLayout;
    private int count=0;


    public static void actionStart(Context context, ArrayList versionInfoArrayList){
        Intent intent=new Intent(context,BetaList.class);
        intent.putExtra("version",(Serializable) versionInfoArrayList);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Beta1", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_list);
        Intent intent=getIntent();
        versionInfoArrayList1=(ArrayList<VersionInfo>)getIntent().getSerializableExtra("version");
        initVersions();
        VersionAdapter adapter=new VersionAdapter(BetaList.this,R.layout.versions_item,versionsList);
        loadListView=(LoadListView)findViewById(R.id.beta_list);
        loadListView.setAdapter(adapter);
        loadListView.setInterface(this);

    }

    private void initVersions(){
        for (int i=0;i<versionInfoArrayList1.size();i++){
            Versions version1=new Versions(versionInfoArrayList1.get(i).versionId,versionInfoArrayList1.get(i).versionCode,versionInfoArrayList1.get(i).versionDetail,versionInfoArrayList1.get(i).versonDate,
                    versionInfoArrayList1.get(i).versionPublisher,versionInfoArrayList1.get(i).versionForce);
            versionsList.add(version1);
        }
    }



    public void getBetaList (int pageNumber){
        Call<ResponseBody> getList = MainActivity.res.getTapTapBeta(2);
        getList.enqueue(new Callback<ResponseBody>() {
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
                                versionInfo.setVersionForce(sublist.get(3));
                                versionInfo.setVersonDate(sublist.get(4));
                                versionInfo.setVersionDetail(updateDetailList.get(detailIndex));
                                detailIndex++;
                                Versionss.add(versionInfo);
                            }
                        }

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



    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BetaList.this,"hhhh",Toast.LENGTH_LONG).show();
                BetaList betaList=new BetaList();
                // TODO Auto-generated method stub
                betaList.getBetaList(2);
                for (int i=0;i<Versionss.size();i++){
                    Versions version2=new Versions(Versionss.get(i).versionId,Versionss.get(i).versionCode,Versionss.get(i).versionDetail,Versionss.get(i).versonDate,
                            Versionss.get(i).versionPublisher,Versionss.get(i).versionForce);
                    versionsList.add(version2);
                    count++;

                } // 通知listview加载完毕
                loadListView.loadComplete();
            }
        }, 2000);
        // TODO Auto-generated method stub

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Beta", "onDestroy: ");
        versionsList.clear();
    }
}
