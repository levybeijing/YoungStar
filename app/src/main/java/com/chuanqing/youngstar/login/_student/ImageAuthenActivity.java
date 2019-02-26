package com.chuanqing.youngstar.login._student;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
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
import com.chuanqing.youngstar.login.bean.CommenBean;
import com.chuanqing.youngstar.login.login.LoginActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ImageAuthenActivity extends BaseActivity implements View.OnClickListener {

    private Student student;
    private LinearLayout ll_lable;
    private static final int FORLABLE=210;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private ImageView iv1, iv2, iv3;
    private String[] names=new String[3];
    private boolean havelable=false;
    private String lable;
    private float density;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageauthen);
//
        student = getIntent().getParcelableExtra("student");
//
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        density = dm.density;
        initView();
    }

    private void initView() {

        ll_lable = findViewById(R.id.ll_container_imgauthen);

        findViewById(R.id.rl_imgauthen).setOnClickListener(this);

        rl1 = findViewById(R.id.rl1_imgauthen);
        rl1.setOnClickListener(this);
        iv1 = findViewById(R.id.iv1_imgauthen);

        rl2 = findViewById(R.id.rl2_imgauthen);
        rl2.setOnClickListener(this);
        iv2 = findViewById(R.id.iv2_imgauthen);

        rl3 = findViewById(R.id.rl3_imgauthen);
        rl3.setOnClickListener(this);
        iv3 = findViewById(R.id.iv3_imgauthen);

        TextView ok=findViewById(R.id.tv_ok_imgthen);
        ok.setOnClickListener(this);

        findViewById(R.id.iv_back_imgauthen).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_imgauthen:
                Intent intent = new Intent(ImageAuthenActivity.this,LableActivity.class);
                startActivityForResult(intent,FORLABLE);
                break;
//
            case R.id.rl1_imgauthen:
                Intent intent2 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 202);
                break;
            case R.id.rl2_imgauthen:
                Intent intent3 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent3, 203);
                break;
            case R.id.rl3_imgauthen:
                Intent intent4 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent4, 204);
                break;
            case R.id.tv_ok_imgthen:
                if (!havelable){
                    Toast.makeText(this, "请添加标签", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < names.length; i++) {
                    if (names[i]==null){
                        Toast.makeText(this, "第"+(i+1)+"图片未上传", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                request();
                break;
            case R.id.iv_back_imgauthen:
                finish();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null){
            return;
        }
        if (resultCode==Activity.RESULT_OK){
            switch (requestCode){
                case 202:
                    if (resultCode==RESULT_OK){
                        handleImg(202,data);
                    }
                    break;
                case 203:
                    if (resultCode==RESULT_OK){
                        handleImg(203,data);
                    }
                    break;
                case 204:
                    if (resultCode==RESULT_OK){
                        handleImg(204,data);
                    }
                    break;
                case FORLABLE:
                    if (resultCode!=RESULT_OK){
                        break;
                    }
                    ll_lable.removeAllViews();
                    String lable1 = data.getStringExtra("lable1");
                    String lable2 = data.getStringExtra("lable2");
                    if (lable1 !=null&&lable1.length()!=0){
                        havelable=true;
                        TextView tv1=new TextView(this);
                        tv1.setGravity(Gravity.CENTER);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                (int) (60*density), (int) (30*density));
                        tv1.setLayoutParams(lp);
                        tv1.setText(lable1);
                        tv1.setTextColor(Color.parseColor("#FFFFFF"));
                        tv1.setBackground(getResources().getDrawable(R.mipmap.bg_red,null));
                        ll_lable.addView(tv1);
                        lable=lable1;
                    }
                    if (lable2 !=null&&lable2.length()!=0){
                        havelable=true;
                        TextView tv2=new TextView(this);
                        tv2.setGravity(Gravity.CENTER);
                        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                                (int) (60*density), (int) (30*density));
                        tv2.setLayoutParams(lp2);
                        tv2.setText(lable2);
                        tv2.setTextColor(Color.parseColor("#FFFFFF"));
                        tv2.setBackground(getResources().getDrawable(R.mipmap.bg_red,null));
                        ll_lable.addView(tv2);
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
            case 202:
                findViewById(R.id.tv1_imgauthen).setVisibility(View.INVISIBLE);
                iv1.setImageBitmap(bitmap);
                uploadOss(202,name,path);
                break;
            case 203:
                findViewById(R.id.tv2_imgauthen).setVisibility(View.INVISIBLE);
                iv2.setImageBitmap(bitmap);
                uploadOss(203,name,path);
                break;
            case 204:
                findViewById(R.id.tv3_imgauthen).setVisibility(View.INVISIBLE);
                iv3.setImageBitmap(bitmap);
                uploadOss(204,name,path);
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
        String s = SharedPFUtils.getParam(this,"usercode","")+File.separator+name;
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
                    case 202:
                        names[0]=name;
                        Log.e("===============", "=202");
                        break;
                    case 203:
                        names[1]=name;
                        Log.e("===============", "=203");
                        break;
                    case 204:
                        names[2]=name;
                        Log.e("===============", "=204");
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
    private void request(){
        OkGo.post(Urls.addStudent)//
                .tag(this)//
                .params("mobile", student.getPhone())
                .params("userCode", (String)SharedPFUtils.getParam(this,"usercode",""))
                .params("sex", student.getSex())
                .params("name", student.getName())
                .params("school", student.getSchool())
                .params("major", student.getMajor())
                .params("studentNo", student.getStudentNo())
                .params("cardId", student.getCardId())
                .params("cardImg1", names[0])
                .params("cardImg2", names[1])
                .params("studentCardImg", names[2])
                .params("type", "1")
                .params("label", lable)
                .params("userImg", student.getImgpath())
                .params("mail", student.getEmail())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===========", "onSuccess: "+s);
//                        然后进入 粉丝状态的界面
                        CommenBean commenBean = new Gson().fromJson(s, CommenBean.class);
                        if ("请求成功".equals(commenBean.getMessage())){
                            startActivity(new Intent(ImageAuthenActivity.this, MainActivity.class));
                            SharedPFUtils.setParam(ImageAuthenActivity.this,"identity",4);
                            SharedPFUtils.setParam(ImageAuthenActivity.this,"checkdata",true);
                            SharedPFUtils.setParam(ImageAuthenActivity.this,"status",commenBean.getState());
                        }else{
                            Toast.makeText(ImageAuthenActivity.this, ""+commenBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
