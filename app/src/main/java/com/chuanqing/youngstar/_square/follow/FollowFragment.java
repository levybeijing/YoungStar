package com.chuanqing.youngstar._square.follow;


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

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.myadapter.SquareFollowAdapter;
import com.chuanqing.youngstar.myadapter.SquareStarShowAdaper;
import com.chuanqing.youngstar.mybean.SquareFollowBean;
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
 * 广场 关注
 * A simple {@link Fragment} subclass.
 */
public class FollowFragment extends Fragment implements XListView.IXListViewListener{
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    SquareFollowAdapter adapter;
    ArrayList<SquareFollowBean> arrayList = new ArrayList<>();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHandler = new Handler();
        adapter = new SquareFollowAdapter(context,arrayList);
        mListView.setAdapter(adapter);
        showinfo();
    }

    /**
     * 展示信息
     */
    @BindView(R.id.xlistview)
    XListView mListView;
    private Handler mHandler;
    int page = 1, pageSize = 10;
    private void showinfo() {
        OkGo.post(Api.square_guanzhu)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("userCode","8572451327") //先写默认的
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

//                        Log.e("关注",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        SquareFollowBean squareFollowBean = gson.fromJson(s,SquareFollowBean.class);
                        if (pageSize>=squareFollowBean.getData().getPageInfo().getList().size()){
                            mListView.zhanshi(false);
                        }else{
                            mListView.zhanshi(true);
                        }
                        if (squareFollowBean.getState()==1){
                            if (squareFollowBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i < squareFollowBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(squareFollowBean);
                                }
                                adapter.notifyDataSetChanged();
                            }

                        }else {
                            ToastUtils.shortToast(squareFollowBean.getMessage());
                        }
                    }
                });
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }

    private void showinfo2(int page2,int pageSize2) {
        OkGo.post(Api.square_guanzhu)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .params("userCode","8572451327") //先写默认的
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {

//                        Log.e("关注",s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        SquareFollowBean squareFollowBean = gson.fromJson(s,SquareFollowBean.class);
                        if (pageSize>=squareFollowBean.getData().getPageInfo().getList().size()){
                            mListView.zhanshi(false);
                        }else{
                            mListView.zhanshi(true);
                        }
                        if (squareFollowBean.getState()==1){
                            if (squareFollowBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i < squareFollowBean.getData().getPageInfo().getList().size(); i++) {
                                    arrayList.add(squareFollowBean);
                                }
                                adapter.notifyDataSetChanged();
                            }

                        }else {
                            ToastUtils.shortToast(squareFollowBean.getMessage());
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
