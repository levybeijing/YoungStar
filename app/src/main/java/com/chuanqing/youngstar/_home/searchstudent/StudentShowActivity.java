package com.chuanqing.youngstar._home.searchstudent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.myadapter.SearchStudentAdapter;
import com.chuanqing.youngstar.mybean.HomeActivityBean;
import com.chuanqing.youngstar.mybean.HomeYanyiBean;
import com.chuanqing.youngstar.mybean.SearchStudentBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.CircleImageView;
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

public class StudentShowActivity extends AppCompatActivity  implements XListView.IXListViewListener{
    private static final String TAG = "StudentShowActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_show);
        ButterKnife.bind(this);
        adapter = new SearchStudentAdapter(StudentShowActivity.this,arrayList_student);
        mListView.setAdapter(adapter);
        searchinfo();
    }
    /**
     * 搜索
     * 取消
     */
    @BindView(R.id.student_search_img)
    ImageView img_search;
    @BindView(R.id.student_search_edt)
    EditText edt_info;
    @BindView(R.id.student_search_callback)
    TextView tv_back;
    @BindView(R.id.student_xlistview)
    XListView mListView;
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
    @BindView(R.id.search_student_body)
    LinearLayout linearLayout_student;
    @BindView(R.id.search_yanyi_body)
    LinearLayout linearLayout_yanyi;
    String onclickzhi = "1";
    private void searchinfo(){
        //让第一个横线显示，其他的隐藏
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_info.getText().toString())){
                    ToastUtils.shortToast("请输入要搜索的信息");
                }else {
                    if (onclickzhi.equals("1")){
                        //查询学生
                        searchstudent();
                    }else if (onclickzhi.equals("2")){
                        //查询活动
                        searchactivity();
                    }else if (onclickzhi.equals("3")){
                        //查询招聘
                        searchzhaopin();
                    }
                }
            }
        });
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentShowActivity.this.finish();
            }
        });

        //第一个点击展示
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_info.setHint("请输入用户ID或职业");

                onclickzhi = "1";
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);

                mListView.setVisibility(View.VISIBLE);
                linearLayout_student.setVisibility(View.GONE);
                linearLayout_yanyi.setVisibility(View.GONE);
            }
        });
        //第2个点击展示
        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_info.setHint("关键字");

                onclickzhi = "2";
                view2.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                view3.setVisibility(View.GONE);

                mListView.setVisibility(View.GONE);
                linearLayout_student.setVisibility(View.VISIBLE);
                linearLayout_yanyi.setVisibility(View.GONE);
            }
        });
        //第3个点击展示
        relativeLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_info.setHint("关键字");

                onclickzhi = "3";
                view3.setVisibility(View.VISIBLE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);

                mListView.setVisibility(View.GONE);
                linearLayout_student.setVisibility(View.GONE);
                linearLayout_yanyi.setVisibility(View.VISIBLE);
            }
        });
    }

    private Handler mHandler;
    int page = 1, pageSize = 10;
    SearchStudentAdapter adapter;
    ArrayList<SearchStudentBean> arrayList_student = new ArrayList<>();
    private void searchstudent() {
        mHandler = new Handler();
        OkGo.post(Api.home_search_student)
                .tag(this)
                .params("page",page)
                .params("pageSize",pageSize)
                .params("keyWord",edt_info.getText().toString())
                .params("code","8572451327")//先写死
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: 搜索学生"+s );
                        arrayList_student.clear();
                        Gson gson = new Gson();
                        final SearchStudentBean searchStudentBean = gson.fromJson(s,SearchStudentBean.class);
                        if (pageSize>=searchStudentBean.getData().getPageInfo().getList().size()){
                            mListView.zhanshi(false);
                        }else{
                            mListView.zhanshi(true);
                        }
                        if (searchStudentBean.getState()==1){
                            if (searchStudentBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i <searchStudentBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList_student.add(searchStudentBean);
                                }

                            }else {
                                ToastUtils.shortToast("暂无搜索信息");
                            }
                        }else {
                            ToastUtils.shortToast(searchStudentBean.getMessage());
                        }
                    }
                });
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
    }

    String yanyiimg;
    private void searchzhaopin() {
        OkGo.post(Api.home_search_zhichang)
                .tag(this)
                .params("keyWord",edt_info.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        final HomeYanyiBean yanyiBean = gson.fromJson(s,HomeYanyiBean.class);
                        if (yanyiBean.getState()==1){
                            if (yanyiBean.getData().size()>0){
                                for (int i = 0; i <yanyiBean.getData().size() ; i++) {
                                    final View v_item=LayoutInflater.from(StudentShowActivity.this).inflate(R.layout.home_yanyi_items,null);
                                    ImageView img;
                                    TextView tv_name;
                                    TextView tv_time;
                                    TextView tv_people;
                                    img = v_item.findViewById(R.id.home_activity_1_img);
                                    tv_name = v_item.findViewById(R.id.home_activity_1_name);
                                    tv_time = v_item.findViewById(R.id.home_activity_1_time);
                                    tv_people = v_item.findViewById(R.id.home_activity_1_people);
                                    CircleImageView img_head = v_item.findViewById(R.id.yanyi_headimg);
                                    if (yanyiBean.getData().get(i).getImg().contains(",")){
                                        yanyiimg = Api.ossurl+yanyiBean.getData().get(i).getImg().split(",")[0];
                                    }else {
                                        yanyiimg = Api.ossurl+yanyiBean.getData().get(i).getImg();
                                    }
                                    Glide.with(StudentShowActivity.this)
                                            .load(yanyiimg)
                                            .placeholder(R.mipmap.my166)
                                            .error(R.mipmap.my166)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .into(img);
                                    String headimg = Api.ossurl+yanyiBean.getData().get(i).getUser_img();
                                    Log.e("演绎图片",headimg);
                                    Glide.with(StudentShowActivity.this)
                                            .load(headimg)
                                            .placeholder(R.mipmap.my11)
                                            .error(R.mipmap.my11)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .into(img_head);
                                    tv_name.setText(yanyiBean.getData().get(i).getTitle());
                                    tv_people.setText(yanyiBean.getData().get(i).getAttendCount()+"");
                                    tv_time.setText(yanyiBean.getData().get(i).getUser_code());
                                    linearLayout_yanyi.addView(v_item);
                                    final int finalI = i;
                                    v_item.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtils.shortToast("演绎"+yanyiBean.getData().get(finalI).getAttendCount());
                                        }
                                    });
                                }
                            }else {

                            }
                        }else {
                            ToastUtils.shortToast(yanyiBean.getMessage());
                        }
                    }
                });
    }

    private void searchactivity() {
        OkGo.post(Api.home_search_activity)
                .tag(this)
                .params("keyWord",edt_info.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        final HomeActivityBean activityBean = gson.fromJson(s,HomeActivityBean.class);
                        if (activityBean.getState()==1){
                            if (activityBean.getData()!=null){
                                for (int i = 0; i <activityBean.getData().size() ; i++) {
                                    final View v_item=LayoutInflater.from(StudentShowActivity.this).inflate(R.layout.home_activity_items,null);
                                    ImageView img;
                                    TextView tv_name;
                                    TextView tv_time;
                                    TextView tv_people;
                                    img = v_item.findViewById(R.id.home_activity_1_img);
                                    tv_name = v_item.findViewById(R.id.home_activity_1_name);
                                    tv_time = v_item.findViewById(R.id.home_activity_1_time);
                                    tv_people = v_item.findViewById(R.id.home_activity_1_people);
                                    Glide.with(StudentShowActivity.this)
                                            .load(Api.ossurl+activityBean.getData().get(i).getList_img())
                                            .placeholder(R.mipmap.my166)
                                            .error(R.mipmap.my166)
                                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                                            .into(img);
                                    tv_name.setText(activityBean.getData().get(i).getActivity_name());
                                    tv_people.setText(activityBean.getData().get(i).getAttendCount());
                                    tv_time.setText(activityBean.getData().get(i).getCurrentTime());
                                    linearLayout_student.addView(v_item);
                                    final int finalI = i;
                                    v_item.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ToastUtils.shortToast("活动名字"+activityBean.getData().get(finalI).getActivity_code());
                                        }
                                    });
                                }
                            }
                        }else {
                            ToastUtils.shortToast(activityBean.getMessage());
                        }
                    }
                });
    }
    private void searchstudent(int page2,int pageSize2) {
        OkGo.post(Api.home_search_student)
                .tag(this)
                .params("page",page2)
                .params("pageSize",pageSize2)
                .params("keyWord",edt_info.getText().toString())
                .params("code","8572451327")//先写死
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e(TAG, "onSuccess: 搜索学生"+s );
                        arrayList_student.clear();
                        Gson gson = new Gson();
                        SearchStudentBean searchStudentBean = gson.fromJson(s,SearchStudentBean.class);
                        if (pageSize>=searchStudentBean.getData().getPageInfo().getList().size()){
                            mListView.zhanshi(false);
                        }else{
                            mListView.zhanshi(true);
                        }
                        if (searchStudentBean.getState()==1){
                            if (searchStudentBean.getData().getPageInfo().getList().size()>0){
                                for (int i = 0; i <searchStudentBean.getData().getPageInfo().getList().size() ; i++) {
                                    arrayList_student.add(searchStudentBean);
                                }
                                adapter = new SearchStudentAdapter(StudentShowActivity.this,arrayList_student);
                                mListView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            }else {
                                ToastUtils.shortToast("暂无搜索信息");
                            }
                        }else {
                            ToastUtils.shortToast(searchStudentBean.getMessage());
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
                searchstudent();
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
                searchstudent(page ++,(pageSize+5));
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
