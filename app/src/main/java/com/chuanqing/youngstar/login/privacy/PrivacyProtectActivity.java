package com.chuanqing.youngstar.login.privacy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class PrivacyProtectActivity extends BaseActivity implements IView{

    private TextView tv_privacy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        P p = new P(this);
        tv_privacy = findViewById(R.id.tv_privacy);
    }

    @Override
    public void setContent(String content) {
        tv_privacy.setText(content);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
