package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.wangyu.retrofittest.ProjectListActivity.updateDetailList;


public class BetaList extends AppCompatActivity implements LoadListView.IloadListener {
    static List<Versions> versionsList=new ArrayList<>();

    private ViewPager mViewPager;
    private LoadListView loadListView;
    private TabLayout mTabLayout;
    private int count=0;
    ProjectListActivity projectListActivity=new ProjectListActivity();

    public static void actionStart(Context context, Parcelable Versions){
        Intent intent=new Intent(context,BetaList.class);
        intent.putExtra("version",Versions);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Beta1", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_list);
        initVersions();
        VersionAdapter adapter=new VersionAdapter(BetaList.this,R.layout.versions_item,versionsList);
        loadListView=(LoadListView)findViewById(R.id.beta_list);
        loadListView.setAdapter(adapter);
        loadListView.setInterface(this);

    }

    private void initVersions(){

        for (int i=0;i<projectListActivity.Versions.size();i++){
            Versions version1=new Versions(projectListActivity.Versions.get(i).versionName,projectListActivity.Versions.get(i).versionCode,
                    updateDetailList.get(i), projectListActivity.Versions.get(i).versionForce,
                    projectListActivity.Versions.get(i).versionPublisher,projectListActivity.Versions.get(i).versonDate);
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
                ProjectListActivity.getBetaList(2);
                for (int i=0;i<projectListActivity.Versions.size();i++){
                Versions version1=new Versions(projectListActivity.Versions.get(i).versionName,projectListActivity.Versions.get(i).versionCode,
                        updateDetailList.get(i), projectListActivity.Versions.get(i).versionForce,
                        projectListActivity.Versions.get(i).versionPublisher,projectListActivity.Versions.get(i).versonDate);
                versionsList.add(version1);
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
