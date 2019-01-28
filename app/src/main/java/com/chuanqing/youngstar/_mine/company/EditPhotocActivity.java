package com.chuanqing.youngstar._mine.company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class EditPhotocActivity extends BaseActivity implements View.OnClickListener {

    private List<ImageView> list=new ArrayList<>();
    private int checkedNum=-1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editphotoc);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_editphotoc).setOnClickListener(this);

        ImageView photo1 = findViewById(R.id.iv1_editphotoc);
        ImageView photo2 = findViewById(R.id.iv2_editphotoc);
        ImageView photo3 = findViewById(R.id.iv3_editphotoc);
        ImageView photo4 = findViewById(R.id.iv4_editphotoc);
        ImageView photo5 = findViewById(R.id.iv5_editphotoc);
        ImageView photo6 = findViewById(R.id.iv6_editphotoc);
        ImageView photo7 = findViewById(R.id.iv7_editphotoc);
        ImageView photo8 = findViewById(R.id.iv8_editphotoc);

        list.add(photo1);
        list.add(photo2);
        list.add(photo3);
        list.add(photo4);
        list.add(photo5);
        list.add(photo6);
        list.add(photo7);
        list.add(photo8);

        findViewById(R.id.iv_1_editphotoc).setOnClickListener(this);
        findViewById(R.id.iv_2_editphotoc).setOnClickListener(this);
        findViewById(R.id.iv_3_editphotoc).setOnClickListener(this);
        findViewById(R.id.iv_4_editphotoc).setOnClickListener(this);
        findViewById(R.id.iv_5_editphotoc).setOnClickListener(this);
        findViewById(R.id.iv_6_editphotoc).setOnClickListener(this);
        findViewById(R.id.iv_7_editphotoc).setOnClickListener(this);
        findViewById(R.id.iv_8_editphotoc).setOnClickListener(this);

        findViewById(R.id.tv_ok_editphotoc).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_1_editphotoc:
                checked(1);
                break;
            case R.id.iv_2_editphotoc:
                checked(2);
                break;
            case R.id.iv_3_editphotoc:
                checked(3);
                break;
            case R.id.iv_4_editphotoc:
                checked(4);
                break;
            case R.id.iv_5_editphotoc:
                checked(5);
                break;
            case R.id.iv_6_editphotoc:
                checked(6);
                break;
            case R.id.iv_7_editphotoc:
                checked(7);
                break;
            case R.id.iv_8_editphotoc:
                checked(8);
                break;
            case R.id.tv_ok_editphotoc:
                request(checkedNum);
                break;
            case R.id.iv_back_editphotoc:
                finish();
                break;
        }
    }

    private void request(int index) {
//        根据位置上传头像并提交
        OkGo.post(Urls.updateUserImg)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .params("userImg", "")//文件名
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "getUserConcernCompany"+s);
                    }
                });
    }

    private void checked(int i){
        checkedNum=i;
        i=i-1;
        for (int j = 0; j < list.size(); j++) {
            if (i==j){
                list.get(j).setVisibility(View.VISIBLE);
            }else{
                list.get(j).setVisibility(View.INVISIBLE);
            }
        }
    }
}
