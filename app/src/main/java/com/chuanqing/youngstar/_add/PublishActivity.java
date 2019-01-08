package com.chuanqing.youngstar._add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class PublishActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishs);

        initView();
    }

    private void initView() {
        EditText title = findViewById(R.id.et_title_publish);
        EditText content = findViewById(R.id.et_content_publish);
        TextView titleNum = findViewById(R.id.tv_titlenum_publish);
        TextView contentNum = findViewById(R.id.tv_contentnum_publish);

        RecyclerView rv = findViewById(R.id.rv_publish);
        GridLayoutManager manager=new GridLayoutManager(this,3);
        rv.setLayoutManager(manager);


    }
}
