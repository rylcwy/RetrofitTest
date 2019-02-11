package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class BetaList extends AppCompatActivity implements LoadListView.IloadListener {
    static List<Versions> versionsList=new ArrayList<>();
    private  ArrayList<VersionInfo> versionInfoArrayList1;
    private ViewPager mViewPager;
    private LoadListView loadListView;
    private TabLayout mTabLayout;
    private int count=0;
    ProjectListActivity projectListActivity=new ProjectListActivity();

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



    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                projectListActivity.getBetaList(2);
                for (int i=1;i<projectListActivity.Versions.size();i++){
                    Versions version2=new Versions(versionInfoArrayList1.get(i).versionId,versionInfoArrayList1.get(i).versionCode,versionInfoArrayList1.get(i).versionDetail,versionInfoArrayList1.get(i).versonDate,
                            versionInfoArrayList1.get(i).versionPublisher,versionInfoArrayList1.get(i).versionForce);
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
