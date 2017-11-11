package zzbcar.cckj.com.nzzb.utils;

import android.content.res.Resources;

/**
 * Created by Scout
 * Created on 2017/11/11 17:04.
 */

public class ScreenUtils {

    public static final double dp2px(int dp){
        final float density = Resources.getSystem().getDisplayMetrics().density;
        return density * dp;
    }

}
