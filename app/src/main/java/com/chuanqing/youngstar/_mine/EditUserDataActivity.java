package com.chuanqing.youngstar._mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._mine.company.ChangeVericActivity;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login.login.LoginActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;

public class EditUserDataActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        initView();
    }

    private void initView() {
        TextView tv_phone = findViewById(R.id.tv_phone_editdata);
        tv_phone.setText((String)SharedPFUtils.getParam(this,"phone",""));

        ImageView iv_photo = findViewById(R.id.iv_photo_editdata);
        String photo = (String) SharedPFUtils.getParam(this, "photo", "");
        Glide.with(this).load(photo).into(iv_photo);

        findViewById(R.id.tv_exit_editdata).setOnClickListener(this);
        findViewById(R.id.ll_set_editdata).setOnClickListener(this);
        findViewById(R.id.ll_changepwd_editdata).setOnClickListener(this);
        findViewById(R.id.ll_changeveri_editdata).setOnClickListener(this);
        findViewById(R.id.ll_phone_editdata).setOnClickListener(this);
        findViewById(R.id.ll_email_editdata).setOnClickListener(this);

        findViewById(R.id.iv_back_editdata).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_exit_editdata:
//           退出登录
                SharedPFUtils.setParam(EditUserDataActivity.this, "islogin", false);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.ll_set_editdata:
                startActivity(new Intent(this,SettinsActivity.class));
                break;
            case R.id.ll_changepwd_editdata:
                startActivity(new Intent(this,EditPWDActivity.class));
                break;
            case R.id.ll_changeveri_editdata:
//                分类
                startActivity(new Intent(this, ChangeVericActivity.class));
                break;
            case R.id.ll_phone_editdata:
                startActivity(new Intent(this,EditPhoneActivity.class));
                break;
            case R.id.ll_email_editdata:
                startActivity(new Intent(this,EditEmailActivity.class));
                break;
            case R.id.iv_back_editdata:
                finish();
                break;
        }
    }
}
