package com.chuanqing.youngstar._mine.student;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;


public class AddAudioActivity extends BaseActivity implements View.OnClickListener {

    private ImageView start;
    private ImageView pause;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
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
                pause.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);

                break;
            case R.id.iv_pause_addaudio:
                pause.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);

                break;
        }
    }

}
