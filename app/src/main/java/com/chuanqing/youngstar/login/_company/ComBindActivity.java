package com.chuanqing.youngstar.login._company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.chuanqing.youngstar.MainActivity;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login._student.ImageAuthenActivity;
import com.chuanqing.youngstar.login.bean.CommenBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class ComBindActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combind);

        initView();
    }

    private void initView() {

        EditText et_name = findViewById(R.id.et_name_combind);
        EditText et_phone = findViewById(R.id.et_phone_combind);
        EditText et_id = findViewById(R.id.et_comId_combind);

        findViewById(R.id.tv_ok_combind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String id = et_id.getText().toString().trim();

                request(name,phone,id);
            }
        });


        findViewById(R.id.iv_back_combind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void request(String name, String phone, String id) {
        OkGo.post(Urls.addStudent)//
                .tag(this)//
                .params("mobile", name)
                .params("userCode", phone)
                .params("sex", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===========", "onSuccess: "+s);
////                        finish

                    }
                });
    }

}
