package com.chuanqing.youngstar._mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.FileUtil;

import java.io.File;

public class SettinsActivity extends BaseActivity implements View.OnClickListener {

    private TextView cache;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();
    }

    private void initView() {
        findViewById(R.id.ll_system_settings).setOnClickListener(this);
        findViewById(R.id.ll_picket_settings).setOnClickListener(this);
        findViewById(R.id.ll_clear_settings).setOnClickListener(this);
        findViewById(R.id.ll_aboutours_settings).setOnClickListener(this);
        findViewById(R.id.ll_publish_settings).setOnClickListener(this);
        findViewById(R.id.ll_help_settings).setOnClickListener(this);

        findViewById(R.id.iv_back_settings).setOnClickListener(this);

        cache = findViewById(R.id.tv_cache_settings);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_system_settings:
                startActivity(new Intent(this,SystemSetActivity.class));
                break;
            case R.id.ll_picket_settings:
                showdialog();
                break;
            case R.id.ll_clear_settings:
//                start a new thread to do it?
                FileUtil.deleteFolderFile(Urls.CachePATH,false);
                File file=new File(Urls.CachePATH);
                long folderSize = FileUtil.getFolderSize(file);
                String formatSize = FileUtil.getFormatSize(folderSize);
                cache.setText(formatSize+"");
                break;
            case R.id.ll_aboutours_settings:
                startActivity(new Intent(this,CopyRightActivity.class));
                break;
            case R.id.ll_publish_settings:
                startActivity(new Intent(this,ReliefActivity.class));
                break;
            case R.id.ll_help_settings:
                startActivity(new Intent(this,HelpActivity.class));
                break;
            case R.id.iv_back_settings:
                finish();
                break;
        }
    }

    private void showdialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_service,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();

        TextView no = view.findViewById(R.id.tv_cancel_dialogservice);
        TextView ok = view.findViewById(R.id.tv_ok_dialogservice);

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + Urls.SERVICE);
                intent.setData(data);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        统计缓存数量
        File file=new File(Urls.CachePATH);
        long folderSize = FileUtil.getFolderSize(file);
        String formatSize = FileUtil.getFormatSize(folderSize);
        cache.setText(formatSize+"");
    }
}
