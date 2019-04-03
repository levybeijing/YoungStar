package com.chuanqing.youngstar._mine.fans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.AdapterFragCareSRV;
import com.chuanqing.youngstar.mybean.FragCareSBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import java.util.List;
import okhttp3.Call;
import okhttp3.Response;

public class CareActivity extends BaseActivity {

    private int page=1;
    private AdapterFragCareSRV adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caref);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_caref).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView rv = findViewById(R.id.rv_caref);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
        adapter = new AdapterFragCareSRV(this);
        rv.setAdapter(adapter);
        request();
    }

    private void request() {
        OkGo.post(Urls.getUserConcernStudent)
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))
                .params("page", page)
                .params("pageSize", 15)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "Caref"+s);
                        FragCareSBean bean = new Gson().fromJson(s, FragCareSBean.class);
                        List<FragCareSBean.DataBean.PageInfoBean.ListBean> list = bean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
    }
}
