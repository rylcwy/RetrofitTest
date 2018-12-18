package com.example.wangyu.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BetaList extends AppCompatActivity {
    private String[] data={"2.0.7.4：[新增功能] - 详情页社区下新增视频TAB - 新增论坛内搜索 - 推荐视频模块增加“换一组” [优化体验] - 视频加载速度优化 - 夜间模式体验优化","2.0.7.3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beta_list);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(
                BetaList.this,android.R.layout.simple_list_item_1,data);
        ListView listView=(ListView)findViewById(R.id.beta_list);
        listView.setAdapter(adapter);

    }
}
