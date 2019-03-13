package com.chuanqing.youngstar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.base.BaseActivity;

public class ActiveDetailActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actdetail);

        initview();
    }

    private void initview() {
        findViewById(R.id.common_left_img).setOnClickListener(this);
        TextView tv = findViewById(R.id.common_center_title);
        tv.setText("活动详情");
        ImageView iv = findViewById(R.id.common_rigth_img);
        iv.setImageDrawable(getResources().getDrawable(R.mipmap.share_mine));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.common_left_img:
                finish();
                break;
        }
    }
}
