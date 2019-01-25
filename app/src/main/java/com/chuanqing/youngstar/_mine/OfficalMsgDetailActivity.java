package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.AdapterOfficeMsgRV;
import com.chuanqing.youngstar.mybean.OfficeMsgBean;
import com.chuanqing.youngstar.mybean.OfficeMsgDetailBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class OfficalMsgDetailActivity extends BaseActivity {
    private int infoid;
    private TextView tv_title;
    private TextView tv_time;
    private TextView tv_content;
    private TextView tv_web;
    private String toptitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officalmsgdetail);

        infoid = getIntent().getIntExtra("infoid",0);
        toptitle = getIntent().getStringExtra("toptitle");
        TextView tv_top = findViewById(R.id.tv_top_officemsgdetail);
        tv_top.setText(toptitle);

        initView();
        request();
    }

    private void initView() {
        findViewById(R.id.iv_back_officemsgdetail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_title = findViewById(R.id.tv_title_officemsgdetail);
        tv_time = findViewById(R.id.tv_time_officemsgdetail);
        tv_content = findViewById(R.id.tv_content_officemsgdetail);
        tv_web = findViewById(R.id.tv_website_officemsgdetail);

    }

    private void request() {
        OkGo.post(Urls.getUserSystemInfoDetails)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("infoId", infoid)//墙的ID
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("=============", "getUserSystemInfoDetails"+s);
                        OfficeMsgDetailBean bean = new Gson().fromJson(s, OfficeMsgDetailBean.class);
                        tv_title.setText(bean.getData().getTitle());
                        tv_time.setText(bean.getData().getTime());
                        tv_content.setText(bean.getData().getContent());
                        tv_web.setText(bean.getData().getUrl());
                    }
                });
    }
}
