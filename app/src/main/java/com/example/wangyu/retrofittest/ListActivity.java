package com.example.wangyu.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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


public class ListActivity extends AppCompatActivity implements LoadListView.IloadListener {
    private List<Versions> versionsList = new ArrayList<>();
    private ViewPager mViewPager;
    private static LoadListView loadListView;
    private TabLayout mTabLayout;
    private int currentPage = 0;
    private ProjectResponseFetcher fetcher;

    public static void actionStart(Context context, Serializable fetcher) {
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtra("fetcher", fetcher);
        context.startActivity(intent);
    }

    private void generateView(boolean init) {
        Call<ResponseBody> rsp = fetcher.getCallableResponse(++currentPage);
        drawVersionInfoList(rsp, init);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPage = 0;
        setContentView(R.layout.activity_list_view);
        fetcher = (ProjectResponseFetcher) getIntent().getSerializableExtra("fetcher");
        generateView(true);
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                generateView(false);
            }
        }, 2000);

    }


    private void drawVersionInfoList(Call<ResponseBody> listCall, final boolean init) {
        listCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                ArrayList<VersionInfo> versionInfoResult = new ArrayList<>();
                List<String> versionsInfoList;
                List<String> sublist;
                List<String> updateDetailList;
                try {
                    String html = response.body().string();
                    if (html.contains("登录你的账户")) {
                        Toast.makeText(MyApplication.getContext(), "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.login");
                        MyApplication.getContext().startActivity(intent);
                    } else {
                        Document doc = Jsoup.parse(html);
//                        Elements name = doc.select("a.nav-link");
//                        String listName = name.eachText().get(2);
                        Elements versionDetailElements = doc.select("table.table.table-hover tr:nth-child(even)");
                        Elements versionsInfoElements = doc.select("table.table.table-hover tr:nth-child(odd) td");
                        updateDetailList = versionDetailElements.eachText();
                        versionsInfoList = versionsInfoElements.eachText();
                        int count = versionsInfoList.size() / 7;
                        int remainder = versionsInfoList.size() % 7;
                        if (remainder == 0) {
                            int detailIndex = 0;
                            for (int i = 0; i < count; i++) {
                                VersionInfo versionInfo = new VersionInfo();
                                sublist = versionsInfoList.subList(i * 7, i * 7 + 6);
                                //VersionInfo versionInfo=new VersionInfo();
                                versionInfo.setVersionId(sublist.get(0));
                                versionInfo.setVersionCode(sublist.get(1));
                                versionInfo.setVersionName(sublist.get(1));
                                versionInfo.setVersionSize(sublist.get(2));
                                versionInfo.setVersionForce(sublist.get(3));
                                versionInfo.setVersionPublisher(sublist.get(4));
                                versionInfo.setVersonDate(sublist.get(5));
                                versionInfo.setVersionDetail(updateDetailList.get(detailIndex));
                                detailIndex++;
                                versionInfoResult.add(versionInfo);
                            }
                        }
                    }
                    processShow(versionInfoResult, init);
                } catch (IOException e) {
                    LogUtil.e("ProjectActivity", "get TapTapBeta list call error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e("ProjectActivity", "get TapTapBeta list call error");

            }
        });
    }

    private void processShow(final ArrayList<VersionInfo> versionInfoList, final boolean init) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                for (VersionInfo versionInfo : versionInfoList) {
                    Versions versionView = new Versions(versionInfo.getVersionCode(), versionInfo.getVersionCode(), versionInfo.getVersionDetail(), versionInfo.getVersonDate(),
                            versionInfo.getVersionPublisher(), versionInfo.getVersionForce());
                    versionsList.add(versionView);
                }

                if (init) {
                    VersionAdapter adapter = new VersionAdapter(ListActivity.this, R.layout.versions_item, versionsList);
                    loadListView = (LoadListView) findViewById(R.id.list_view);
                    loadListView.setAdapter(adapter);
                    loadListView.setInterface(ListActivity.this);
                } else {
                    // 通知listview加载完毕
                    loadListView.loadComplete();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        versionsList.clear();
    }
}
