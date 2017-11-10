package zzbcar.cckj.com.nzzb.view.fragment;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import zzbcar.cckj.com.nzzb.R;
import zzbcar.cckj.com.nzzb.adapter.MyGoodExperenceAdapter;
import zzbcar.cckj.com.nzzb.base.MyApplication;
import zzbcar.cckj.com.nzzb.utils.Gradient;
import zzbcar.cckj.com.nzzb.utils.OkManager;
import zzbcar.cckj.com.nzzb.utils.ScaleTransformer;
import zzbcar.cckj.com.nzzb.view.activity.LoginActivity;
import zzbcar.cckj.com.nzzb.view.activity.RentActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.MarriedActivity;
import zzbcar.cckj.com.nzzb.view.activity.itemactivity.PayActivity;

/**
 * Created by Admin on 2017/10/31.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {


    private RadioButton rb_homefragment_self_rent;
    private RadioButton rb_homefragment_business_rent;
    private RadioButton rb_homefragment_married_rent;
    private Button bt_select_time;
    private Button bt_login;
    private Button bt_rent;

    private Button bt_order_pay;
    private OkManager manager;
    private Gradient gradient;
    private ViewPager vp_homefragment_good_experence;
    private ViewPager vp_homefragment_fresh_car_type;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        return view;
    }

    @Override
    protected void initFindViewById(View view) {

        rb_homefragment_self_rent = (RadioButton) view.findViewById(R.id.rb_homefragment_self_rent);
        rb_homefragment_business_rent = (RadioButton) view.findViewById(R.id.rb_homefragment_business_rent);
        rb_homefragment_married_rent = (RadioButton) view.findViewById(R.id.rb_homefragment_married_rent);
        gradient = (Gradient) view.findViewById(R.id.gradient);
        vp_homefragment_good_experence = (ViewPager) view.findViewById(R.id.vp_homefragment_good_experence);
        vp_homefragment_fresh_car_type = (ViewPager) view.findViewById(R.id.vp_homefragment_fresh_car_type);

        bt_login = view.findViewById(R.id.bt_login);
        bt_order_pay = view.findViewById(R.id.bt_order_pay);
        initGradient();
        initGoodExperence();
        initFreshCarType();

    }

    private void initFreshCarType() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.p001);
        list.add(R.drawable.p002);
        list.add(R.drawable.p003);
        list.add(R.drawable.p004);
        list.add(R.drawable.p005);
        MyGoodExperenceAdapter myGoodExperenceAdapter = new MyGoodExperenceAdapter(mActivity, list);
        vp_homefragment_fresh_car_type.setAdapter(myGoodExperenceAdapter);
        vp_homefragment_fresh_car_type.setPageTransformer(false, new ScaleTransformer());
        vp_homefragment_fresh_car_type.setOffscreenPageLimit(5);
    }

    private void initGoodExperence() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.p001);
        list.add(R.drawable.p002);
        list.add(R.drawable.p003);
        list.add(R.drawable.p004);
        list.add(R.drawable.p005);
        MyGoodExperenceAdapter myGoodExperenceAdapter = new MyGoodExperenceAdapter(mActivity, list);
        vp_homefragment_good_experence.setAdapter(myGoodExperenceAdapter);
        vp_homefragment_good_experence.setPageTransformer(false, new ScaleTransformer());
        vp_homefragment_good_experence.setOffscreenPageLimit(5);

    }

    private void initGradient() {
        //创建imageview
        ImageView imageView = new ImageView(mActivity);
        imageView.setImageResource(R.mipmap.image1);
        ImageView imageView2 = new ImageView(mActivity);
        imageView2.setImageResource(R.mipmap.image2);
        ImageView imageView3 = new ImageView(mActivity);
        imageView3.setImageResource(R.mipmap.image3);
        ImageView imageView4 = new ImageView(mActivity);
        imageView4.setImageResource(R.mipmap.image4);
        List<ImageView> list = new ArrayList<>();
        list.add(imageView);
        list.add(imageView2);
        list.add(imageView3);
        list.add(imageView4);
        //设置图片即可
        gradient.setImageViews(list);
    }


    @Override
    public void initData() {
        rb_homefragment_self_rent.setOnClickListener(this);
        rb_homefragment_business_rent.setOnClickListener(this);
        rb_homefragment_married_rent.setOnClickListener(this);

        bt_login.setOnClickListener(this);
        bt_order_pay.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.rb_homefragment_self_rent:
                intent = new Intent(MyApplication.getContext(), RentActivity.class);
                startActivity(intent);
                break;
            case R.id.rb_homefragment_business_rent:
                intent = new Intent(MyApplication.getContext(), RentActivity.class);
                startActivity(intent);
                break;
            case R.id.rb_homefragment_married_rent:
                intent = new Intent(MyApplication.getContext(), MarriedActivity.class);
                startActivity(intent);
                break;

            case R.id.bt_login:
                intent = new Intent(MyApplication.getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_order_pay:
                intent = new Intent(MyApplication.getContext(), PayActivity.class);
                startActivity(intent);
                break;

        }

    }
}
