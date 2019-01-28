package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class EditPWD2Activity extends BaseActivity implements View.OnClickListener {

    private EditText et_old;
    private EditText et_new;
    private EditText et_again;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpwd2);

        initView();
    }

    private void initView() {
        findViewById(R.id.tv_ok_editpwd2).setOnClickListener(this);

        et_old = findViewById(R.id.et_oldpwd_editpwd2);
        et_new = findViewById(R.id.et_newpwd_editpwd2);
        et_again = findViewById(R.id.et_pwdagain_editpwd2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ok_editpwd2:
                String old = et_old.getText().toString().trim();
                String mew = et_new.getText().toString().trim();
                OkGo.post(Urls.updateUserPassword)//
                        .tag(this)//
                        .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//墙的ID
                        .params("oldPassword", old)//墙的ID
                        .params("newPassword", mew)//墙的ID
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Log.e("=============", "getSms"+s);
                                finish();
                            }
                        });
                break;

        }
    }
}
