package com.chuanqing.youngstar.login._company;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class Authen1Activity extends BaseActivity {
    private int current=1;
    private RadioGroup rg1;
    private RadioGroup rg2;

    private int pre;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comauthen1);

        initView();
    }

    private void initView() {
        rg1 = findViewById(R.id.rg1_comauthen1);
        rg2 = findViewById(R.id.rg2_comauthen1);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1_comauthen1:
                        current = 1;
                        break;
                    case R.id.rb2_comauthen1:
                        current = 2;
                        break;
                    case R.id.rb3_comauthen1:
                        current = 3;
                        break;
                    case R.id.rb4_comauthen1:
                        current = 4;
                        break;
                }
                Log.e("==============", "onCheckedChanged: "+current);
                RadioButton rbcurrent = findViewById(checkedId);
                rbcurrent.setChecked(true);
                rbcurrent.setButtonDrawable(getDrawable(R.mipmap.checked_company));
                RadioButton rbpre = findViewById(pre);
                rbpre.setChecked(false);
                rbpre.setButtonDrawable(null);
                pre=checkedId;
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb5_comauthen1:
                        current=5;
                        break;
                    case R.id.rb6_comauthen1:
                        current=6;
                        break;
                    case R.id.rb7_comauthen1:
                        current=7;
                        break;
                    case R.id.rb8_comauthen1:
                        current=8;
                        break;
                }
                Log.e("==============", "onCheckedChanged: "+current);
                RadioButton rbcurrent = findViewById(checkedId);
                rbcurrent.setChecked(true);
                rbcurrent.setButtonDrawable(getDrawable(R.mipmap.checked_company));
                RadioButton rbpre = findViewById(pre);
                rbpre.setChecked(false);
                rbpre.setButtonDrawable(null);
                pre=checkedId;
            }
        });

    }
}
