package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class PlaySetActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playset);

        initView();
    }

    private void initView() {
        ImageView all = findViewById(R.id.iv_all_play);
        ImageView wifi = findViewById(R.id.iv_wifi_play);
        ImageView close = findViewById(R.id.iv_close_play);

        findViewById(R.id.ll_all_play).setOnClickListener(this);
        findViewById(R.id.ll_wifi_play).setOnClickListener(this);
        findViewById(R.id.ll_close_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_all_play:

                break;
            case R.id.ll_wifi_play:

                break;
            case R.id.ll_close_play:

                break;

        }
    }
}
