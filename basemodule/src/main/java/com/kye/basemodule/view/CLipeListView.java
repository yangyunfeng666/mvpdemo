package com.kye.basemodule.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kye.basemodule.R;


/**
 * Created by yyf on 2018/5/3.
 * 横向滑动删除本行的listview
 */

public class CLipeListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private   float x=0;
    private   float y =0;
    public interface DeleteListener {
        void DeleteItem(int position);
    }
    private DeleteListener deleteListener;
    public CLipeListView(Context context) {
        super(context);
    }
    public void setDeleteListener(DeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }
    //删除按钮
    private View deleteView;
    private TextView textView;
    private ViewGroup viewGroup;//列表布局
    private int mSelectItem;//列表选项
    private boolean isDeleteShow;//删除按钮是否显示出来


    private int mTextsize = 14;
    private int mTextcolor = 14;
    private int mCorp_width = 20;
    private int mCorp_height = 20;
    private int mCorp_margin = 20;
    private int mBackgroupColor =Color.RED;
    private String mText ="删除";

    public CLipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(getContext
                (),this);
        setOnTouchListener(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CLipeListView);
        mTextcolor = typedArray.getColor(R.styleable.CLipeListView_corp_text_color, Color.WHITE);
        mTextsize = typedArray.getDimensionPixelOffset(R.styleable.CLipeListView_corp_text_size,14);
        mBackgroupColor = typedArray.getColor(R.styleable.CLipeListView_corp_text_background_color,Color.RED);
        mCorp_height = typedArray.getDimensionPixelOffset(R.styleable.CLipeListView_corp_text_height,20);
        mCorp_width = typedArray.getDimensionPixelOffset(R.styleable.CLipeListView_corp_text_width,20);
        mText = typedArray.getString(R.styleable.CLipeListView_corp_text);
        typedArray.recycle();

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.e("show","onDown");
        int  nowItem = pointToPosition((int)motionEvent.getX(),(int)motionEvent.getY());
        if(nowItem != mSelectItem){
            if(isDeleteShow) {
                hideDeletem();
            }
            mSelectItem = nowItem;
        }
        return false;
    }



    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float x, float y) {

            if (!isDeleteShow && Math.abs(x) > Math.abs(y)) {//如果删除按钮显示并且x的相对值大于y值
                Log.e("show:", x + "");
                if (x < 0) {
                    deleteView = LayoutInflater.from(getContext()).inflate(R.layout.view_delete_text, null);
                    textView = deleteView.findViewById(R.id.corp_text);
                    textView.setText(mText);
                    textView.setTextSize(mTextsize);
                    textView.setTextColor(mTextcolor);
                    textView.setBackgroundColor(mBackgroupColor);
                    LinearLayout.LayoutParams textViewLayoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
                    textViewLayoutParams.width = mCorp_width;
                    textViewLayoutParams.height= mCorp_height;
                    textView.setLayoutParams(textViewLayoutParams);
                    deleteView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.e("show", "onClick");
                            viewGroup.removeView(deleteView);
                            deleteView = null;
                            isDeleteShow = false;
                            deleteListener.DeleteItem(mSelectItem);
                        }
                    });
                    viewGroup = (ViewGroup) getChildAt(mSelectItem - getFirstVisiblePosition());
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    viewGroup.addView(deleteView, params);
                    ObjectAnimator.ofFloat(deleteView, "translationX", 0, deleteView.getWidth()).setDuration(1000).start();
                    isDeleteShow = true;
                } else {
                    isDeleteShow = false;
                    hideDeletem();
                }
            }else{//横向删除
                if(isDeleteShow&& Math.abs(x) > Math.abs(y)){
                   if(x>0){
                       isDeleteShow = false;
                       hideDeletem();
                   }
                }
            }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    // 触摸监听事件


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.e("show","onTouch");
        return gestureDetector.onTouchEvent(motionEvent);
    }

    private void hideDeletem() {
        viewGroup.removeView(deleteView);
        deleteView = null;
        isDeleteShow = false;
    }
}
