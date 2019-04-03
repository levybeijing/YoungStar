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
import com.chuanqing.youngstar.myadapter.AdapterFragStatusRV;
import com.chuanqing.youngstar.myadapter.AdapterFragWorksRV;
import com.chuanqing.youngstar.mybean.FragStatusBean;
import com.chuanqing.youngstar.mybean.FragWorksBean;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class FragmentWorks extends BaseFragment {
    private int page=1;
    private AdapterFragWorksRV adapter;
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
        adapter = new AdapterFragWorksRV(getContext());
        rv.setAdapter(adapter);

        String user = null;
        if (savedInstanceState==null){
            user=(String) SharedPFUtils.getParam(getContext(),"usercode","");
        }else{
            user=savedInstanceState.getString("currentuser");
        }
        request(user);
    }


    private void request(String user) {
        OkGo.post(Urls.getUserCollection)//
                .tag(this)//
                .params("userCode", user)
                .params("page", page)
                .params("pageSize", 15)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "FragmentStatus"+s);
                        FragWorksBean bean = new Gson().fromJson(s, FragWorksBean.class);
                        List<FragWorksBean.DataBean.PageInfoBean.ListBean> list = bean.getData().getPageInfo().getList();
                        adapter.setData(list);
                    }
                });
    }
}
