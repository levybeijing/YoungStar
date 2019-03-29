package com.chuanqing.youngstar._mine;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar._mine.company.FragmentJob;
import com.chuanqing.youngstar._mine.company.FragmentPublish;
import com.chuanqing.youngstar._mine.company.SubAccountActivity;
import com.chuanqing.youngstar._mine.fans.CareActivity;
import com.chuanqing.youngstar._mine.student.FragmentStatus;
import com.chuanqing.youngstar._mine.student.FragmentWorks;
import com.chuanqing.youngstar.login.choose.ChooseActivity;
import com.chuanqing.youngstar.myadapter.AdapterMineVP;
import com.chuanqing.youngstar.myadapter.AdapterMineiRV;
import com.chuanqing.youngstar.mybean.FragMinecBean;
import com.chuanqing.youngstar.mybean.FragMinefBean;
import com.chuanqing.youngstar.mybean.FragMinesBean;
import com.chuanqing.youngstar.mybean.MineWorksBean;
import com.chuanqing.youngstar.tools.CircleImageView;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 我的页面
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private List<Fragment> list=new ArrayList<>();
    private int identity=3;
    //    11111111111
    private ViewPager vp;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv_wallet;
    private TextView tv_lable;
    private TextView tv_id;
    private CircleImageView iv_photo;
    private ImageView iv_sex;
    //    22222222222
    private ViewPager vp2;
    private ImageView iv2_photo;
    private TextView tv2_id;
    private TextView tv2_wallet;
    private TextView tv2_care;
//    3333333333
    private ImageView iv3_photo;
    private TextView tv3_wallet;
    private TextView tv3_care;
    private TextView tv3_id;

    private int pagei=1;
    private AdapterMineiRV adapter3;
//    44444444444
    private ImageView iv4_photo;
    private TextView tv4_id;
    private TextView tv4_wallet;
    private TextView tv4_care;
    private RelativeLayout rl_top3;
    private RelativeLayout rl_top4;
    private RelativeLayout rl_top2;
    private RelativeLayout rl_top1;

    //
    public MineFragment() {
    }

    private int audit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        identity = (int) SharedPFUtils.getParam(getContext(), "identity", 4);
        audit = (int) SharedPFUtils.getParam(getContext(), "audit", -1);
        switch (audit){
            case 1:
                return inflater.inflate(R.layout.activity_auditing, container, false);
            case 2:
                return inflater.inflate(R.layout.activity_auditfailed, container, false);
        }
        switch (identity){
            case 1:
                return inflater.inflate(R.layout.fragment_mines, container, false);
            case 2:
                return inflater.inflate(R.layout.fragment_minec, container, false);
            case 3:
                return inflater.inflate(R.layout.fragment_minei, container, false);
            case 4:
                return inflater.inflate(R.layout.fragment_minef, container, false);
        }
        list.clear();
        return inflater.inflate(R.layout.fragment_minef, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        switch (audit){
            case 1:
                view.findViewById(R.id.tv_back_auditing).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent();
                        intent.setAction(Urls.TOHOMECAST);
                        getContext().sendBroadcast(intent);
                    }
                });
                return;
            case 2:
                TextView info = view.findViewById(R.id.tv2_auditfailed);
//                设置返回失败原因信息
                info.setText("失败原因×××××××××");
                view.findViewById(R.id.iv_back_auditfailed).setVisibility(View.GONE);
                view.findViewById(R.id.tv_again_auditfailed).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getContext(),ChooseActivity.class);
                        startActivity(intent);
                    }
                });
                return;
        }
        switch (identity){
            case 1:
                rl_top1 = view.findViewById(R.id.rl_mines);
                rl_top1.setOnClickListener(this);

                vp = view.findViewById(R.id.vp_mines);

                RadioButton rb1 = view.findViewById(R.id.rb1_mines);
                RadioButton rb2 = view.findViewById(R.id.rb2_mines);
                rb1.setOnClickListener(this);
                rb2.setOnClickListener(this);
                RadioGroup rg = view.findViewById(R.id.rg_mines);

                tv1 = view.findViewById(R.id.tv1_mines);
                tv2 = view.findViewById(R.id.tv2_mines);
                tv3 = view.findViewById(R.id.tv3_mines);

                tv_wallet = view.findViewById(R.id.tv_wallet_mines);
                tv_lable = view.findViewById(R.id.tv_lable_mines);
                tv_id = view.findViewById(R.id.tv_id_mines);

                view.findViewById(R.id.iv_msg_mines).setOnClickListener(this);
                view.findViewById(R.id.iv_call_mines).setOnClickListener(this);
                view.findViewById(R.id.iv_share_mines).setOnClickListener(this);

                iv_photo = view.findViewById(R.id.iv_photo_mines);
                iv_sex = view.findViewById(R.id.iv_sex_mines);
                iv_photo.setOnClickListener(this);
                list.add(new FragmentStatus());
                list.add(new FragmentWorks());
                vp.setAdapter(new AdapterMineVP(getChildFragmentManager(),list));

//                TabLayout tab = view.findViewById(R.id.tab_mines);
//                tab.setTabMode(TabLayout.MODE_FIXED);
//                tab.setupWithViewPager(vp);
//
//                TabLayout.Tab tab11 = tab.getTabAt(0);
//                tab11.setText("动态");
//                TabLayout.Tab tab12 = tab.getTabAt(1);
//                tab12.setText("作品集");

                vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int i) {
                        if (i==0){
                            rb1.setChecked(true);
                        }else{
                            rb2.setChecked(true);
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
                AppBarLayout appbars = view.findViewById(R.id.appbar_mines);
                Toolbar toolbars = view.findViewById(R.id.toolbar_mines);
                appbars.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        toolbars.setBackgroundColor(changeAlpha(getResources().getColor(R.color.mainColor),Math.abs(i*1.0f)/appbars.getTotalScrollRange()));
//                        tab.setBackgroundColor(changeAlpha(getResources().getColor(R.color.mainColor),1-Math.abs(i*1.0f)/appbars.getTotalScrollRange()));
                        if (i==100){
//                            tab.setVisibility(View.VISIBLE);
                            rg.setVisibility(View.INVISIBLE);
                        }
                    }
                });
//        //设置tab1
//        TabLayout.Tab tab1 = tab.getTabAt(0);
////        tab1.setCustomView(R.layout.item_tab_mall);//给每一个tab设置view
////        tab1.getCustomView().findViewById(R.id.iv_item_tab_mall).setSelected(true);//第一个tab被选中
////        ImageView iv1 = tab1.getCustomView().findViewById(R.id.iv_item_tab_mall);
////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
////            iv1.setBackground(getResources().getDrawable(R.mipmap.tab_reme));//设置tab上的文字
////        }
                break;
            case 2:
                rl_top2 = view.findViewById(R.id.rl_minec);
                rl_top2.setOnClickListener(this);

                iv2_photo = view.findViewById(R.id.iv_photo_minec);
                tv2_id = view.findViewById(R.id.tv_id_minec);
                tv2_wallet = view.findViewById(R.id.tv_wallet_minec);
                tv2_care = view.findViewById(R.id.tv_care_minec);
                iv2_photo.setOnClickListener(this);

                view.findViewById(R.id.iv_msg_minec).setOnClickListener(this);
                view.findViewById(R.id.iv_tocall_minec).setOnClickListener(this);
                view.findViewById(R.id.iv_manage_minec).setOnClickListener(this);

                RadioButton rb21 = view.findViewById(R.id.rb1_minec);
                RadioButton rb22 = view.findViewById(R.id.rb2_minec);
                rb21.setOnClickListener(this);
                rb22.setOnClickListener(this);

                vp2 = view.findViewById(R.id.vp_minec);

                vp2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int i) {
                        if (i==0){
                            rb21.setChecked(true);
                        }else{
                            rb22.setChecked(true);
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
                AppBarLayout appbarc = view.findViewById(R.id.appbar_minec);
                Toolbar toolbarc = view.findViewById(R.id.toolbar_minec);
                appbarc.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        toolbarc.setBackgroundColor(changeAlpha(getResources().getColor(R.color.mainColor),Math.abs(i*1.0f)/appbarc.getTotalScrollRange()));
//                        tab.setBackgroundColor(changeAlpha(getResources().getColor(R.color.mainColor),1-Math.abs(i*1.0f)/appbars.getTotalScrollRange()));
//                        if (i==100){
//                            tab.setVisibility(View.VISIBLE);
//                            rg.setVisibility(View.INVISIBLE);
//                        }
                    }
                });
                list.add(new FragmentPublish());
                list.add(new FragmentJob());

                AdapterMineVP adapter=new AdapterMineVP(getChildFragmentManager(),list);
                vp2.setAdapter(adapter);

                TabLayout tab2 = view.findViewById(R.id.tab_minec);
                tab2.setTabMode(TabLayout.MODE_FIXED);
                tab2.setupWithViewPager(vp2);

                TabLayout.Tab tab21 = tab2.getTabAt(0);
                tab21.setText("公告");
                TabLayout.Tab tab22 = tab2.getTabAt(1);
                tab22.setText("星职场");
                break;
            case 3:
                rl_top3 = view.findViewById(R.id.rl_minei);
                rl_top3.setOnClickListener(this);

                iv3_photo = view.findViewById(R.id.iv_photo_minei);
                tv3_wallet = view.findViewById(R.id.tv_wallet_minei);
                tv3_care = view.findViewById(R.id.tv_care_minei);
                tv3_id = view.findViewById(R.id.tv_id_minei);
                iv3_photo.setOnClickListener(this);

                view.findViewById(R.id.iv_msg_minei).setOnClickListener(this);
                view.findViewById(R.id.iv_tocall_minei).setOnClickListener(this);

                RecyclerView rv3 = view.findViewById(R.id.rv_minei);
                GridLayoutManager manager=new GridLayoutManager(getContext(),3);
                rv3.setLayoutManager(manager);

                adapter3 = new AdapterMineiRV(getContext());
                rv3.setAdapter(adapter3);
                //工具栏透明渐变
                AppBarLayout appbari = view.findViewById(R.id.appbar_minei);
                Toolbar toolbari = view.findViewById(R.id.toolbar_minei);
                appbari.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        toolbari.setBackgroundColor(changeAlpha(getResources().getColor(R.color.mainColor),Math.abs(i*1.0f)/appbari.getTotalScrollRange()));
                    }
                });
                requestWorks();
                break;
            case 4:
                rl_top4 = view.findViewById(R.id.rl_minef);
                rl_top4.setOnClickListener(this);

                iv4_photo = view.findViewById(R.id.iv_photo_minef);
                tv4_id = view.findViewById(R.id.tv_id_minef);
                view.findViewById(R.id.tv_change_minef).setOnClickListener(this);
                tv4_wallet = view.findViewById(R.id.tv_wallet_minef);
                view.findViewById(R.id.ll_wallet_minef).setOnClickListener(this);
                view.findViewById(R.id.ll_care_minef).setOnClickListener(this);
                tv4_care = view.findViewById(R.id.tv_care_minef);
                view.findViewById(R.id.iv_msg_minef).setOnClickListener(this);
                view.findViewById(R.id.iv_tocall_minef).setOnClickListener(this);
                iv4_photo.setOnClickListener(this);

                RecyclerView rv4 = view.findViewById(R.id.rv_minef);
                GridLayoutManager manager4=new GridLayoutManager(getContext(),3);
                rv4.setLayoutManager(manager4);

                adapter3 = new AdapterMineiRV(getContext());
                rv4.setAdapter(adapter3);
                //工具栏透明渐变
                AppBarLayout appbar = view.findViewById(R.id.appbar_minef);
                Toolbar toolbar = view.findViewById(R.id.toolbar_minef);
                appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                    @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                        toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.mainColor),Math.abs(i*1.0f)/appbar.getTotalScrollRange()));
                    }
                });
                requestWorks();
                break;

        }
        request();
    }

    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }

    private void requestWorks() {
        OkGo.post(Urls.getBlogsINMy)//
                .tag(this)//
                .params("userCode", (String)SharedPFUtils.getParam(getContext(),"usercode",""))//文件名
                .params("page", pagei)//墙的ID
                .params("pageSize", 15)//缩略图 省略>?
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "getBlogsINMy"+s);
                        MineWorksBean bean = new Gson().fromJson(s, MineWorksBean.class);
                        List<MineWorksBean.DataBean.PageInfoBean.ListBean> list = bean.getData().getPageInfo().getList();
                        adapter3.setData(list);
                    }
                });
    }

    private void request() {
        OkGo.post(Urls.getUserByCode)//
                .tag(this)//
                .params("userCode", (String) SharedPFUtils.getParam(getContext(),"usercode",""))//文件名
                .params("type",identity)
                //将来写成 identity
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e("=============", "getUserByCode"+s);
                        switch (identity)
                        {
                            case 1:
                                FragMinesBean.DataBean data = new Gson().fromJson(s, FragMinesBean.class).getData();
                                //保存属性值 将来写到拦截器里面
                                SharedPFUtils.setParam(getActivity(),"identity",data.getType());

                                tv_id.setText(data.getUser_code()+"");
                                tv_wallet.setText(data.getStandard_coin()+"");
                                tv_lable.setText(data.getLabel());

                                tv1.setText(data.getActivityUserNum()+"");
                                tv2.setText(data.getUserRecommend()+"");
                                tv3.setText(data.getUserConcernNum()+"");

                                switch (data.getSex()){
                                    case "男":
                                        iv_sex.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.mipmap.sex_man));
                                        break;
                                    case "女":
                                        iv_sex.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.mipmap.sex_woman));
                                        break;
                                }
                                Glide.with(getActivity())
                                        .load(Urls.IMAGEURL+data.getUser_img())
                                        .into(iv_photo);
                                break;
                            case 2:
                                FragMinecBean bean2 = new Gson().fromJson(s, FragMinecBean.class);
                                FragMinecBean.DataBean data2 = bean2.getData();
                                tv2_id.setText(data2.getUser_code());
                                tv2_wallet.setText(data2.getStandard_coin()+"");
                                tv2_care.setText(data2.getUserConcernNum()+"");
                                Glide.with(getActivity()).load(Urls.IMAGEURL+data2.getUser_img()).into(iv2_photo);


                                break;
                            case 3:
                                FragMinefBean bean3 = new Gson().fromJson(s, FragMinefBean.class);
                                FragMinefBean.DataBean data3 = bean3.getData();
                                tv3_id.setText(data3.getUser_code());
                                tv3_care.setText(data3.getUserConcernNum()+"");
                                tv3_wallet.setText(data3.getStandard_coin()+"");
                                Glide.with(getActivity()).load(Urls.IMAGEURL+data3.getUser_img()).into(iv3_photo);
                                break;
                            case 4:
                                FragMinefBean bean4 = new Gson().fromJson(s, FragMinefBean.class);
                                FragMinefBean.DataBean data4 = bean4.getData();
                                tv4_id.setText(data4.getUser_code());
                                tv4_care.setText(data4.getUserConcernNum()+"");
                                tv4_wallet.setText(data4.getStandard_coin()+"");
                                Glide.with(getActivity()).load(Urls.IMAGEURL+data4.getUser_img()).into(iv4_photo);
                                break;
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb1_mines:
                vp.setCurrentItem(0);
                break;
            case R.id.rb2_mines:
                vp.setCurrentItem(1);
                break;
            case R.id.rb1_minec:
                vp2.setCurrentItem(0);
                break;
            case R.id.rb2_minec:
                vp2.setCurrentItem(1);
                break;
            case R.id.iv_msg_mines:
            case R.id.iv_msg_minec:
            case R.id.iv_msg_minei:
            case R.id.iv_msg_minef:
                startActivity(new Intent(getActivity(),OfficalMsgActivity.class));
                break;
            case R.id.tv_change_minef:
                startActivity(new Intent(getActivity(), ChooseActivity.class));
                break;
//                设置背景图片
            case R.id.rl_mines:
//                vp2.setCurrentItem(1);
                break;
            case R.id.rl_minec:
//                vp2.setCurrentItem(1);
                break;
            case R.id.rl_minei:
//                vp2.setCurrentItem(1);
                break;
            case R.id.rl_minef:
//                vp2.setCurrentItem(1);
                break;
            case R.id.iv_photo_mines:
                startActivity(new Intent(getContext(),EditUserDataActivity.class));
                break;
            case R.id.iv_photo_minec:
                startActivity(new Intent(getContext(),EditUserDataActivity.class));
                break;
            case R.id.iv_photo_minei:
                startActivity(new Intent(getContext(),EditUserDataActivity.class));
                break;
            case R.id.iv_photo_minef:
                startActivity(new Intent(getContext(),EditUserDataActivity.class));
                break;
            case R.id.iv_manage_minec:
                startActivity(new Intent(getContext(), SubAccountActivity.class));
                break;
            case R.id.iv_tocall_minec:
            case R.id.iv_tocall_minef:
            case R.id.iv_tocall_minei:
            case R.id.iv_call_mines:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" +Urls.SERVICE);
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.iv_share_mines:
// startActivity(new Intent(getContext(), SubAccountActivity.class));

                break;
            case R.id.ll_wallet_minef:
                startActivity(new Intent(getContext(), WalletActivity.class));
                break;
            case R.id.ll_care_minef:
                Intent care = new Intent(getContext(), CareActivity.class);
                startActivity(care);
                break;

        }
    }

}
