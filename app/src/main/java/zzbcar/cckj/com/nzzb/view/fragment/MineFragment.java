package zzbcar.cckj.com.nzzb.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.MyCollectBean;
import zzbcar.cckj.com.nzzb.bean.PeresonMessageBean;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
import zzbcar.cckj.com.nzzb.utils.Constant;
import zzbcar.cckj.com.nzzb.utils.GsonUtil;
import zzbcar.cckj.com.nzzb.utils.LogUtil;
import zzbcar.cckj.com.nzzb.utils.OssUtils;
import zzbcar.cckj.com.nzzb.utils.SPUtils;
import zzbcar.cckj.com.nzzb.utils.StatusBarUtil;
import zzbcar.cckj.com.nzzb.view.activity.LoginActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.AboutUsActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.AccountBindActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.BreakRuleActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.CarIdentifiActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.CommonAddressActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.HelpCenterActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.MyCarActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.MyCollectActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.MyOrderActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.PreCarFriendIdentifiActivity;
import zzbcar.cckj.com.nzzb.view.customview.RoundImageView;


/**
 * Created by Admin on 2017/10/31.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout rl_my_card;
    private RelativeLayout rl_my_address;
    private RelativeLayout rl_my_account_bind;
    private RelativeLayout rl_my_break_rules;
    private RelativeLayout rl_my_invite_friends;
    private RelativeLayout rl_my_help_center;
    private RelativeLayout rl_my_about_us;
    private TextView tv_minfragment_car_identifi;
    private ImageView iv_mine_fragment_carowener_recruit;
    private RoundImageView iv_minfragment_head_pic;
    private AlertDialog dialog;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "zzbcar" + File.separator + "icon";
    private File cropfile;
     private List<MyCollectBean.DataBean> dataBeans;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String hearUrl = Constant.SERVER_PHOTO_HEAD + Constant.HEAD_KEYPATH + cropfile.getName();
                    LogUtil.e(hearUrl+"头像地址");
                    submitHeadUrl(hearUrl);
                    break;
                case 1:
                    Toast.makeText(mActivity, "头像上传失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private int collectCount;

    private void submitHeadUrl(final String hearUrl) {
        final SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(mActivity);
        if (signInfo!=null)
        OkGo.<String>get(Constant.CHANGE_INFO)
                .params("userId",signInfo.getId())
                .params("avatar",hearUrl)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int errno = jsonObject.getInt("errno");
                            if(errno!=0){
                                handler.sendEmptyMessage(1);
                            }else{
                                Picasso.with(mActivity).load(Uri.fromFile(cropfile)).fit().into(iv_minfragment_head_pic);
                                SigninBean user = GsonUtil.parseJsonWithGson(SPUtils.getString(mActivity, "User", ""), SigninBean.class);
                                user.getData().getMember().setAvatar(hearUrl);
                                SPUtils.saveString(mActivity,"User",GsonUtil.getGson().toJson(user));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @BindView(R.id.iv_minfragment_head_pic)
    RoundImageView ivUserHead;

    @BindView(R.id.tv_minfragment_phone_number)
    TextView tvUserPhone;

    @BindView(R.id.tv_signout)
    TextView tvSignout;


    File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());

    private boolean isSignin = false;
    private SigninBean.DataBean.MemberBean signInfo;
    private LinearLayout ll_my_order;
    private LinearLayout ll_my_collect;

    @Override
    public void onResume() {
        super.onResume();
        signInfo = SPUtils.getSignInfo(mActivity);
        if (signInfo != null) {
            isSignin = true;
            initSignInfo();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initDatas() {
        rl_my_card.setOnClickListener(this);
        rl_my_address.setOnClickListener(this);
        rl_my_account_bind.setOnClickListener(this);
        rl_my_break_rules.setOnClickListener(this);
        rl_my_invite_friends.setOnClickListener(this);
        rl_my_help_center.setOnClickListener(this);
        rl_my_about_us.setOnClickListener(this);
        tv_minfragment_car_identifi.setOnClickListener(this);
        iv_mine_fragment_carowener_recruit.setOnClickListener(this);
        iv_minfragment_head_pic.setOnClickListener(this);
        ll_my_collect.setOnClickListener(this);
        ll_my_order.setOnClickListener(this);



    }

    /*初始化登录信息*/
    private void initSignInfo() {
        final String avatar = signInfo.getAvatar();
        if (avatar.equals("") || avatar.equals(":")) {
            Toast.makeText(mActivity, "头像地址错误，且Picasso加载有异常", Toast.LENGTH_SHORT).show();
        } else {
            LogUtil.e(avatar+"保存的头像");
            Picasso.with(mActivity).load(avatar)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(ivUserHead);
        }
        /*车主认证状态*/
        if (signInfo.getAuthStatus() == 1) {
            tv_minfragment_car_identifi.setText("车主已认证");
        }else if(signInfo.getAuthStatus() == 0){
            tv_minfragment_car_identifi.setText("车主未认证");
        }else if(signInfo.getAuthStatus() == 3){
            tv_minfragment_car_identifi.setText("车主认证失败");
        }
        tvUserPhone.setText(signInfo.getMobile());
        tvSignout.setVisibility(View.VISIBLE);
    }

    @Override
    public void initViews(View view) {
        ll_my_order = (LinearLayout)view.findViewById(R.id.ll_my_order);
        ll_my_collect = (LinearLayout)view.findViewById(R.id.ll_my_collect);

        rl_my_card = (RelativeLayout) view.findViewById(R.id.rl_my_card);
        rl_my_address = (RelativeLayout) view.findViewById(R.id.rl_my_address);
        rl_my_account_bind = (RelativeLayout) view.findViewById(R.id.rl_my_account_bind);
        rl_my_break_rules = (RelativeLayout) view.findViewById(R.id.rl_my_break_rules);
        rl_my_invite_friends = (RelativeLayout) view.findViewById(R.id.rl_my_invite_friends);
        rl_my_help_center = (RelativeLayout) view.findViewById(R.id.rl_my_help_center);
        rl_my_about_us = (RelativeLayout) view.findViewById(R.id.rl_my_about_us);

        tv_minfragment_car_identifi = (TextView) view.findViewById(R.id.tv_minfragment_car_identifi);
        iv_mine_fragment_carowener_recruit = view.findViewById(R.id.iv_mine_fragment_carowener_recruit);

        iv_minfragment_head_pic = (RoundImageView) view.findViewById(R.id.iv_minfragment_head_pic);

        iv_minfragment_head_pic = (RoundImageView) view.findViewById(R.id.iv_minfragment_head_pic);

        StatusBarUtil.setViewTopPadding(mActivity, view, R.id.top_bar);
    }

    @Override
    protected void initListeners() {
        ivUserHead.setOnClickListener(this);
        tvUserPhone.setOnClickListener(this);
        tvSignout.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rl_my_card:
                intent = new Intent(mActivity, MyCarActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_address:
                toActivity(CommonAddressActivity.class,true);
                break;
            case R.id.rl_my_account_bind:
                toActivity(AccountBindActivity.class,true);
                break;
            case R.id.rl_my_break_rules:
                toActivity(BreakRuleActivity.class,true);
                break;
            case R.id.rl_my_invite_friends:
//                intent = new Intent(mActivity, InviteFriendsActivity.class);
//                startActivity(intent);
                openShared();
                break;
            case R.id.rl_my_help_center:
                intent = new Intent(mActivity, HelpCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_about_us:
                intent = new Intent(mActivity, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_my_order:
                toActivity(MyOrderActivity.class,true);
                break;
            case R.id.ll_my_collect:

                toActivity(MyCollectActivity.class,true);

                toCollectView();
                break;
            case R.id.tv_minfragment_car_identifi:
                toActivity(CarIdentifiActivity.class,true);
                break;
            case R.id.iv_mine_fragment_carowener_recruit:
                intent = new Intent(mActivity, PreCarFriendIdentifiActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_minfragment_head_pic:
                if (isSignin) DialogtoUpPic();
                else toActivity(LoginActivity.class);
                break;

            case R.id.tv_minfragment_phone_number:
                if (isSignin) Toast.makeText(mActivity,
                        "登陆了，别瞎点了", Toast.LENGTH_SHORT).show();
                else toActivity(LoginActivity.class);
                break;
            case R.id.tv_signout:
                /*此按钮可点击时已经是登录状态*/
                new AlertDialog.Builder(mActivity)
                        .setTitle("是否退出登录")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /*清除登录信息*/
                                SPUtils.saveString(mActivity, "User", "");
                                ivUserHead.setImageResource(R.mipmap.ic_launcher);
                                tvUserPhone.setText("请登陆");
                                view.setVisibility(View.GONE);
                                isSignin = false;
                                signInfo = null;
                            }
                        })
                        .setNegativeButton("否", null).show();
                break;
        }

    }

    private void toCollectView() {
        SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(getContext());
        OkGo.<String>get(Constant.PERSON_MESSAGE)
//                .params("userId",signInfo.getId())
                .params("userId",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PeresonMessageBean.DataBean permessBean = GsonUtil.parseJsonWithGson(response.body(), PeresonMessageBean.class).getData();
                        setViewInfo(permessBean);

                    }

                });
    }

    private void setViewInfo(PeresonMessageBean.DataBean permessBean) {
        collectCount = permessBean.getCollectCount();

    }


    /*暂时调整到此处，确定逻辑后剪切即可*/
    private void openShared() {
        new ShareAction(mActivity)
                .withText("至尊宝豪车共享")
                .withMedia(new UMImage(mActivity, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)))
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener)
                .open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            asyncShowToast("成功分享");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            asyncShowToast("失败" + t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            asyncShowToast("取消了");
        }
    };

    private void DialogtoUpPic() {
        new AlertDialog.Builder(mActivity)
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
            OssUtils.initOss(mActivity).asyncPutObject(OssUtils.putImage(cropfile, Constant.HEAD_KEYPATH), new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
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

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
