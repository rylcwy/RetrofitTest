package com.example.wangyu.retrofittest;

import android.app.Activity;

import java.util.ArrayList;

public class ActivityUtils {
    public static ArrayList<Activity> activitys=new ArrayList<>();

    public static void addActivity(Activity activity){
        if (! activitys.contains(activity)){
            activitys.add(activity);
        }
    }

    public static void removeActivity(Activity activity){
        activitys.remove(activity);
    }

    public static void finishAllActivity(){
        for (Activity activity:activitys){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
