package com.chuanqing.youngstar.login._student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class ImageAuthenActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageauthen);

        initView();
    }

    private void initView() {

        RelativeLayout lable = findViewById(R.id.rl_imgauthen);
        RelativeLayout front = findViewById(R.id.rl_imgauthen);
        RelativeLayout behind = findViewById(R.id.rl_imgauthen);
        RelativeLayout studentId = findViewById(R.id.rl_imgauthen);

        TextView ok=findViewById(R.id.tv_ok_imgthen);
    }
}
