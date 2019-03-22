package com.chuanqing.youngstar._square.starshow;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.myadapter.SquareStarShowAdaper;
import com.chuanqing.youngstar.mybean.SquareStarShowBean;
import com.chuanqing.youngstar.tools.Api;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static com.chuanqing.youngstar.MainActivity.usercodes;

/**
 * 广场 星秀
 * A simple {@link Fragment} subclass.
 */
public class StarShowFragment extends Fragment {

    Context context;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    adaper.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_star_show, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @BindView(R.id.starshow_listview)
    RecyclerView listView;
    SquareStarShowAdaper adaper;
    List<SquareStarShowBean.DataBean.PageInfoBean.ListBean> arrayList = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adaper = new SquareStarShowAdaper(context, arrayList);
        listView.setLayoutManager(new GridLayoutManager(context, 3));
        listView.setAdapter(adaper);
        showinfo();
    }

    int page = 1, pageSize = 40;

    //展示信息
    private void showinfo() {
        OkGo.post(Api.square_starshow)
                .tag(this)
                .params("page", page)
                .params("pageSize", pageSize)
                .params("type", 1)
                .params("userCode", usercodes) //先写死
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e("广场星秀", s);
                        arrayList.clear();
                        Gson gson = new Gson();
                        SquareStarShowBean squareStarShowBean = gson.fromJson(s, SquareStarShowBean.class);
                        if (squareStarShowBean.getState() == 1) {
                            arrayList = squareStarShowBean.getData().getPageInfo().getList();
                            if (arrayList!= null&&arrayList.size()>0) {
                                handler.obtainMessage(1).sendToTarget();
                            }
                        }
                    }
                });
    }
}
