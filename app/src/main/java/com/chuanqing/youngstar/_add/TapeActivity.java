package com.chuanqing.youngstar._add;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
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
import com.chuanqing.youngstar.tools.CircleProgressBar;
import com.chuanqing.youngstar.tools.ToastUtils;

import java.io.File;
import java.io.IOException;
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
//                count=0;
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
                    intent.putExtra("path",recordFile);
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

    // 录音类
    public MediaRecorder mediaRecorder;
    // 以文件的形式保存
    public File recordFile;
//    private RecordPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tape);
        ButterKnife.bind(this);
        try {
            Log.e(TAG, "onCreate: 根目录"+Environment.getExternalStorageDirectory().getCanonicalFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        recordFile = new File(Environment.getExternalStorageDirectory(), "kk.amr");
//        player = new RecordPlayer(TapeActivity.this);
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
//                    Log.e(TAG, "onCheckedChanged: "+recordFile.exists());
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
                if (recordFile==null){
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
                    intent.putExtra("path",recordFile);
                    startActivity(intent);
                }
            }
        });
    }
    /**
     * 录制开始
     */
    private void startRecording() {
        if (mediaRecorder==null){
            mediaRecorder = new MediaRecorder();
        }

        if (!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(TapeActivity.this, "SD卡不存在，请插入SD卡！", Toast.LENGTH_LONG).show();
            return;
        }
        try
        {
            // 创建保存录音的音频文件
            recordFile = new File(Urls.AUDIOPATH + "ss.amr");
            recordFile.createNewFile();
            if (!recordFile.getParentFile().exists()){
                recordFile.getParentFile().mkdirs();
                recordFile = new File(Urls.AUDIOPATH + "ss.amr");
                recordFile.createNewFile();
            }
            mediaRecorder = new MediaRecorder();
            // 设置录音的声音来源
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 设置录制的声音的输出格式（必须在设置声音编码格式之前设置）
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);
            // 设置声音编码的格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
            mediaRecorder.setOutputFile(recordFile.getAbsolutePath());
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
     * 录制完毕
     */
    private void stopRecording() {
        if (recordFile != null) {
            if (mediaRecorder!=null){
                mediaRecorder.reset();
                mediaRecorder.release();
                mediaRecorder=null;
            }
            circleProgressBar.setProgress(0); // 使用数字过渡动画
            tv_miao.setText(""+0);
            count=0;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRecording();
    }
}
