package zzbcar.cckj.com.nzzb.utils;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by Admin on 2017/11/21.
 */

public class NoLineClickSpan  extends ClickableSpan {
    String color;

    public NoLineClickSpan(String color) {
        super();
        this.color = color;
    }

    @Override
    public void onClick(View widget) {

    }

    @Override
    public void updateDrawState(TextPaint ds) {
        //设置字体颜色
        ds.setColor(Color.parseColor(color));
        ds.setUnderlineText(false); //去掉下划线
    }
}
