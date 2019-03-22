package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.chuanqing.youngstar.tools.StringUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class EditEmailActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_new;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editemail);

        initView();
    }

    private void initView() {
        findViewById(R.id.tv_ok_editemail).setOnClickListener(this);

        TextView tv_old = findViewById(R.id.tv_oldemail_editemail);
        String email = getIntent().getStringExtra("email");
        tv_old.setText(email);
        et_new = findViewById(R.id.et_new_editemail);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_ok_editemail:
                String trim = et_new.getText().toString().trim();
                if (StringUtil.isEmail(trim)){
                    request(trim);
                }else{
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void request(String trim) {
//        OkGo.post(Urls.getSms)//
//                .tag(this)//
//                .params("mobile", trim)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("===============", "obtioncode: "+s);
//                    }
//                });
    }
}
