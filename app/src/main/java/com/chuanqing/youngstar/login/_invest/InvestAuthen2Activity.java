package com.chuanqing.youngstar.login._invest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
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
import com.chuanqing.youngstar.tools.DpPxUtil;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class InvestAuthen2Activity extends BaseActivity implements View.OnClickListener {

    private String name;
    private String phone;
    private String email;
    private String intro;
    private String photo;
    private ProgressDialog waitingDialog;

    private String[] names=new String[3];
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private PopupWindow window;
    private View pop;
    private TextView tv_camera;
    private TextView tv_gallery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investauthen2);

        waitingDialog = new ProgressDialog(InvestAuthen2Activity.this);
        waitingDialog.setTitle("图片上传");
        waitingDialog.setMessage("上传中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        email = intent.getStringExtra("email");
        intro = intent.getStringExtra("intro");
        photo = intent.getStringExtra("photo");

        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_investauthen2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_ok_investauthen2).setOnClickListener(this);

        iv1 = findViewById(R.id.iv1_investauthen2);
        iv1.setOnClickListener(this);
        iv2 = findViewById(R.id.iv2_investauthen2);
        iv2.setOnClickListener(this);
        iv3 = findViewById(R.id.iv3_investauthen2);
        iv3.setOnClickListener(this);

//初始化
        pop = LayoutInflater.from(this).inflate(R.layout.popup_camera, null, false);
        window = new PopupWindow(pop,DpPxUtil.dip2px(this,100),DpPxUtil.dip2px(this,120),true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        tv_camera = pop.findViewById(R.id.tv_camera_popup);
        tv_gallery = pop.findViewById(R.id.tv_gallery_popup);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1_investauthen2:
                showpop(R.id.iv1_investauthen2);
                break;
            case R.id.iv2_investauthen2:
                showpop(R.id.iv2_investauthen2);
                break;
            case R.id.iv3_investauthen2:
                showpop(R.id.iv3_investauthen2);
                break;
            case R.id.tv_ok_investauthen2:
//          判断内容
                for (int i = 0; i < names.length; i++) {
                    if (names[i]==null){
                        Toast.makeText(this, "第"+(i+1)+"图片未上传", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                request();
                break;
        }
    }
    private File tempFile;
    //    拍照调用
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showpop(int id){
        switch (id){
            case R.id.iv1_investauthen2:
                window.showAsDropDown(iv1, DpPxUtil.dip2px(this,85),-DpPxUtil.dip2px(this,127.5f),Gravity.CENTER);
                tv_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                        //用于保存调用相机拍照后所生成的文件
                        tempFile = new File(Urls.IMGPATH, System.currentTimeMillis() + ".png");
                        if (!tempFile.getParentFile().exists()){
                            tempFile.getParentFile().mkdirs();
                            try {
                                tempFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //跳转到调用系统相机
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //        //判断版本
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
                            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            Uri contentUri = FileProvider.getUriForFile(InvestAuthen2Activity.this, getPackageName()+".provider", tempFile);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                            Log.e("getPicFromCamera", contentUri.toString());
                        } else {    //否则使用Uri.fromFile(file)方法获取Uri
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                        }
                        startActivityForResult(intent, 411);
                    }
                });
                tv_gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 401);
                        window.dismiss();
                    }
                });

                break;
            case R.id.iv2_investauthen2:
                window.showAsDropDown(iv2, DpPxUtil.dip2px(this,85),-DpPxUtil.dip2px(this,127.5f),Gravity.CENTER);
                tv_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                        tempFile = new File(Urls.IMGPATH, System.currentTimeMillis() + ".png");
                        if (!tempFile.getParentFile().exists()){
                            tempFile.getParentFile().mkdirs();
                            try {
                                tempFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //跳转到调用系统相机
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //        //判断版本
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
                            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            Uri contentUri = FileProvider.getUriForFile(InvestAuthen2Activity.this, getPackageName()+".provider", tempFile);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                            Log.e("getPicFromCamera", contentUri.toString());
                        } else {    //否则使用Uri.fromFile(file)方法获取Uri
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                        }
                        startActivityForResult(intent, 412);
                    }
                });
                tv_gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 402);
                        window.dismiss();

                    }
                });
                break;
            case R.id.iv3_investauthen2:
                window.showAsDropDown(iv3, DpPxUtil.dip2px(this,85),-DpPxUtil.dip2px(this,127.5f),Gravity.CENTER);
                tv_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        window.dismiss();
                        tempFile = new File(Urls.IMGPATH, System.currentTimeMillis() + ".png");
                        if (!tempFile.getParentFile().exists()){
                            tempFile.getParentFile().mkdirs();
                            try {
                                tempFile.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        //跳转到调用系统相机
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //        //判断版本
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
                            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                            Uri contentUri = FileProvider.getUriForFile(InvestAuthen2Activity.this, getPackageName()+".provider", tempFile);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                            Log.e("getPicFromCamera", contentUri.toString());
                        } else {    //否则使用Uri.fromFile(file)方法获取Uri
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                        }
                        startActivityForResult(intent, 413);
                    }
                });
                tv_gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 403);
                        window.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 401:
                if (resultCode==RESULT_OK){
                    waitingDialog.show();
                    handleImg(401,data);
                }
                break;
            case 402:
                if (resultCode==RESULT_OK){
                    waitingDialog.show();
                    handleImg(402,data);
                }
                break;
            case 403:
                if (resultCode==RESULT_OK){
                    waitingDialog.show();
                    handleImg(403,data);
                }
                break;
            case 411:
                if (resultCode==RESULT_OK){
                    waitingDialog.show();
                    handleCamera(411,tempFile.getName());
                }
                break;
            case 412:
                if (resultCode==RESULT_OK){
                    waitingDialog.show();
                    handleCamera(412,tempFile.getName());
                }
                break;
            case 413:
                if (resultCode==RESULT_OK){
                    waitingDialog.show();
                    handleCamera(413,tempFile.getName());
                }
                break;
        }
    }
//    拍照调用
    private void handleCamera(int code,String tmpname){
        File file=new File(Urls.IMGPATH+tmpname);
        if (!file.exists()){
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
            waitingDialog.dismiss();
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(Urls.IMGPATH+tmpname);
        switch (code){
            case 411:
                findViewById(R.id.tv1_investauthen2).setVisibility(View.INVISIBLE);
                iv1.setImageBitmap(bitmap);
                uploadOss(411,tmpname,Urls.IMGPATH+tmpname);
                break;
            case 412:
                findViewById(R.id.tv2_investauthen2).setVisibility(View.INVISIBLE);
                iv2.setImageBitmap(bitmap);
                uploadOss(412,tmpname,Urls.IMGPATH+tmpname);
                break;
            case 413:
                findViewById(R.id.tv3_investauthen2).setVisibility(View.INVISIBLE);
                iv3.setImageBitmap(bitmap);
                uploadOss(413,tmpname,Urls.IMGPATH+tmpname);
                break;
        }
    }
//    相册选择调用
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
            case 401:
                findViewById(R.id.tv1_investauthen2).setVisibility(View.INVISIBLE);
                iv1.setImageBitmap(bitmap);
                uploadOss(401,name,path);
                break;
            case 402:
                findViewById(R.id.tv2_investauthen2).setVisibility(View.INVISIBLE);
                iv2.setImageBitmap(bitmap);
                uploadOss(402,name,path);
                break;
            case 403:
                findViewById(R.id.tv3_investauthen2).setVisibility(View.INVISIBLE);
                iv3.setImageBitmap(bitmap);
                uploadOss(403,name,path);
                break;
        }
    }
    //     阿里云上传文件
    private void uploadOss(int code,String name,String path){
        String s = SharedPFUtils.getParam(this,"usercode","")+ File.separator+name;
// 构造上传请求
        PutObjectRequest put = new PutObjectRequest("star-1", s, path);
        Log.e("=============name", ""+s);

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
                Log.e("=============PutObject", "UploadSuccess");
                switch (code){
                    case 401:
                        waitingDialog.dismiss();
                        names[0]=s;
                        break;
                    case 402:
                        waitingDialog.dismiss();
                        names[1]=s;
                        break;
                    case 403:
                        waitingDialog.dismiss();
                        names[2]=s;
                        break;
                    case 411:
                        waitingDialog.dismiss();
                        names[0]=s;
                        break;
                    case 412:
                        waitingDialog.dismiss();
                        names[1]=s;
                        break;
                    case 413:
                        waitingDialog.dismiss();
                        names[2]=s;
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

    private void request(){
        OkGo.post(Urls.addInvestor)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))
                .params("name", name)
                .params("mobile", phone)
                .params("investorHistory",intro)
                .params("cardImg1", names[0])
                .params("cardImg2",names[1])
                .params("personCardImg", names[2])
                .params("type", "3")
                .params("userImg", photo)
                .params("mail", email)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===========", "onSuccess: "+s);
//                        然后进入 粉丝状态的界面
                        CommenBean commenBean = new Gson().fromJson(s, CommenBean.class);
                        if ("请求成功".equals(commenBean.getMessage())){
                            startActivity(new Intent(InvestAuthen2Activity.this, MainActivity.class));
                            SharedPFUtils.setParam(InvestAuthen2Activity.this,"status",commenBean.getState());
                            SharedPFUtils.setParam(InvestAuthen2Activity.this,"identity",4);
                            finish();
                        }
                    }
                });
    }
}
