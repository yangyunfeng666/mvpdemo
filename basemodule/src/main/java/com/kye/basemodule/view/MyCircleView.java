package com.kye.basemodule.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.kye.basemodule.R;

/**
 * Author: yangyunfeng
 * Date: 公元2018-5-2 16:55
 * Description:this is MyCircleView
 * 自定义圆形
 */

public class MyCircleView extends View {

    private int color;//颜色
    private Paint paint;//画笔

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();
    }

    public MyCircleView(Context context) {
        super(context);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyCircleView);
        color = typedArray.getColor(R.styleable.MyCircleView_inner_color, Color.BLACK);
        paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        typedArray.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int radius = Math.min(getWidth()/2,getHeight()/2);
        if(radius==0) return;
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);
    }
}
