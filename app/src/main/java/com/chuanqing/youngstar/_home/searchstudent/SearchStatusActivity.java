package com.chuanqing.youngstar._home.searchstudent;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chuanqing.youngstar.MainActivity;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.StatusListAdapter;
import com.chuanqing.youngstar.mybean.StatusBean;
import com.chuanqing.youngstar.mybean.StatusListBean;
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
 * 首页点进来的身份搜索页面
 */
public class SearchStatusActivity extends BaseActivity  implements XListView.IXListViewListener {
    private static final String TAG = "SearchStatusActivity";
    @BindView(R.id.fenlie_xlistview1)
    XListView mListView1;
    @BindView(R.id.fenlie_xlistview2)
    XListView mListView2;
    @BindView(R.id.fenlie_xlistview3)
    XListView mListView3;
    @BindView(R.id.no_info)
    TextView tv_noinfo;
    private Handler mHandler;
    int page = 1, pageSize = 20;
    //适配器，数据源
    StatusListAdapter adapter_list;
    ArrayList<StatusListBean> arrayList = new ArrayList<>();
    ArrayList<StatusListBean> arrayList2 = new ArrayList<>();
    ArrayList<StatusListBean> arrayList3 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_status);
        ButterKnife.bind(this);
        setTtitle();
        getstatus();
        mHandler = new Handler();
        setinfo();
    }

    /**
     * 获取身份信息
     */
    private void getstatus() {
        OkGo.post(Api.home_shenfen)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        StatusBean statusBean = gson.fromJson(s,StatusBean.class);
                        if (statusBean.getState()==1){
                            tv_name.setText(statusBean.getData().get(0).getName());
                            zuire();
                        }else {
                            tv_name.setText("");
                            ToastUtils.shortToast(statusBean.getMessage());
                        }
                    }
                });
    }

    /**
     * 展示信息
     */
    @BindView(R.id.student_search_relativelayout1)
    RelativeLayout relativeLayout1;
    @BindView(R.id.student_search_relativelayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.student_search_relativelayout3)
    RelativeLayout relativeLayout3;
    @BindView(R.id.student_search_view1)
    View view1;
    @BindView(R.id.student_search_view2)
    View view2;
    @BindView(R.id.student_search_view3)
    View view3;
    String onclickzhi = "1";
    private void setinfo() {
        //第一个展示，其他的隐藏
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
        //让第一个信息展示


        //第一个点击展示
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onclickzhi = "1";
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);
                mListView1.setVisibility(View.VISIBLE);
                mListView2.setVisibility(View.GONE);
                mListView3.setVisibility(View.GONE);
                zuire();
            }
        });
        //第2个点击展示
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onclickzhi = "2";
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);


                mListView2.setVisibility(View.VISIBLE);
                mListView1.setVisibility(View.GONE);
                mListView3.setVisibility(View.GONE);
                zuihuo();
            }
        });
        //第3个点击展示
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onclickzhi = "3";
                view3.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);


                mListView3.setVisibility(View.VISIBLE);
                mListView2.setVisibility(View.GONE);
                mListView1.setVisibility(View.GONE);
                xinren();
            }
        });
    }

    /**
     * 写入title名字
     */
    @BindView(R.id.common_left_img)
    ImageView left_img;
    @BindView(R.id.common_rigth_img)
    ImageView right_img;
    @BindView(R.id.student_search_edt)
    EditText edt_search;
    @BindView(R.id.status_name)
    TextView tv_name;
    private void setTtitle(){

        left_img.setVisibility(View.VISIBLE);
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchStatusActivity.this.finish();
            }
        });
        right_img.setVisibility(View.VISIBLE);
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (edt_search.getText().toString().equals("")){
                   ToastUtils.shortToast("请输入要搜索的用户id");
               }else {
                   if (onclickzhi.equals("1")){
                       zuire();
                   }else if (onclickzhi.equals("2")){
                       zuihuo();
                   }else {
                       xinren();
                   }
               }
            }
        });

        tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });
    }

    /**
     * 最热,最火，新人
     */
    private void zuire() {
//        Log.e(TAG, "onSuccess: hot传值 "+tv_name.getText().toString()+"**"+edt_search.getText().toString() );
        OkGo.post(Api.home_shenfen_search)
                .tag(this)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("name",tv_name.getText().toString())
                .params("userCode",edt_search.getText().toString())
                .params("hot","a")
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: hot"+s );
                        arrayList.clear();
                        Gson gson = new Gson();
                        StatusListBean statusListBean = gson.fromJson(s,StatusListBean.class);
                        if (pageSize>=statusListBean.getData().getPageInfo().getList().size()){
                            mListView1.zhanshi(false);
                        }else{
                            mListView1.zhanshi(true);
                        }
                        if (statusListBean.getState()==1){
                            if (statusListBean.getData().getPageInfo().getList().size()>0){
                                mListView1.setVisibility(View.VISIBLE);
                                mListView2.setVisibility(View.GONE);
                                mListView3.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.GONE);
                                for (int i = 0; i <statusListBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(statusListBean);
                                }
                                adapter_list = new StatusListAdapter(SearchStatusActivity.this,arrayList);
                                mListView1.setAdapter(adapter_list);
                            }else {
                                mListView1.setVisibility(View.GONE);
                                mListView2.setVisibility(View.GONE);
                                mListView3.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.VISIBLE);
                            }
                        }else {
                            ToastUtils.shortToast(statusListBean.getMessage());
                        }
                    }
                });
        mListView1.setPullLoadEnable(true);
        mListView1.setXListViewListener(this);
    }
    private void zuihuo() {
        OkGo.post(Api.home_shenfen_search)
                .tag(this)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("name",tv_name.getText().toString())
                .params("userCode",edt_search.getText().toString())
                .params("fire","b")
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        arrayList2.clear();
                        Gson gson = new Gson();
                        StatusListBean statusListBean = gson.fromJson(s,StatusListBean.class);
                        if (pageSize>=statusListBean.getData().getPageInfo().getList().size()){
                            mListView2.zhanshi(false);
                        }else{
                            mListView2.zhanshi(true);
                        }
                        if (statusListBean.getState()==1){
                            if (statusListBean.getData().getPageInfo().getList().size()>0){
                                mListView2.setVisibility(View.VISIBLE);
                                mListView1.setVisibility(View.GONE);
                                mListView3.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.GONE);
                                for (int i = 0; i <statusListBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList2.add(statusListBean);
                                }
                                adapter_list = new StatusListAdapter(SearchStatusActivity.this,arrayList2);
                                mListView2.setAdapter(adapter_list);
                            }else {
                                mListView2.setVisibility(View.GONE);
                                mListView1.setVisibility(View.GONE);
                                mListView3.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.VISIBLE);
                            }
                        }else {
                            ToastUtils.shortToast(statusListBean.getMessage());
                        }
                    }
                });
        mListView2.setPullLoadEnable(true);
        mListView2.setXListViewListener(this);
    }
    private void xinren() {
        OkGo.post(Api.home_shenfen_search)
                .tag(this)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("name",tv_name.getText().toString())
                .params("userCode",edt_search.getText().toString())
                .params("newTime","c")
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        arrayList3.clear();
                        Gson gson = new Gson();
                        StatusListBean statusListBean = gson.fromJson(s,StatusListBean.class);
                        if (pageSize>=statusListBean.getData().getPageInfo().getList().size()){
                            mListView3.zhanshi(false);
                        }else{
                            mListView3.zhanshi(true);
                        }
                        if (statusListBean.getState()==1){
                            if (statusListBean.getData().getPageInfo().getList().size()>0){
                                mListView3.setVisibility(View.VISIBLE);
                                mListView2.setVisibility(View.GONE);
                                mListView1.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.GONE);
                                for (int i = 0; i <statusListBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList3.add(statusListBean);
                                }
                                adapter_list = new StatusListAdapter(SearchStatusActivity.this,arrayList3);
                                mListView3.setAdapter(adapter_list);
                            }else {
                                mListView3.setVisibility(View.GONE);
                                mListView2.setVisibility(View.GONE);
                                mListView1.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.VISIBLE);
                            }
                        }else {
                            ToastUtils.shortToast(statusListBean.getMessage());
                        }
                    }
                });
        mListView3.setPullLoadEnable(true);
        mListView3.setXListViewListener(this);
    }

    /**
     * 最热,最火，新人 加载更多
     */
    private void zuire(int page2,int pageSize2) {
//        Log.e(TAG, "onSuccess: hot传值 "+tv_name.getText().toString()+"**"+edt_search.getText().toString() );
        OkGo.post(Api.home_shenfen_search)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .params("name",tv_name.getText().toString())
                .params("userCode",edt_search.getText().toString())
                .params("hot","a")
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: hot"+s );
                        arrayList.clear();
                        Gson gson = new Gson();
                        StatusListBean statusListBean = gson.fromJson(s,StatusListBean.class);
                        if (pageSize>=statusListBean.getData().getPageInfo().getList().size()){
                            mListView1.zhanshi(false);
                        }else{
                            mListView1.zhanshi(true);
                        }
                        if (statusListBean.getState()==1){
                            if (statusListBean.getData().getPageInfo().getList().size()>0){
                                mListView1.setVisibility(View.VISIBLE);
                                mListView2.setVisibility(View.GONE);
                                mListView3.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.GONE);
                                for (int i = 0; i <statusListBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList.add(statusListBean);
                                }
                                adapter_list = new StatusListAdapter(SearchStatusActivity.this,arrayList);
                                mListView1.setAdapter(adapter_list);
                            }else {
                                mListView1.setVisibility(View.GONE);
                                mListView2.setVisibility(View.GONE);
                                mListView3.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.VISIBLE);
                            }
                        }else {
                            ToastUtils.shortToast(statusListBean.getMessage());
                        }
                    }
                });
        mListView1.setPullLoadEnable(true);
        mListView1.setXListViewListener(this);
    }
    private void zuihuo(int page2,int pageSize2) {
        OkGo.post(Api.home_shenfen_search)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .params("name",tv_name.getText().toString())
                .params("userCode",edt_search.getText().toString())
                .params("fire","b")
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        arrayList2.clear();
                        Gson gson = new Gson();
                        StatusListBean statusListBean = gson.fromJson(s,StatusListBean.class);
                        if (pageSize>=statusListBean.getData().getPageInfo().getList().size()){
                            mListView2.zhanshi(false);
                        }else{
                            mListView2.zhanshi(true);
                        }
                        if (statusListBean.getState()==1){
                            if (statusListBean.getData().getPageInfo().getList().size()>0){
                                mListView2.setVisibility(View.VISIBLE);
                                mListView1.setVisibility(View.GONE);
                                mListView3.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.GONE);
                                for (int i = 0; i <statusListBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList2.add(statusListBean);
                                }
                                adapter_list = new StatusListAdapter(SearchStatusActivity.this,arrayList2);
                                mListView2.setAdapter(adapter_list);
                            }else {
                                mListView2.setVisibility(View.GONE);
                                mListView1.setVisibility(View.GONE);
                                mListView3.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.VISIBLE);
                            }
                        }else {
                            ToastUtils.shortToast(statusListBean.getMessage());
                        }
                    }
                });
        mListView2.setPullLoadEnable(true);
        mListView2.setXListViewListener(this);
    }
    private void xinren(int page2,int pageSize2) {
        OkGo.post(Api.home_shenfen_search)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .params("name",tv_name.getText().toString())
                .params("userCode",edt_search.getText().toString())
                .params("newTime","c")
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        arrayList3.clear();
                        Gson gson = new Gson();
                        StatusListBean statusListBean = gson.fromJson(s,StatusListBean.class);
                        if (pageSize>=statusListBean.getData().getPageInfo().getList().size()){
                            mListView3.zhanshi(false);
                        }else{
                            mListView3.zhanshi(true);
                        }
                        if (statusListBean.getState()==1){
                            if (statusListBean.getData().getPageInfo().getList().size()>0){
                                mListView3.setVisibility(View.VISIBLE);
                                mListView2.setVisibility(View.GONE);
                                mListView1.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.GONE);
                                for (int i = 0; i <statusListBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList3.add(statusListBean);
                                }
                                adapter_list = new StatusListAdapter(SearchStatusActivity.this,arrayList3);
                                mListView3.setAdapter(adapter_list);
                            }else {
                                mListView3.setVisibility(View.GONE);
                                mListView2.setVisibility(View.GONE);
                                mListView1.setVisibility(View.GONE);
                                tv_noinfo.setVisibility(View.VISIBLE);
                            }
                        }else {
                            ToastUtils.shortToast(statusListBean.getMessage());
                        }
                    }
                });
        mListView3.setPullLoadEnable(true);
        mListView3.setXListViewListener(this);
    }
    //下拉刷新
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                page = 1;
                pageSize = 20;
                if (onclickzhi.equals("1")){
                    zuire();
                }else if (onclickzhi.equals("2")){
                    zuihuo();
                }else {
                    xinren();
                }
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
                if (onclickzhi.equals("1")){
                    zuire(page ++,(pageSize+10));
                }else if (onclickzhi.equals("2")){
                    zuihuo(page ++,(pageSize+10));
                }else {
                    xinren(page ++,(pageSize+10));
                }
                adapter_list.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    private void onLoad() {
        if (onclickzhi.equals("1")){
            mListView1.stopRefresh();
            mListView1.stopLoadMore();
            //获取当前时间
            Date curDate = new Date(System.currentTimeMillis());
            //格式化
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            String time = formatter.format(curDate);
            mListView1.setRefreshTime(time);
        }else if (onclickzhi.equals("2")){
            mListView2.stopRefresh();
            mListView2.stopLoadMore();
            //获取当前时间
            Date curDate = new Date(System.currentTimeMillis());
            //格式化
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            String time = formatter.format(curDate);
            mListView2.setRefreshTime(time);
        }else {
            mListView3.stopRefresh();
            mListView3.stopLoadMore();
            //获取当前时间
            Date curDate = new Date(System.currentTimeMillis());
            //格式化
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            String time = formatter.format(curDate);
            mListView3.setRefreshTime(time);
        }

    }

    /**
     * 显示popupWindow
     */
    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.xiala_items, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);


        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAsDropDown(findViewById(R.id.ceshi), 0, 0);
        LinearLayout linearLayout_items = view.findViewById(R.id.xiala_items_layout);
        // popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });
        /**
         * 动态添加下拉框里面的值
         */
        OkGo.post(Api.home_shenfen)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        StatusBean statusBean = gson.fromJson(s,StatusBean.class);
                        if (statusBean.getState()==1){
                            tv_name.setText(statusBean.getData().get(0).getName());
                            for (int i = 0; i < statusBean.getData().size(); i++) {
                                final View v_item=LayoutInflater.from(SearchStatusActivity.this).inflate(R.layout.xiala_items_textviews,null);
                                TextView tv_title = v_item.findViewById(R.id.xiala_items_layout_item);
                                tv_title.setText(statusBean.getData().get(i).getName());
                                linearLayout_items.addView(v_item);
                                tv_title.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        tv_name.setText(tv_title.getText().toString());
                                        if (window.isShowing()){
                                            window.dismiss();
                                        }
                                        if (onclickzhi.equals("1")){
                                            zuire();
                                        }else if (onclickzhi.equals("2")){
                                            zuihuo();
                                        }else {
                                            xinren();
                                        }
                                    }
                                });
                            }
                            zuire();
                        }else {
                            tv_name.setText("");
                            ToastUtils.shortToast(statusBean.getMessage());
                        }
                    }
                });




    }
}
