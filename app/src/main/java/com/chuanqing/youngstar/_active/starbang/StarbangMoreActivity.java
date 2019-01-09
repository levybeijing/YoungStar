package com.chuanqing.youngstar._active.starbang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._active.leitai.LeitaiMoreActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 星榜详情页面
 */
public class StarbangMoreActivity extends AppCompatActivity {
    String activityCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starbang_more);
        ButterKnife.bind(this);
        setTtitle();
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.common_left_img)
    ImageView left_img;
    @BindView(R.id.common_center_title)
    TextView tv_title;
    @BindView(R.id.common_rigth_img)
    ImageView right_img;
    private void setTtitle(){
        tv_title.setText("星榜详情");
        left_img.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        //返回
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StarbangMoreActivity.this.finish();
            }
        });
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂无分享功能
            }
        });
    }
}
