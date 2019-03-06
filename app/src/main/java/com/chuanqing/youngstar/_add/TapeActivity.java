package com.chuanqing.youngstar._add;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chuanqing.youngstar.MyApplication;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tomp3.AudioRecorder;
import com.chuanqing.youngstar.tools.CircleProgressBar;
import com.chuanqing.youngstar.tools.StringUtil;
import com.chuanqing.youngstar.tools.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 录音功能
 */
public class TapeActivity extends BaseActivity {
    private static final String TAG = "TapeActivity";
    private boolean lock=true;
    private int count=0;

    @BindView(R.id.rb_tape_star)
    CheckBox rb_toggle;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!lock){
                return;
            }
            if (msg.what==1&&count<=100){
                if (count==100){
                    //先暂停，然后跳转页面
                    stopRecording();
                    lock = false;
                    rb_toggle.setText("开始");
                    rb_toggle.setChecked(false);
                    MyApplication.getApplication().addActivity(TapeActivity.this);
                    Intent intent = new Intent(TapeActivity.this,TapeMoreActivity.class);
                    intent.putExtra("path",Urls.AUDIOPATH+name);
                    startActivity(intent);
                }else{
                    tv_miao.setText(++count+"");
                    //动画进度条展示
                    circleProgressBar.setFirstColor(white);
                    circleProgressBar.setColorArray(colors); //觉得进度条颜色丑的，这里可以自行传入一个颜色渐变数组。
                    circleProgressBar.setCircleWidth(6);
                    circleProgressBar.setProgress(count); // 使用数字过渡动画
                }
            }
        }
    };
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while(lock){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg=handler.obtainMessage();
                msg.what=1;
                handler.sendMessage(msg);
            }
        }
    };

    private AudioRecorder recorder;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tape);
        ButterKnife.bind(this);

        recorder = new AudioRecorder();

        setTtitle();
        rb_toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    startRecording();
                    rb_toggle.setText("完成");
                    lock = true;
                    new Thread(runnable).start();
                }else{
//                    录制装填
                    stopRecording();
                    rb_toggle.setText("开始");
                    lock = false;
                }
            }
        });
    }

    /**
     * 写入录音
     */
    @BindView(R.id.tape_CircleProgressBar)
    CircleProgressBar circleProgressBar; // 自定义的进度条
    private int[] colors = new int[] { Color.parseColor("#FB1F72"), Color.parseColor("#F56250") };
    int white = 0x00000000;
    @BindView(R.id.tape_miao)
    TextView tv_miao;

    /**
     * 写入title名字
     */
    @BindView(R.id.common_left_img)
    ImageView left_img;
    @BindView(R.id.common_center_title)
    TextView tv_title;
    @BindView(R.id.common_rigth_img)
    TextView right_tv;
    private void setTtitle(){
        tv_title.setText("发布作品");
        left_img.setVisibility(View.VISIBLE);
        //返回
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file=new File(Urls.AUDIOPATH+name+".mp3");
                if (file==null){
                    ToastUtils.shortToast("请录制音频");
                }else {
                    if (rb_toggle.isChecked()){
                        rb_toggle.setText("开始");
                        lock = false;
                        //先暂停录制后再跳转
                        stopRecording();
                    }
                    MyApplication.getApplication().addActivity(TapeActivity.this);
                    Intent intent = new Intent(TapeActivity.this,TapeMoreActivity.class);
                    intent.putExtra("path",Urls.AUDIOPATH+name);
                    startActivity(intent);
                }
            }
        });
    }
    /**
     * 录制开始
     */
    private void startRecording() {
        if (recorder==null){
            return;
        }
        name="star"+System.currentTimeMillis();
        recorder.createDefaultAudio(name);
        recorder.startRecord();
    }

    /**
     * 录制完毕
     */
    private void stopRecording() {
        if (recorder==null){
            return;
        }
        recorder.stopRecord();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (recorder!=null){
            recorder.stopRecord();
        }
    }
}
