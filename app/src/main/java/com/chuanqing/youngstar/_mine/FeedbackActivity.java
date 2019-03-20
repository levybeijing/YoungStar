package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class FeedbackActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();
    }

    private void initView() {
        EditText et_content = findViewById(R.id.et_content_feedback);
        String content = et_content.getText().toString().trim();

        findViewById(R.id.tv_ok_feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (content==null||content.length()==0){
                    Toast.makeText(FeedbackActivity.this, "信息为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                feedback(content);
            }
        });
    }

    private void feedback(String content){
        OkGo.post(Urls.addFeedback)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("reason", content)//墙的ID
                .params("mobile", (String) SharedPFUtils.getParam(this,"phone",""))//墙的ID
                .params("name", (String) SharedPFUtils.getParam(this,"name",""))//墙的ID
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "addFeedback"+s);
                        Toast.makeText(FeedbackActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
