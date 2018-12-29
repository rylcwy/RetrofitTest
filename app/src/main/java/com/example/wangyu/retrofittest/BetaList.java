package com.example.wangyu.retrofittest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class BetaList extends AppCompatActivity {
    private List<Versions> versionsList=new ArrayList<>();

    private ViewPager mViewPager;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_list);
        initVersions();
        VersionAdapter adapter=new VersionAdapter(BetaList.this,R.layout.versions_item,versionsList);
        ListView listView=(ListView)findViewById(R.id.beta_list);
        listView.setAdapter(adapter);

    }

    private void initVersions(){
            Versions version3=new Versions("2.0.7.3","503","Android provides a rich application framework that allows you to build innovative apps and games for mobile devices in a Java language environment. The documents listed in the left navigation provide details about how to build apps using Android's various APIs.","2018-12-06 18:30:32");
            versionsList.add(version3);
            Versions version4=new Versions("2.0.7.3","503","[新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB  "
                +"- 新增论坛内搜索 "+"- 推荐视频模块增加“换一组”" +"[优化体验] - 视频加载速度优化"+" - 夜间模式体验优化","2018-12-06 18:30:32");
            versionsList.add(version4);
            Versions version5=new Versions("2.0.7.3","503","[新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB  "
                +"- 新增论坛内搜索 "+"- 推荐视频模块增加“换一组”" +"[优化体验] - 视频加载速度优化"+" - 夜间模式体验优化","2018-12-06 18:30:32");
            versionsList.add(version5);
            Versions version6=new Versions("2.0.7.3","503","[新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB  "
                +"- 新增论坛内搜索 "+"- 推荐视频模块增加“换一组”" +"[优化体验] - 视频加载速度优化"+" - 夜间模式体验优化","2018-12-06 18:30:32");
            versionsList.add(version6);
            Versions version7=new Versions("2.0.7.3","503","[新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB  "
                +"- 新增论坛内搜索 "+"- 推荐视频模块增加“换一组”" +"[优化体验] - 视频加载速度优化"+" - 夜间模式体验优化","2018-12-06 18:30:32");
            versionsList.add(version7);

    }



}
