package zzbcar.cckj.com.nzzb.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.bean.SigninBean;
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

    @BindView(R.id.iv_minfragment_head_pic)
    RoundImageView ivUserHead;

    @BindView(R.id.tv_minfragment_phone_number)
    TextView tvUserPhone;

    @BindView(R.id.tv_signout)
    TextView tvSignout;


    File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());

    private boolean isSignin = false;
    private SigninBean.DataBean.MemberBean signInfo;

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

    }

    /*初始化登录信息*/
    private void initSignInfo() {
        final String avatar = signInfo.getAvatar();
        if (avatar.equals("") || avatar.equals(":")) {
            Toast.makeText(mActivity, "头像地址错误，且Picasso加载有异常", Toast.LENGTH_SHORT).show();
        } else {
            Picasso.with(mActivity).load(avatar)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(ivUserHead);
        }
        /*车主认证状态*/
        if (signInfo.getAuthStatus() == 1) tv_minfragment_car_identifi.setText("车主已认证");
        tvUserPhone.setText(signInfo.getMobile());
        tvSignout.setVisibility(View.VISIBLE);
    }

    @Override
    public void initViews(View view) {
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
                intent = new Intent(mActivity, CommonAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_account_bind:
                intent = new Intent(mActivity, AccountBindActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_my_break_rules:
                intent = new Intent(mActivity, BreakRuleActivity.class);
                startActivity(intent);
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
            case R.id.tv_minfragment_car_identifi:
                intent = new Intent(mActivity, CarIdentifiActivity.class);
                startActivity(intent);
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

    /*暂时调整到此处，确定逻辑后剪切即可*/
    private void openShared() {
        new ShareAction(mActivity)
                .withText("至尊宝豪车共享")
                .withMedia(new UMImage(mActivity, BitmapFactory.decodeResource(getResources(), R.drawable.p001)))
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

        intent.putExtra("return-data", true);
        Log.e("zoom", "begin1");
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private void setPicToView(Intent picdata) {
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");

//            Drawable drawable = new BitmapDrawable(photo);
            iv_minfragment_head_pic.setImageBitmap(photo);
        }
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }
}
