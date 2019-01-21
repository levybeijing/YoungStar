package com.chuanqing.youngstar._add;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.MainActivity;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 中间按钮--- 发布动态视频
 */
public class PublishActivity extends BaseActivity{
    private static final String TAG = "PublishActivity";
    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量 单选
    private static final int REQUEST_CAMERA_CODE = 24;//定义请求码常量  多选
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishs);
        ButterKnife.bind(this);
        initView();
    }

    @BindView(R.id.publishs_fengmian)
    ImageView img_up_fengmian;
    @BindView(R.id.publishs_info)
    ImageView img_up_info;
    ArrayList<String> imagePaths = new ArrayList<>();
    private void initView() {
        EditText title = findViewById(R.id.et_title_publish);
        EditText content = findViewById(R.id.et_content_publish);
        TextView titleNum = findViewById(R.id.tv_titlenum_publish);
        TextView contentNum = findViewById(R.id.tv_contentnum_publish);
        //标题
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleNum.setText("("+title.getText().toString().length()+"/20）");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //详情
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contentNum.setText("("+title.getText().toString().length()+"/200）");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        RecyclerView rv = findViewById(R.id.rv_publish);
//        GridLayoutManager manager=new GridLayoutManager(this,3);
//        rv.setLayoutManager(manager);
        img_up_fengmian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //单选
                PhotoPickerIntent intent = new PhotoPickerIntent(PublishActivity.this);
                intent.setSelectModel(SelectModel.SINGLE);
                intent.setShowCarema(false); // 是否显示拍照， 默认false
                startActivityForResult(intent, REQUEST_CODE_CHOOSE);


            }
        });
        //多选
        img_up_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //多选
                PhotoPickerIntent intent = new PhotoPickerIntent(PublishActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照， 默认false
                intent.setMaxTotal(8); // 最多选择照片数量，默认为8
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
// intent.setImageConfig(config);
                startActivityForResult(intent, REQUEST_CAMERA_CODE);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null){
            return;
        }
        switch (requestCode) {
            // 选择照片
            case REQUEST_CODE_CHOOSE:
                refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                break;
//            // 多选
            case REQUEST_CAMERA_CODE:
                refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                break;
//            // 预览
//            case REQUEST_PREVIEW_CODE:
//                refreshAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
//                break;
        }
    }
    private void refreshAdpater(ArrayList<String> paths){
        // 处理返回照片地址
        Log.e(TAG, "refreshAdpater: 图片返回地址"+paths.get(0) );
        Uri uri = Uri.parse(paths.get(0));
        img_up_fengmian.setImageURI(uri);
    }
}
