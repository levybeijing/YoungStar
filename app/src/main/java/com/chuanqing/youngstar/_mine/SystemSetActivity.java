package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Switch;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class SystemSetActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_systemset);

        initView();
    }

    private void initView() {
        findViewById(R.id.ll_autoplay_system).setOnClickListener(this);

        Switch audio = findViewById(R.id.sw_audio_system);
        Switch shake = findViewById(R.id.sw_shake_system);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_autoplay_system:

                break;

        }
    }
}
