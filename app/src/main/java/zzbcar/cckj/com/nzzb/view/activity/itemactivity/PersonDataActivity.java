package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.LogUtil;
import zzbcar.cckj.com.nzzb.utils.OssUtils;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;
import zzbcar.cckj.com.nzzb.view.customview.RoundImageView;

/**
 * Created by Admin on 2017/11/30.
 */

public class PersonDataActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout ll_person_data_name;
    private LinearLayout ll_person_data_pic;

    private TextView tv__person_data_phoneNumber;
    private TextView tv_person_data_name;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private File cropfile;
    private RoundImageView iv_person_data_pic;
    private String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "zzbcar" + File.separator + "icon";
    File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String hearUrl = Constant.SERVER_PHOTO_HEAD + Constant.HEAD_KEYPATH + cropfile.getName();
                    LogUtil.e(hearUrl + "头像地址");
                    submitHeadUrl(hearUrl);
                    break;
                case 1:
                    Toast.makeText(mContext, "头像上传失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_data;
    }

    @Override
    protected void initViews() {
        ll_person_data_name = (LinearLayout) findViewById(R.id.ll_person_data_name);
        ll_person_data_pic = (LinearLayout) findViewById(R.id.ll_person_data_pic);
        tv__person_data_phoneNumber = (TextView) findViewById(R.id.tv__person_data_phoneNumber);
        tv_person_data_name = (TextView) findViewById(R.id.tv_person_data_name);

        iv_person_data_pic = (RoundImageView) findViewById(R.id.iv_person_data_pic);

        StatusBarUtil.setViewTopPadding(this, R.id.top_bar);

    }

    @Override
    protected void initDatas() {
        ll_person_data_pic.setOnClickListener(this);
        new TitleBuilder(this).setTitleText("个人资料").setLeftIco(R.mipmap.row_back).setLeftIcoListening(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_person_data_pic:
                DialogtoUpPic();
                break;
            case R.id.ll_person_data_name:

                break;
        }
    }

    private void DialogtoUpPic() {
        new AlertDialog.Builder(mContext)
                .setTitle("选择图片")
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                startPhotoZoom(Uri.fromFile(tempFile), 150);
                break;

            case PHOTO_REQUEST_GALLERY:
                if (data != null)
                    startPhotoZoom(data.getData(), 150);
                break;

            case PHOTO_REQUEST_CUT:
                Log.e("zoom", "begin2");
                if (data != null)
                    setPicToView(data);
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startPhotoZoom(Uri uri, int size) {
        Log.e("zoom", "begin");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");


        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
//        intent.putExtra("outputX", size);
//        intent.putExtra("outputY", size);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        cropfile = new File(file.getPath(), System.currentTimeMillis() + ".jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropfile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        Log.e("zoom", "begin1");
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private void setPicToView(Intent picdata) {
        OssUtils.initOss(mContext).asyncPutObject(OssUtils.putImage(cropfile, Constant.HEAD_KEYPATH), new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
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
                    handler.sendEmptyMessage(1);
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    handler.sendEmptyMessage(1);
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });

    }

    private void submitHeadUrl(final String hearUrl) {
        final SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(mContext);
        if (signInfo != null)
            OkGo.<String>get(Constant.CHANGE_INFO)
                    .params("userId", signInfo.getId())
                    .params("avatar", hearUrl)
                    .params("token", SPUtils.getToken(mContext))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body());
                                int errno = jsonObject.getInt("errno");
                                if (errno != 0) {
                                    handler.sendEmptyMessage(1);
                                } else {

                                    Picasso.with(mContext).load(Uri.fromFile(cropfile)).fit().into( iv_person_data_pic);
                                    SigninBean user = GsonUtil.parseJsonWithGson(SPUtils.getString(mContext, "User", ""), SigninBean.class);
                                    user.getData().getMember().setAvatar(hearUrl);
                                    SPUtils.saveString(mContext, "User", GsonUtil.getGson().toJson(user));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
    }


    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
