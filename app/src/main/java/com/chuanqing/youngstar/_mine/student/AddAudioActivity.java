package com.chuanqing.youngstar._mine.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;


public class AddAudioActivity extends BaseActivity implements View.OnClickListener {

    private ImageView start;
    private ImageView pause;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaudio);

        initView();
    }

    private void initView() {
        start = findViewById(R.id.iv_start_addaudio);
        start.setOnClickListener(this);
        pause = findViewById(R.id.iv_pause_addaudio);
        pause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_start_addaudio:

                break;
            case R.id.iv_pause_addaudio:

                break;
        }
    }
}
