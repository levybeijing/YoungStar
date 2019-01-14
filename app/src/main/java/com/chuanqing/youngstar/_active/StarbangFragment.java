package com.chuanqing.youngstar._active;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.myadapter.StarbangAdapter;
import com.chuanqing.youngstar.mybean.StarLeitaiBean;
import com.chuanqing.youngstar.mybean.StarbangBean;
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
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 星活动 星榜
 * A simple {@link Fragment} subclass.
 */
public class StarbangFragment extends Fragment  implements XListView.IXListViewListener{

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_starbang, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    StarbangAdapter adapter;
    ArrayList<StarbangBean> arrayList = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new StarbangAdapter(context,arrayList);
        mListView.setAdapter(adapter);
        showimg();
    }

    /**
     * 展示信息
     */
    @BindView(R.id.xlistview)
    XListView mListView;
    private Handler mHandler;
    int page = 1, pageSize = 10;

    private void showimg() {
        mHandler = new Handler();
        OkGo.post(Api.star_xingbang)
                .tag(this)
                .params("page",page)
                .params("pageSize",pageSize)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        arrayList.clear();
                        Gson gson = new Gson();
                        StarbangBean starbangBean = gson.fromJson(s,StarbangBean.class);
                        if (starbangBean.getState()==1){
                            if (starbangBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i < starbangBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(starbangBean);
                                }
                                if (pageSize>=starbangBean.getData().getPageInfo().getList().size()){
                                    mListView.zhanshi(false);
                                }else{
                                    mListView.zhanshi(true);
                                }
                            }else {
                                 //没有信息
                            }
                        }else {
                            ToastUtils.shortToast(starbangBean.getMessage());
                        }
                    }
                });
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }
    private void showinfo2(int page2,int pageSize2) {
        OkGo.post(Api.star_xingbang)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        arrayList.clear();
                        Gson gson = new Gson();
                        StarbangBean starbangBean = gson.fromJson(s,StarbangBean.class);
                        if (pageSize>=starbangBean.getData().getPageInfo().getList().size()){
                            mListView.zhanshi(false);
                        }else{
                            mListView.zhanshi(true);
                        }
                        if (starbangBean.getState()==1){
                            if (starbangBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i < starbangBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(starbangBean);
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }else {
                            ToastUtils.shortToast(starbangBean.getMessage());
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
                showimg();
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
