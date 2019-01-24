package com.chuanqing.youngstar._add;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chuanqing.youngstar.MyApplication;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.tools.CircleProgressBar;
import com.chuanqing.youngstar.tools.ToastUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 录音功能
 */
public class TapeZuopinActivity extends BaseActivity {
    private static final String TAG = "TapeActivity";
    private boolean lock=true;
    private int count=0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1&&count<=100){
                if (count==100){
                    if (tv_tittle.getText().toString().equals("暂停")){
                        //先暂停，然后跳转页面
                        stopRecording();
                        lock = false;
                        tv_tittle.setText("开始");

                        MyApplication.getApplication().addActivity(TapeZuopinActivity.this);
                        Intent intent = new Intent(TapeZuopinActivity.this,TapeMoreActivity.class);
                        intent.putExtra("path",recordFile_zuopin);
                        startActivity(intent);
                    }
                }
                tv_miao.setText(""+count++);
                //动画进度条展示
                circleProgressBar.setFirstColor(white);
                circleProgressBar.setColorArray(colors); //觉得进度条颜色丑的，这里可以自行传入一个颜色渐变数组。
                circleProgressBar.setCircleWidth(6);
                circleProgressBar.setProgress(count); // 使用数字过渡动画
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

    // 录音类
    private MediaRecorder mediaRecorder;
    // 以文件的形式保存
    public static File recordFile_zuopin;
    private RecordPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tape);
        ButterKnife.bind(this);
        try {
            Log.e(TAG, "onCreate: 根目录"+Environment
                    .getExternalStorageDirectory()
                    .getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        recordFile = new File(Environment.getExternalStorageDirectory(), "kk.amr");
        player = new RecordPlayer(TapeZuopinActivity.this);
        setTtitle();
        setinfo();

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
                TapeZuopinActivity.this.finish();
            }
        });
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.shortToast("完成");
                if (recordFile_zuopin==null){
                    ToastUtils.shortToast("请录制音频");
                }else {
                    if (tv_tittle.getText().toString().equals("暂停")){
                        tv_tittle.setText("开始");
                        lock = false;
                        //先暂停录制后再跳转
                            stopRecording();
                    }
                    TapeZuopinActivity.this.finish();
                }

            }
        });
    }

    /**
     * 写入信息
     */
    @BindView(R.id.tape_star)
    ImageView img_star;
    @BindView(R.id.tape_title)
    TextView tv_tittle;

    private void setinfo(){
        img_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_tittle.getText().toString().equals("开始")){
                    startRecording();
                    tv_tittle.setText("暂停");
                    lock = true;
                    new Thread(runnable).start();
                }else {
                    stopRecording();
                    tv_tittle.setText("开始");
                    lock = false;

                }

            }
        });
    }
;

    /**
     * 录制开始
     */
    private void startRecording() {
        if (mediaRecorder==null){
            mediaRecorder = new MediaRecorder();
        }

        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(TapeZuopinActivity.this
                    , "SD卡不存在，请插入SD卡！"
                    , Toast.LENGTH_LONG)
                    .show();
            return;
        }
        try
        {
            // 创建保存录音的音频文件
            recordFile_zuopin = new File(Environment
                    .getExternalStorageDirectory()
                    .getCanonicalFile() + "/sound.amr");
            mediaRecorder = new MediaRecorder();
            // 设置录音的声音来源
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置录制的声音的输出格式（必须在设置声音编码格式之前设置）
            mediaRecorder.setOutputFormat(MediaRecorder
                    .OutputFormat.THREE_GPP);
            // 设置声音编码的格式
            mediaRecorder.setAudioEncoder(MediaRecorder
                    .AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(recordFile_zuopin.getAbsolutePath());
            mediaRecorder.prepare();
            // 开始录音
            mediaRecorder.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 录制暂停
     */
    private void zanting(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mediaRecorder.pause();
        }
    }

    /**
     * 录制完毕
     */
    private void stopRecording() {
        if (recordFile_zuopin != null) {
//            ToastUtils.shortToast("录制完毕");
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder=null;
        }
    }
    private void playRecording() {
//        Log.e(TAG, "playRecording: 播放"+recordFile );
        player.playRecordFile(recordFile_zuopin);
    }
    private void pauseplayer() {
        player.pausePalyer();
    }
    private void stopplayer() {
        // TODO Auto-generated method stub
        player.stopPalyer();
    }

}
