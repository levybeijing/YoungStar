package com.chuanqing.youngstar._mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar._mine.company.ChangeVericActivity;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login.login.LoginActivity;
import com.chuanqing.youngstar.mybean.UserDataBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class EditUserDataActivity extends BaseActivity implements View.OnClickListener {

    private Switch sw_qq;
    private Switch sw_wb;
    private Switch sw_wx;
    private TextView tv_email;
    private LinearLayout ll_email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        initView();
    }

    private void initView() {
        TextView tv_phone = findViewById(R.id.tv_phone_editdata);
        tv_phone.setText((String)SharedPFUtils.getParam(this,"phone",""));

//        ImageView iv_photo = findViewById(R.id.iv_photo_editdata);
//        String photo = (String) SharedPFUtils.getParam(this, "photo", "");
//        Glide.with(this).load(photo).into(iv_photo);

        tv_email = findViewById(R.id.tv_email_editdata);
        sw_qq = findViewById(R.id.sw_qq_editdata);
        sw_wb = findViewById(R.id.sw_wb_editdata);
        sw_wx = findViewById(R.id.sw_wx_editdata);

        findViewById(R.id.tv_exit_editdata).setOnClickListener(this);
        findViewById(R.id.ll_set_editdata).setOnClickListener(this);
        findViewById(R.id.ll_changepwd_editdata).setOnClickListener(this);

        LinearLayout ll_veri = findViewById(R.id.ll_changeveri_editdata);
        ll_veri.setOnClickListener(this);
        int identity = (int) SharedPFUtils.getParam(this, "identity", -1);
        if (identity==4) {
            ll_veri.setVisibility(View.GONE);
        }

        findViewById(R.id.ll_phone_editdata).setOnClickListener(this);
        ll_email = findViewById(R.id.ll_email_editdata);
        ll_email.setOnClickListener(this);

        findViewById(R.id.iv_back_editdata).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        request();
    }
    private String mail;
    private void request() {
        OkGo.post(Urls.getUserDetails)//
                .tag(this)//
                .params("userCode", (String)SharedPFUtils.getParam(this,"usercode",""))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===============", "obtioncode: "+s);
                        UserDataBean bean = new Gson().fromJson(s, UserDataBean.class);
                        UserDataBean.DataBean data = bean.getData();
                        mail = data.getMail();
                        if (mail==null){
                            ll_email.setClickable(false);
                        }else{
                            ll_email.setClickable(true);
                            tv_email.setText(mail);
                        }
                        if (data.getQq()!=null){
                            sw_qq.setChecked(true);
                        }
                        if (data.getWb_code()!=null){
                            sw_wb.setChecked(true);
                        }
                        if (data.getWx_code()!=null){
                            sw_wx.setChecked(true);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_exit_editdata:
//           退出登录
                SharedPFUtils.clearData();
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

                Intent intent1 = new Intent(this, EditEmailActivity.class);
                intent1.putExtra("email",mail);
                startActivity(intent1);
                break;
            case R.id.iv_back_editdata:
                finish();
                break;
        }
    }
}
