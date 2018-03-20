package com.lotus.animationdemo.comprehensive.animpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class PathView extends View {

    private Paint paint;

    private int mHeight;
    private int mWidth;

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
        //抗锯齿
        paint.setAntiAlias(true);
        //防抖动
        paint.setDither(true);
        //设置画笔未实心
        paint.setStyle(Paint.Style.STROKE);
        //设置颜色
        paint.setColor(Color.GREEN);
        //设置画笔宽度
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Path path = new Path();
//        path.moveTo(60,60);
//        path.lineTo(460,460);
//        path.quadTo(660, 260, 860, 460);
//        path.cubicTo(160,660,960,1060,260,1260);
//        canvas.drawPath(path,paint);

        //左上
        Path path = new Path();
        path.moveTo( mWidth/2, mHeight/2);
        path.lineTo( 0,mHeight/2);
        path.cubicTo(mWidth/8, mHeight/6, mWidth/8, mHeight/12, mWidth/2, mHeight/6);
        path.quadTo( mWidth/2 + mWidth/6, mHeight/6, mWidth/2, mHeight/2);
        canvas.drawPath(path,paint);

        //左下
//        Path path = new Path();
//        path.moveTo( mWidth/2, mHeight/2);
//        path.lineTo( 0,mHeight/2);
//        path.cubicTo(mWidth/12, mHeight/2 + mHeight/6, mWidth/12, mHeight/2 + mHeight/3, mWidth/2, mHeight/2 + mHeight/3);
//        path.quadTo( mWidth/2 + mWidth/6, mHeight/2 + mHeight/3, mWidth/2, mHeight/2);
//        canvas.drawPath(path,paint);

//        //右上
//        Path path = new Path();
//        path.moveTo( mWidth/2, mHeight/2);
//        path.lineTo( mWidth - mWidth/20, mHeight/2);
//        path.cubicTo( mWidth, mHeight/8, mWidth, mHeight/16, mWidth/2, mHeight/8);
//        path.quadTo( mWidth/2 - mWidth/6, mHeight/6, mWidth/2, mHeight/2);
//        canvas.drawPath(path,paint);

        //右下
//        Path path = new Path();
//        path.moveTo( mWidth/2, mHeight/2);
//        path.lineTo( mWidth - mWidth/20, mHeight/2);
//        path.cubicTo( mWidth, mHeight/2 + mHeight/6, mWidth, mHeight/2 + mHeight/3, mWidth/2, mHeight/2 + mHeight/3);
//        path.quadTo( mWidth/2 - mWidth/6, mHeight/2 + mHeight/3, mWidth/2, mHeight/2);
//        canvas.drawPath(path,paint);
    }

    /**
     * 获取屏幕的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
}

