package com.chuanqing.youngstar.login._student;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.util.Log;
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
import com.chuanqing.youngstar.MyApplication;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.chuanqing.youngstar.tools.StringUtil;
import com.chuanqing.youngstar.tools.UrlCollect;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
    private List<Boolean> isOk=new ArrayList<>();
    private boolean havelable=false;
    private List<String> listName=new ArrayList<>();
    private String lable;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageauthen);

        student = getIntent().getParcelableExtra("student");
        isOk.add(false);
        isOk.add(false);
        isOk.add(false);
        listName.add(null);
        listName.add(null);
        listName.add(null);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_imgauthen:
                Intent intent = new Intent(ImageAuthenActivity.this,LableActivity.class);
                startActivityForResult(intent,FORLABLE);
                break;
            case R.id.rl1_imgauthen:
                Intent intent2 = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent2, 202);
                break;
            case R.id.rl2_imgauthen:
                Intent intent3 = new Intent(Intent.ACTION_PICK);
                intent3.setType("image/*");
                startActivityForResult(intent3, 203);
                break;
            case R.id.rl3_imgauthen:
                Intent intent4 = new Intent(Intent.ACTION_PICK);
                intent4.setType("image/*");
                startActivityForResult(intent4, 204);
                break;
            case R.id.tv_ok_imgthen:
//              开始上传文件???????
//                然后接口访问{}
                if (!havelable){
                    Toast.makeText(this, "请添加标签", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < isOk.size(); i++) {
                    if (!isOk.get(i)){
                        return;
                    }
                }
                request();
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
                    handleImg(202,data);
                    break;
                case 203:
                    handleImg(203,data);
                    break;
                case 204:
                    handleImg(204,data);
                    break;
                case FORLABLE:
                    if (requestCode!=200){
                        break;
                    }
                    String lable1 = data.getStringExtra("lable1");
                    String lable2 = data.getStringExtra("lable2");
                    if (lable1 ==null|| lable1.length()==0){
                    }else{
                        havelable=true;
                        TextView tv1=new TextView(this);
                        tv1.setText(lable1);
                        tv1.setBackground(getResources().getDrawable(R.mipmap.bg_red,null));
                        ll_lable.addView(tv1);
                        lable=lable1;
                    }

                    if (lable2 ==null|| lable2.length()==0){
                    }else{
                        havelable=true;
                        TextView tv2=new TextView(this);
                        tv2.setText(lable2);
                        tv2.setBackground(getResources().getDrawable(R.mipmap.bg_red,null));
                        ll_lable.addView(tv2);
                        lable=lable+lable2;
                    }

                    break;
            }
        }


    }
    private void handleImg(int code,Intent data){
            String path;
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
                listName.set(0,name);
                break;
            case 203:
                findViewById(R.id.tv2_imgauthen).setVisibility(View.INVISIBLE);
                iv2.setImageBitmap(bitmap);
                listName.set(1,name);

                break;
            case 204:
                findViewById(R.id.tv3_imgauthen).setVisibility(View.INVISIBLE);
                iv3.setImageBitmap(bitmap);
                listName.set(2,name);

                break;
        }
//  进行操作

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

// 构造上传请求
        PutObjectRequest put = new PutObjectRequest("star-1", name, path);

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
                        isOk.set(0,true);
                        break;
                    case 203:
                        isOk.set(1,true);
                        break;
                    case 204:
                        isOk.set(2,true);
                        break;
//                    检验是否都上传完成

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
        switch (code){
            case 202:

                break;
            case 203:

                break;
            case 204:

                break;

        }
    }
//
    private void request(){
        OkGo.post(Urls.addStudent)//
                .tag(this)//
                .params("mobile", student.getPhone())
                .params("userCode", (int)SharedPFUtils.getParam(this,"usercode",-1))
                .params("sex", student.getSex())
                .params("name", student.getName())
                .params("school", student.getSchool())
                .params("major", student.getMajor())
                .params("studentNo", student.getStudentNo())
                .params("cardId", student.getCardId())
                .params("cardImg1", listName.get(0))
                .params("cardImg2", listName.get(1))
                .params("studentCardImg", listName.get(2))
                .params("type", "1")
                .params("label", lable)
                .params("userImg", student.getImgpath())
                .params("mail", student.getEmail())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("===========", "onSuccess: "+s);
                    }
                });
    }

}
