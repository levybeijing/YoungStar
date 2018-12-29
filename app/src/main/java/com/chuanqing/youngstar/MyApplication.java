package com.chuanqing.youngstar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyApplication extends Application {
    private static Context context;
    //单例模式中获取唯一的MyApplication实例
    private List<Activity> activityList = new LinkedList<Activity>();
    private static MyApplication instance;
    public static MyApplication getInstance() {
        if(null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }
    //添加Activity到容器中
    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }
    //遍历所有Activity并finish
    public void exit(){
        for(Activity activity:activityList) {
            activity.finish();
        }
    }
    public void removeactivity(Class<MainActivity> activity){
        activityList.remove(activity);
    }

    // 获取到主线程的上下文
    private static MyApplication mContext = null;
    private static List<Activity> cacheActivity = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;

    }

    // 对外暴露上下文
    public static MyApplication getApplication() {
        return mContext;
    }

    public static Context getContext() {
        return context;
    }
}