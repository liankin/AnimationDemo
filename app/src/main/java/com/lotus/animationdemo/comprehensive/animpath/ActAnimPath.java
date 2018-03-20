package com.lotus.animationdemo.comprehensive.animpath;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ——运动轨迹——
 */
public class ActAnimPath extends AppCompatActivity {

    @BindView(R.id.btn_view)
    Button btnView;

    private AnimatorPath path;//声明动画集合

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_anim_path);
        ButterKnife.bind(this);

        setPath();

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 注意："btnView"参数其实对应的就是setBtnView(PathPoint newLoc)方法,
                // 当我们在当前类中定义了该方法,就会自动通过反射的机制来调用该方法
                startAnimatorPath(btnView, "btnView", path);
            }
        });
    }

    /*设置动画路径*/
    public void setPath() {

        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int mWidth = wm.getDefaultDisplay().getWidth()-260;//因为有标题栏占据了一定高度，若不减去一部分的话，运动轨迹将是偏屏幕中心右下方一点
        int mHeight = wm.getDefaultDisplay().getHeight()-300;

        path = new AnimatorPath();
//        path.moveTo(0, 0);
//        path.lineTo(400, 400);
//        path.secondBesselCurveTo(600, 200, 800, 400); //二阶贝塞尔曲线移动
//        path.thirdBesselCurveTo(100, 600, 900, 1000, 200, 1200);//三阶贝塞尔曲线移动
        path.moveTo( mWidth/2, mHeight/2);
        path.lineTo( 0,mHeight/2);
        path.thirdBesselCurveTo(mWidth/8, mHeight/6, mWidth/8, mHeight/12, mWidth/2, mHeight/6);
        path.secondBesselCurveTo( mWidth/2 + mWidth/6, mHeight/6, mWidth/2, mHeight/2);
    }



    /**
     * 指定移动轨迹
     *
     * @param view         使用动画的View
     * @param propertyName 属性名字
     * @param path         动画路径集合
     */
    private void startAnimatorPath(View view, String propertyName, AnimatorPath path) {
        //路径
        ObjectAnimator anim1 = ObjectAnimator.ofObject( ActAnimPath.this, propertyName, new PathEvaluator(), path.getPoints().toArray());
        anim1.setInterpolator(new DecelerateInterpolator());
        anim1.setDuration(3000);
        //组合添加缩放透明效果
        addAnimation( anim1, view);
    }

    /**
     * 为控件添加动画
     * @param animator1
     * @param view
     */
    private void addAnimation(ObjectAnimator animator1, final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 1000);
        valueAnimator.setDuration(3000);
//        valueAnimator.setStartDelay(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float alpha = 1 - value / 2000;
                float scale = 1.0f ;
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

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, valueAnimator);
        animatorSet.start();
    }

    /**
     * 添加动画结束监听
     */
    private class AnimEndListener extends AnimatorListenerAdapter {
        private View view;

        public AnimEndListener(View view) {
            this.view = view;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 设置View的属性通过ObjectAnimator.ofObject()的反射机制来调用
     *
     * @param newLoc
     */
    public void setBtnView(PathPoint newLoc) {
        btnView.setTranslationX(newLoc.mX);
        btnView.setTranslationY(newLoc.mY);
    }

}

