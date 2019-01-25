package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.AdapterOfficeMsgRV;
import com.chuanqing.youngstar.mybean.OfficeMsgBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class OfficalMsgActivity extends BaseActivity {
    private int page=1;
    private AdapterOfficeMsgRV adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officalmsg);

        initView();
    }

    private void initView() {
        RecyclerView rv = findViewById(R.id.rv_officalmsg);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

        adapter = new AdapterOfficeMsgRV(OfficalMsgActivity.this);
        rv.setAdapter(adapter);

        findViewById(R.id.iv_back_officemsg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        request();
    }

    private void request() {
        OkGo.post(Urls.getUserSystemInfo)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("page", page)//墙的ID
                .params("pageSize", 15)//缩略图 省略>?
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=============", "getUserSystemInfo"+s);
                        OfficeMsgBean bean = new Gson().fromJson(s, OfficeMsgBean.class);
                        List<OfficeMsgBean.DataBean.PageInfoBean.ListBean> list = bean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
    }
}
