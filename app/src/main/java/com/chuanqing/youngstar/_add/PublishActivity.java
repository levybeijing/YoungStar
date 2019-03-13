package com.chuanqing.youngstar._add;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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
import com.chuanqing.youngstar.BuildConfig;
import com.chuanqing.youngstar.Urls;
import com.chuanqing.youngstar.R;
import com.chuanqing.youngstar.base.BaseActivity;
import com.chuanqing.youngstar.myadapter.PublishAdapter;
import com.chuanqing.youngstar.mybean.CommonBean;
import com.chuanqing.youngstar.tools.Api;
import com.chuanqing.youngstar.tools.DpPxUtil;
import com.chuanqing.youngstar.tools.SharedPFUtils;
import com.chuanqing.youngstar.tools.ToastUtils;
import com.chuanqing.youngstar.tools.UiUtils;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import okhttp3.Call;
import okhttp3.Response;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;
import static com.chuanqing.youngstar.MainActivity.usercodes;

/**
 * 中间按钮--- 发布动态视频
 */
public class PublishActivity extends BaseActivity{
    private static final String TAG = "PublishActivity";
    private static final int REQUEST_CODE_CHOOSE = 23;//定义请求码常量 单选
    private static final int REQUEST_CAMERA_CODE = 24;//定义请求码常量  多选
    private static final int REQUEST_CODE_PICK = 25; //视频

    PublishAdapter adapter;
    ArrayList<String>  arrayList = new ArrayList<>();
    //视频播放器
    @BindView(R.id.publish_player)
    JzvdStd player;
    @BindView(R.id.publish_player_img)
    ImageView player_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishs);
        ButterKnife.bind(this);

        initView();
        setTtitle();
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
    ArrayList<String> imagePaths = new ArrayList<>();
//    ArrayList<String> imagePathsone = new ArrayList<>();
    String media_type = "1"; //用于保存上传的类型是1图片，还是2视频，先默认是1
    boolean info_img = false; //用于判断是否选择了详情图片
    private void setTtitle(){
        int identity = (int)SharedPFUtils.getParam(this, "identity", 4);
        if (identity==1){
            tv_title.setText("发布作品");
        }else{
            tv_title.setText("发布公告");
        }

        left_img.setVisibility(View.VISIBLE);
        //返回
        left_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublishActivity.this.finish();
            }
        });
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(title.getText().toString())){
                    ToastUtils.shortToast("请输入标题");
                }else {
                    if (TextUtils.isEmpty(content.getText().toString())){
                        ToastUtils.shortToast("请输入详情信息");
                    }else {
                        if (media_type.equals("1")){
                            if (fengmian_uri==null&&!cover.exists()){
                                ToastUtils.shortToast("请上传封面图片");
                            }else {
                                if (!info_img){
                                    ToastUtils.shortToast("请上传详情图片");
                                }else {
                                    beginupload(fengmian_uri);
                                }
                            }
                        }else {
                            beginupload(fengmian_uri);
                        }

                    }
                }
            }
        });
    }

    /**
     * 上传信息
     */
    private void upinfo(String path,String imgname) {
//        Log.e(TAG, "upinfo: 返回信息"+path );
        OkGo.post(Api.updongtai)
                .tag(this)
                .params("userCode",usercodes)
                .params("title",title.getText().toString())
                .params("blogDetail",content.getText().toString())
                .params("mediaUrl",path)
                .params("blogImg",imgname)
                .params("type",1)
                .params("mediaType",media_type)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtils.shortToast(e+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        CommonBean commonBean = gson.fromJson(s,CommonBean.class);
                        if (commonBean.getState()==1){
                            dissDialog();
                            ToastUtils.shortToast("上传成功");
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    PublishActivity.this.finish();
                                }
                            },1000);
                        }else {
                            ToastUtils.shortToast(commonBean.getMessage());
                        }
                    }
                });
    }


    private LoadingDailog loadBuilder;
    private void showMyDialog(){
        if (loadBuilder==null){
            loadBuilder=new LoadingDailog.Builder(PublishActivity.this)
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

    @BindView(R.id.publishs_fengmian)
    ImageView img_up_fengmian;
    @BindView(R.id.publishs_info)
    ImageView img_up_info;
    RecyclerView rv;
    EditText title;
    EditText content;
    private void initView() {
        title = findViewById(R.id.et_title_publish);
        content = findViewById(R.id.et_content_publish);
        TextView titleNum = findViewById(R.id.tv_titlenum_publish);
        TextView contentNum = findViewById(R.id.tv_contentnum_publish);
        rv= findViewById(R.id.rv_publish);
        GridLayoutManager manager=new GridLayoutManager(this,3);
        rv.setLayoutManager(manager);
        //标题
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                titleNum.setText("("+title.getText().toString().length()+"/20）");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //详情
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contentNum.setText("("+content.getText().toString().length()+"/200）");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        img_up_fengmian.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
//                //单选
//                PhotoPickerIntent intent = new PhotoPickerIntent(PublishActivity.this);
//                intent.setSelectModel(SelectModel.SINGLE);
//                intent.setShowCarema(false); // 是否显示拍照， 默认false
//                startActivityForResult(intent, REQUEST_CODE_CHOOSE);
//                onClickOpenGallery(v);
                showpop();
            }
        });


        //多选
        img_up_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showPopwindow();
            }
        });

        player_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                media_type = "2";
//                //直接跳到系统相册去选取视频
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, REQUEST_CODE_PICK);
                player.setVisibility(View.GONE);
                player_img.setVisibility(View.GONE);
                img_up_info.setVisibility(View.VISIBLE);

            }
        });
    }
    private File cover;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void showpop() {
        PopupWindow window;
        View pop = LayoutInflater.from(this).inflate(R.layout.popup_camera, null, false);
        window = new PopupWindow(pop, DpPxUtil.dip2px(this,100),DpPxUtil.dip2px(this,120),true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAsDropDown(img_up_fengmian, DpPxUtil.dip2px(this,85),-DpPxUtil.dip2px(this,127.5f),Gravity.CENTER);
        pop.findViewById(R.id.tv_camera_popup).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                window.dismiss();
                //用于保存调用相机拍照后所生成的文件
                cover = new File(Urls.IMGPATH, System.currentTimeMillis() + ".png");
                if (!cover.getParentFile().exists()){
                    cover.getParentFile().mkdirs();
                    try {
                        cover.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //跳转到调用系统相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //  判断版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
                    intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    Uri contentUri = FileProvider.getUriForFile(PublishActivity.this, getPackageName()+".provider", cover);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                    Log.e("getPicFromCamera", contentUri.toString());
                } else {    //否则使用Uri.fromFile(file)方法获取Uri
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cover));
                }
                startActivityForResult(intent, 511);
//                openCamera();
            }
        });
        pop.findViewById(R.id.tv_gallery_popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 401);
                window.dismiss();
                openGallery();
            }
        });
    }

    /**
     * 显示popupWindow
     */
    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindow_show, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(false);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);


        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
//        //底部弹出
        window.showAtLocation(PublishActivity.this.findViewById(R.id.publishs_info), Gravity.BOTTOM,0,0);

        TextView tv_tupian = view.findViewById(R.id.pop_tupian);
        TextView tv_shipin = view.findViewById(R.id.pop_shipin);
        //选择图片
        tv_tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                    player.setVisibility(View.GONE);
                    player_img.setVisibility(View.GONE);
                    //多选
                    PhotoPickerIntent intent = new PhotoPickerIntent(PublishActivity.this);
                    intent.setSelectModel(SelectModel.MULTI);
                    intent.setShowCarema(false); // 是否显示拍照， 默认false
                    intent.setMaxTotal(8); // 最多选择照片数量，默认为8
                    intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                }
            }
        });
        //选择视频
        tv_shipin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()){
                    window.dismiss();
                    media_type = "2";
                    //直接跳到系统相册去选取视频
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_CODE_PICK);
                }
            }
        });

        // popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                System.out.println("popWindow消失");
            }
        });

    }


    private void refreshAdpater(ArrayList<String> paths){
        // 处理返回照片地址
        Log.e(TAG, "refreshAdpater: 图片返回地址"+paths.size() );
        Uri uri = Uri.parse(paths.get(0));
        img_up_fengmian.setImageURI(uri);
    }

    PublishAdapter.CallBack callBack = new PublishAdapter.CallBack() {
        @Override
        public void clalback() {
            //回调展示
            //多选
            PhotoPickerIntent intent = new PhotoPickerIntent(PublishActivity.this);
            intent.setSelectModel(SelectModel.MULTI);
            intent.setShowCarema(false); // 是否显示拍照， 默认false
            intent.setMaxTotal(8); // 最多选择照片数量，默认为8
            intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
            startActivityForResult(intent, REQUEST_CAMERA_CODE);
        }
    };

    /**
     * 授权照相机代码
     */

    //打开相机的返回码
    private static final int CAMERA_REQUEST_CODE = 1;
    //选择图片的返回码
    private static final int IMAGE_REQUEST_CODE = 2;
    //剪切图片的返回码
    public static final int CROP_REREQUEST_CODE = 3;
//    private ImageView iv;

    //相机
    public static final int REQUEST_CODE_PERMISSION_CAMERA = 100;

    public static final int REQUEST_CODE_PERMISSION_GALLERY = 101;
    //照片图片名
    private String photo_image;
    //截图图片名
    private String crop_image;
    //拍摄的图片的真实路径
    private String takePath;
    //拍摄的图片的虚拟路径
    private Uri imageUri;
    private Uri cropUri;
    //    private File tempFile = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    /**
     * 拍照
     *
     * @param view
     */
    public void onClickTakePhoto(View view) {

        openCamera();
    }

    private void openCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission(REQUEST_CODE_PERMISSION_CAMERA);
            return;
        }
        if (isSdCardExist()) {
            Intent cameraIntent = new Intent(
                    "android.media.action.IMAGE_CAPTURE");

            photo_image = new SimpleDateFormat("yyyy_MMdd_hhmmss").format(new Date()) + ".jpg";
            imageUri = getImageUri(photo_image);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT
                    , imageUri);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(this, "SD卡不存在", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 打开图库
     * 不需要用FileProvider
     *
     * @param view
     */
    public void onClickOpenGallery(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission(REQUEST_CODE_PERMISSION_GALLERY);
            return;
        }
        openGallery();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }
    /**
     * 获得一个uri。该uri就是将要拍摄的照片的uri
     *
     * @return
     */
    private Uri getImageUri(String name) {
        if (isSdCardExist()) {
            File file = createFile(name);
            if (file != null) {
                takePath = file.getAbsolutePath();
                Log.e("zmm", "图片的路径---》" + takePath);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    return FileProvider.getUriForFile(getApplicationContext(),
                            BuildConfig.APPLICATION_ID + ".provider", file);
                } else {
                    return Uri.fromFile(file);
                }
            }
        }
        return Uri.EMPTY;
    }

    public File createFile(String name) {
        if (isSdCardExist()) {
            File[] dirs = ContextCompat.getExternalFilesDirs(this, null);
            if (dirs != null && dirs.length > 0) {
                File dir = dirs[0];
                return new File(dir, name);
            }
        }

        return null;
    }

    Uri shangchaunImg;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("==============", "onActivityResult: "+ requestCode);
//        if (data==null){
//            return;
//        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE://选择图片成功返回
                    if (data != null && data.getData() != null) {
                        imageUri = data.getData();
                        cropPhoto(imageUri,true); //注视后不裁剪图，释放就裁剪
                    }
                    break;
                case CROP_REREQUEST_CODE:
                    Log.e("zmm", "裁剪以后的地址是------------>" + cropUri);
                    shangchaunImg  = cropUri;
                    decodeImage(cropUri);
                    break;
                // 选择照片
                case REQUEST_CODE_CHOOSE:
                    refreshAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));

                    break;
//            // 多选
                case REQUEST_CAMERA_CODE:
                    //多选按钮隐藏
                    img_up_info.setVisibility(View.GONE);
                    //写入适配器
                    info_img = true;

                    arrayList = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    adapter = new PublishAdapter(PublishActivity.this,arrayList,callBack);
                    rv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
                    //视频
                case REQUEST_CODE_PICK:
                    Uri uri = data.getData();
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    cursor.moveToFirst();
//                    String number= cursor.getString(0); // 视频编号
                    pathvideo_bendi = cursor.getString(1); // 视频文件路径
                    player.setVisibility(View.VISIBLE);
                    player_img.setVisibility(View.VISIBLE);
                    img_up_info.setVisibility(View.GONE);
                    player.setUp(pathvideo_bendi, "", Jzvd.SCREEN_WINDOW_NORMAL);

                    break;
                case 511:
                    Log.e("==============", "onActivityResult: "+ cover.exists());
                    if (cover.exists()){
                        Bitmap bitmap = BitmapFactory.decodeFile(cover.getAbsolutePath());
                        img_up_fengmian.setImageBitmap(bitmap);
                    }
                    break;
            }
        }
    }
    // 图片裁剪
    private void cropPhoto(Uri uri, boolean fromCapture) {
        Intent intent = new Intent("com.android.camera.action.CROP"); //打开系统自带的裁剪图片的intent
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("scale", true);
        // 设置裁剪区域的宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 设置裁剪区域的宽度和高度
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);
        // 取消人脸识别
        intent.putExtra("noFaceDetection", true);
        // 图片输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        // 若为false则表示不返回数据
        intent.putExtra("return-data", false);
        // 指定裁剪完成以后的图片所保存的位置,pic info显示有延时
        if (fromCapture) {
            // 如果是使用拍照，那么原先的uri和最终目标的uri一致
            cropUri = uri;
        } else { // 从相册中选择，那么裁剪的图片保存在take_photo中
            String time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
            String fileName = "photo_" + time;
            File mCutFile = new File(Environment.getExternalStorageDirectory() + "/take_photo", fileName + ".jpeg");
            if (!mCutFile.getParentFile().exists()) {
                mCutFile.getParentFile().mkdirs();
            }
            cropUri = getUriForFile(this, mCutFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        Toast.makeText(this, "剪裁图片", Toast.LENGTH_SHORT).show();
        // 以广播方式刷新系统相册，以便能够在相册中找到刚刚所拍摄和裁剪的照片
        Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intentBc.setData(uri);
        this.sendBroadcast(intentBc);

        startActivityForResult(intent, CROP_REREQUEST_CODE); //设置裁剪参数显示图片至ImageVie
    }
    // 从file中获取uri
    // 7.0及以上使用的uri是contentProvider content://com.rain.takephotodemo.FileProvider/images/photo_20180824173621.jpg
    // 6.0使用的uri为file:///storage/emulated/0/take_photo/photo_20180824171132.jpg
    private static Uri getUriForFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(),  BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }
    /**
     * 根据uri拿到bitmap
     *
     * @param imageUri 这个Uri是
     */
    Uri fengmian_uri;
    private void decodeImage(final Uri imageUri) {
        try {
            Bitmap bitmapFormUri = getBitmapFormUri(this, imageUri);
            fengmian_uri = imageUri;
            img_up_fengmian.setImageBitmap(bitmapFormUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap getBitmapFormUri(Activity ac, Uri uri) throws FileNotFoundException, IOException {
        InputStream input = ac.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        int originalWidth = onlyBoundsOptions.outWidth;
        int originalHeight = onlyBoundsOptions.outHeight;
        if ((originalWidth == -1) || (originalHeight == -1))
            return null;
        //图片分辨率以480x800为标准
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (originalWidth / ww);
        } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (originalHeight / hh);
        }
        if (be <= 0)
            be = 1;
        //比例压缩
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = ac.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return compressImage(bitmap);//再进行质量压缩
    }
    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
    /**
     * 检查权限
     *
     * @param requestCode
     */
    private void checkPermission(int requestCode) {

        boolean granted = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                Manifest.permission_group.CAMERA);
        if (granted) {//有权限
            if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {
                openCamera();//打开相机
            } else {
                openGallery();//打开图库
            }
            return;
        }
        //没有权限的要去申请权限
        //注意：如果是在Fragment中申请权限，不要使用ActivityCompat.requestPermissions,
        // 直接使用Fragment的requestPermissions方法，否则会回调到Activity的onRequestPermissionsResult
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            boolean flag = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PERMISSION_GRANTED) {
                    flag = false;
                    break;
                }
            }
            //权限通过以后。自动回调拍照
            if (flag) {
                if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {
                    openCamera();//打开相机
                } else {
                    openGallery();//打开图库
                }
            } else {
                Toast.makeText(this, "请开启权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 检查SD卡是否存在
     */
    public boolean isSdCardExist() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
    /**上传单张图片**/
    public void beginupload(Uri bitmap) {
        showMyDialog();
        Log.e("传递值",bitmap+"");
        final String endpoint = "oss-cn-beijing.aliyuncs.com";
        final String startpoint = "star-1";
        //     明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAI8ygujYgDvLJ9", "nLrO1bpn9IOpEu0tt0zyAaChc22j0c");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        // 构造上传请求
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        final String objectname =df.format(new Date())+ ".png";

        final String url = startpoint +"."+ endpoint+"/"+ objectname;

        PutObjectRequest put = new PutObjectRequest(startpoint, objectname, UiUtils.getImageAbsolutePath(PublishActivity.this,bitmap) );
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.e("测试图片","https://star-1.oss-cn-beijing.aliyuncs.com/"+objectname);
                if (media_type.equals("1")){
                    beginupload(arrayList,objectname);
                }else {
                    beginupload(pathvideo_bendi,objectname);
                }

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                ToastUtils.shortToast("图片上传失败导致信息无法发布");
                dissDialog();
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    ToastUtils.shortToast("图片上传失败导致信息无法发布");
                }
                if (serviceException != null) {
                }
            }
        });

    }

    String img_path="";
    public void beginupload(ArrayList<String> arrayList,String imgname) {
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
//                    Log.e("上传结果","https://star-1.oss-cn-beijing.aliyuncs.com/"+objectname);
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

    /**上传单个视频**/
    private String pathvideo_address="",pathvideo_bendi="";
    public void beginupload(String pathvideo,String imgname) {
        Log.e("本地视频地址",pathvideo);
        showMyDialog();
        final String endpoint = "oss-cn-beijing.aliyuncs.com";
        final String startpoint = "star-1";
        //     明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAI8ygujYgDvLJ9", "nLrO1bpn9IOpEu0tt0zyAaChc22j0c");
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider);
        //通过填写文件名形成objectname,通过这个名字指定上传和下载的文件
        // 构造上传请求
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        final String objectname =df.format(new Date())+ ".mp4";

        final String url = startpoint +"."+ endpoint+"/"+ objectname;

        PutObjectRequest put = new PutObjectRequest(startpoint, objectname, pathvideo );
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.e("测试上传视频","https://star-1.oss-cn-beijing.aliyuncs.com/"+objectname);
                pathvideo_address = objectname;
                upinfo(pathvideo_address,imgname);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                ToastUtils.shortToast("视频上传失败导致信息无法发布");
                dissDialog();
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    ToastUtils.shortToast("视频上传失败导致信息无法发布");
                }
                if (serviceException != null) {
                }
            }
        });

    }
}
