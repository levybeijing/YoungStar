package com.chuanqing.youngstar.login._student;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageAuthenActivity extends BaseActivity implements View.OnClickListener {

    private Student student;
    private LinearLayout lable;
    private static final int FORLABLE=210;
    private RelativeLayout rl1;
    private RelativeLayout rl2;
    private RelativeLayout rl3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageauthen);
        student = getIntent().getParcelableExtra("student");
        initView();
    }

    private void initView() {

        lable = findViewById(R.id.ll_container_imgauthen);

        findViewById(R.id.rl_imgauthen).setOnClickListener(this);

        rl1 = findViewById(R.id.rl1_imgauthen);
        rl1.setOnClickListener(this);
        rl2 = findViewById(R.id.rl2_imgauthen);
        rl2.setOnClickListener(this);
        rl3 = findViewById(R.id.rl3_imgauthen);
        rl3.setOnClickListener(this);

        TextView ok=findViewById(R.id.tv_ok_imgthen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_imgauthen:
                Intent intent = new Intent(ImageAuthenActivity.this,LableActivity.class);
                startActivityForResult(intent,FORLABLE);
                break;
            case R.id.rl1_imgauthen:
                Intent intent2 = new Intent(Intent.ACTION_PICK);
                intent2.setType("image/*");
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
        }
    }
    private String mFile;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        switch (requestCode){
            case 202:
                rl1.removeAllViews();
                try {
                    Drawable stream = Drawable.createFromStream(new URL(uri.getPath()).openStream(), "image.jpg");
                    rl1.setBackground(stream);
                } catch (IOException e) {
                    e.printStackTrace();
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
