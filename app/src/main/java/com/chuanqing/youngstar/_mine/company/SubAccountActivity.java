package com.chuanqing.youngstar._mine.company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.AdapterSubaccountRV;
import com.chuanqing.youngstar.mybean.FragCareCBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class SubAccountActivity extends BaseActivity {

    private AdapterSubaccountRV adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subaccount);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_subaccoun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView rv = findViewById(R.id.rv_subaccount);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterSubaccountRV(this);
        rv.setAdapter(adapter);
        request();
    }
    private void request() {
        OkGo.post(Urls.getCompanySecondary)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "getUserConcernCompany"+s);
                    }
                });
    }

}
