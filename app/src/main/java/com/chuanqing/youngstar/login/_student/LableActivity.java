package com.chuanqing.youngstar.login._student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class LableActivity extends BaseActivity {

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

    }
}
