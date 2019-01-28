package com.chuanqing.youngstar.login._company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.mybean.FragCareCBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Authen1Activity extends BaseActivity implements View.OnClickListener {
    private int chooseindex=1;
    private List<ImageView> list=new ArrayList<>();
    private EditText et_com;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_intro;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comauthen1);

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_comauthen1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_com = findViewById(R.id.et_cpyname_comauthen1);
        et_name = findViewById(R.id.et_username_comauthen1);
        et_phone = findViewById(R.id.et_phone_comauthen1);
        et_email = findViewById(R.id.et_email_comauthen1);
        et_intro = findViewById(R.id.et_intro_comauthen1);

        findViewById(R.id.tv_next_comauthen1).setOnClickListener(this);

        ImageView photo1 = findViewById(R.id.iv1_comauthen1);
        ImageView photo2 = findViewById(R.id.iv2_comauthen1);
        ImageView photo3 = findViewById(R.id.iv3_comauthen1);
        ImageView photo4 = findViewById(R.id.iv4_comauthen1);
        ImageView photo5 = findViewById(R.id.iv5_comauthen1);
        ImageView photo6 = findViewById(R.id.iv6_comauthen1);
        ImageView photo7 = findViewById(R.id.iv7_comauthen1);
        ImageView photo8 = findViewById(R.id.iv8_comauthen1);

        list.add(photo1);
        list.add(photo2);
        list.add(photo3);
        list.add(photo4);
        list.add(photo5);
        list.add(photo6);
        list.add(photo7);
        list.add(photo8);

        findViewById(R.id.iv_1_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_2_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_3_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_4_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_5_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_6_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_7_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_8_comauthen1).setOnClickListener(this);

        findViewById(R.id.tv_next_comauthen1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_1_comauthen1:
                checked(1);
                break;
            case R.id.iv_2_comauthen1:
                checked(2);
                break;
            case R.id.iv_3_comauthen1:
                checked(3);
                break;
            case R.id.iv_4_comauthen1:
                checked(4);
                break;
            case R.id.iv_5_comauthen1:
                checked(5);
                break;
            case R.id.iv_6_comauthen1:
                checked(6);
                break;
            case R.id.iv_7_comauthen1:
                checked(7);
                break;
            case R.id.iv_8_comauthen1:
                checked(8);
                break;
            case R.id.tv_next_comauthen1:
//                头像?

                String com = et_com.getText().toString().trim();
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String intro = et_intro.getText().toString().trim();

                Intent intent=new Intent(this,Authen2Activity.class);
                intent.putExtra("name",name);
                intent.putExtra("com",com);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);
                intent.putExtra("intro",intro);
//                头像
                intent.putExtra("photo",chooseindex);

                startActivity(intent);
                break;
        }
    }

    private void checked(int i){
        chooseindex=i;
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
