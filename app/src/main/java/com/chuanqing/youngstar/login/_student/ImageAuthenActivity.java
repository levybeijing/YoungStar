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

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.StringUtil;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageAuthenActivity extends BaseActivity implements View.OnClickListener {

    private Student student;
    private LinearLayout lable;
    private static final int FORLABLE=210;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;
    private ImageView iv1, iv2, iv3;
    private List<Boolean> isOk=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageauthen);

        student = getIntent().getParcelableExtra("student");
        isOk.add(false);
        isOk.add(false);
        isOk.add(false);
        initView();
    }

    private void initView() {

        lable = findViewById(R.id.ll_container_imgauthen);

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
//              开始上传文件
//                然后接口访问
                break;
        }
    }

    private String mFile;

    public  String getPath() {
        mFile = Urls.IMGPATH +StringUtil.getRandomName(8) + ".png";
        return mFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data==null){
            Log.e("========", " data=null");
            return;
        }
        if (resultCode==Activity.RESULT_OK){
            switch (requestCode){
                case 202:
                    if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                        String path;
                        try {
                            Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                            String[] filePathColumn = {MediaStore.Images.Media.DATA};
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                            cursor.moveToFirst();
                            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                            path = cursor.getString(columnIndex);  //获取照片路径
                            cursor.close();
                            Bitmap bitmap = BitmapFactory.decodeFile(path);
                            iv1.setImageBitmap(bitmap);
                        } catch (Exception e) {
                            // TODO Auto-generatedcatch block
                            e.printStackTrace();
                        }
                    }
                    break;

                case 203:

                    break;
                case 204:

                    break;
                case FORLABLE:
                    if (requestCode!=200){
                        break;
                    }
                    String lable1 = data.getStringExtra("lable1");
                    String lable2 = data.getStringExtra("lable2");
                    if (lable1==null||lable1.length()==0){
                        break;
                    }
                    TextView tv1=new TextView(this);
                    tv1.setText(lable1);
                    tv1.setBackground(getResources().getDrawable(R.mipmap.bg_red,null));
                    lable.addView(tv1);
                    if (lable2==null||lable2.length()==0){
                        break;
                    }
                    TextView tv2=new TextView(this);
                    tv2.setText(lable2);
                    tv2.setBackground(getResources().getDrawable(R.mipmap.bg_red,null));
                    lable.addView(tv2);
                    break;
            }
        }


    }

}
