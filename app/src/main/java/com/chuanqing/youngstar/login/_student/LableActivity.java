package com.chuanqing.youngstar.login._student;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class LableActivity extends BaseActivity {

    private AdapterLables adapter;
    private List<Boolean> check=new ArrayList<>();
    private List<Integer> list=new ArrayList<>();

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
        adapter.setOnItemClickListener(new OnItemClickListenerPosition() {
            @Override
            public void onItemClick(int position) {
//                check.set(position,true);
//                list.add(position);
//                if (list.size()>2){
//                    int index = list.get(list.size() - 3);
//                    if (index==position){
//                        return;
//                    }
//                    check.set(index,false);
//                    adapter.setFlag(check);
//                }

            }
        });
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
                        List<String> data = lablesBean.getData();
                        adapter.setData(data);
                        for (int i = 0; i < data.size(); i++) {
                            check.add(false);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        设置按钮点击事件
        Intent intent = new Intent();
        intent.putExtra("lable1", "演员"); //将计算的值回传回去
        intent.putExtra("lable2", "歌手"); //将计算的值回传回去
        setResult(2, intent);
    }
}
