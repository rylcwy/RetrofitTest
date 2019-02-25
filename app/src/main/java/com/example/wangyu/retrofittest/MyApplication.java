package com.example.wangyu.retrofittest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
//todo myapplication
public class MyApplication extends Application {
    private static Context context;
    private ArrayList<Activity> activityList;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        activityList=new ArrayList<>();

    }

    public static Context getContext(){
        return context;
    }

    public void addActivity_(Activity activity){
        if (!activityList.contains(activity)){
            activityList.add(activity);
        }
    }

    public void removeActivity_(Activity activity){
        if (activityList.contains(activity)){
            activity.finish();
            LogUtil.d("removeActivity","LoginActivity finish");
        }
    }
}
