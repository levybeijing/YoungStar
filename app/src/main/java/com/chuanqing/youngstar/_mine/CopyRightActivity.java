package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class CopyRightActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyright);

        findViewById(R.id.iv_back_copyright).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
