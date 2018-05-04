package com.kye.basemodule.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.kye.basemodule.R;

/**
 * Created by yyf on 2018/5/4 18:02
 */

public class CircleProgress extends View {

    private int mMaxProgress =100;
    private int  mProgress = 0;
    private int mCircleStorkeWidth = 4;//圆环的大小
    private int mTextStorkeWidth = 2;//文字的大小
    private int defaultCircleColor = Color.BLACK;
    private int allCircleColor = Color.GRAY;
    private RectF mRectF;//画圆的区域
    private Paint mPaint;//画圆的画笔
    private Context mContext;
    private boolean showText=false;



    public int getmProgress() {
        return mProgress;
    }

    public void setmProgress(int mProgress) {
        this.mProgress = mProgress;
        postInvalidate();
    }

    public CircleProgress(Context context) {
        super(context);    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    @SuppressLint("ResourceAsColor")
    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.CircleProgressView,defStyleAttr,0);
        mMaxProgress = a.getInt(R.styleable.CircleProgress_circle_max_progress,mMaxProgress);
        mProgress = a.getInt(R.styleable.CircleProgress_circle_progress,mProgress);
        mCircleStorkeWidth = a.getDimensionPixelSize(R.styleable.CircleProgress_circle_border_width,mCircleStorkeWidth);
        mTextStorkeWidth = a.getDimensionPixelSize(R.styleable.CircleProgress_circle_text_size,mTextStorkeWidth);
        showText = a.getBoolean(R.styleable.CircleProgress_circle_show_text,false);
        defaultCircleColor = a.getColor(R.styleable.CircleProgress_circle_text_color,Color.BLACK);
        allCircleColor = a.getColor(R.styleable.CircleProgress_circle_color,allCircleColor);
        mContext =  context;
        mRectF = new RectF();
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mCircleStorkeWidth);
        mPaint.setColor(allCircleColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        if(width!=height){
            int min = Math.min(width,height);
           height= width = min;
        }
        //定义画的区域
        mRectF.left = mCircleStorkeWidth/2;
        mRectF.top = mCircleStorkeWidth/2;
        mRectF.right = width - mCircleStorkeWidth/2;
        mRectF.bottom = height - mCircleStorkeWidth/2;
        //画背景圆圈
        canvas.drawArc(mRectF,-90,360,false,mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawArc(mRectF,-90,((float) mProgress/mMaxProgress)*360,false,mPaint);
        if(showText){
            int textheight = height/4;
            String text = mProgress+"%";
            mPaint.setTextSize(mTextStorkeWidth);
            int textwidth = (int)mPaint.measureText(text,0,text.length());
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(text,width/2-textwidth/2,height/4+textheight/2,mPaint);
        }
     }
}
