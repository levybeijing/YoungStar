package com.chuanqing.youngstar._add;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.android.tu.loadingdialog.LoadingDailog;
import com.chuanqing.youngstar.MyApplication;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.mybean.CommonBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static com.chuanqing.youngstar.MainActivity.usercodes;

/**
 * 发布音频详情界面
 */
public class TapeMoreActivity extends AppCompatActivity {
    private RecordPlayer player;
    private File recordFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tape_more);
        ButterKnife.bind(this);

        String path = getIntent().getStringExtra("path");
//        Log.e("================", "发布音频详情界面path: "+path);
        recordFile = new File(path);

        player = new RecordPlayer(TapeMoreActivity.this);
        MyApplication.getApplication().addActivity(TapeMoreActivity.this);
        initView();
        setTtitle();
    }
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
                TapeMoreActivity.this.finish();
            }
        });
        right_tv.setText("发布");
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText().toString())){
                    ToastUtils.shortToast("请输入标题");
                }else {
                    if (TextUtils.isEmpty(content.getText().toString())){
                        ToastUtils.shortToast("请输入作品");
                    }else {
                        beginupload(recordFile);
                    }
                }
            }
        });
    }

    EditText title;
    EditText content;
    ImageView img_yinpin;
    private void initView(){
        title = findViewById(R.id.et_title_publish);
        content = findViewById(R.id.et_content_publish);
        TextView titleNum = findViewById(R.id.tv_titlenum_publish);
        TextView contentNum = findViewById(R.id.tv_contentnum_publish);
        img_yinpin = findViewById(R.id.tape_more_yinpin);
        //标题
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleNum.setText("("+title.getText().toString().length()+"/20）");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //详情
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contentNum.setText("("+content.getText().toString().length()+"/200）");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //播放音频
        img_yinpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRecording();
            }
        });
    }

    private void playRecording() {
//        Log.e(TAG, "playRecording: 播放"+recordFile );
        player.playRecordFile(recordFile);
    }


    private static final String TAG = "TapeMoreActivity";
    public void beginupload(File path) {
        showMyDialog();
//        Log.e(TAG, "beginupload: 路径"+path );
//        https://star-1.oss-cn-beijing.aliyuncs.com
        final String endpoint = "oss-cn-beijing.aliyuncs.com";
        final String startpoint = "star-1";
        //     明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAI8ygujYgDvLJ9", "nLrO1bpn9IOpEu0tt0zyAaChc22j0c");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        // 构造上传请求
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        final String objectname =df.format(new Date())+ ".mp4";

        PutObjectRequest put = new PutObjectRequest(startpoint, objectname,path+"");
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {

//                arrayList.remove(i);
                Log.e("上传结果","https://star-1.oss-cn-beijing.aliyuncs.com/"+objectname);
                upinfo(objectname);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                ToastUtils.shortToast("上传失败导致信息无法发布");
                dissDialog();
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    ToastUtils.shortToast("上传失败导致信息无法发布");
                }
                if (serviceException != null) {
                }
            }
        });

    }

    /**
     * 上传请求接口
     */
    private void upinfo(String path){
        OkGo.post(Api.updongtai)
                .tag(this)
                .params("userCode",usercodes)
                .params("title",title.getText().toString())
                .params("blogDetail",content.getText().toString())
                .params("mediaUrl",path)
                .params("type",1)
                .params("mediaType",3)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(s,CommonBean.class);
                        if (commonBean.getState()==1){
                            dissDialog();
                            ToastUtils.shortToast("上传成功");
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MyApplication.getApplication().exit();
                                }
                            },1000);
                        }else {
                            ToastUtils.shortToast(commonBean.getMessage());
                        }
                    }
                });
    }
    private LoadingDailog loadBuilder;
    private void showMyDialog(){
        if (loadBuilder==null){
            loadBuilder=new LoadingDailog.Builder(TapeMoreActivity.this)
                    .setMessage("信息上传中...")
                    .setCancelable(false)
                    .setCancelOutside(false).create();
            loadBuilder.show();
        }

    }
    private void dissDialog(){
        if (loadBuilder!=null){
            loadBuilder.dismiss();
        }
    }
}
