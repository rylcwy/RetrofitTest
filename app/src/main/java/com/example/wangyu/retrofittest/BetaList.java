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
    static List<Versions> versionsList=new ArrayList<>();

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
            Versions version3=new Versions("2.0.7.3","503","[新增功能] - 详情页社区下新增视频TAB - 新增论坛内搜索 - 推荐视频模块增加“换一组” [优化体验] - 视频加载速度优化 - 夜间模式体验优化- 优化成就徽章功能 - 优化视频首帧加载速度- 优化成就徽章功能 - 优化视频首帧加载速度111111111112222222222222233333333333333333344444444444444444444455555555555566666666666666666666666666667","2018-12-06 18:30:32","wanngyu","否");
            versionsList.add(version3);
            Versions version4=new Versions("2.0.7.3","503","[新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB  "
                +"- 新增论坛内搜索 "+"- 推荐视频模块增加“换一组”" +"[优化体验] - 视频加载速度优化"+" - 夜间模式体验优化","2018-12-06 18:30:32","wangyu","否");
            versionsList.add(version4);
            Versions version5=new Versions("2.0.7.3","503","[新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB [新增功能] - 详情页社区下新增视频TAB  "
                +"- 新增论坛内搜索 "+"- 推荐视频模块增加“换一组”" +"[优化体验] - 视频加载速度优化"+" - 夜间模式体验优化","2018-12-06 18:30:32","wangyu","否");
            versionsList.add(version5);


    }



}
