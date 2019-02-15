package com.chuanqing.youngstar;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.store.PersistentCookieStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;


public class MyApplication extends Application {
    private static Context context;
    //单例模式中获取唯一的MyApplication实例
    private List<Activity> activityList = new LinkedList<>();
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
        initOKGO();
    }

    // 对外暴露上下文
    public static MyApplication getApplication() {
        return mContext;
    }

    public static Context getContext() {
        return context;
    }
    private void initOKGO(){
        //必须调用初始化
        OkGo.init(this);
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    //打开该调试开关,控制台会使用 红色error 级别打印log,并不是错误,是为了显眼,不需要就不要加入该行
                    .debug("OkGo")
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy0216/
                    .setCacheMode(CacheMode.NO_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //如果不想让框架管理cookie,以下不需要
//                .setCookieStore(new MemoryCookieStore())                //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())    //cookie持久化存储，如果cookie不过期，则一直有效
                    //可以添加全局拦截器,不会用的千万不要传,错误写法直接导致任何回调不执行
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public okhttp3.Response intercept(Chain chain) throws IOException {
//////                        创建一个request
//                        okhttp3.Response proceed = chain.proceed(chain.request());
//                        String string = proceed.body().string();
//                        Log.e("=====================" ,"intercept: "+string);
//                        try {
//                            JSONObject jo=new JSONObject(string);
//                            String message = jo.getString("message");
//                            Toast.makeText(MyApplication.this, "****", Toast.LENGTH_SHORT).show();
//                            if (message.equals("请重新登录")){
////                                Intent intent=new Intent();
////                                intent.setAction("action.LOGIN.OTHER");
////                                sendBroadcast(intent);
////                                return null;
//                            }else{
//
//                            }
////                            proceed.body().close();
////                            okhttp3.Response response = new okhttp3.Response();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
////                        return proceed;
//                            return chain.proceed(chain.request());
//                        }
//                    })
                    ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}