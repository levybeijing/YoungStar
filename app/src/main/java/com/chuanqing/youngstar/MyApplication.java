package com.chuanqing.youngstar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;

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
    public static OSS oss;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;

// 在移动端建议使用STS方式初始化OSSClient。
// 更多信息可查看sample 中 sts 使用方式(https://github.com/aliyun/aliyun-oss-android-sdk/tree/master/app/src/main/java/com/alibaba/sdk/android/oss/app)
        final OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider("LTAI8ygujYgDvLJ9", "nLrO1bpn9IOpEu0tt0zyAaChc22j0c", "");

//该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

        OSSLog.enableLog();
        new Thread(new Runnable() {
            @Override
            public void run() {
                oss = new OSSClient(MyApplication.this, "oss-cn-beijing.aliyuncs.com", credentialProvider);
            }
        }).start();
    }

    // 对外暴露上下文
    public static MyApplication getApplication() {
        return mContext;
    }

    public static Context getContext() {
        return context;
    }
}