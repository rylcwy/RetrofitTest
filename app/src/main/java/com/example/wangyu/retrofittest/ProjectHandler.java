package com.example.wangyu.retrofittest;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectHandler {

    private ArrayList<VersionInfo> toVersionInfoList(Call<ResponseBody> listCall) {
        final ArrayList<VersionInfo> versionInfoResult = new ArrayList<>();
        listCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                List<String> versionsInfoList = new ArrayList<>();
                List<String> sublist = new ArrayList<String>();
                List<String> updateDetailList = new ArrayList<String>();
                try {
                    String html = response.body().string();
                    if (html.contains("登录你的账户")) {
                        Toast.makeText(MyApplication.getContext(), "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.login");
                        MyApplication.getContext().startActivity(intent);
                    } else {
                        Document doc = Jsoup.parse(html);
                        Elements name = doc.select("a.nav-link");
                        String listName = name.eachText().get(2);
                        Log.d("1111", "onResponse: " + listName);
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

                } catch (IOException e) {
                    LogUtil.e("ProjectActivity", "get TapTapBeta list call error");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e("ProjectActivity", "get TapTapBeta list call error");

            }
        });

        return versionInfoResult;
    }
}
