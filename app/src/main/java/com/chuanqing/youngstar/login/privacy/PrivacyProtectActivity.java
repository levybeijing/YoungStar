package com.chuanqing.youngstar.login.privacy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class PrivacyProtectActivity extends BaseActivity{

    private TextView tv_privacy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        tv_privacy = findViewById(R.id.tv_privacy);
        tv_privacy.setText(R.string.privacy);
    }
}
