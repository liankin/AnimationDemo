package com.lotus.animationdemo.comprehensive.hiddenopenanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 下拉展开动画
 */

public class ActDropView extends AppCompatActivity {

    @BindView(R.id.layout_hide)
    LinearLayout layoutHide;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;

    private float mDensity;
    private int mHiddenViewMeasuredHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_rotate_value_anim);
        ButterKnife.bind(this);
        //获取像素密度
        mDensity = getResources().getDisplayMetrics().density;
        //获取布局的高度
//        mHiddenViewMeasuredHeight = (int) (mDensity * 280 + 0.5);

        // Android是消息驱动的模式，View.post的Runnable任务会被加入任务队列，
        // 并且等待第一次TraversalRunnable执行结束后才执行，此时已经执行过一次measure、layout过程了，
        // 所以在后面执行post的Runnable时，已经有measure的结果，因此此时可以获取到View的宽高
        layoutHide.post(new Runnable() {
            @Override
            public void run() {
                //获取需要平铺展开的控件高度
                mHiddenViewMeasuredHeight = layoutHide.getHeight();
                layoutHide.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 动画打开——下拉展开
     * @param view
     */
    private void animateOpen(final View view){
        view.setVisibility(View.VISIBLE);
        ValueAnimator animator = createDropAnimator(view,0,mHiddenViewMeasuredHeight);
        animator.start();
    }

    /**
     * 动画关闭——上升收起
     * @param view
     */
    private void animatorClose(final View view){
        int origHeight = view.getHeight();
        ValueAnimator animator = createDropAnimator(view,
                origHeight,0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }

    /**
     * 下拉展开、上升收起效果动画
     * @param view
     * @param start
     * @param end
     * @return
     */
    private ValueAnimator createDropAnimator(final  View view,int start,
                                             int end){
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (Integer)valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    /**
     * 开关View旋转动画
     * @param view
     * @param isOpend
     */
    private void switchRotateAnimation(View view,boolean isOpend){
        RotateAnimation animation;
        if(isOpend){
            animation = new RotateAnimation(0, 180,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }else {
            animation = new RotateAnimation(180, 0,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        animation.setDuration(30);//设置动画持续时间
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatMode(Animation.REVERSE);//设置反方向执行
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        view.startAnimation(animation);
    }

    @OnClick({R.id.layout_bottom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_bottom:
                if(layoutHide.getVisibility() == View.GONE){
                    switchRotateAnimation(layoutBottom,true);
                    //打开动画
                    animateOpen(layoutHide);
                }else {
                    switchRotateAnimation(layoutBottom,false);
                    //关闭动画
                    animatorClose(layoutHide);
                }
                break;
        }
    }
}
