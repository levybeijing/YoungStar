package com.chuanqing.youngstar.login.privacy;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import okhttp3.Call;
import okhttp3.Response;

public class M {

        public String getprivacy(){
                OkGo.post("")//
                      .tag(this)//
                      .params("", "")
                      .execute(new StringCallback() {
                              @Override
                              public void onSuccess(String s, Call call, Response response) {


                              }
                      });
                return null;
        }
}
