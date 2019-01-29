package com.chuanqing.youngstar._mine.company;

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
import android.widget.EditText;
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
import com.chuanqing.youngstar.MyApplication;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


public class ChangeVericActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_company;
    private EditText et_phone;
    private EditText et_intro;

    private List<String> imglist=new ArrayList<>();
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeveric);
        imglist.add(null);
        imglist.add(null);
        imglist.add(null);
        imglist.add(null);
        initView();
    }

    private void initView() {
        iv1 = findViewById(R.id.iv1_changeveric);
        iv1.setOnClickListener(this);
        iv2 = findViewById(R.id.iv2_changeveric);
        iv2.setOnClickListener(this);
        iv3 = findViewById(R.id.iv3_changeveric);
        iv3.setOnClickListener(this);
        iv4 = findViewById(R.id.iv4_changeveric);
        iv4.setOnClickListener(this);

        findViewById(R.id.tv_ok_changeveric).setOnClickListener(this);
        findViewById(R.id.iv_back_changeveric).setOnClickListener(this);

        et_name = findViewById(R.id.et_name_changeveric);
        et_company = findViewById(R.id.et_company_changeveric);
        et_phone = findViewById(R.id.et_phone_changeveric);
        et_intro = findViewById(R.id.et_intro_changeveric);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1_changeveric:
                Intent intent1 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 600);
                break;
            case R.id.iv2_changeveric:
                Intent intent2 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 601);
                break;
            case R.id.iv3_changeveric:
                Intent intent3 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent3, 602);
                break;
            case R.id.iv4_changeveric:
                Intent intent4 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent4, 603);
                break;
            case R.id.iv_back_changeveric:
                finish();
                break;
            case R.id.tv_ok_changeveric:
//

                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String company = et_company.getText().toString().trim();
                String intro = et_intro.getText().toString().trim();

                OkGo.post(Urls.getUserConcernCompany)//
                        .tag(this)//
                        .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                        .params("companyName", company)
                        .params("companyTel", phone)
                        .params("ownerName", name)
                        .params("companyDetail", intro)
                        .params("companyImg1", imglist.get(0))
                        .params("ownerImg2", imglist.get(1))
                        .params("ownerImg1", imglist.get(2))
                        .params("otherImg", imglist.get(3) )
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Log.e("=============", "getUserConcernCompany"+s);
                                Toast.makeText(ChangeVericActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                            }
                        });
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
                case 600:
                    if (resultCode==RESULT_OK){
                        handleImg(600,data);
                    }
                    break;
                case 601:
                    if (resultCode==RESULT_OK){
                        handleImg(601,data);
                    }
                    break;
                case 602:
                    if (resultCode==RESULT_OK){
                        handleImg(602,data);
                    }
                    break;
                case 603:
                    if (resultCode==RESULT_OK){
                        handleImg(603,data);
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
            case 500:
                iv1.setImageBitmap(bitmap);
                uploadOss(500,name,path);
                break;
            case 501:
                iv2.setImageBitmap(bitmap);
                uploadOss(501,name,path);
                break;
            case 502:
                iv3.setImageBitmap(bitmap);
                uploadOss(502,name,path);
                break;
        }
    }
    public  String getName(String path) {
        if (path!=null){
            String[] split = path.split("/");
            return split[split.length-1];
        }
        return null;
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
                    case 600:
                        imglist.remove(0);
                        imglist.add(0,name);
                        Log.e("===============", "=600");
                        break;
                    case 601:
                        imglist.remove(1);
                        imglist.add(1,name);
                        Log.e("===============", "=601");
                        break;
                    case 602:
                        imglist.remove(2);
                        imglist.add(2,name);
                        Log.e("===============", "=602");
                        break;
                    case 603:
                        imglist.remove(3);
                        imglist.add(3,name);
                        Log.e("===============", "=603");
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

}
