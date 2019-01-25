package com.chuanqing.youngstar._mine.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.AdapterMatchRV;
import com.chuanqing.youngstar.mybean.MatchBean;
import com.chuanqing.youngstar.mybean.WalletBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MatchActivity extends BaseActivity {

    private int page =1;
    private AdapterMatchRV adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        initView();

        request();
    }

    private void initView() {

        RecyclerView rv = findViewById(R.id.rv_match);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterMatchRV(this);
        rv.setAdapter(adapter);

    }

    private void request() {
        OkGo.post(Urls.getActivityUser)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("page", page)//文件名
                .params("pageSize",15)//文件名
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "getActivityUser"+s);
                        MatchBean bean = new Gson().fromJson(s, MatchBean.class);
                        List<MatchBean.DataBean.PageInfoBean.ListBean> list = bean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
    }
}
