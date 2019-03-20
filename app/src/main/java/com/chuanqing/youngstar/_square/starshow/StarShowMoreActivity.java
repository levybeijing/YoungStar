package com.chuanqing.youngstar._square.starshow;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.MainActivity;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._home.UserDetailActivity;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.mybean.CommonBean;
import com.chuanqing.youngstar.mybean.StarShowMoreBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.MyDialog;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import okhttp3.Call;
import okhttp3.Response;

import static com.chuanqing.youngstar.MainActivity.usercodes;

public class StarShowMoreActivity extends BaseActivity {
    private static final String TAG = "StarShowMoreActivity";
    MediaPlayer mediaPlayer = new MediaPlayer();
    @BindView(R.id.ll_author_starshowmore)
    LinearLayout ll_author;
    private String id,hot;
    private String user_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_show_more);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("userBlogId");
        hot = intent.getStringExtra("hot");
        Log.e(TAG, "onCreate:id值"+ id);
        setTtitle();
        setinfo();
        ll_author.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StarShowMoreActivity.this, UserDetailActivity.class);
                intent.putExtra("usercode",user_code);
                intent.putExtra("hot",hot);
                startActivity(intent);
            }
        });
    }


    /**
     * 展示信息
     */
    @BindView(R.id.starshow_more_player)
    JzvdStd player;
    @BindView(R.id.yinpinbofang)
    LinearLayout linearLayout;
    @BindView(R.id.vp_starshowmore)
    ViewPager vp;
    @BindView(R.id.rv_starshowmore)
    RecyclerView rv;
    //获取详情信息展示控件
    @BindView(R.id.search_student_headimg)
    ImageView img_head;
    @BindView(R.id.search_student_id)
    TextView tv_id;
    @BindView(R.id.search_student_type)
    TextView tv_type;
    @BindView(R.id.search_guanzhu)
    Button btn_guanzhu;
    @BindView(R.id.starshow_more_title)
    TextView tv_title;
    @BindView(R.id.starshow_more_time)
    TextView tv_time;
    @BindView(R.id.starshow_more_dianzan)
    ImageView img_zan;
    @BindView(R.id.starshow_more_info)
    TextView tv_info;


    private void setinfo() {

        OkGo.post(Api.square_starshow_more)
                .tag(this)
                .params("userBlogId", getIntent().getStringExtra("userBlogId"))
                .params("userCode", usercodes) //先写死
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e + "");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: 星秀详情" + s);
                        Gson gson = new Gson();
                        StarShowMoreBean starShowMoreBean = gson.fromJson(s, StarShowMoreBean.class);
                        if (starShowMoreBean.getState() == 1) {
                            String url = Api.ossurl + starShowMoreBean.getData().getMedia_url();
                            if (getIntent().getStringExtra("type").equals("1")) {
                                vp.setVisibility(View.VISIBLE);
                                rv.setVisibility(View.VISIBLE);
                                player.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.GONE);
//                                处理数据
                                List<String> list=new ArrayList<>();
                                String[] strings = starShowMoreBean.getData().getMedia_url().split(",");
                                for (int i = 0; i < strings.length; i++) {
                                    list.add(strings[i]);
                                }

//初始化两个控件
                                LinearLayoutManager manager=new LinearLayoutManager(StarShowMoreActivity.this);
                                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rv.setLayoutManager(manager);
                                AdapterStarShowMoreRV adapterRV=new AdapterStarShowMoreRV(StarShowMoreActivity.this);
                                rv.setAdapter(adapterRV);
                                AdapterStarShowMoreVP adapterVP=new AdapterStarShowMoreVP(StarShowMoreActivity.this,list);
                                vp.setAdapter(adapterVP);
                                adapterRV.setData(list);
                                vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                    @Override
                                    public void onPageScrolled(int i, float v, int i1) {

                                    }

                                    @Override
                                    public void onPageSelected(int i) {
                                        rv.smoothScrollToPosition(i);
                                        adapterRV.setTag(i);
                                    }

                                    @Override
                                    public void onPageScrollStateChanged(int i) {

                                    }
                                });

                                adapterRV.setOnItemClickListener(new AdapterStarShowMoreRV.OnItemClickListenerPosition() {
                                    @Override
                                    public void onItemClick(int position) {
                                        vp.setCurrentItem(position);
                                    }
                                });

                            } else if (getIntent().getStringExtra("type").equals("2")) {
                                vp.setVisibility(View.GONE);
                                rv.setVisibility(View.GONE);
                                player.setVisibility(View.VISIBLE);
                                linearLayout.setVisibility(View.GONE);
                                player.setUp(url, starShowMoreBean.getData().getTitle(), Jzvd.SCREEN_WINDOW_NORMAL);
                            } else if (getIntent().getStringExtra("type").equals("3")) {
                                vp.setVisibility(View.GONE);
                                rv.setVisibility(View.GONE);
                                player.setVisibility(View.GONE);
                                linearLayout.setVisibility(View.VISIBLE);
                                linearLayout.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        play(url);
                                    }
                                });
                            }
                            //展示信息
                            Glide.with(StarShowMoreActivity.this)
                                    .load(Api.ossurl+starShowMoreBean.getData().getUser_img())
                                    .error(R.mipmap.myheadimg)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .into(img_head);
                            user_code = starShowMoreBean.getData().getUser_code();
                            tv_id.setText("SID:"+ user_code);
                            if (TextUtils.isEmpty(starShowMoreBean.getData().getLabel())){
                                tv_type.setText("");
                            }else {
                                tv_type.setText(starShowMoreBean.getData().getLabel());
                            }

                            tv_title.setText(starShowMoreBean.getData().getTitle());
                            tv_time.setText(starShowMoreBean.getData().getCreateTime());
                            tv_info.setText(starShowMoreBean.getData().getBlog_detail());

                            //点赞按钮
                            img_zan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    OkGo.post(Api.dongtaizan)
                                            .tag(this)
                                            .params("userCode",usercodes)
                                            .params("concernCode",starShowMoreBean.getData().getId())
                                            .execute(new StringCallback() {
                                                @Override
                                                public void onError(Call call, Response response, Exception e) {
                                                    super.onError(call, response, e);
                                                    ToastUtils.shortToast(e+"");
                                                }

                                                @Override
                                                public void onSuccess(String s, Call call, Response response) {
                                                    Log.e(TAG, "onSuccess: 点赞"+s );
                                                    Gson gson1 = new Gson();
                                                    CommonBean commonBean = gson1.fromJson(s,CommonBean.class);
                                                    if (commonBean.getState()==1){
                                                      ToastUtils.shortToast("点赞成功");
                                                    }else {
                                                        ToastUtils.shortToast(commonBean.getMessage());
                                                    }
                                                }
                                            });
                                }
                            });
                            if (starShowMoreBean.getData().getFlag()==1){
                                btn_guanzhu.setBackground(StarShowMoreActivity.this.getResources().getDrawable(R.mipmap.yiguanzhu));
                                btn_guanzhu.setText("已关注");
                                btn_guanzhu.setTextColor(StarShowMoreActivity.this.getResources().getColor(R.color.home_gray));
                            }else {
                                //关注
                                btn_guanzhu.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (btn_guanzhu.getText().toString().equals("关注")){
                                            OkGo.post(Api.guanzhu)
                                                    .tag(this)
                                                    .params("userCode",usercodes)
                                                    .params("concernCode",starShowMoreBean.getData().getUser_code())
                                                    .params("type",starShowMoreBean.getData().getType())
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onError(Call call, Response response, Exception e) {
                                                            super.onError(call, response, e);
                                                            ToastUtils.shortToast(e+"");
                                                        }

                                                        @Override
                                                        public void onSuccess(String s, Call call, Response response) {
                                                            Log.e(TAG, "onSuccess: 关注"+s );
                                                            Gson gson1 = new Gson();
                                                            CommonBean commonBean = gson1.fromJson(s,CommonBean.class);
                                                            if (commonBean.getState()==1){
                                                                btn_guanzhu.setBackground(StarShowMoreActivity.this.getResources().getDrawable(R.mipmap.yiguanzhu));
                                                                btn_guanzhu.setText("已关注");
                                                                btn_guanzhu.setTextColor(StarShowMoreActivity.this.getResources().getColor(R.color.home_gray));
//                                                      ToastUtils.shortToast("关注成功");
                                                            }else {
                                                                ToastUtils.shortToast(commonBean.getMessage());
                                                            }
                                                        }
                                                    });
                                        }
                                    }
                                });
                            }

                        } else {
                            if (starShowMoreBean.getMessage().equals("免费次数不足，即将消耗星币")){
                                ToastUtils.shortToast(starShowMoreBean.getMessage());
                            }else if (starShowMoreBean.getMessage().equals("星币不足，请充值")){

                                MyDialog.show(StarShowMoreActivity.this, "是否退出登录？", new MyDialog.OnConfirmListener() {
                                    @Override
                                    public void onConfirmClick() {
                                        ToastUtils.shortToast("跳转页面");
                                    }
                                });
                            }

                        }
//
                    }
                });


    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }


    /**
     * 播放音乐
     */
    protected void play(String url) {
        String path = url;
        Log.e(TAG, "play: 音频"+path );
        File file = new File(path);
        if (file.exists() && file.length() > 0) {
            try {
                mediaPlayer = new MediaPlayer();
                // 设置指定的流媒体地址
                mediaPlayer.setDataSource(path);
                // 设置音频流的类型
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                // 通过异步的方式装载媒体资源
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        // 装载完毕 开始播放流媒体
                        mediaPlayer.start();
//                        Toast.makeText(StarShowMoreActivity.this, "开始播放", 0).show();
                        // 避免重复播放，把播放按钮设置为不可用
                        linearLayout.setEnabled(false);
                    }
                });
                // 设置循环播放
                // mediaPlayer.setLooping(true);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // 在播放完毕被回调
                        linearLayout.setEnabled(true);
                    }
                });

                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        // 如果发生错误，重新播放
                        replay(path);
                        return false;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.shortToast("播放失败");
            }
        } else {
            ToastUtils.shortToast("文件不存在");
        }

    }

    /**
     * 重新播放
     */
    protected void replay(String url) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
//            ToastUtils.shortToast("正在重新播放");
            return;
        }
        play(url);
    }


    /**
     * 写入title名字
     */
    @BindView(R.id.common_left_img)
    ImageView left_img;
    @BindView(R.id.common_center_title)
    TextView tv_titles;
    @BindView(R.id.common_rigth_img)
    ImageView right_img;

    private void setTtitle() {
        tv_titles.setText("活动详情");
        tv_titles.setVisibility(View.INVISIBLE);
        left_img.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        //返回
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StarShowMoreActivity.this.finish();
            }
        });
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂无分享功能
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    protected void onDestroy() {
        if (mediaPlayer != null)
            mediaPlayer.release();
        super.onDestroy();

    }
}
