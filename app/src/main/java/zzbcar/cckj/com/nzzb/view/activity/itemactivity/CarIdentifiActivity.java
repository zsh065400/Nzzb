package zzbcar.cckj.com.nzzb.view.activity.itemactivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.base.TitleBuilder;
import zzbcar.cckj.com.nzzb.view.activity.BaseActivity;

public class CarIdentifiActivity extends BaseActivity implements View.OnClickListener {


    private Button bt_identicar_nest;
    private ImageView iv_identificar_idcar_up;
    private ImageView iv_identificar_idcar_down;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;

    File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());
    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_identifi1;
    }

    @Override
    protected void initViews() {
        bt_identicar_nest = (Button) findViewById(R.id.bt_identicar_nest);
        iv_identificar_idcar_up = (ImageView) findViewById(R.id.iv_identificar_idcar_up);
        iv_identificar_idcar_down = (ImageView) findViewById(R.id.iv_identificar_idcar_down);


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
        Intent intent=null;
        switch (view.getId()) {

            case R.id.bt_identicar_nest:
                intent  =new Intent(this,CarLicenceActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_identificar_idcar_up:
               showDialog();
                break;

        }
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
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
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
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);
        Log.e("zoom", "begin1");
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
    private void setPicToView(Intent picdata) {
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            iv_identificar_idcar_up.setBackgroundDrawable(drawable);
        }
    }

    private  View.OnClickListener leftReturnListener=new View.OnClickListener() {
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
}
