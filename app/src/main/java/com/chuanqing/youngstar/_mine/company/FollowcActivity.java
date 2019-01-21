package com.chuanqing.youngstar._mine.company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class FollowcActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followc);

        initView();
    }

    private void initView() {
        RecyclerView rv = findViewById(R.id.rv_followc);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

//

    }
}
