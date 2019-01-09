package com.chuanqing.youngstar._active.leitai;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.PartakeAdapter;
import com.chuanqing.youngstar.mybean.PartakeBean;
import com.chuanqing.youngstar.tools.Api;
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

public class PartakeActivity extends BaseActivity implements XListView.IXListViewListener{
    private static final String TAG = "PartakeActivity";
    @BindView(R.id.partake_listiview)
    XListView mListView;
    private Handler mHandler;
    int page = 1, pageSize = 10;
    @BindView(R.id.partake_null_zhi)
    TextView tv_null;
    PartakeAdapter adapter;
    ArrayList<PartakeBean> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partake);
        ButterKnife.bind(this);
        showinfo();
        adapter = new PartakeAdapter(PartakeActivity.this,arrayList);
        mListView.setAdapter(adapter);
        setTtitle();
//        Log.e(TAG, "onCreate: 传值"+getIntent().getStringExtra("activityCode").toString() );
    }

    private void showinfo() {
        mHandler = new Handler();
        OkGo.post(Api.canyu_people)
                .tag(this)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("activityCode",getIntent().getStringExtra("activityCode"))
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: 参与人"+s );
                        arrayList.clear();
                        Gson gson = new Gson();
                        PartakeBean partakeBean = gson.fromJson(s,PartakeBean.class);
//                        Log.e(TAG, "onSuccess: 长度"+partakeBean.getData().getPageInfo().getList().size() );
                        if (pageSize>=partakeBean.getData().getPageInfo().getList().size()){
                            mListView.zhanshi(false);
                        }else{
                            mListView.zhanshi(true);
                        }
                        if (partakeBean.getState()==1){
                            if (partakeBean.getData().getPageInfo().getList().size()>0){
                                tv_null.setVisibility(View.GONE);
                                mListView.setVisibility(View.VISIBLE);
                                for (int i = 0; i < partakeBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(partakeBean);
                                }
                            }else {
                                tv_null.setVisibility(View.VISIBLE);
                                mListView.setVisibility(View.GONE);
                            }
                        }else {
                            ToastUtils.shortToast(partakeBean.getMessage());
                        }
                    }
                });
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }
    private void showinfo2(int page2,int pageSize2) {
        OkGo.post(Api.canyu_people)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .params("activityCode",getIntent().getStringExtra("activityCode"))
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: 参与人"+s );
                        arrayList.clear();
                        Gson gson = new Gson();
                        PartakeBean partakeBean = gson.fromJson(s,PartakeBean.class);
                        if (pageSize>=partakeBean.getData().getPageInfo().getList().size()){
                            mListView.zhanshi(false);
                        }else{
                            mListView.zhanshi(true);
                        }
                        if (partakeBean.getState()==1){
                            if (partakeBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i < partakeBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(partakeBean);
                                }

                            }else {

                            }
                        }else {
                            ToastUtils.shortToast(partakeBean.getMessage());
                        }
                    }
                });
    }
    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                pageSize = 10;
                showinfo();
                onLoad();
            }
        }, 2000);
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showinfo2(page ++,(pageSize+5));
                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        //获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        //格式化
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String time = formatter.format(curDate);
        mListView.setRefreshTime(time);
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
        tv_title.setText("已报名");
        left_img.setVisibility(View.VISIBLE);
        right_img.setVisibility(View.GONE);
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PartakeActivity.this.finish();
            }
        });
    }
}
