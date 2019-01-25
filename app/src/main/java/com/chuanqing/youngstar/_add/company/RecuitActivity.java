package com.chuanqing.youngstar._add.company;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.android.tu.loadingdialog.LoadingDailog;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar._active.zhichang.ZhichangMoreActivity;
import com.chuanqing.youngstar._add.PublishActivity;
import com.chuanqing.youngstar._add.student.WorksActivity;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.PublishAdapter;
import com.chuanqing.youngstar.mybean.CommonBean;
import com.chuanqing.youngstar.mybean.ProvinceBean;
import com.chuanqing.youngstar.mybean.StatusBean;
import com.chuanqing.youngstar.mybean.UpZhaopinBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.BamAutoLineList;
import com.chuanqing.youngstar.tools.MyUitils;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
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

import static com.chuanqing.youngstar.MainActivity.usercodes;

/**
 * 发布招聘
 */
public class RecuitActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RecuitActivity";
    private EditText theme;
    private EditText job;
    private EditText require;
    private TextView lablenum;
    private RecyclerView lables;
    private RecyclerView imgs;
    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText address;
    private EditText comname;
    private TextView release;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuit);
        ButterKnife.bind(this);
        getOptionData();
        setTtitle();
        getfuli();
        getpeople();
        getxinzi();
        gettype();
        initView();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(RecuitActivity.this,3);
        imgs.setLayoutManager(gridLayoutManager);
    }

    /**
     * 上传信息
     */
    private void upinfo(String imgname,String benefitsss) {
        OkGo.post(Api.upzhaopininfo)
                .tag(this)
                .params("userCode",usercodes)
                .params("title",theme.getText().toString())
                .params("position",job.getText().toString())
                .params("num",tv_show_people.getText().toString())
                .params("salary",tv_show_xinzi.getText().toString())
                .params("major",tv_show_type.getText().toString())
                .params("location",tv_show_address.getText().toString())
                .params("experience",jingyan)
                .params("requirements",require.getText().toString())
                .params("benefits",benefitsss)
                .params("contacts",name.getText().toString())
                .params("companyName",comname.getText().toString())
                .params("mobile",phone.getText().toString())
                .params("companyLocation",address.getText().toString())
                .params("mail",email.getText().toString())
                .params("img",imgname)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dissDialog();
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(s,CommonBean.class);
                        if (commonBean.getState()==1){
                            ToastUtils.shortToast("发布成功");
                            dissDialog();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    RecuitActivity.this.finish();
                                }
                            },1000);
                        }
                    }
                });
    }

    /**
     * 请求招聘人数信息
     */

    private ArrayList<ProvinceBean> options1Items_people = new ArrayList<>();
    private void getpeople() {
        OkGo.post(Api.zhaopininfo)
                .tag(this)
                .params("type",1)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e(TAG, "onSuccess: 人数"+s);
                        Gson gson = new Gson();
                        UpZhaopinBean upZhaopinBean = gson.fromJson(s,UpZhaopinBean.class);
                        if (upZhaopinBean.getState()==1){
                            for (int i = 0; i < upZhaopinBean.getData().size() ; i++) {
                                options1Items_people.add(new ProvinceBean(i, upZhaopinBean.getData().get(i).getContent(), "描述部分", "其他数据"));
                            }
                        }else {
                            ToastUtils.shortToast(upZhaopinBean.getMessage());
                        }
                    }
                });
    }
    /**
     * 请求薪资信息
     */
    private ArrayList<ProvinceBean> options1Items_xinzi = new ArrayList<>();
    private void getxinzi() {
        OkGo.post(Api.zhaopininfo)
                .tag(this)
                .params("type",2)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
//                        Log.e(TAG, "onSuccess: 薪资"+s);
                        Gson gson = new Gson();
                        UpZhaopinBean upZhaopinBean = gson.fromJson(s,UpZhaopinBean.class);
                        if (upZhaopinBean.getState()==1){
                            for (int i = 0; i < upZhaopinBean.getData().size(); i++) {
                                options1Items_xinzi.add(new ProvinceBean(i, upZhaopinBean.getData().get(i).getContent(), "描述部分", "其他数据"));
                            }

                        }else {
                            ToastUtils.shortToast(upZhaopinBean.getMessage());
                        }
                    }
                });
    }
    /**
     * 请求福利信息
     */
    ArrayList<UpZhaopinBean.DataBean> arrayList_fuli = new ArrayList<>();
    private void getfuli() {
        OkGo.post(Api.zhaopininfo)
                .tag(this)
                .params("type",3)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: 福利"+s);
                        Gson gson = new Gson();
                        UpZhaopinBean upZhaopinBean = gson.fromJson(s,UpZhaopinBean.class);
                        if(upZhaopinBean.getState()==1){
                            for (int i = 0; i <upZhaopinBean.getData().size() ; i++) {
                                arrayList_fuli.add(upZhaopinBean.getData().get(i));
                            }
                            createView(arrayList_fuli);
                        }else {
                            ToastUtils.shortToast(upZhaopinBean.getMessage());
                        }
                    }
                });
    }
    /**
     * 专业类型
     */
    private ArrayList<ProvinceBean> options1Items_type = new ArrayList<>();
    private void gettype(){
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
                        if(statusBean.getState()==1){
                            for (int i = 0; i < statusBean.getData().size() ; i++) {
                                options1Items_type.add(new ProvinceBean(i, statusBean.getData().get(i).getName(), "描述部分", "其他数据"));
                            }
                        }else {
                            ToastUtils.shortToast(statusBean.getMessage());
                        }
                    }
                });
    }

    /**
     * 初始化一个布局
     *
     * @return
     */
    @BindView(R.id.recuit_fulilinear)
    BamAutoLineList recuit_linner;
    boolean fuli = false;
    ArrayList<String> fuli_list = new ArrayList<>();
    String benefits = "";
    private void createView(ArrayList<UpZhaopinBean.DataBean> name) {
        for (int i = 0; i < name.size(); i++) {
            // 实例化一个View，以放到List里
            View item = LayoutInflater.from(RecuitActivity.this).inflate(R.layout.fuli_items, null);
            // 设置View里的文本值
            TextView textView = item.findViewById(R.id.item_linear);
            textView.setBackground(RecuitActivity.this.getResources().getDrawable(R.drawable.fillettextviewup));
            textView.setText(name.get(i).getContent());
            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!fuli){
                        fuli_list.add(textView.getText().toString());
                        fuli=!fuli;
                        textView.setBackground(RecuitActivity.this.getResources().getDrawable(R.drawable.fillettextview));
                    }else {
                        fuli_list.remove(textView.getText().toString());
                        fuli=!fuli;
                        textView.setBackground(RecuitActivity.this.getResources().getDrawable(R.drawable.fillettextviewup));
                    }
                }
            });
            recuit_linner.addView(item);
        }
    }
    /**
     * 写入title名字
     */
    @BindView(R.id.common_left_img)
    ImageView left_img;
    @BindView(R.id.common_center_title)
    TextView tv_title;
    @BindView(R.id.common_rigth_img)
    TextView right_tv;
    private void setTtitle(){
        tv_title.setText("发布");
        left_img.setVisibility(View.VISIBLE);
        //返回
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecuitActivity.this.finish();
            }
        });
        right_tv.setVisibility(View.INVISIBLE);
    }

    String jingyan = "0";
    @BindView(R.id.rb_no_recuit)
    RadioButton r_no;
    @BindView(R.id.rb_yes_recuit)
    RadioButton r_yes;
    @BindView(R.id.recuit_fengmian_img)
    ImageView img_fengmian_img;
    ArrayList<String> imagePaths = new ArrayList<>();  //用于存放选择图片
    private static final int REQUEST_CAMERA_CODE = 24;//定义请求码常量  多选
    private void initView() {
        /**
         * 有无经验点击
         */
        r_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jingyan = "0";
            }
        });
        r_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jingyan = "1";
            }
        });
        /**
         * 上传图片
         */
        img_fengmian_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //多选
                PhotoPickerIntent intent = new PhotoPickerIntent(RecuitActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(false); // 是否显示拍照， 默认false
                intent.setMaxTotal(8); // 最多选择照片数量，默认为8
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
            }
        });

//        1
        theme = findViewById(R.id.et_theme_recuit);
        job = findViewById(R.id.et_job_recuit);

        findViewById(R.id.rl_num_recuit).setOnClickListener(this);
        findViewById(R.id.rl_money_recuit).setOnClickListener(this);
        findViewById(R.id.rl_major_recuit).setOnClickListener(this);
        findViewById(R.id.rl_addre_recuit).setOnClickListener(this);
//        2
        require = findViewById(R.id.et_require_recuit);
//        3
        lablenum = findViewById(R.id.tv_lablenum_recuit);

//        4
        imgs = findViewById(R.id.rv_imgs_recuit);
//        5
        name = findViewById(R.id.et_name_recuit);
        phone = findViewById(R.id.et_phone_recuit);
        email = findViewById(R.id.et_email_recuit);
        address = findViewById(R.id.et_address_recuit);
        comname = findViewById(R.id.et_comname_recuit);
//        6
        release = findViewById(R.id.tv_release_recuit);
        release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < fuli_list.size(); i++) {
                    benefits = benefits+fuli_list.get(i)+",";
                    if (i+1==fuli_list.size()){
                        benefits = benefits.substring(0,benefits.length()-1);
                    }
                }


                if (MyUitils.isFastClick()){

                    if (TextUtils.isEmpty(theme.getText().toString())){
                        ToastUtils.shortToast("请输入招聘主题");
                    }else {
                        if (TextUtils.isEmpty(job.getText().toString())){
                            ToastUtils.shortToast("请输入职位职责");
                        }else {
                            if (TextUtils.isEmpty(tv_show_people.getText().toString())){
                                ToastUtils.shortToast("请选择招聘人数");
                            }else {
                                if (TextUtils.isEmpty(tv_show_xinzi.getText().toString())){
                                    ToastUtils.shortToast("请选择工作薪资");
                                }else {
                                    if (TextUtils.isEmpty(tv_show_type.getText().toString())){
                                        ToastUtils.shortToast("请选择专业类型");
                                    }else {
                                        if (TextUtils.isEmpty(tv_show_address.getText().toString())){
                                            ToastUtils.shortToast("请选择工作地点");
                                        }else {
                                            if (TextUtils.isEmpty(require.getText().toString())){
                                                ToastUtils.shortToast("请输入职位要求");
                                            }else {
                                                if (fuli_list.size()==0){
                                                    ToastUtils.shortToast("请选择福利待遇");
                                                }else {
                                                    if (arrayList.size()==0){
                                                        ToastUtils.shortToast("请上传公司/片场图片");
                                                    }else {
                                                        if (TextUtils.isEmpty(name.getText().toString())){
                                                            ToastUtils.shortToast("请输入联系人姓名");
                                                        }else {
                                                            if (TextUtils.isEmpty(phone.getText().toString())){
                                                                ToastUtils.shortToast("请输入联系电话");
                                                            }else {
                                                                if (MyUitils.isCellphone(phone.getText().toString())==false){
                                                                    ToastUtils.shortToast("请输入正确的联系电话");
                                                                }else {
                                                                    if (TextUtils.isEmpty(email.getText().toString())){
                                                                        ToastUtils.shortToast("请输入联系邮箱");
                                                                    }else {
                                                                        if (MyUitils.isEmail(email.getText().toString())==false){
                                                                            ToastUtils.shortToast("请输入正确的联系邮箱");
                                                                        }else {
                                                                            if (TextUtils.isEmpty(address.getText().toString())){
                                                                                ToastUtils.shortToast("请输入公司地址");
                                                                            }else {
                                                                                if (TextUtils.isEmpty(comname.getText().toString())){
                                                                                    ToastUtils.shortToast("请输入公司名称");
                                                                                }else {
                                                                                    beginupload(arrayList,benefits);
                                                                                }
                                                                            }
                                                                        }

                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }else {
                    ToastUtils.shortToast("短时间内请勿重复提交");
                }
            }
        });

    }

    @BindView(R.id.recuit_peoplenum)
    TextView tv_show_people;
    @BindView(R.id.recuit_xinzi)
    TextView tv_show_xinzi;
    @BindView(R.id.recuit_type)
    TextView tv_show_type;
    @BindView(R.id.recuit_address)
    TextView tv_show_address;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_num_recuit:
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);
                OptionsPickerView pvOptions = new OptionsPickerBuilder(RecuitActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items_people.get(options1).getPickerViewText();
                        tv_show_people.setText(tx);
                    }
                }).build();
                pvOptions.setPicker(options1Items_people);
                pvOptions.show();

                break;
            case R.id.rl_money_recuit:
                InputMethodManager inputmanger_xinzi = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger_xinzi.hideSoftInputFromWindow(v.getWindowToken(), 0);
                OptionsPickerView pvOptions_xinzi = new OptionsPickerBuilder(RecuitActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items_xinzi.get(options1).getPickerViewText();
                        tv_show_xinzi.setText(tx);
                    }
                }).build();
                pvOptions_xinzi.setPicker(options1Items_xinzi);
                pvOptions_xinzi.show();

                break;
            case R.id.rl_major_recuit:
                InputMethodManager inputmanger_type = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger_type.hideSoftInputFromWindow(v.getWindowToken(), 0);
                OptionsPickerView pvOptions_type = new OptionsPickerBuilder(RecuitActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items_type.get(options1).getPickerViewText();
                        tv_show_type.setText(tx);
                    }
                }).build();
                pvOptions_type.setPicker(options1Items_type);
                pvOptions_type.show();

                break;
            case R.id.rl_addre_recuit:
                InputMethodManager inputmanger_address = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger_address.hideSoftInputFromWindow(v.getWindowToken(), 0);
                OptionsPickerView pvOptions_address = new OptionsPickerBuilder(RecuitActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = options1Items.get(options1).getPickerViewText()
                                + options2Items.get(options1).get(option2);
                        tv_show_address.setText(tx);
                    }
                }).build();
                pvOptions_address.setPicker(options1Items, options2Items);
                pvOptions_address.show();

                break;
        }
    }

    ArrayList<String> arrayList = new ArrayList<>();
    PublishAdapter publishAdapter;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            switch (requestCode) {
                // 多选
                case REQUEST_CAMERA_CODE:
                    //多选按钮隐藏
                    img_fengmian_img.setVisibility(View.GONE);

                    arrayList = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);

                    publishAdapter = new PublishAdapter(RecuitActivity.this,arrayList,callBack);

                    imgs.setAdapter(publishAdapter);

                    publishAdapter.notifyDataSetChanged();
                    break;
            }
        }
    }
    PublishAdapter.CallBack callBack = new PublishAdapter.CallBack() {
        @Override
        public void clalback() {
            publishAdapter.notifyDataSetChanged();
        }
    };
    private void getOptionData() {

        /**
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */


        //选项1
        options1Items.add(new ProvinceBean(0, "北京", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "天津", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(2, "上海", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(3, "重庆", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(4, "河北", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(5, "山西", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(6, "内蒙古", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(7, "辽宁", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(8, "吉林", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(9, "黑龙江", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(10, "江苏", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(11, "浙江", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(12, "安徽", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(13, "福建", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(14, "江西", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(15, "山东", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(16, "河南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(17, "湖北", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(18, "湖南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(19, "广东", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(20, "广西", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(21, "海南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(22, "四川", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(23, "贵州", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(24, "云南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(25, "西藏", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(26, "陕西", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(27, "甘肃", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(28, "青海", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(29, "宁夏", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(30, "新疆", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(31, "香港", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(32, "澳门", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(33, "台湾", "描述部分", "其他数据"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("东城区");
        options2Items_01.add("西城区");
        options2Items_01.add("崇文区");
        options2Items_01.add("宣武区");
        options2Items_01.add("朝阳区");
        options2Items_01.add("丰台区");
        options2Items_01.add("石景山区");
        options2Items_01.add("海淀区");
        options2Items_01.add("门头沟区");
        options2Items_01.add("房山区");
        options2Items_01.add("通州区");
        options2Items_01.add("顺义区");
        options2Items_01.add("昌平区");
        options2Items_01.add("大兴区");
        options2Items_01.add("怀柔区");
        options2Items_01.add("平谷区");
        options2Items_01.add("密云县");
        options2Items_01.add("延庆县");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("和平区");
        options2Items_02.add("河东区");
        options2Items_02.add("河西区");
        options2Items_02.add("南开区");
        options2Items_02.add("河北区");
        options2Items_02.add("红桥区");
        options2Items_02.add("滨海新区");
        options2Items_02.add("东丽区");
        options2Items_02.add("西青区");
        options2Items_02.add("津南区");
        options2Items_02.add("北辰区");
        options2Items_02.add("武清区");
        options2Items_02.add("宝坻区");
        options2Items_02.add("宁河区");
        options2Items_02.add("静海区");
        options2Items_02.add("蓟县");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("黄浦区");
        options2Items_03.add("卢湾区");
        options2Items_03.add("徐汇区");
        options2Items_03.add("长宁区");
        options2Items_03.add("静安区");
        options2Items_03.add("普陀区");
        options2Items_03.add("闸北区");
        options2Items_03.add("杨浦区");
        options2Items_03.add("虹口区");
        options2Items_03.add("闵行区");
        options2Items_03.add("宝山区");
        options2Items_03.add("嘉定区");
        options2Items_03.add("浦东新区");
        options2Items_03.add("金山区");
        options2Items_03.add("松江区");
        options2Items_03.add("青浦区");
        options2Items_03.add("南汇区");
        options2Items_03.add("奉贤区");
        options2Items_03.add("崇明县");
        ArrayList<String> options2Items_04 = new ArrayList<>();
        options2Items_04.add("渝中区");
        options2Items_04.add("大渡口区");
        options2Items_04.add("江北区");
        options2Items_04.add("沙坪坝区");
        options2Items_04.add("九龙坡区");
        options2Items_04.add("南岸区");
        options2Items_04.add("北碚区");
        options2Items_04.add("万盛区");
        options2Items_04.add("双桥区");
        options2Items_04.add("渝北区");
        options2Items_04.add("巴南区");
        options2Items_04.add("万州区");
        options2Items_04.add("涪陵区");
        options2Items_04.add("黔江区");
        options2Items_04.add("长寿区");
        options2Items_04.add("江津区");
        options2Items_04.add("永川区");
        options2Items_04.add("南川区");
        ArrayList<String> options2Items_05 = new ArrayList<>();
        options2Items_05.add("石家庄");
        options2Items_05.add("唐山");
        options2Items_05.add("秦皇岛");
        options2Items_05.add("邯郸");
        options2Items_05.add("邢台");
        options2Items_05.add("保定");
        options2Items_05.add("张家口");
        options2Items_05.add("承德");
        options2Items_05.add("沧州");
        options2Items_05.add("廊坊");
        options2Items_05.add("衡水");
        ArrayList<String> options2Items_06 = new ArrayList<>();
        options2Items_06.add("太原");
        options2Items_06.add("大同");
        options2Items_06.add("阳泉");
        options2Items_06.add("长治");
        options2Items_06.add("晋城");
        options2Items_06.add("朔州");
        options2Items_06.add("晋中");
        options2Items_06.add("运城");
        options2Items_06.add("忻州");
        options2Items_06.add("临汾");
        options2Items_06.add("吕梁");
        ArrayList<String> options2Items_07 = new ArrayList<>();
        options2Items_07.add("呼和浩特");
        options2Items_07.add("包头");
        options2Items_07.add("乌海");
        options2Items_07.add("赤峰");
        options2Items_07.add("通辽");
        options2Items_07.add("鄂尔多斯");
        options2Items_07.add("呼伦贝尔");
        options2Items_07.add("巴彦淖尔");
        options2Items_07.add("乌兰察布");
        options2Items_07.add("锡林郭勒盟");
        options2Items_07.add("兴安盟");
        options2Items_07.add("阿拉善盟");
        ArrayList<String> options2Items_08 = new ArrayList<>();
        options2Items_08.add("沈阳");
        options2Items_08.add("大连");
        options2Items_08.add("金州");
        options2Items_08.add("鞍山");
        options2Items_08.add("抚顺");
        options2Items_08.add("本溪");
        options2Items_08.add("丹东");
        options2Items_08.add("锦州");
        options2Items_08.add("营口");
        options2Items_08.add("阜新");
        options2Items_08.add("辽阳");
        options2Items_08.add("盘锦");
        options2Items_08.add("铁岭");
        options2Items_08.add("朝阳");
        options2Items_08.add("葫芦岛");
        ArrayList<String> options2Items_09 = new ArrayList<>();
        options2Items_09.add("长春");
        options2Items_09.add("吉林");
        options2Items_09.add("四平");
        options2Items_09.add("辽源");
        options2Items_09.add("通化");
        options2Items_09.add("白山");
        options2Items_09.add("松原");
        options2Items_09.add("白城");
        options2Items_09.add("延边");
        ArrayList<String> options2Items_10 = new ArrayList<>();
        options2Items_10.add("哈尔滨");
        options2Items_10.add("大庆");
        options2Items_10.add("齐齐哈尔");
        options2Items_10.add("佳木斯");
        options2Items_10.add("鸡西");
        options2Items_10.add("鹤岗");
        options2Items_10.add("双鸭山");
        options2Items_10.add("牡丹江");
        options2Items_10.add("伊春");
        options2Items_10.add("七台河");
        options2Items_10.add("黑河");
        options2Items_10.add("绥化");
        options2Items_10.add("大兴安岭");
        ArrayList<String> options2Items_11 = new ArrayList<>();
        options2Items_11.add("南京");
        options2Items_11.add("镇江");
        options2Items_11.add("常州");
        options2Items_11.add("无锡");
        options2Items_11.add("苏州");
        options2Items_11.add("徐州");
        options2Items_11.add("连云港");
        options2Items_11.add("淮安");
        options2Items_11.add("盐城");
        options2Items_11.add("扬州");
        options2Items_11.add("泰州");
        options2Items_11.add("南通");
        options2Items_11.add("宿迁");
        ArrayList<String> options2Items_12 = new ArrayList<>();
        options2Items_12.add("杭州");
        options2Items_12.add("嘉兴");
        options2Items_12.add("湖州");
        options2Items_12.add("宁波");
        options2Items_12.add("金华");
        options2Items_12.add("温州");
        options2Items_12.add("丽水");
        options2Items_12.add("绍兴");
        options2Items_12.add("衢州");
        options2Items_12.add("舟山");
        options2Items_12.add("台州");
        ArrayList<String> options2Items_13 = new ArrayList<>();
        options2Items_13.add("合肥");
        options2Items_13.add("蚌埠");
        options2Items_13.add("芜湖");
        options2Items_13.add("淮南");
        options2Items_13.add("亳州");
        options2Items_13.add("阜阳");
        options2Items_13.add("淮北");
        options2Items_13.add("宿州");
        options2Items_13.add("滁州");
        options2Items_13.add("安庆");
        options2Items_13.add("巢湖");
        options2Items_13.add("马鞍山");
        options2Items_13.add("宣城");
        options2Items_13.add("黄山");
        options2Items_13.add("池州");
        options2Items_13.add("铜陵");
        ArrayList<String> options2Items_14 = new ArrayList<>();
        options2Items_14.add("福州");
        options2Items_14.add("厦门");
        options2Items_14.add("泉州");
        options2Items_14.add("三明");
        options2Items_14.add("南平");
        options2Items_14.add("漳州");
        options2Items_14.add("莆田");
        options2Items_14.add("宁德");
        options2Items_14.add("龙岩");
        ArrayList<String> options2Items_15 = new ArrayList<>();
        options2Items_15.add("南昌");
        options2Items_15.add("九江");
        options2Items_15.add("赣州");
        options2Items_15.add("吉安");
        options2Items_15.add("鹰潭");
        options2Items_15.add("上饶");
        options2Items_15.add("萍乡");
        options2Items_15.add("景德镇");
        options2Items_15.add("新余");
        options2Items_15.add("宜春");
        options2Items_15.add("抚州");
        ArrayList<String> options2Items_16 = new ArrayList<>();
        options2Items_16.add("济南");
        options2Items_16.add("青岛");
        options2Items_16.add("淄博");
        options2Items_16.add("枣庄");
        options2Items_16.add("东营");
        options2Items_16.add("烟台");
        options2Items_16.add("潍坊");
        options2Items_16.add("济宁");
        options2Items_16.add("泰安");
        options2Items_16.add("威海");
        options2Items_16.add("日照");
        options2Items_16.add("莱芜");
        options2Items_16.add("临沂");
        options2Items_16.add("德州");
        options2Items_16.add("聊城");
        options2Items_16.add("菏泽");
        options2Items_16.add("滨州");
        ArrayList<String> options2Items_17 = new ArrayList<>();
        options2Items_17.add("郑州");
        options2Items_17.add("洛阳");
        options2Items_17.add("开封");
        options2Items_17.add("漯河");
        options2Items_17.add("安阳");
        options2Items_17.add("新乡");
        options2Items_17.add("周口");
        options2Items_17.add("三门峡");
        options2Items_17.add("焦作");
        options2Items_17.add("平顶山");
        options2Items_17.add("信阳");
        options2Items_17.add("南阳");
        options2Items_17.add("鹤壁");
        options2Items_17.add("濮阳");
        options2Items_17.add("许昌");
        options2Items_17.add("商丘");
        options2Items_17.add("驻马店");
        ArrayList<String> options2Items_18 = new ArrayList<>();
        options2Items_18.add("武汉");
        options2Items_18.add("襄樊");
        options2Items_18.add("宜昌");
        options2Items_18.add("黄石");
        options2Items_18.add("鄂州");
        options2Items_18.add("随州");
        options2Items_18.add("荆州");
        options2Items_18.add("荆门");
        options2Items_18.add("十堰");
        options2Items_18.add("孝感");
        options2Items_18.add("黄冈");
        options2Items_18.add("咸宁");
        options2Items_18.add("恩施");
        ArrayList<String> options2Items_19 = new ArrayList<>();
        options2Items_19.add("长沙");
        options2Items_19.add("株洲");
        options2Items_19.add("湘潭");
        options2Items_19.add("衡阳");
        options2Items_19.add("岳阳");
        options2Items_19.add("郴州");
        options2Items_19.add("永州");
        options2Items_19.add("邵阳");
        options2Items_19.add("怀化");
        options2Items_19.add("常德");
        options2Items_19.add("益阳");
        options2Items_19.add("张家界");
        options2Items_19.add("娄底");
        options2Items_19.add("湘西");
        ArrayList<String> options2Items_20 = new ArrayList<>();
        options2Items_20.add("广州");
        options2Items_20.add("深圳");
        options2Items_20.add("汕头");
        options2Items_20.add("惠州");
        options2Items_20.add("珠海");
        options2Items_20.add("揭阳");
        options2Items_20.add("佛山");
        options2Items_20.add("河源");
        options2Items_20.add("阳江");
        options2Items_20.add("茂名");
        options2Items_20.add("湛江");
        options2Items_20.add("梅州");
        options2Items_20.add("肇庆");
        options2Items_20.add("韶关");
        options2Items_20.add("潮州");
        options2Items_20.add("东莞");
        options2Items_20.add("中山");
        options2Items_20.add("清远");
        options2Items_20.add("江门");
        options2Items_20.add("汕尾");
        options2Items_20.add("云浮");
        ArrayList<String> options2Items_21 = new ArrayList<>();
        options2Items_21.add("南宁");
        options2Items_21.add("柳州");
        options2Items_21.add("桂林");
        options2Items_21.add("梧州");
        options2Items_21.add("北海");
        options2Items_21.add("崇左");
        options2Items_21.add("来宾");
        options2Items_21.add("贺州");
        options2Items_21.add("玉林");
        options2Items_21.add("百色");
        options2Items_21.add("河池");
        options2Items_21.add("钦州");
        options2Items_21.add("防城港");
        options2Items_21.add("贵港");
        ArrayList<String> options2Items_22 = new ArrayList<>();
        options2Items_22.add("海口");
        options2Items_22.add("三亚");
        ArrayList<String> options2Items_23 = new ArrayList<>();
        options2Items_23.add("成都市");
        options2Items_23.add("自贡市");
        options2Items_23.add("攀枝花市");
        options2Items_23.add("泸州市");
        options2Items_23.add("德阳市");
        options2Items_23.add("绵阳市");
        options2Items_23.add("广元市");
        options2Items_23.add("遂宁市");
        options2Items_23.add("内江市");
        options2Items_23.add("乐山市");
        options2Items_23.add("南充市");
        options2Items_23.add("眉山市");
        options2Items_23.add("宜宾市");
        options2Items_23.add("广安市");
        options2Items_23.add("达州市");
        options2Items_23.add("雅安市");
        options2Items_23.add("巴中市");
        options2Items_23.add("资阳市");
        options2Items_23.add("阿坝");
        options2Items_23.add("甘孜");
        options2Items_23.add("凉山");
        ArrayList<String> options2Items_24 = new ArrayList<>();
        options2Items_24.add("贵阳");
        options2Items_24.add("六盘水");
        options2Items_24.add("遵义");
        options2Items_24.add("安顺");
        ArrayList<String> options2Items_25 = new ArrayList<>();
        options2Items_25.add("昆明");
        options2Items_25.add("曲靖");
        options2Items_25.add("玉溪");
        options2Items_25.add("保山");
        options2Items_25.add("昭通");
        options2Items_25.add("丽江");
        options2Items_25.add("普洱");
        options2Items_25.add("临沧");
        ArrayList<String> options2Items_26 = new ArrayList<>();
        options2Items_26.add("拉萨");
        options2Items_26.add("昌都");
        options2Items_26.add("山南");
        options2Items_26.add("日喀则");
        options2Items_26.add("那曲");
        options2Items_26.add("阿里");
        options2Items_26.add("林芝");
        ArrayList<String> options2Items_27 = new ArrayList<>();
        options2Items_27.add("西安");
        options2Items_27.add("咸阳");
        options2Items_27.add("铜川");
        options2Items_27.add("延安");
        options2Items_27.add("宝鸡");
        options2Items_27.add("渭南");
        options2Items_27.add("汉中");
        options2Items_27.add("安康");
        options2Items_27.add("商洛");
        options2Items_27.add("榆林");
        ArrayList<String> options2Items_28 = new ArrayList<>();
        options2Items_28.add("兰州");
        options2Items_28.add("天水");
        options2Items_28.add("平凉");
        options2Items_28.add("酒泉");
        options2Items_28.add("嘉峪关");
        options2Items_28.add("金昌");
        options2Items_28.add("白银");
        options2Items_28.add("武威");
        options2Items_28.add("张掖");
        options2Items_28.add("庆阳");
        options2Items_28.add("定西");
        options2Items_28.add("陇南");
        options2Items_28.add("临夏");
        options2Items_28.add("甘南");
        ArrayList<String> options2Items_29 = new ArrayList<>();
        options2Items_29.add("西宁");
        options2Items_29.add("海东");
        options2Items_29.add("海北");
        options2Items_29.add("黄南");
        options2Items_29.add("海南");
        options2Items_29.add("果洛");
        options2Items_29.add("玉树");
        options2Items_29.add("海西");
        ArrayList<String> options2Items_30 = new ArrayList<>();
        options2Items_30.add("银川");
        options2Items_30.add("石嘴山");
        options2Items_30.add("吴忠");
        options2Items_30.add("固原");
        options2Items_30.add("中卫");
        ArrayList<String> options2Items_31 = new ArrayList<>();
        options2Items_31.add("乌鲁木齐");
        options2Items_31.add("克拉玛依");
        options2Items_31.add("吐鲁番");
        options2Items_31.add("哈密");
        options2Items_31.add("昌吉");
        options2Items_31.add("博尔塔拉蒙古");
        options2Items_31.add("巴音郭楞蒙古");
        options2Items_31.add("阿克苏");
        options2Items_31.add("克孜勒苏柯尔克孜");
        options2Items_31.add("喀什");
        options2Items_31.add("和田");
        options2Items_31.add("伊犁");
        options2Items_31.add("塔城");
        options2Items_31.add("阿勒泰");
        ArrayList<String> options2Items_32 = new ArrayList<>();
        options2Items_32.add("中西区");
        options2Items_32.add("东区");
        options2Items_32.add("九龙城区");
        options2Items_32.add("观塘区");
        options2Items_32.add("南区");
        options2Items_32.add("深水埗区");
        options2Items_32.add("黄大仙区");
        options2Items_32.add("湾仔区");
        options2Items_32.add("油尖旺区");
        options2Items_32.add("离岛区");
        options2Items_32.add("葵青区");
        options2Items_32.add("北区");
        options2Items_32.add("西贡区");
        options2Items_32.add("沙田区");
        options2Items_32.add("屯门区");
        options2Items_32.add("大埔区");
        options2Items_32.add("荃湾区");
        options2Items_32.add("元朗区");
        ArrayList<String> options2Items_33 = new ArrayList<>();
        options2Items_33.add("花地玛堂区");
        options2Items_33.add("圣安多尼堂区");
        options2Items_33.add("望德堂区");
        options2Items_33.add("大堂区");
        options2Items_33.add("风顺堂区");
        options2Items_33.add("离岛");
        options2Items_33.add("氹仔");
        options2Items_33.add("路环 ");
        ArrayList<String> options2Items_34 = new ArrayList<>();
        options2Items_34.add("台北");
        options2Items_34.add("台中");
        options2Items_34.add("基隆");
        options2Items_34.add("高雄");
        options2Items_34.add("台南");
        options2Items_34.add("新竹");
        options2Items_34.add("嘉义");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);
        options2Items.add(options2Items_04);
        options2Items.add(options2Items_05);
        options2Items.add(options2Items_06);
        options2Items.add(options2Items_07);
        options2Items.add(options2Items_08);
        options2Items.add(options2Items_09);
        options2Items.add(options2Items_10);
        options2Items.add(options2Items_11);
        options2Items.add(options2Items_12);
        options2Items.add(options2Items_13);
        options2Items.add(options2Items_14);
        options2Items.add(options2Items_15);
        options2Items.add(options2Items_16);
        options2Items.add(options2Items_17);
        options2Items.add(options2Items_18);
        options2Items.add(options2Items_19);
        options2Items.add(options2Items_20);
        options2Items.add(options2Items_21);
        options2Items.add(options2Items_22);
        options2Items.add(options2Items_23);
        options2Items.add(options2Items_24);
        options2Items.add(options2Items_25);
        options2Items.add(options2Items_26);
        options2Items.add(options2Items_27);
        options2Items.add(options2Items_28);
        options2Items.add(options2Items_29);
        options2Items.add(options2Items_30);
        options2Items.add(options2Items_31);
        options2Items.add(options2Items_32);
        options2Items.add(options2Items_33);
        options2Items.add(options2Items_34);
        /*--------数据源添加完毕---------*/
    }

    String img_path="";
    public void beginupload(ArrayList<String> arrayList,String imgname) {
        showMyDialog();
        Log.e(TAG, "beginupload: 长度"+arrayList.size() );
//        https://star-1.oss-cn-beijing.aliyuncs.com
        final String endpoint = "oss-cn-beijing.aliyuncs.com";
        final String startpoint = "star-1";
        //     明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAI8ygujYgDvLJ9", "nLrO1bpn9IOpEu0tt0zyAaChc22j0c");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        // 构造上传请求
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式

        for (int i = 0; i <arrayList.size() ; i++) {
            final String objectname =df.format(new Date())+i+ ".png";

            PutObjectRequest put = new PutObjectRequest(startpoint, objectname,arrayList.get(i));
            // 异步上传时可以设置进度回调
            put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
                @Override
                public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

                }
            });
            int finalI = i;
            OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                    Log.e("上传结果","https://star-1.oss-cn-beijing.aliyuncs.com/"+objectname);
                    img_path = img_path+ objectname+",";

                    if ((finalI +1)==arrayList.size()){
                        //当他都上传成功了，请求网络接口
                        img_path = img_path.substring(0,img_path.length()-1);
//                        Log.e(TAG, "onSuccess: 结果测试"+img_path );
                        upinfo(img_path,imgname);
                    }
                }

                @Override
                public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                    ToastUtils.shortToast("上传失败导致信息无法发布");
                    dissDialog();
                    // 请求异常
                    if (clientExcepion != null) {
                        // 本地异常如网络异常等
                        clientExcepion.printStackTrace();
                        ToastUtils.shortToast("上传失败导致信息无法发布");
                    }
                    if (serviceException != null) {
                    }
                }
            });


        }

    }
    private LoadingDailog loadBuilder;
    private void showMyDialog(){
        if (loadBuilder==null){
            loadBuilder=new LoadingDailog.Builder(RecuitActivity.this)
                    .setMessage("信息上传中...")
                    .setCancelable(false)
                    .setCancelOutside(false).create();
            loadBuilder.show();
        }

    }
    private void dissDialog(){
        if (loadBuilder!=null){
            loadBuilder.dismiss();
        }
    }
}
