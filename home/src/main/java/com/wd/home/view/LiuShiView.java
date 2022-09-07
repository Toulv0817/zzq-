package com.wd.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * <p>项目名称:Health</p>
 * <p>包名:com.wd.home.view</p>
 * <p>简述:流式布局</p>
 *
 * @author 张梓萁
 * @date 2022/8/17
 */
public class LiuShiView extends ViewGroup {

//    private ImageView imageView;

    public LiuShiView(Context context) {
        super(context);
//        imageView = new ImageView(context);
//        imageView.setImageResource(R.mipmap.common_button_close_n);
    }

    public LiuShiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LiuShiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置子布局的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //设置子布局的间距
        int left=10,top=10;
        for (int i = 0; i < getChildCount(); i++) {
            //获取子布局视图
            View childview = getChildAt(i);
            //获取子布局宽高
            int width = childview.getMeasuredWidth();
            int height = childview.getMeasuredHeight();
            //判断是否换行
            if(left+width>getWidth()){
                left=10;
                top+=height;
            }
            childview.layout(left,top,left+width,top+height);
            left+=width;
        }
    }
}
