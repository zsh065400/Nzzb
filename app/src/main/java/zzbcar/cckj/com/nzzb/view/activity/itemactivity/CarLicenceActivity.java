package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.CardBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.LogUtil;
import zzbcar.cckj.com.nzzb.utils.OssUtils;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class CarLicenceActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.tv_choose_date)
    TextView tvChooseDate;
    @BindView(R.id.iv_carlicence_up)
    ImageView ivCarlicenceUp;
    @BindView(R.id.iv_carlicence_down)
    ImageView ivCarlicenceDown;
    @BindView(R.id.tv_choose_cartype)
    TextView tvChooseCartype;
    @BindView(R.id.bt_licence_car_nest)
    Button btLicenceCarNest;
    @BindView(R.id.et_choose_carage)
    EditText etChooseCarage;
    private Button bt_licence_car_nest;
    private String carLicenceDate;
    private String name;
    private String idCardNumber;
    private String idCardUp;
    private String idCardDown;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_IDCAR_UP = 300;
    private boolean isUp = true;
    private TimePickerView pvTime;
    private OptionsPickerView pvCustomOptions;
    private String[] type = {"A1", "A2", "A3", "B1", "B2", "C1", "C2"};
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    progressDialog.dismiss();
                    PutObjectResult obj = (PutObjectResult) msg.obj;
                    String carLicence = Constant.SERVER_PHOTO_HEAD + Constant.CARLICENCE_KEYPATH + cropfile.getName();
                    if (isUp) {
                        cardLicence.add(0, carLicence);
                    } else {
                        cardLicence.add(1, carLicence);
                    }
                    LogUtil.e(carLicence);
                    break;
                case 1:
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "上传失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private ArrayList<String> cardLicence = new ArrayList<>();
    private String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "zzbcar" + File.separator + "icon";
    File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
    private File cropfile;
    private ProgressDialog progressDialog;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_car_identifi2;
    }

    @Override
    protected void initViews() {
        bt_licence_car_nest = (Button) findViewById(R.id.bt_licence_car_nest);

        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        initTimePicker();
        getCardData();
        initCustomOptionPicker();
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        idCardNumber = intent.getStringExtra("idCardNumber");
        idCardUp = intent.getStringExtra("idCardUp");
        idCardDown = intent.getStringExtra("idCardDown");

        bt_licence_car_nest.setOnClickListener(this);
        new TitleBuilder(this).setTitleText("车友认证").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2019, 11, 28);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                TextView tv = (TextView) v;
                tv.setText(getTime(date));
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
//                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .setSubmitColor(0xff24AD9D)
                .setCancelColor(0xff24AD9D)
                .setOutSideCancelable(false)
                .isDialog(true)
                .build();
    }

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                tvChooseCartype.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });

                    }
                })
                .setOutSideCancelable(false)
                .isDialog(true)
                .build();
        pvCustomOptions.setPicker(cardItem);//添加数据

    }

    private void getCardData() {
        for (int i = 0; i < type.length; i++) {
            cardItem.add(new CardBean(i, type[i]));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_licence_car_nest:
                //提交下一步
                commit();
                break;
            case R.id.tv_choose_date:
                pvTime.show(view);
                break;
            case R.id.iv_carlicence_up:
                isUp = true;
                showDialog();
                break;
            case R.id.iv_carlicence_down:
                isUp = false;
                showDialog();
                break;
            case R.id.tv_choose_cartype:
                pvCustomOptions.show();
                break;
        }
    }

    private void commit() {
        /*Intent intent = new Intent(this, IdentiCompleteActivity.class);
        startActivity(intent);*/
        String carage = etChooseCarage.getText().toString();
        String carDate = tvChooseDate.getText().toString();
        String cartype = tvChooseCartype.getText().toString();
        if (carDate.equals("请选择日期")) {
            Toast.makeText(mContext, "请选择日期", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cartype.equals("请选择车型")) {
            Toast.makeText(mContext, "请选择车型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(carage)) {
            Toast.makeText(mContext, "请输入驾龄", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cardLicence.size() != 2) {
            Toast.makeText(mContext, "还没有上传完驾驶证", Toast.LENGTH_SHORT).show();
            return;
        }
        showWaitDialog();
        String user = SPUtils.getString(mContext, "User", "");
        SigninBean signinBean = GsonUtil.parseJsonWithGson(user, SigninBean.class);
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", signinBean.getData().getMember().getId() + "");
        params.put("idcard1", idCardUp);
        params.put("idcard1", idCardUp);
        params.put("idcard2", idCardDown);
        params.put("idno", idCardNumber);
        params.put("name", name);
        params.put("licenseDate", carDate);
        params.put("permitType", cartype);
        params.put("driveAge", carage);
        params.put("drvPic1", cardLicence.get(0));
        params.put("drvPic2", cardLicence.get(1));
        OkGo.<String>post(Constant.USER_AUTH)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Intent intent = new Intent(mContext, IdentiCompleteActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        progressDialog.dismiss();
                        Toast.makeText(mContext, "提交出现问题，请稍后重试", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setTitle("选择图片")
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                        Log.e("file", tempFile.toString());
                        startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                    }
                })
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                    }
                }).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {
        tvChooseDate.setOnClickListener(this);
        ivCarlicenceUp.setOnClickListener(this);
        ivCarlicenceDown.setOnClickListener(this);
        tvChooseCartype.setOnClickListener(this);
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                startPhotoZoom(Uri.fromFile(tempFile), 150);
                break;

            case PHOTO_REQUEST_GALLERY:

                if (data != null) {
                    startPhotoZoom(data.getData(), 150);
                }
                break;

            case PHOTO_REQUEST_IDCAR_UP:
                Log.e("zoom", "begin2");
                if (data != null) {
                    if (isUp) {
                        setIdCarUPToView(data);
                    } else {
                        setIdCarDownToView(data);
                    }
                }

                break;
        }
    }

    private void setIdCarDownToView(Intent data) {
        Picasso.with(this).load(Uri.fromFile(cropfile)).fit().into(ivCarlicenceDown);
        upload(cropfile);
    }

    private void setIdCarUPToView(Intent data) {
        Picasso.with(this).load(Uri.fromFile(cropfile)).fit().into(ivCarlicenceUp);
        upload(cropfile);
    }

    private void startPhotoZoom(Uri uri, int i) {
        Log.e("zoom", "begin");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        Log.e("zoom", "begin1");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        cropfile = new File(file.getPath(), System.currentTimeMillis() + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropfile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, PHOTO_REQUEST_IDCAR_UP);
    }

    private void upload(File file) {
        showWaitDialog();
        OssUtils.initOss(this).asyncPutObject(OssUtils.putImage(file, Constant.CARLICENCE_KEYPATH), new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                Message obtain = Message.obtain();
                obtain.obj = putObjectResult;
                obtain.what = 0;
                handler.sendMessage(obtain);
            }

            @Override
            public void onFailure(PutObjectRequest putObjectRequest, ClientException clientExcepion, ServiceException serviceException) {

                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
                handler.sendEmptyMessage(1);
            }
        });
    }

    private void showWaitDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(R.style.MaterialDialog);
        progressDialog.setMessage("正在上传");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
