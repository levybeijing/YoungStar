package com.chuanqing.youngstar._mine.student;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.base.BaseFragment;
import com.chuanqing.youngstar.myadapter.AdapterFragCareCRV;
import com.chuanqing.youngstar.myadapter.AdapterFragStatusRV;
import com.chuanqing.youngstar.mybean.FragCareCBean;
import com.chuanqing.youngstar.mybean.FragStatusBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class FragmentCareC extends BaseFragment {
    private int page=1;
    private AdapterFragCareCRV adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_status, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = view.findViewById(R.id.rv_fragstatus);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);

        adapter = new AdapterFragCareCRV(getContext());
        rv.setAdapter(adapter);

        request();
    }

    private void request() {
        OkGo.post(Urls.getUserConcernCompany)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(getContext(),"usercode",""))//文件名
                .params("page", page)//墙的ID
                .params("pageSize", 15)//缩略图 省略>?
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "getUserConcernCompany"+s);
                        FragCareCBean bean = new Gson().fromJson(s, FragCareCBean.class);
                        List<FragCareCBean.DataBean.PageInfoBean.ListBean> list = bean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
    }
}
