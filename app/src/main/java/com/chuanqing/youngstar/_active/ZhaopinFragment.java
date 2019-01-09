package com.chuanqing.youngstar._active;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._active.leitai.LeitaiMoreActivity;
import com.chuanqing.youngstar.myadapter.StarZhaopinAdapter;
import com.chuanqing.youngstar.mybean.HomeYanyiBean;
import com.chuanqing.youngstar.mybean.StarZhaopinBean;
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

/**
 * 星职场
 * A simple {@link Fragment} subclass.
 */
public class ZhaopinFragment extends Fragment implements XListView.IXListViewListener{
    private static final String TAG = "ZhaopinFragment";
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhaopin, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    StarZhaopinAdapter adapter;
    ArrayList<StarZhaopinBean> arrayList = new ArrayList<>();
    @BindView(R.id.zhaopin_null)
    TextView tv_null;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showinfo();
        adapter = new StarZhaopinAdapter(context,arrayList);
        mListView.setAdapter(adapter);
    }

    /**
     * 展示信息
     */
    @BindView(R.id.xlistview)
    XListView mListView;
    private Handler mHandler;
    int page = 1, pageSize = 10;
    private void showinfo() {
        mHandler = new Handler();
        OkGo.post(Api.star_zhaopin)
                .tag(this)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("useType","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: 招聘"+s );
                        arrayList.clear();
                        Gson gson = new Gson();
                        StarZhaopinBean zhaopinBean = gson.fromJson(s,StarZhaopinBean.class);
                        if (zhaopinBean.getState()==1){
                            if (zhaopinBean.getData().getPageInfo().getList().size()>0){
                                tv_null.setVisibility(View.GONE);
                                mListView.setVisibility(View.VISIBLE);
                                for (int i = 0; i <zhaopinBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(zhaopinBean);
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                tv_null.setVisibility(View.VISIBLE);
                                mListView.setVisibility(View.GONE);
                            }
                        }else {
                            ToastUtils.shortToast(zhaopinBean.getMessage());
                        }
                    }
                });
    }
    private void showinfo2(int page2,int pageSize2) {
        mHandler = new Handler();
        OkGo.post(Api.star_zhaopin)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .params("useType","1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e(TAG, "onSuccess: 招聘"+s );
                        arrayList.clear();
                        Gson gson = new Gson();
                        StarZhaopinBean zhaopinBean = gson.fromJson(s,StarZhaopinBean.class);
                        if (zhaopinBean.getState()==1){
                            if (zhaopinBean.getData().getPageInfo().getList().size()>0){
                                tv_null.setVisibility(View.GONE);
                                mListView.setVisibility(View.VISIBLE);
                                for (int i = 0; i <zhaopinBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(zhaopinBean);
                                }
                                adapter.notifyDataSetChanged();
                            }else {
                                tv_null.setVisibility(View.VISIBLE);
                                mListView.setVisibility(View.GONE);
                            }
                        }else {
                            ToastUtils.shortToast(zhaopinBean.getMessage());
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
}
