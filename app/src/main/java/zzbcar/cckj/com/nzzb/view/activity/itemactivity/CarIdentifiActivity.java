package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.LogUtil;
import zzbcar.cckj.com.nzzb.utils.OssUtils;
import zzbcar.cckj.com.nzzb.utils.REGutil;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class CarIdentifiActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.et_car_1_name)
    EditText etCar1Name;
    @BindView(R.id.et_car_1_idcard)
    EditText etCar1Idcard;
    private Button bt_identicar_nest;
    private ImageView iv_identificar_idcar_up;
    private ImageView iv_identificar_idcar_down;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_IDCAR_UP = 300;
    private static final int PHOTO_REQUEST_IDCAR_DOWN = 4;
    private boolean isUp = true;
    private String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "zzbcar" + File.separator + "icon";
    private ArrayList<String> idCard = new ArrayList<>();
    File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
    private File cropfile;
    private ProgressDialog progressDialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    progressDialog.dismiss();
                    PutObjectResult obj = (PutObjectResult) msg.obj;
                    String idCardTemp = Constant.SERVER_PHOTO_HEAD + Constant.IDCARD_KEYPATH + cropfile.getName();
                    ;
                    if (isUp) {
                        idCard.add(0, idCardTemp);
                    } else {
                        idCard.add(1, idCardTemp);
                    }
                    LogUtil.e(idCardTemp);
                    break;
                case 1:
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "上传失败", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(mContext, (String)msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_identifi1;
    }

    @Override
    protected void initViews() {
        bt_identicar_nest = (Button) findViewById(R.id.bt_identicar_nest);
        iv_identificar_idcar_up = (ImageView) findViewById(R.id.iv_identificar_idcar_up);
        iv_identificar_idcar_down = (ImageView) findViewById(R.id.iv_identificar_idcar_down);

        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);
    }

    @Override
    protected void initDatas() {
        bt_identicar_nest.setOnClickListener(this);
        iv_identificar_idcar_down.setOnClickListener(this);
        iv_identificar_idcar_up.setOnClickListener(this);

        new TitleBuilder(this).setTitleText("车友认证").setLeftIco(R.mipmap.row_back).setLeftIcoListening(leftReturnListener);
    }


    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {

            case R.id.bt_identicar_nest:
                //判断是否能提交下个界面
                ifCommit();
                break;
            case R.id.iv_identificar_idcar_up:
                isUp = true;
                showDialog();
                break;

            case R.id.iv_identificar_idcar_down:
                isUp = false;
                showDialog();
                break;

        }
    }

    //提交下一个界面的方法。
    private void ifCommit() {
        String name = etCar1Name.getText().toString().trim();
        String idcardNumber = etCar1Idcard.getText().toString().trim();
        Message obtain = Message.obtain();
        obtain.what =3;
        if(TextUtils.isEmpty(name)){
            obtain.obj = "还没有输入姓名";
            handler.sendMessage(obtain);
            return;
        }
        if(TextUtils.isEmpty(idcardNumber)){
            obtain.obj = "还没有输入身份证号";
            handler.sendMessage(obtain);
            return;
        }
        if(idcardNumber.length()!=18){
            obtain.obj = "身份证号码长度不符，再检查一下吧";
            handler.sendMessage(obtain);
            return;
        }
        if(!REGutil.checkIDcardNumber(idcardNumber)){
            obtain.obj = "身份证格式错误，再检查一下吧";
            handler.sendMessage(obtain);
            return;
        }
        if(idCard.size()!=2){
            obtain.obj = "还没有上传完身份证照片";
            handler.sendMessage(obtain);
            return;
        }
        Intent intent = new Intent(mContext, CarLicenceActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("idCardNumber",idcardNumber);
        intent.putExtra("idCardUp",idCard.get(0));
        intent.putExtra("idCardDown",idCard.get(1));
        /*intent.putExtra("idCardUp","");
        intent.putExtra("idCardDown","");*/
        startActivity(intent);
        finish();
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
        Picasso.with(this).load(Uri.fromFile(cropfile)).fit().into(iv_identificar_idcar_down);
        upload(cropfile);
    }

    private void startPhotoZoom(Uri uri, int size) {
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

    private void setIdCarUPToView(Intent picdata) {
        //Bundle bundle = picdata.getExtras();
        /*if (picdata != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            iv_identificar_idcar_up.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv_identificar_idcar_up.setImageBitmap(bitmap);*/
        Picasso.with(this).load(Uri.fromFile(cropfile)).fit().into(iv_identificar_idcar_up);
        upload(cropfile);


        //Bitmap photo2= bundle.getParcelable("data");
        //iv_identificar_idcar_down.setImageBitmap(photo2);
    }

    private void upload(File file) {
        showWaitDialog();
        OssUtils.initOss(this).asyncPutObject(OssUtils.putImage(file,Constant.IDCARD_KEYPATH), new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
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

    private View.OnClickListener leftReturnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    private void showWaitDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(R.style.MaterialDialog);
        progressDialog.setMessage("正在上传");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
