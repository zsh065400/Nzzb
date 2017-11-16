package zzbcar.cckj.com.nzzb.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import zzbcar.cckj.com.nzzb.R;

/**
 * Created by Admin on 2017/11/7.
 */

public class PopWindow extends PopupWindow {
    private View conentView;

    public PopWindow(final Activity context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.self_rentcar_popupwindow, null);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w / 2 + 40);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);

        conentView.findViewById(R.id.pop_loc_near).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //do something you need here
                if (itemListener != null) itemListener.onLocationNearSort();
                PopWindow.this.dismiss();
            }
        });
        conentView.findViewById(R.id.pop_price_lower).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // do something before signing out
                if (itemListener != null) itemListener.onPriceLowerSort();
                PopWindow.this.dismiss();
            }
        });
        conentView.findViewById(R.id.pop_share_many).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // do something you need here
                if (itemListener != null) itemListener.onShareMoreSort();
                PopWindow.this.dismiss();
            }
        });

        conentView.findViewById(R.id.pop_niankuan_new).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (itemListener != null) itemListener.onNianKuanNewSort();
                PopWindow.this.dismiss();
            }
        });
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, parent.getLayoutParams().width / 2, 5);
        } else {
            this.dismiss();
        }
    }

    private OnPopItemClickListener itemListener;

    public void setItemListener(OnPopItemClickListener itemListener) {
        this.itemListener = itemListener;
    }

    public interface OnPopItemClickListener{
        void onLocationNearSort();

        void onPriceLowerSort();

        void onShareMoreSort();

        void onNianKuanNewSort();
    }
}
