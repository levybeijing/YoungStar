package com.chuanqing.youngstar.login._invest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class InvestAuthenActivity extends BaseActivity implements View.OnClickListener {

    private List<ImageView> list=new ArrayList<>();
    private int chooseindex=1;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_intro;
    private String photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investauthen);

        initView();
    }

    private void initView() {

        findViewById(R.id.iv_back_investauthen1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageView photo1 = findViewById(R.id.iv1_investauthen1);
        ImageView photo2 = findViewById(R.id.iv2_investauthen1);
        ImageView photo3 = findViewById(R.id.iv3_investauthen1);
        ImageView photo4 = findViewById(R.id.iv4_investauthen1);
        ImageView photo5 = findViewById(R.id.iv5_investauthen1);
        ImageView photo6 = findViewById(R.id.iv6_investauthen1);
        ImageView photo7 = findViewById(R.id.iv7_investauthen1);
        ImageView photo8 = findViewById(R.id.iv8_investauthen1);

        list.add(photo1);
        list.add(photo2);
        list.add(photo3);
        list.add(photo4);
        list.add(photo5);
        list.add(photo6);
        list.add(photo7);
        list.add(photo8);

        findViewById(R.id.iv_1_investauthen1).setOnClickListener(this);
        findViewById(R.id.iv_2_investauthen1).setOnClickListener(this);
        findViewById(R.id.iv_3_investauthen1).setOnClickListener(this);
        findViewById(R.id.iv_4_investauthen1).setOnClickListener(this);
        findViewById(R.id.iv_5_investauthen1).setOnClickListener(this);
        findViewById(R.id.iv_6_investauthen1).setOnClickListener(this);
        findViewById(R.id.iv_7_investauthen1).setOnClickListener(this);
        findViewById(R.id.iv_8_investauthen1).setOnClickListener(this);


        et_name = findViewById(R.id.et_username_investauthen1);
        et_phone = findViewById(R.id.et_phone_investauthen1);
        et_email = findViewById(R.id.et_email_investauthen1);
        et_intro = findViewById(R.id.et_intro_investauthen1);

        findViewById(R.id.tv_next_investauthen1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_1_investauthen1:
                checked(1);
                break;
            case R.id.iv_2_investauthen1:
                checked(2);
                break;
            case R.id.iv_3_investauthen1:
                checked(3);
                break;
            case R.id.iv_4_investauthen1:
                checked(4);
                break;
            case R.id.iv_5_investauthen1:
                checked(5);
                break;
            case R.id.iv_6_investauthen1:
                checked(6);
                break;
            case R.id.iv_7_investauthen1:
                checked(7);
                break;
            case R.id.iv_8_investauthen1:
                checked(8);
                break;
            case R.id.tv_next_investauthen1:
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String intro = et_intro.getText().toString().trim();
                Intent intent=new Intent(this,InvestAuthen2Activity.class);
                intent.putExtra("name",name);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);
                intent.putExtra("intro",intro);

//              头像?????
                intent.putExtra("photo",photo);

                startActivity(intent);
                break;
        }
    }

    private void checked(int i){
        photo="";
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
