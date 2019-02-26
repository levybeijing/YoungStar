package com.chuanqing.youngstar.login._invest;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.chuanqing.youngstar.login._company.Authen2Activity;
import com.chuanqing.youngstar.login._student.ImageAuthenActivity;
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

        waitingDialog = new ProgressDialog(InvestAuthen2Activity.this);
        waitingDialog.setTitle("图片上传");
        waitingDialog.setMessage("上传中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);

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
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1_investauthen2:
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 401);
                break;
            case R.id.iv2_investauthen2:
                Intent intent2 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 402);
                break;
            case R.id.iv3_investauthen2:
                Intent intent3 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent3, 403);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null){
            return;
        }
        if (resultCode== Activity.RESULT_OK){
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
                        Log.e("===============", "=401");
                        names[0]=s;
                        break;
                    case 402:
                        waitingDialog.dismiss();
                        names[1]=s;
                        Log.e("===============", "=402");
                        break;
                    case 403:
                        waitingDialog.dismiss();
                        names[2]=s;
                        Log.e("===============", "=403");
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
                        Log.e("===========", "onSuccess: "+photo);
                        Log.e("===========", "onSuccess: "+names[0]);
                        Log.e("===========", "onSuccess: "+names[1]);
                        Log.e("===========", "onSuccess: "+names[2]);
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
