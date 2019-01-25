package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BetaList extends AppCompatActivity {
    static List<Versions> versionsList=new ArrayList<>();

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

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
        Log.d("Beta", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_list);
        initVersions();
        VersionAdapter adapter=new VersionAdapter(BetaList.this,R.layout.versions_item,versionsList);
        ListView listView=(ListView)findViewById(R.id.beta_list);
        listView.setAdapter(adapter);

    }

    private void initVersions(){
        for (int i =0;i<10;i++){
            Versions version1=new Versions(ProjectListActivity.versions_name_list.get(i),ProjectListActivity.versions_code_list.get(i),ProjectListActivity.update_detail_list.get(i),ProjectListActivity.versions_date_list.get(i),
                    ProjectListActivity.versions_publisher_list.get(i),ProjectListActivity.versions_force_list.get(i));
            versionsList.add(version1);
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Beta", "onDestroy: ");
        versionsList.clear();
    }
}
