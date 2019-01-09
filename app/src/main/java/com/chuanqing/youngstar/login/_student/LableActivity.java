package com.chuanqing.youngstar.login._student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class LableActivity extends BaseActivity {

    private AdapterLables adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lable);

        initView();
    }

    private void initView() {
        LinearLayout container = findViewById(R.id.ll_lable);
        TextView tv_number = findViewById(R.id.tv_number_lable);

        RecyclerView rv = findViewById(R.id.rv_lable);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.VERTICAL);
        rv.setLayoutManager(manager);

        adapter = new AdapterLables(this);
        rv.setAdapter(adapter);
//        adapter.setOnItemClickListener(new OnItemClickListenerPosition() {
//            @Override
//            public void onItemClick(int position) {
//
//            }
//        });
        request();
    }

    private void request() {
        OkGo.post(Urls.getLabel)//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===============", "obtioncode: "+s);
                        LablesBean lablesBean = new Gson().fromJson(s, LablesBean.class);
                        adapter.setData(lablesBean.getData());
                    }
                });
    }


}
