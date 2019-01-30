package com.chuanqing.youngstar._mine.student;

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
import android.util.DisplayMetrics;
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
import com.chuanqing.youngstar.login._student.LableActivity;
import com.chuanqing.youngstar.login._student.LablesBean;
import com.chuanqing.youngstar.mybean.FragCareCBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ChangeVerisActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_phone;
    private EditText et_school;
    private EditText et_major;
    private EditText et_snum;
    private EditText et_inum;
    private List<String> imglist=new ArrayList<>();
    private String lable;
    private TextView tv_lablenum;
    private LinearLayout container;
    private float density;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changeveris);
        imglist.add(null);
        imglist.add(null);
        imglist.add(null);
//
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = dm.density;
        initView();
    }

    private void initView() {
        iv1 = findViewById(R.id.iv1_changeveris);
        iv1.setOnClickListener(this);
        iv2 = findViewById(R.id.iv2_changeveris);
        iv2.setOnClickListener(this);
        iv3 = findViewById(R.id.iv3_changeveris);
        iv3.setOnClickListener(this);

        et_name = findViewById(R.id.et_name_changeveris);
        et_phone = findViewById(R.id.et_phone_changeveris);
        et_school = findViewById(R.id.et_school_changeveris);
        et_major = findViewById(R.id.et_major_changeveris);
        et_snum = findViewById(R.id.et_snum_changeveris);
        et_inum = findViewById(R.id.et_inum_changeveris);

        tv_lablenum = findViewById(R.id.tv_lablesnum_changeveris);

        findViewById(R.id.tv_ok_changeveris).setOnClickListener(this);
        container = findViewById(R.id.ll_lables_changeveris);
        container.setOnClickListener(this);

        findViewById(R.id.iv_back_changeveris).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1_changeveris:
                Intent intent1 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, 500);
                break;
            case R.id.iv2_changeveris:
                Intent intent2 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 501);
                break;
            case R.id.iv3_changeveris:
                Intent intent3 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent3, 502);
                break;
            case R.id.tv_ok_changeveris:
                for (int i = 0; i < 3; i++) {
                    if (imglist.get(i)==null){
                        Toast.makeText(this, "图片正在上传,请稍后", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                String name = et_name.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                String school = et_school.getText().toString().trim();
                String snum = et_snum.getText().toString().trim();
                String major = et_major.getText().toString().trim();
                String inum = et_inum.getText().toString().trim();

                OkGo.post(Urls.getUserConcernCompany)//
                        .tag(this)//
                        .params("userCode", (String) SharedPFUtils.getParam(this,"usercode",""))//文件名
                        .params("name", name)//墙的ID
                        .params("mobile", phone)//缩略图 省略>?
                        .params("school", school)//缩略图 省略>?
                        .params("major", major)//缩略图 省略>?
                        .params("studentNo", snum)//缩略图 省略>?
                        .params("cardId", inum)//缩略图 省略>?
                        .params("cardImg1", imglist.get(0))//缩略图 省略>?
                        .params("cardImg2", imglist.get(1) )//缩略图 省略>?
                        .params("studentCardImg", imglist.get(2) )//缩略图 省略>?
                        .params("label", lable)//缩略图 省略>?
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                Log.e("=============", "getUserConcernCompany"+s);
                            }
                        });
                break;
            case R.id.iv_back_changeveris:
                finish();
                break;
            case R.id.ll_lables_changeveris:
                Intent intent = new Intent(this, LableActivity.class);
                startActivityForResult(intent,500);
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
                case 500:
                    if (resultCode==RESULT_OK){
                        handleImg(500,data);
                    }
                    break;
                case 501:
                    if (resultCode==RESULT_OK){
                        handleImg(501,data);
                    }
                    break;
                case 502:
                    if (resultCode==RESULT_OK){
                        handleImg(502,data);
                    }
                    break;
                case 503:
                    if (resultCode!=RESULT_OK){
                        break;
                    }
                    container.removeAllViews();
                    String lable1 = data.getStringExtra("lable1");
                    String lable2 = data.getStringExtra("lable2");
                    if (lable1 !=null&&lable1.length()!=0){
                        TextView tv1=new TextView(this);
                        tv1.setGravity(Gravity.CENTER);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                (int) (60*density), (int) (30*density));
                        tv1.setLayoutParams(lp);
                        tv1.setText(lable1);
                        tv1.setTextColor(Color.parseColor("#FFFFFF"));
                        tv1.setBackground(getResources().getDrawable(R.mipmap.bg_red,null));
                        container.addView(tv1);
                        lable=lable1;
                    }
                    if (lable2 !=null&&lable2.length()!=0){
                        TextView tv2=new TextView(this);
                        tv2.setGravity(Gravity.CENTER);
                        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                                (int) (60*density), (int) (30*density));
                        tv2.setLayoutParams(lp2);
                        tv2.setText(lable2);
                        tv2.setTextColor(Color.parseColor("#FFFFFF"));
                        tv2.setBackground(getResources().getDrawable(R.mipmap.bg_red,null));
                        container.addView(tv2);
                        lable+=lable2;
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
//
}
