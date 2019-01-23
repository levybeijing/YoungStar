package com.chuanqing.youngstar.login._student;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class LableActivity extends BaseActivity implements View.OnClickListener {

    private AdapterLables adapter;
    private List<Boolean> check=new ArrayList<>();
    private int[] pos=new int[]{-1,-1};
    private String[] lables=new String[]{null,null};
    private TextView tv_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lable);

        initView();
    }

    private void initView() {
        tv_number = findViewById(R.id.tv_number_lable);

        RecyclerView rv = findViewById(R.id.rv_lable);

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayout.VERTICAL);
        rv.setLayoutManager(manager);

        adapter = new AdapterLables(this);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterLables.ClickPositon() {
            @Override
            public void sendInfo(int p, String lable) {
//              初始化数量
                int num=0;
                for (int i = 0; i <pos.length; i++) {
                    if (pos[i]!=-1){
                        num++;
                    }
                }
//                1 检测是否和其中的相同
                for (int i = 0; i < pos.length; i++) {
                    if (p==pos[i]){
                        pos[i]=-1;
                        lables[i]=null;
                        tv_number.setText("("+(--num)+"/2)");
                        adapter.setFlag(pos);
                        return;
                    }
                }
//                2 检测是否有空值
                for (int i = 0; i < pos.length; i++) {
                    if (pos[i]==-1){
                        pos[i]=p;
                        lables[i]=lable;
                        tv_number.setText("("+(++num)+"/2)");
                        adapter.setFlag(pos);
                        return;
                    }
                }
//                3 不然是置换
                pos[0]=pos[1];
                pos[1]=p;
                lables[0]=lables[1];
                lables[1]=lable;
//                4 然后重新刷新数据源list
                adapter.setFlag(pos);
            }
        });
        findViewById(R.id.iv_close_lable).setOnClickListener(this);
        findViewById(R.id.tv_sure_lable).setOnClickListener(this);
        request();
    }

    private void request() {
        OkGo.post(Urls.getLabel)//
                .tag(this)//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("===============", "obtioncode: "+s);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_lable:
                finish();
                break;
            case R.id.tv_sure_lable:
                Intent intent = new Intent();
                intent.putExtra("lable1", lables[0]); //将计算的值回传回去
                intent.putExtra("lable2", lables[1]); //将计算的值回传回去
                setResult(RESULT_OK, intent);
                finish();
                break;

        }
    }
}
