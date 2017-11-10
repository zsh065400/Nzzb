package zzbcar.cckj.com.nzzb.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashMap;

import zzbcar.cckj.com.nzzb.R;

/**
 * Created by Scout
 * Created on 2017/11/3 14:30.
 */

public final class ViewPagerIndicator extends View {

    private Paint mForcePaint;

    private Paint mBackPaint;
    private float mOffset;
    private int mNumbers;
    private float mRadius;
    private int mForceColor;
    private int mBackColor;
    private int mPaddingLeft;
    private int mPaddingRight;
    private float dpiX;
    private HashMap _$_findViewCache;


    public final Paint getMForcePaint() {
        return this.mForcePaint;
    }


    public final Paint getMBackPaint() {
        return this.mBackPaint;
    }

    public final float getMOffset() {
        return this.mOffset;
    }

    public final void setMOffset(float var1) {
        this.mOffset = var1;
    }

    public final int getMNumbers() {
        return this.mNumbers;
    }

    public final void setMNumbers(int var1) {
        this.mNumbers = var1;
    }

    public final float getMRadius() {
        return this.mRadius;
    }

    public final void setMRadius(float var1) {
        this.mRadius = var1;
    }

    public final int getMForceColor() {
        return this.mForceColor;
    }

    public final void setMForceColor(int var1) {
        this.mForceColor = var1;
    }

    public final int getMBackColor() {
        return this.mBackColor;
    }

    public final void setMBackColor(int var1) {
        this.mBackColor = var1;
    }

    public final int getMPaddingLeft() {
        return this.mPaddingLeft;
    }

    public final void setMPaddingLeft(int var1) {
        this.mPaddingLeft = var1;
    }

    public final int getMPaddingRight() {
        return this.mPaddingRight;
    }

    public final void setMPaddingRight(int var1) {
        this.mPaddingRight = var1;
    }

    public final float getDpiX() {
        return this.dpiX;
    }

    public final void setDpiX(float var1) {
        this.dpiX = var1;
    }

    public final void setOffset(int position, float positionOffset) {
        int index = position % this.mNumbers;
        float offset = positionOffset;
        int last = this.mNumbers - 1;
        if (index == last && positionOffset > (float) 0) {
            offset = 0.0F;
        }

        this.mOffset = (float) index * this.mRadius * (float) 3 + offset * this.mRadius * (float) 3;
        this.invalidate();
    }

    private final void initPaint() {
        this.mForcePaint.setStrokeWidth(2.0F);
        this.mForcePaint.setStyle(Paint.Style.FILL);
        this.mForcePaint.setColor(this.mForceColor);
        this.mForcePaint.setAntiAlias(true);
        this.mBackPaint.setStrokeWidth(2.0F);
        this.mBackPaint.setStyle(Paint.Style.STROKE);
        this.mBackPaint.setColor(this.mBackColor);
        this.mBackPaint.setAntiAlias(true);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpec = MeasureSpec.getSize(heightMeasureSpec);
        int width = widthSpec;
        int height = heightSpec;
        this.dpiX = (float) (this.getResources().getDisplayMetrics().densityDpi / 160);
        if (widthMode == Integer.MIN_VALUE) {
            width = (int) ((float) (this.mPaddingLeft * 2) + (float) this.mNumbers * this.mRadius * (float) 2 + (float) (this.mNumbers - 1) * this.mRadius);
        }

        if (heightMode == Integer.MIN_VALUE) {
            height = (int) ((double) (this.mRadius * (float) 2 * this.dpiX) + 0.5D);
        }

        this.setMeasuredDimension(width, height);
    }

    protected void onDraw(@Nullable Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;

        float y;
        for (y = (float) ((double) (this.mRadius * this.dpiX) + 0.5D); i < this.mNumbers; ++i) {
            if (canvas != null) {
                canvas.drawCircle((float) this.mPaddingLeft + this.mRadius + (float) i * this.mRadius * 3.0F, y, this.mRadius, this.mBackPaint);
            }
        }

        if (canvas != null) {
            canvas.drawCircle((float) this.mPaddingLeft + this.mRadius + this.mOffset, y, this.mRadius, this.mForcePaint);
        }
    }

    public ViewPagerIndicator( Context context) {
        this(context, (AttributeSet) null);
        this.initPaint();
    }

    public ViewPagerIndicator( Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mForcePaint = new Paint();
        this.mBackPaint = new Paint();
        this.mNumbers = 3;
        this.mRadius = 20.0F;
        this.mForceColor = -7829368;
        this.mBackColor = -7829368;
        this.mPaddingLeft = 20;
        this.mPaddingRight = 20;
        if (attributeSet != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ViewPagerIndicator);
            this.mNumbers = typedArray.getInteger(R.styleable.ViewPagerIndicator_numbers, this.mNumbers);
            this.mRadius = typedArray.getFloat(R.styleable.ViewPagerIndicator_radius, this.mRadius);
            int forceColorResId = typedArray.getResourceId(R.styleable.ViewPagerIndicator_forceColor, 0);
            this.mForceColor = forceColorResId != 0 ? this.getResources().getColor(forceColorResId) : typedArray.getColor(R.styleable.ViewPagerIndicator_forceColor, Color.BLACK);
            int backColorResId = typedArray.getResourceId(R.styleable.ViewPagerIndicator_backColor, 0);
            this.mBackColor = backColorResId != 0 ? this.getResources().getColor(backColorResId) : typedArray.getColor(R.styleable.ViewPagerIndicator_backColor, Color.GRAY);
            typedArray.recycle();
        }

        this.initPaint();
    }
}
