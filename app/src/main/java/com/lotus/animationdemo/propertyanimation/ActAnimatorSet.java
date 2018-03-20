package com.lotus.animationdemo.propertyanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ActAnimatorSet extends AppCompatActivity {

    @BindView(R.id.tv_together_run)
    TextView tvTogetherRun;
    @BindView(R.id.tv_play_with_after)
    TextView tvPlayWithAfter;
    @BindView(R.id.tv_ball)
    TextView tvBall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_animator_set);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_together_run, R.id.tv_play_with_after})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_together_run:
                togetherRun( tvBall);
                break;
            case R.id.tv_play_with_after:
                playWithAfter( tvBall);
                break;
        }
    }

    /**
     * 简单的多动画Together
     * @param view
     */
    public void togetherRun(View view)
    {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat( view, "scaleX",1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat( view, "scaleY",1.0f, 2f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(2000);
        animSet.setInterpolator(new LinearInterpolator());
        //两个动画同时执行
        animSet.playTogether(anim1, anim2);
        animSet.start();
    }

    /**
     * 多动画按次序执行
     * @param view
     */
    public void playWithAfter(View view)
    {
        float cx = view.getX();
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(view, "x",  cx ,  0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(view, "x", cx);
        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(1000);
        animSet.start();
    }
}
