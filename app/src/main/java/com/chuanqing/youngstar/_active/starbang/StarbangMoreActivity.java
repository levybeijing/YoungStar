package com.chuanqing.youngstar._active.starbang;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.MainActivity;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar._active.leitai.LeitaiMoreActivity;
import com.chuanqing.youngstar._square.starshow.StarShowMoreActivity;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.StarbangAdapter;
import com.chuanqing.youngstar.myadapter.StarbangMoreAdapter;
import com.chuanqing.youngstar.mybean.StarBangMoreBean;
import com.chuanqing.youngstar.mybean.StarbangBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.GlideRectRound;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.chuanqing.youngstar.view.XListView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 星榜详情页面
 */
public class StarbangMoreActivity extends BaseActivity {

    @BindView(R.id.starbang_more_linearlayout)
    LinearLayout linearLayout;
    @BindView(R.id.starbang_more_bgimg)
    ImageView img_bg;
    @BindView(R.id.starbang_more_headimg)
    ImageView img_head;
    StarbangMoreAdapter adapter;
    ArrayList<StarBangMoreBean> arrayList = new ArrayList<>();
    @BindView(R.id.listview)
    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starbang_more);
        ButterKnife.bind(this);
        setTtitle();
        showinfo();
        //获取屏幕的宽度
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int ping_width = wm.getDefaultDisplay().getWidth();
        //获取要展示的控件的高度和宽度
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        linearLayout.measure(w, h);
        int height = linearLayout.getMeasuredHeight();
        int width = linearLayout.getMeasuredWidth();
        //设置后面的背景图的宽高，宽度为屏幕宽度，高度为展示控件的高度
        ViewGroup.LayoutParams lp;
        lp=img_bg.getLayoutParams();
        lp.width = ping_width;
        lp.height = height;
        img_bg.setLayoutParams(lp); //使设置好的布局参数应用到控件
    }

    /**
     * 展示信息
     */
    @BindView(R.id.starbang_more_name)
    TextView tv_name;
    @BindView(R.id.starbang_more_time)
    TextView tv_time;
    @BindView(R.id.starbang_more_info)
    TextView tv_info;
    private void showinfo() {
        OkGo.post(Api.star_xingbang_more)
                .tag(this)
                .params("activityCode", getIntent().getStringExtra("activityCode"))
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("星榜详情",s);
                        Gson gson = new Gson();
                        final StarBangMoreBean starBangMoreBean = gson.fromJson(s,StarBangMoreBean.class);
                        if (starBangMoreBean.getState()==1){
                            //展示圆角头像
                            Glide.with(StarbangMoreActivity.this)
                                    .load(Api.ossurl+starBangMoreBean.getData().getMicro_img())
                                    .placeholder(R.mipmap.my11)
                                    .error(R.mipmap.my11)
                                    .into(img_head);
                            //展示背景图
                            Glide.with(StarbangMoreActivity.this)
                                    .load(Api.ossurl+starBangMoreBean.getData().getDetail_img())
                                    .placeholder(R.mipmap.my169)
                                    .error(R.mipmap.my169)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .into(img_bg);
                            tv_name.setText(starBangMoreBean.getData().getActivity_name()+"");
                            tv_title.setText(starBangMoreBean.getData().getActivity_name()+"");
                            tv_time.setText(starBangMoreBean.getData().getStart_time()+"-"+starBangMoreBean.getData().getEnd_time());
                            tv_info.setText(starBangMoreBean.getData().getActivity_introduce()+"");
                            if (starBangMoreBean.getData().getStudent().size()>0){
                                for (int i = 0; i < starBangMoreBean.getData().getStudent().size() ; i++) {
                                    arrayList.add(starBangMoreBean);
                                }
                                adapter = new StarbangMoreAdapter(StarbangMoreActivity.this,arrayList);
                                mListView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }
                            //跳转到活动详情
                            linearLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(StarbangMoreActivity.this,LeitaiMoreActivity.class);
                                    intent.putExtra("activitycode", getIntent().getStringExtra("activityCode"));
                                    startActivity(intent);
                                }
                            });
                        }else {
                            ToastUtils.shortToast(starBangMoreBean.getMessage());
                        }
                    }
                });

    }

    /**
     * 写入title名字
     */
    @BindView(R.id.common_left_img)
    ImageView left_img;
    @BindView(R.id.common_center_title)
    TextView tv_title;
    @BindView(R.id.common_rigth_img)
    ImageView right_img;
    private void setTtitle(){
//        tv_title.setText("星榜详情");
        left_img.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.VISIBLE);
        //返回
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StarbangMoreActivity.this.finish();
            }
        });
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂无分享功能
                showdialog();
            }
        });
    }


    private void showdialog() {

        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_toshare, null);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(false);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
//        //底部弹出
        window.showAtLocation(findViewById(R.id.rl_starbang), Gravity.BOTTOM,0,0);

        ImageView wechat = view.findViewById(R.id.iv_wechat_dialogtoshare);
        ImageView wx = view.findViewById(R.id.iv_wx_dialogtoshare);
        ImageView wb = view.findViewById(R.id.iv_wb_dialogtoshare);
        ImageView qq = view.findViewById(R.id.iv_qq_dialogtoshare);
        TextView cancel = view.findViewById(R.id.tv_cancel_dialogtoshare);

        wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StarbangMoreActivity.this, "账号未申请下来", Toast.LENGTH_SHORT).show();
            }
        });

        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StarbangMoreActivity.this, "账号未申请下来", Toast.LENGTH_SHORT).show();
            }
        });

        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StarbangMoreActivity.this, "账号未申请下来", Toast.LENGTH_SHORT).show();
            }
        });

        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StarbangMoreActivity.this, "账号未申请下来", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
    }

}
