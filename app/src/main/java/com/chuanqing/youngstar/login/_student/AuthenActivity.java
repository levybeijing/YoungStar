package com.chuanqing.youngstar.login._student;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.chuanqing.youngstar.MainActivity;
import com.chuanqing.youngstar.MyApplication;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.login.bean.CommenBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.chuanqing.youngstar.tools.StringUtil;
import com.chuanqing.youngstar.widget.CirImageView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class AuthenActivity extends BaseActivity implements View.OnClickListener {

    private boolean havephoto=false;
    private String sex;
    private EditText name;
    private EditText phone;
    private EditText school;
    private EditText major;
    private EditText snumber;
    private EditText inumber;
    private EditText email;
    private PopupWindow popupWindow;
    private RelativeLayout root;
    private static final int ALBUM_REQUEST_CODE=1001;
    private static final int CAMERA_REQUEST_CODE=1002;
    private static final int CROP_SMALL_PICTURE=1003;
    private CirImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authen);

        initView();
    }

    private void initView() {

        findViewById(R.id.iv_back_authen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        root = findViewById(R.id.rl_root_authen);
//
        logo = findViewById(R.id.iv_logo_authen);
        logo.setOnClickListener(this);
//
        RadioButton man = findViewById(R.id.rb_man_authen);
        man.setOnClickListener(this);
        RadioButton woman = findViewById(R.id.rb_woman_authen);
        woman.setOnClickListener(this);
//
        name = findViewById(R.id.et_name_authen);
        phone = findViewById(R.id.et_phone_authen);
        school = findViewById(R.id.et_school_authen);
        major = findViewById(R.id.et_major_authen);
        snumber = findViewById(R.id.et_snumber_authen);
        inumber = findViewById(R.id.et_inumber_authen);
        email = findViewById(R.id.et_email_authen);
//
        TextView next = findViewById(R.id.iv_next_authen);
        next.setOnClickListener(this);
//
        // 一个自定义的布局，作为显示的内容
        View view = LayoutInflater.from(this).inflate(R.layout.photo_choose_pop, null);

        view.findViewById(R.id.cv_gallery_pop).setOnClickListener(this);
        view.findViewById(R.id.cv_camera_pop).setOnClickListener(this);
        view.findViewById(R.id.cv_cancel_pop).setOnClickListener(this);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_white5));

    }

//    private List<String> list=new ArrayList<>();

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_logo_authen:
                popupWindow.showAtLocation(root,Gravity.BOTTOM,0,0);
                break;
            case R.id.iv_next_authen:
                if (!havephoto){
                    Toast.makeText(this, "头像不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (sex==null||sex.length()==0){
                    Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
                    return;
                }
                String trim1 = name.getText().toString().trim();
                if (trim1==null||trim1.length()==0){
                    Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String trim2 = phone.getText().toString().trim();
                if (trim2==null||trim2.length()==0){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String trim3 = school.getText().toString().trim();
                if (trim3==null||trim3.length()==0){
                    Toast.makeText(this, "学校不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String trim4 = major.getText().toString().trim();
                if (trim4==null||trim4.length()==0){
                    Toast.makeText(this, "专业不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String trim5 = snumber.getText().toString().trim();
                if (trim5==null||trim5.length()==0){
                    Toast.makeText(this, "学号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String trim6 = inumber.getText().toString().trim();
                if (trim6==null||trim6.length()==0){
                    Toast.makeText(this, "身份证号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String trim7 = email.getText().toString().trim();
                if (trim7==null||trim7.length()==0) {
                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                Student student=new Student();
                student.setName(trim1);
                student.setPhone(trim2);
                student.setSchool(trim3);
                student.setMajor(trim4);
                student.setStudentNo(trim5);
                student.setCardId(trim6);
                student.setEmail(trim7);
                String[] r = mFile.split("/");
                student.setImgpath(SharedPFUtils.getParam(this,"usercode","")+File.separator+r[r.length-1]);
                Intent intent = new Intent(AuthenActivity.this, ImageAuthenActivity.class);
                intent.putExtra("student",student);
                startActivity(intent);
                break;
            case R.id.rb_man_authen:
                sex="男";
                break;
            case R.id.rb_woman_authen:
                sex="女";
                break;
            case R.id.cv_gallery_pop:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
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

    private File tempFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
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
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    startPhotoZoom(uri); // 开始对图片进行裁剪处理
                }
                break;
            case CROP_SMALL_PICTURE:  //调用剪裁后返回
                if (data != null) {
                    // 让刚才选择裁剪得到的图片显示在界面上
                    Bitmap photo =BitmapFactory.decodeFile(mFile);
                    logo.setImageBitmap(photo);
                    Log.e("==========", "CROP_SMALL_PICTURE: "+mFile);
                    String[] r = mFile.split("/");
                    String name = SharedPFUtils.getParam(this,"usercode","")+File.separator+r[r.length-1];
                    uploadOss(name,mFile);
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
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    //裁剪后的地址
    public  String getPath() {
        mFile = Urls.IMGPATH +StringUtil.getRandomName(8) + ".png";
        return mFile;
    }

    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Urls.IMGPATH, System.currentTimeMillis() + ".png");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, getPackageName()+".provider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            Log.e("getPicFromCamera", contentUri.toString());
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    //     阿里云上传文件
    private void uploadOss(String name,String path){
//        String s = SharedPFUtils.getParam(this,"usercode","")+File.separator+name;
// 构造上传请求
        PutObjectRequest put = new PutObjectRequest("star-1", name, path);
        Log.e("=============name", "uploadOss:"+name);


// 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = MyApplication.oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("=============PutObject", "UploadSuccess");
                havephoto=true;
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }
}
