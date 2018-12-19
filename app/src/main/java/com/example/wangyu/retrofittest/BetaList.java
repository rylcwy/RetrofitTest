package com.example.wangyu.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BetaList extends AppCompatActivity {
    private List<Versions> versionsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_list);
        initVersions();
        VersionAdapter adapter=new VersionAdapter(BetaList.this,R.layout.version_item,versionsList);
        ListView listView=(ListView)findViewById(R.id.beta_list);
        listView.setAdapter(adapter);

    }

    private void initVersions(){
            Versions version1=new Versions("2.0.7.4","504","日志日志日志日志日志日志日志","2018-12-03 18:30:32");
            versionsList.add(version1);
            Versions version2=new Versions("2.0.7.3","503","[新增功能] - 详情页社区下新增视频TAB "
                    +"- 新增论坛内搜索 "+"- 推荐视频模块增加“换一组”" +"[优化体验] - 视频加载速度优化"+" - 夜间模式体验优化","2018-12-06 18:30:32");
            versionsList.add(version2);

    }
}
