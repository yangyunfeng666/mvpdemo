package com.kye.basemodule.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.kye.basemodule.R;

import java.text.NumberFormat;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-7 16:32
 * Description:this is MyProgress
 */


public class MyCircleProgress extends View {


    private boolean show;

    private boolean progressShow;//是否显示进度值

    private int border_color;

    private int border_bg_color;

    private int border_width;

    private String text;

    private String mSuffix;//后缀

    private int mProgress;

    private int mMaxProgress;

    private Paint mBorderPaint;

    private Paint mTextPaint;

    private Paint mProgressPaint;

    private RectF mRectF;

    private int mTextColor;

    private int mTextSize;

    private int mPTextColor;//进度样式

    private int mPTextSize;//进度大小

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof  Bundle){
            Bundle bundle = (Bundle) state;
            show = bundle.getBoolean(SHOW);
            progressShow = bundle.getBoolean(PROGRESSSHOW);
            border_color = bundle.getInt(BORDER_COLOR);
            border_width = bundle.getInt(BORDER_WIDTH);
            text = bundle.getString(TEXT);
            mSuffix = bundle.getString(MSUFFIX);
            mProgress = bundle.getInt(MPROGRESS);
            mMaxProgress = bundle.getInt(MMAXPROGRESS);
            mTextColor = bundle.getInt(MTEXTCOLOR);
            mTextSize = bundle.getInt(MTEXTSIZE);
            mPTextColor = bundle.getInt(MPTEXTCOLOR);
            mPTextSize = bundle.getInt(MPTEXTSIZE);
            initPaint();
            super.onRestoreInstanceState(state);
            return;
        }
        super.onRestoreInstanceState(state);
    }

    private static final String  STATE = "state";
    private static final String  SHOW = "show";
    private static final String  PROGRESSSHOW = "progressShow";
    private static final String  BORDER_COLOR = "border_color";
    private static final String  BORDER_BG_COLOR = "border_bg_color";
    private static final String  BORDER_WIDTH = "border_width";
    private static final String  TEXT = "text";
    private static final String  MSUFFIX = "mSuffix";
    private static final String  MPROGRESS = "mProgress";
    private static final String  MMAXPROGRESS = "mMaxProgress";
    private static final String  MTEXTCOLOR = "mTextColor";
    private static final String  MTEXTSIZE = "mTextSize";
    private static final String  MPTEXTCOLOR = "mPTextColor";
    private static final String  MPTEXTSIZE = "mPTextSize";


    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE,super.onSaveInstanceState());
        bundle.putBoolean(SHOW,isShow());
        bundle.putBoolean(PROGRESSSHOW,isProgressShow());
        bundle.putInt(BORDER_COLOR,getBorder_color());
        bundle.putInt(BORDER_BG_COLOR,getBorder_bg_color());
        bundle.putInt(BORDER_WIDTH,getBorder_width());
        bundle.putString(TEXT,getText());
        bundle.putString(MSUFFIX,getmSuffix());
        bundle.putInt(MPROGRESS,getmProgress());
        bundle.putInt(MMAXPROGRESS,getmMaxProgress());
        bundle.putInt(MTEXTCOLOR,getmTextColor());
        bundle.putInt(MTEXTSIZE,getmTextSize());
        bundle.putInt(MPTEXTCOLOR,getmPTextColor());
        bundle.putInt(MPTEXTSIZE,getmPTextSize());
        return bundle;
    }



    public MyCircleProgress(Context context) {
        super(context);
    }

    public MyCircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributeSet(context,attrs);
        initPaint();
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
        invalidate();
    }

    public boolean isProgressShow() {
        return progressShow;
    }

    public void setProgressShow(boolean progressShow) {
        this.progressShow = progressShow;
        invalidate();
    }

    public int getBorder_color() {
        return border_color;
    }

    public void setBorder_color(int border_color) {
        this.border_color = border_color;
        invalidate();
    }

    public int getBorder_bg_color() {
        return border_bg_color;
    }

    public void setBorder_bg_color(int border_bg_color) {
        this.border_bg_color = border_bg_color;
        invalidate();
    }

    public int getBorder_width() {
        return border_width;
    }

    public void setBorder_width(int border_width) {
        this.border_width = border_width;
        invalidate();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public String getmSuffix() {
        return mSuffix;
    }

    public void setmSuffix(String mSuffix) {
        this.mSuffix = mSuffix;
        invalidate();
    }

    public int getmProgress() {
        return mProgress;
    }

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
        invalidate();
    }

    public int getmMaxProgress() {
        return mMaxProgress;
    }

    public void setmMaxProgress(int mMaxProgress) {
        this.mMaxProgress = mMaxProgress;
        invalidate();
    }

    public int getmTextColor() {
        return mTextColor;
    }

    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        invalidate();
    }

    public int getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        invalidate();
    }

    public int getmPTextColor() {
        return mPTextColor;
    }

    public void setmPTextColor(int mPTextColor) {
        this.mPTextColor = mPTextColor;
        invalidate();
    }

    public int getmPTextSize() {
        return mPTextSize;
    }

    public void setmPTextSize(int mPTextSize) {
        this.mPTextSize = mPTextSize;
        invalidate();
    }

    public void setAttributeSet(Context context, AttributeSet attributeSet){
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet,R.styleable.MyCircleProgress);
        show = typedArray.getBoolean(R.styleable.MyCircleProgress_progress_show_txt,false);
         progressShow= typedArray.getBoolean(R.styleable.MyCircleProgress_progress_show_progress,false);
        if(typedArray.hasValue(R.styleable.MyCircleProgress_progress_str)) {
            text = typedArray.getString(R.styleable.MyCircleProgress_progress_str);
        }
        border_bg_color = typedArray.getColor(R.styleable.MyCircleProgress_progress_border_bg_color,Color.GRAY);
        border_color = typedArray.getColor(R.styleable.MyCircleProgress_progress_bordercolor, Color.BLACK);
        border_width = (int) typedArray.getDimension(R.styleable.MyCircleProgress_progress_border_width,dip2px(context,2));

        mProgress = typedArray.getInt(R.styleable.MyCircleProgress_progress_current_value,0);
        mMaxProgress = typedArray.getInt(R.styleable.MyCircleProgress_progress_max_value,100);

        mTextColor = typedArray.getColor(R.styleable.MyCircleProgress_progress_text_color,Color.BLACK);
        mTextSize = (int) typedArray.getDimension(R.styleable.MyCircleProgress_progress_text_size,sp2px(context,14));

        mPTextColor = typedArray.getColor(R.styleable.MyCircleProgress_progress_p_text_color,Color.BLACK);
        mPTextSize = (int) typedArray.getDimension(R.styleable.MyCircleProgress_progress_p_text_size,sp2px(context,18));

        if(typedArray.hasValue(R.styleable.MyCircleProgress_progress_suffix)) {
            mSuffix = typedArray.getString(R.styleable.MyCircleProgress_progress_suffix);
        }
    }

    public void  initPaint(){
        mRectF = new RectF();
        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(border_width);
        mBorderPaint.setColor(border_bg_color);
        mBorderPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);

        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setTextSize(mPTextSize);
        mProgressPaint.setColor(mPTextColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width,height);
        width = height = radius;

        mRectF.left = border_width/2;
        mRectF.top = border_width/2;
        mRectF.right = width -border_width/2;
        mRectF.bottom = height-border_width/2;

        canvas.drawArc(mRectF,-90,360,false,mBorderPaint);
        mBorderPaint.setColor(border_color);
        canvas.drawArc(mRectF,-90,((float) mProgress/mMaxProgress*360),false,mBorderPaint);

        if(show){
            if(!TextUtils.isEmpty(text)){
                String text_str = text;
                int textwidth = (int) mTextPaint.measureText(text_str);
                int textheight = height/2;
                canvas.drawText(text_str,width/2-textwidth/2,textheight/2,mTextPaint);
            }
        }
        if(progressShow){
            String show_txt = "";
            int value = (int)((float) mProgress/mMaxProgress*100);

            if(!TextUtils.isEmpty(mSuffix)){
                show_txt = value+mSuffix;
            }else{
                show_txt = value+"";
            }
            int textwidth = (int) mProgressPaint.measureText(show_txt);

            canvas.drawText(show_txt,(width-textwidth)/2,(height-(mProgressPaint.descent()-mProgressPaint.ascent()))/2-mProgressPaint.ascent(),mProgressPaint);
        }
    }

    // 根据手机的分辨率将dp的单位转成px(像素)
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    // 将sp值转换为px值
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }




    @Override
    public void invalidate() {
        initPaint();
        super.invalidate();
    }


}
