package com.chuanqing.youngstar.login.privacy;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import okhttp3.Call;
import okhttp3.Response;

public class M{

    private String content;

    private ICallback callback;

    public String getprivacy(){
        OkGo.post("http://www.xiaoyaoji.cn/doc/19frqvRDBv")//
              .tag(this)//
              .execute(new StringCallback() {
                      @Override
                      public void onSuccess(String s, Call call, Response response) {
                          callback.netData(s);
                      }
              });
        return null;
    }

}
