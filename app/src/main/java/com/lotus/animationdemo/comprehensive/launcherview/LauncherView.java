package com.lotus.animationdemo.comprehensive.launcherview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lotus.animationdemo.R;
import com.lotus.animationdemo.comprehensive.animpath.AnimatorPath;
import com.lotus.animationdemo.comprehensive.animpath.PathEvaluator;
import com.lotus.animationdemo.comprehensive.animpath.PathPoint;


public class LauncherView extends RelativeLayout{

    private int mHeight;
    private int mWidth;
    private int dp80 = Utils.dp2px(getContext(), 80);//动画执行结束后上移80dp（显示的logo等在布局中上移了70dp）

    private ViewPath redPath, purplePath, yellowPath, bluePath;
    private ImageView red, purple, yellow, blue;
    private AnimatorSet redAll, purpleAll, yellowAll, blueAll;


    public LauncherView(Context context) {
        super(context);
    }

    public LauncherView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LauncherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 开始整个流程
     */
    public void start( int mWidth, int mHeight) {
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        removeAllViews();
        initPath();
        init();
        redAll.start();
        yellowAll.start();
        purpleAll.start();
        blueAll.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showLogo();
            }
        }, 2400);
    }

    /**
     * 初始化控件，并给控件设置动画
     */
    private void init() {

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(CENTER_HORIZONTAL, TRUE);//这里的TRUE 要注意 不是true
        lp.addRule(CENTER_VERTICAL, TRUE);

        purple = new ImageView(getContext());
        purple.setLayoutParams(lp);
        purple.setImageResource(R.drawable.shape_circle_purple);
        addView(purple);

        yellow = new ImageView(getContext());
        yellow.setLayoutParams(lp);
        yellow.setImageResource(R.drawable.shape_circle_yellow);
        addView(yellow);

        blue = new ImageView(getContext());
        blue.setLayoutParams(lp);
        blue.setImageResource(R.drawable.shape_circle_blue);
        addView(blue);

        red = new ImageView(getContext());
        red.setLayoutParams(lp);
        red.setImageResource(R.drawable.shape_circle_red);
        addView(red);

        setAnimation(red, redPath);
        setAnimation(purple, purplePath);
        setAnimation(yellow, yellowPath);
        setAnimation(blue, bluePath);

    }

    /**
     * 初始化运动轨迹
     */
    private void initPath() {

//        redPath = new ViewPath();
//        redPath.moveTo( mWidth/2, mHeight/2);
//        redPath.lineTo( 0,mHeight/2);
//        redPath.curveTo(mWidth/8, mHeight/6, mWidth/8, mHeight/12, mWidth/2, mHeight/6);
//        redPath.quadTo( mWidth/2 + mWidth/6, mHeight/6, mWidth/2, mHeight/2);
//
//        //左下
//        bluePath = new ViewPath();
//        bluePath.moveTo( mWidth/2, mHeight/2);
//        bluePath.lineTo( 0,mHeight/2);
//        bluePath.curveTo(mWidth/12, mHeight/2 + mHeight/6, mWidth/12, mHeight/2 + mHeight/3, mWidth/2, mHeight/2 + mHeight/3);
//        bluePath.quadTo( mWidth/2 + mWidth/6, mHeight/2 + mHeight/3, mWidth/2, mHeight/2);
//
//        //右上
//        yellowPath = new ViewPath();
//        yellowPath.moveTo( mWidth/2, mHeight/2);
//        yellowPath.lineTo( mWidth - mWidth/20, mHeight/2);
//        yellowPath.curveTo( mWidth, mHeight/8, mWidth, mHeight/16, mWidth/2, mHeight/8);
//        yellowPath.quadTo( mWidth/2 - mWidth/6, mHeight/6, mWidth/2, mHeight/2);
//
//        //右下
//        purplePath = new ViewPath();
//        purplePath.moveTo( mWidth/2, mHeight/2);
//        purplePath.lineTo( mWidth - mWidth/20, mHeight/2);
//        purplePath.curveTo( mWidth, mHeight/2 + mHeight/6, mWidth, mHeight/2 + mHeight/3, mWidth/2, mHeight/2 + mHeight/3);
//        purplePath.quadTo( mWidth/2 - mWidth/6, mHeight/2 + mHeight/3, mWidth/2, mHeight/2);

//*******************************
        redPath = new ViewPath(); //偏移坐标
        redPath.moveTo(0, 0);
        redPath.lineTo(mWidth / 5 - mWidth / 2, 0);//绘制直线轨迹
        redPath.curveTo(-700, -mHeight / 2, mWidth / 3 * 2, -mHeight / 4 * 2, 0, -dp80);

        purplePath = new ViewPath(); //偏移坐标
        purplePath.moveTo(0, 0);
        purplePath.lineTo(mWidth / 5 * 2 - mWidth / 2, 0);
        purplePath.curveTo(-300, -mHeight / 2, mWidth, -mHeight / 9 * 5, 0, -dp80);

        yellowPath = new ViewPath(); //偏移坐标
        yellowPath.moveTo(0, 0);
        yellowPath.lineTo(mWidth / 5 * 3 - mWidth / 2, 0);
        yellowPath.curveTo(300, mHeight, -mWidth, -mHeight / 9 * 5, 0, -dp80);

        bluePath = new ViewPath(); //偏移坐标
        bluePath.moveTo(0, 0);
        bluePath.lineTo(mWidth / 5 * 4 - mWidth / 2, 0);
        bluePath.curveTo(700, mHeight / 3 * 2, -mWidth / 2, mHeight / 2 - 20, 0, -dp80);

    }

    /**
     * 指定移动轨迹
     * @param view
     * @param path
     */
    private void setAnimation(final ImageView view, ViewPath path) {
        //路径
        ObjectAnimator anim1 = ObjectAnimator.ofObject(new ViewObj(view), "fabLoc", new ViewPathEvaluator(), path.getPoints().toArray());
        anim1.setInterpolator(new AccelerateDecelerateInterpolator());
        anim1.setDuration(2600);
        //组合添加缩放透明效果
        addAnimation(anim1, view);
    }

    /**
     * 为控件添加动画
     * @param animator1
     * @param view
     */
    private void addAnimation(ObjectAnimator animator1, final ImageView view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 1000);
        valueAnimator.setDuration(1800);
        valueAnimator.setStartDelay(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float alpha = 1 - value / 2000;
                float scale = getScale(view) - 1;
                if (value <= 500) {
                    scale = 1 + (value / 500) * scale;
                } else {
                    scale = 1 + ((1000 - value) / 500) * scale;
                }
                view.setScaleX(scale);
                view.setScaleY(scale);
                view.setAlpha(alpha);
            }
        });
        valueAnimator.addListener(new AnimEndListener(view));
        if (view == red) {
            redAll = new AnimatorSet();
            redAll.playTogether(animator1, valueAnimator);
        }
        if (view == blue) {
            blueAll = new AnimatorSet();
            blueAll.playTogether(animator1, valueAnimator);
        }
        if (view == purple) {
            purpleAll = new AnimatorSet();
            purpleAll.playTogether(animator1, valueAnimator);
        }
        if (view == yellow) {
            yellowAll = new AnimatorSet();
            yellowAll.playTogether(animator1, valueAnimator);
        }

    }

    /**
     * 获得缩放大小
     * @param target
     * @return
     */
    private float getScale(ImageView target) {
        if (target == red)
            return 3.0f;
        if (target == purple)
            return 2.0f;
        if (target == yellow)
            return 4.5f;
        if (target == blue)
            return 3.5f;
        return 2f;
    }

    /**
     * 动画运行完成后，显示的log图标和标题
     */
    private void showLogo() {
        View view = View.inflate(getContext(), R.layout.launcher_load_views, this);

        View logo = view.findViewById(R.id.iv_logo);
        final View slogo = view.findViewById(R.id.iv_slogo);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(logo, View.ALPHA, 0f, 1f);
        alpha.setDuration(800);
        alpha.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(slogo, View.ALPHA, 0f, 1f);
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(slogo, "scaleX", 1.5f, 1.0f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(slogo, "scaleY", 1.5f, 1.0f);
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(scaleX).with(alpha);
                animSet.play(alpha).with(scaleY);
                animSet.setDuration(300);
                animSet.start();
            }
        }, 400);

    }

    private class AnimEndListener extends AnimatorListenerAdapter {
        private View view;

        public AnimEndListener(View target) {
            this.view = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView((view));
        }
    }


    public class ViewObj {
        private final ImageView view;

        public ViewObj(ImageView view) {
            this.view = view;
        }

        public void setFabLoc(ViewPoint newLoc) {
            view.setTranslationX(newLoc.x);
            view.setTranslationY(newLoc.y);

//            System.out.println("0000000000000  " + newLoc.x + "  11111111111   " + newLoc.y);
        }

    }


}


