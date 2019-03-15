package com.chuanqing.youngstar.login._company;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.chuanqing.youngstar.tools.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.chuanqing.youngstar.MyApp.oss;

public class Authen1Activity extends BaseActivity implements View.OnClickListener {
    private int chooseindex=-1;
    private List<ImageView> list=new ArrayList<>();
    private EditText et_com;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_intro;
    private String photo;
    private Intent intent;
    private ProgressDialog waitingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comauthen1);
        waitingDialog = new ProgressDialog(Authen1Activity.this);
        waitingDialog.setTitle("图片上传");
        waitingDialog.setMessage("上传中...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_comauthen1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_com = findViewById(R.id.et_cpyname_comauthen1);
        et_name = findViewById(R.id.et_username_comauthen1);
        et_phone = findViewById(R.id.et_phone_comauthen1);
        et_email = findViewById(R.id.et_email_comauthen1);
        et_intro = findViewById(R.id.et_intro_comauthen1);

        findViewById(R.id.tv_next_comauthen1).setOnClickListener(this);

        ImageView photo1 = findViewById(R.id.iv1_comauthen1);
        ImageView photo2 = findViewById(R.id.iv2_comauthen1);
        ImageView photo3 = findViewById(R.id.iv3_comauthen1);
        ImageView photo4 = findViewById(R.id.iv4_comauthen1);
        ImageView photo5 = findViewById(R.id.iv5_comauthen1);
        ImageView photo6 = findViewById(R.id.iv6_comauthen1);
        ImageView photo7 = findViewById(R.id.iv7_comauthen1);
        ImageView photo8 = findViewById(R.id.iv8_comauthen1);

        list.add(photo1);
        list.add(photo2);
        list.add(photo3);
        list.add(photo4);
        list.add(photo5);
        list.add(photo6);
        list.add(photo7);
        list.add(photo8);

        findViewById(R.id.iv_1_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_2_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_3_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_4_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_5_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_6_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_7_comauthen1).setOnClickListener(this);
        findViewById(R.id.iv_8_comauthen1).setOnClickListener(this);

        findViewById(R.id.tv_next_comauthen1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_1_comauthen1:
                checked(1);
                break;
            case R.id.iv_2_comauthen1:
                checked(2);
                break;
            case R.id.iv_3_comauthen1:
                checked(3);
                break;
            case R.id.iv_4_comauthen1:
                checked(4);
                break;
            case R.id.iv_5_comauthen1:
                checked(5);
                break;
            case R.id.iv_6_comauthen1:
                checked(6);
                break;
            case R.id.iv_7_comauthen1:
                checked(7);
                break;
            case R.id.iv_8_comauthen1:
                checked(8);
                break;
            case R.id.tv_next_comauthen1:
                if (chooseindex<0){
                    Toast.makeText(this, "请选择头像", Toast.LENGTH_SHORT).show();
                    return;
                }
                String com = et_com.getText().toString().trim();
                if (com==null||com.length()==0){
                    Toast.makeText(this, "公司名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = et_name.getText().toString().trim();
                if (name==null||name.length()==0){
                    Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = et_phone.getText().toString().trim();
                if (phone==null||phone.length()==0){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtil.isPhoneNumber(phone)){
                    Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = et_email.getText().toString().trim();
                if (email==null||email.length()==0){
                    Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtil.isEmail(email)){
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                String intro = et_intro.getText().toString().trim();
                if (intro==null||intro.length()==0){
                    Toast.makeText(this, "介绍不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent = new Intent(this, Authen2Activity.class);
                intent.putExtra("name",name);
                intent.putExtra("com",com);
                intent.putExtra("phone",phone);
                intent.putExtra("email",email);
                intent.putExtra("intro",intro);

                uploadPhoto();
                break;
        }
    }
    Bitmap bitmap;
    private void uploadPhoto() {
        Log.e("==============", "chooseindex: "+chooseindex );
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        switch (chooseindex){
            case 1:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.company1,null);
                break;
            case 2:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.company2,null);
                break;
            case 3:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.company3,null);
                break;
            case 4:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.company4,null);
                break;
            case 5:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.company5,null);
                break;
            case 6:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.company6,null);
                break;
            case 7:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.company7,null);
                break;
            case 8:
                bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.company8,null);
//                bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.company8)).getBitmap();
                break;
        }
        photo = SharedPFUtils.getParam(this,"usercode","")+ File.separator+ StringUtil.getRandomName(8)+".png";
        Log.e("==========", "uploadPhoto: "+photo);

//        bitmap = BitmapFactory.decodeResource(getResources(), res,null);

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        // 构造上传请求。
        PutObjectRequest put = new PutObjectRequest("star-1", photo, bytes);
        try {
            PutObjectResult putResult = oss.putObject(put);
            waitingDialog.dismiss();
            intent.putExtra("photo",photo);
            startActivity(intent);

            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());
        } catch (ClientException e) {
            // 本地异常，如网络异常等。
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常。
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
        }
    }

    private void checked(int i){
        chooseindex=i;
        i=i-1;
        for (int j = 0; j < list.size(); j++) {
            if (i==j){
                list.get(j).setVisibility(View.VISIBLE);
            }else{
                list.get(j).setVisibility(View.INVISIBLE);
            }
        }
    }
//    private void checked(int i){
//
//        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(getpath(i));
//        Bitmap bitmap = drawable.getBitmap();
//
//        synchronized (this){
//        photo = SharedPFUtils.getParam(this, "usercode", "") + File.separator + StringUtil.getRandomName(8)+".png";
//        File file=new File(Urls.IMAGEURL+File.separator+name);
//        if (!file.exists()){
//            file.getParentFile().mkdirs();
//        }
//        try {
//            FileOutputStream out=new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.PNG,100, out);
//            out.close();
//            uploadOss(name,file.getAbsolutePath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//        i=i-1;
//        chooseindex=i;
//        for (int j = 0; j < list.size(); j++) {
//            if (i==j){
//                list.get(j).setVisibility(View.VISIBLE);
//            }else{
//                list.get(j).setVisibility(View.INVISIBLE);
//            }
//        }
//    }
//    private int getpath(int i){
//        switch (i){
//            case 1:
//                return R.mipmap.company1;
//            case 2:
//                return R.mipmap.company2;
//            case 3:
//                return R.mipmap.company3;
//            case 4:
//                return R.mipmap.company4;
//            case 5:
//                return R.mipmap.company5;
//            case 6:
//                return R.mipmap.company6;
//            case 7:
//                return R.mipmap.company7;
//            case 8:
//                return R.mipmap.company8;
//            default:
//                return 0;
//        }
//    }
//    //     阿里云上传文件
//    private void uploadOss(String name,String path){
//
//// 构造上传请求
//        PutObjectRequest put = new PutObjectRequest("star-1", name, path);
//        Log.e("=============name", "uploadOss:"+name);
//
//
//// 异步上传时可以设置进度回调
//        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
//            @Override
//            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
//            }
//        });
//
//        OSSAsyncTask task = MyApp.oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
//            @Override
//            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
//                Log.e("=============PutObject", "UploadSuccess");
//            }
//
//            @Override
//            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                // 请求异常
//                if (clientExcepion != null) {
//                    // 本地异常如网络异常等
//                    clientExcepion.printStackTrace();
//                }
//                if (serviceException != null) {
//                    // 服务异常
//                    Log.e("ErrorCode", serviceException.getErrorCode());
//                    Log.e("RequestId", serviceException.getRequestId());
//                    Log.e("HostId", serviceException.getHostId());
//                    Log.e("RawMessage", serviceException.getRawMessage());
//                }
//            }
//        });
//    }

}
