package com.chuanqing.youngstar._mine.student;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.StringUtil;

import java.io.File;

public class EditPhotosActivity extends BaseActivity implements View.OnClickListener {

    private ImageView photo;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changephoto);
//
        // 一个自定义的布局，作为显示的内容
        View view = LayoutInflater.from(this).inflate(
                R.layout.photo_choose_pop, null);

        popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        view.findViewById(R.id.cv_gallery_pop).setOnClickListener(this);
        view.findViewById(R.id.cv_camera_pop).setOnClickListener(this);
        view.findViewById(R.id.cv_cancel_pop).setOnClickListener(this);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.bg_white5));
        initView();
    }
    private File tempFile;

    private void initView() {
        photo = findViewById(R.id.iv_photo_changephoto);

        findViewById(R.id.tv_change_changephoto).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2000:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".provider", tempFile);
                        startPhotoZoom(contentUri);//开始对图片进行裁剪处理
                    } else {
                        startPhotoZoom(Uri.fromFile(tempFile));//开始对图片进行裁剪处理
                    }
                }
                break;
            case 2001:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    startPhotoZoom(uri); // 开始对图片进行裁剪处理
                }
                break;
            case 2002:  //调用剪裁后返回
                if (data != null) {
                    // 让刚才选择裁剪得到的图片显示在界面上
                    Bitmap p = BitmapFactory.decodeFile(mFile);
                    photo.setImageBitmap(p);
                } else {
                    Log.e("data","data为空");
                }
                break;
        }
    }
    private String mFile;

    protected void startPhotoZoom(Uri uri) {

        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", false);
        File out = new File(getPath());
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out));
        startActivityForResult(intent, 2002);
        Toast.makeText(this, mFile, Toast.LENGTH_SHORT).show();

    }

    //裁剪后的地址
    public  String getPath() {
        mFile = Urls.IMGPATH + StringUtil.getRandomName(8) + ".png";
        return mFile;
    }
    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".png");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".provider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            Log.e("getPicFromCamera", contentUri.toString());
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, 2002);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_gallery_pop:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 2000);
                popupWindow.dismiss();
                break;
            case R.id.cv_camera_pop:
                getPicFromCamera();
                popupWindow.dismiss();
                break;
            case R.id.cv_cancel_pop:
                popupWindow.dismiss();
                break;
        }
    }
}
