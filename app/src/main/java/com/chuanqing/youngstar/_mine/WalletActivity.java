package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.mybean.OfficeMsgDetailBean;
import com.chuanqing.youngstar.mybean.WalletBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class WalletActivity extends BaseActivity implements View.OnClickListener {

    private TextView all;
    private TextView star;
    private TextView sprout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        initView();
        request();
    }

    private void request() {
        OkGo.post(Urls.getUserWallet)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "getUserWallet"+s);
                        WalletBean bean = new Gson().fromJson(s, WalletBean.class);
                        all.setText(bean.getData().getStandard_coin()+"");
                        star.setText(bean.getData().getStar_coin()+"");
                        sprout.setText(bean.getData().getBud_coin()+"");
                    }
                });
    }

    private void initView() {
        findViewById(R.id.iv_back_wallet).setOnClickListener(this);

        all = findViewById(R.id.tv_allnum_wallet);
        star = findViewById(R.id.tv_starnum_wallet);
        sprout = findViewById(R.id.tv_spnum_wallet);

//        6个兑换 说不定是recyclerVeiw

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back_wallet:
                finish();
                break;

        }
    }
}
