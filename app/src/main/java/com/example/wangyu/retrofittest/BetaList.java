package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.wangyu.retrofittest.ProjectListActivity.versions_code_list;
import static com.example.wangyu.retrofittest.ProjectListActivity.versions_date_list;
import static com.example.wangyu.retrofittest.ProjectListActivity.versions_force_list;
import static com.example.wangyu.retrofittest.ProjectListActivity.versions_name_list;
import static com.example.wangyu.retrofittest.ProjectListActivity.versions_publisher_list;

public class BetaList extends AppCompatActivity implements LoadListView.IloadListener {
    static List<Versions> versionsList=new ArrayList<>();

    private ViewPager mViewPager;
    private LoadListView loadListView;
    private TabLayout mTabLayout;
    private int count=0;

    public static void actionStart(Context context,List<String> data1,List<String> data2,List<String> data3,List<String> data4,List<String> data5,List<String> data6){
        Intent intent=new Intent(context,BetaList.class);
        intent.putExtra("version_info","version_code");
        intent.putExtra("update_detail","update_detail");
        intent.putExtra("version_info","version_name");
        intent.putExtra("version_info","version_publisher");
        intent.putExtra("version_info","version_date");
        intent.putExtra("version_info","version_force");
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
        for (int i =0;i<10;i++){
            Versions version1=new Versions(versions_name_list.get(i),versions_code_list.get(i),ProjectListActivity.update_detail_list.get(i),versions_date_list.get(i),
                    versions_publisher_list.get(i),versions_force_list.get(i));
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
                Collections.reverse(versions_name_list);
                Collections.reverse(versions_code_list);
                Collections.reverse(versions_date_list);
                Collections.reverse(versions_publisher_list);
                Collections.reverse(versions_force_list);
                for (int i =0;i<10;i++){
                    Versions version1=new Versions(versions_name_list.get(i),versions_code_list.get(i),ProjectListActivity.update_detail_list.get(i),versions_date_list.get(i),
                            versions_publisher_list.get(i),versions_force_list.get(i));
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
