package com.chuanqing.youngstar.login._company;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Authen2Activity extends BaseActivity implements View.OnClickListener {

    private String com;
    private String name;
    private String phone;
    private String email;
    private String intro;
    private String photo;

    private List<String> imglist=new ArrayList<>();
    private List<Boolean> oklist=new ArrayList<>();
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comauthen2);

        initView();

        Intent intent = getIntent();
        com = intent.getStringExtra("com");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        email = intent.getStringExtra("email");
        intro = intent.getStringExtra("intro");
//        头像>??????
        photo = intent.getStringExtra("photo");

        imglist.add(null);
        imglist.add(null);
        imglist.add(null);
        imglist.add(null);
//
        oklist.add(false);
        oklist.add(false);
        oklist.add(false);
        oklist.add(false);

    }

    private void initView() {
        findViewById(R.id.iv_back_comauthen2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv1 = findViewById(R.id.iv1_comauthen2);
        iv1.setOnClickListener(this);
        iv2 = findViewById(R.id.iv2_comauthen2);
        iv2.setOnClickListener(this);
        iv3 = findViewById(R.id.iv3_comauthen2);
        iv3.setOnClickListener(this);
        iv4 = findViewById(R.id.iv4_comauthen2);
        iv4.setOnClickListener(this);

        findViewById(R.id.tv_ok_comauthen2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1_comauthen2:
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 301);
                break;
            case R.id.iv2_comauthen2:
                Intent intent2 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 302);
                break;
            case R.id.iv3_comauthen2:
                Intent intent3 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent3, 303);
                break;
            case R.id.iv4_comauthen2:
                Intent intent4 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent4, 304);
                break;
            case R.id.tv_ok_comauthen2:
                for (int i = 0; i < oklist.size(); i++) {
                    if (!oklist.get(i)){
                        Toast.makeText(this, "图片上传中,请稍后!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                request();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==Activity.RESULT_OK) {
            switch (requestCode) {
                case 301:
                    if (resultCode == RESULT_OK) {
                        handleImg(301, data);
                    }
                    break;
                case 302:
                    if (resultCode == RESULT_OK) {
                        handleImg(302, data);
                    }
                    break;
                case 303:
                    if (resultCode == RESULT_OK) {
                        handleImg(303, data);
                    }
                    break;
                case 304:
                    if (resultCode == RESULT_OK) {
                        handleImg(304, data);
                    }
                    break;
            }
        }
    }

    private void handleImg(int code,Intent data){
        String path=null;
        Bitmap bitmap=null;
        String name=null;
        try {
            Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            path = cursor.getString(columnIndex);  //获取照片路径
            name=getName(path);
            cursor.close();
            bitmap = BitmapFactory.decodeFile(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (code){
            case 301:
                findViewById(R.id.tv1_comauthen2).setVisibility(View.INVISIBLE);
                iv1.setImageBitmap(bitmap);
                imglist.set(0,name);
                uploadOss(301,name,path);
                break;
            case 302:
                findViewById(R.id.tv2_comauthen2).setVisibility(View.INVISIBLE);
                iv2.setImageBitmap(bitmap);
                imglist.set(1,name);
                uploadOss(302,name,path);

                break;
            case 303:
                findViewById(R.id.tv3_comauthen2).setVisibility(View.INVISIBLE);
                iv3.setImageBitmap(bitmap);
                imglist.set(2,name);
                uploadOss(303,name,path);

                break;
            case 304:
                findViewById(R.id.tv4_comauthen2).setVisibility(View.INVISIBLE);
                iv4.setImageBitmap(bitmap);
                imglist.set(3,name);
                uploadOss(304,name,path);

                break;
        }
    }

    //     阿里云上传文件
    private void uploadOss(int code,String name,String path){
        String s = SharedPFUtils.getParam(this,"usercode","")+ File.separator+name;
// 构造上传请求
        PutObjectRequest put = new PutObjectRequest("star-1", s, path);
        Log.d("=============name", ""+s);

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
                switch (code){
                    case 301:
                        oklist.set(0,true);
                        Log.e("=======/========", "=301");
                        break;
                    case 302:
                        oklist.set(1,true);
                        Log.e("===============", "=302");
                        break;
                    case 303:
                        oklist.set(2,true);
                        Log.e("===============", "=303");
                        break;
                    case 304:
                        oklist.set(3,true);
                        Log.e("===============", "=304");
                        break;
                }
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

    public  String getName(String path) {
        if (path!=null){
            String[] split = path.split("/");
            return split[split.length-1];
        }
        return null;
    }

    private void request() {
        OkGo.post(Urls.addCompany)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(Authen2Activity.this,"usercode",""))
                .params("companyName", com)
                .params("companyTel", phone)
                .params("ownerName", name)
                .params("companyDetail", intro)
                .params("companyImg1", imglist.get(0))
                .params("ownerImg1", imglist.get(1))
                .params("ownerImg2", imglist.get(2))
                .params("otherImg", imglist.get(3))
                .params("userImg", photo)//传回来的头像
                .params("type", 2)
                .params("mail", email)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===========", "onSuccess: "+s);
//                        finish
                        CommenBean commenBean = new Gson().fromJson(s, CommenBean.class);
                        if ("请求成功".equals(commenBean.getMessage()))
                            startActivity(new Intent(Authen2Activity.this, MainActivity.class));
                    }
                });
    }

}
