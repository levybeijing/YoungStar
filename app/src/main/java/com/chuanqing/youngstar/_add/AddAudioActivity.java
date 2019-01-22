package com.chuanqing.youngstar._add;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.widget.WaveBallProgress;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * 录音
 */

public class AddAudioActivity extends BaseActivity implements View.OnClickListener {

    private ImageView start;
    private ImageView pause;
    private boolean isRecording=false;
    private int count=0;
    private MediaRecorder mediaRecorder;
    private boolean lock=true;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==300&&isRecording&&count<=90){
                tv_time.setText(++count);
            }else{
                mediaRecorder.stop();
//                网络访问操作
                isRecording=false;
            }
        }
    };
    private WaveBallProgress wave;
    private TextView tv_time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaudio);

        initView();
        new Thread(runnable).start();
    }
    private String fileName;
    private String filepath;
    private void initView() {
        start = findViewById(R.id.iv_start_addaudio);
        start.setOnClickListener(this);
        pause = findViewById(R.id.iv_pause_addaudio);
        pause.setOnClickListener(this);

        tv_time = findViewById(R.id.tv_time_addaudio);

        if (mediaRecorder==null){
            mediaRecorder=new MediaRecorder();
        }
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        fileName= DateFormat.format("yyyyMMdd_HHmmss", Calendar.getInstance(Locale.CHINA)) + ".m4a";

        File file=new File(Urls.AUDIOPATH);
        if (!file.exists()){
            file.mkdirs();
        }
        filepath=Urls.AUDIOPATH+fileName;
        mediaRecorder.setOutputFile(filepath);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            while(lock){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg=handler.obtainMessage();
                msg.what=300;
                handler.sendMessage(msg);
            }

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_start_addaudio:
                pause.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                isRecording=true;
                mediaRecorder.start();
                break;
            case R.id.iv_pause_addaudio:
                pause.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
                isRecording=false;
                mediaRecorder.pause();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lock=false;
    }
}
