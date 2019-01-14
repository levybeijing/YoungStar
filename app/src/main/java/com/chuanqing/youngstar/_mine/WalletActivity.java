package com.chuanqing.youngstar._mine;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

public class WalletActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        initView();
    }

    private void initView() {
        TextView all = findViewById(R.id.tv_allnum_wallet);
        TextView star = findViewById(R.id.tv_starnum_wallet);
        TextView sprout = findViewById(R.id.tv_spnum_wallet);

//        6个兑换 说不定是recyclerVeiw
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
