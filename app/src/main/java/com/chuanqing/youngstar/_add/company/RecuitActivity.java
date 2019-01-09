package com.chuanqing.youngstar._add.company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class RecuitActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuit);

        initView();
    }

    private void initView() {
//        1
        EditText theme = findViewById(R.id.et_theme_recuit);
        EditText job = findViewById(R.id.et_job_recuit);

        findViewById(R.id.rl_num_recuit).setOnClickListener(this);
        findViewById(R.id.rl_money_recuit).setOnClickListener(this);
        findViewById(R.id.rl_major_recuit).setOnClickListener(this);
        findViewById(R.id.rl_addre_recuit).setOnClickListener(this);
//        2
        EditText require = findViewById(R.id.et_require_recuit);
//        3
        TextView lablenum = findViewById(R.id.tv_lablenum_recuit);
        RecyclerView lables = findViewById(R.id.rv_lables_recuit);
//        4
        RecyclerView imgs = findViewById(R.id.rv_imgs_recuit);
//        5
        EditText name = findViewById(R.id.et_name_recuit);
        EditText phone = findViewById(R.id.et_phone_recuit);
        EditText email = findViewById(R.id.et_email_recuit);
        EditText address = findViewById(R.id.et_address_recuit);
        EditText comname = findViewById(R.id.et_comname_recuit);
//        6
        TextView release = findViewById(R.id.tv_release_recuit);

    }

    @Override
    public void onClick(View v) {

    }
}
