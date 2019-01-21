package com.chuanqing.youngstar._add.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

/**
 * 作品集
 */
public class WorksActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishworks);

        initView();
    }

    private void initView() {

        ImageView cover = findViewById(R.id.iv_cover_works);
        EditText name = findViewById(R.id.et_name_works);
        EditText intro = findViewById(R.id.et_intro_works);
        RecyclerView rv = findViewById(R.id.rv_works);

        findViewById(R.id.tv_addcover_works).setOnClickListener(this);
        findViewById(R.id.iv_gallery_works).setOnClickListener(this);
        findViewById(R.id.iv_camera_works).setOnClickListener(this);
        findViewById(R.id.iv_record_works).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_addcover_works:

                break;
            case R.id.iv_gallery_works:

                break;
            case R.id.iv_camera_works:

                break;
            case R.id.iv_record_works:

                break;
        }
    }
}
