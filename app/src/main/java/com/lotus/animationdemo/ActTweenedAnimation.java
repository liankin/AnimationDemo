package com.lotus.animationdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ——补间动画——
 * 特别特别注意：补间动画执行之后并未改变View的真实布局属性值。
 * 切记这一点，譬如我们在Activity中有一个Button在屏幕上方，
 * 我们设置了平移动画移动到屏幕下方然后保持动画最后执行状态呆在屏幕下方，
 * 这时如果点击屏幕下方动画执行之后的Button是没有任何反应的，
 * 而点击原来屏幕上方没有Button的地方却响应的是点击Button的事件。
 */

public class ActTweenedAnimation extends AppCompatActivity {

    @BindView(R.id.tv_alpha)
    TextView tvAlpha;
    @BindView(R.id.tv_rotate)
    TextView tvRotate;
    @BindView(R.id.tv_scale)
    TextView tvScale;
    @BindView(R.id.tv_translate)
    TextView tvTranslate;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.tv_all_tweened)
    TextView tvAllTweened;

    private Animation myAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_tweened_animation);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_alpha, R.id.tv_rotate, R.id.tv_scale, R.id.tv_translate, R.id.tv_all_tweened})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_alpha://透明动画
                AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
                alphaAnimation.setDuration(2000);
                imageView.startAnimation(alphaAnimation);

                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                /*//使用XML中的动画效果 第一个参数Context为程序的上下文 第二个参数id为动画XML文件的引用
                myAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_alpha);
                imageView.startAnimation(myAnimation);*/
                break;
            case R.id.tv_rotate://旋转动画
                RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(300);//设置动画持续时间
                rotateAnimation.setInterpolator(new LinearInterpolator());
                //设置重复模式，Animation.REVERSE和Animation.RESTART两种方式；
                //默认为Animation.RESTART，当设置重复次数为1时，执行完第一次动画之后，
                //回到动画开始然后执行第二次动画；
                //Animation.REVERSE则当动画是从不透明——》透明，执行完第一次动画的时候，
                //变为不透明，然后执行第二次动画，就从不透明到透明
                rotateAnimation.setRepeatMode(Animation.REVERSE);
                //设置重复次数，repeatCount默认为0，即执行一次；为1时，执行2次
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                rotateAnimation.setFillAfter(false);//动画执行完后是否停留在执行完的状态
                imageView.startAnimation(rotateAnimation);

                /*//使用XML中的动画效果
                myAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_rotate);
                imageView.startAnimation(myAnimation);*/
                break;
            case R.id.tv_scale://缩放动画
                //参数说明：
                //fromX：动画开始前在X坐标的大小。
                //toX：动画结束后在X坐标的大小。
                //fromY：动画开始前在Y坐标的大小。
                //toY：动画结束后在Y坐标的大小。
                //pivotXType：缩放中心点的X坐标类型。取值范围为ABSOLUTE(绝对位置)、RELATIVE_TO_SELF(相对于自身)、RELATIVE_TO_PARENT(相对于父控件(容器))。
                //pivotXValue：缩放中心点的X坐标值。当pivotXType==ABSOLUTE时，表示绝对位置；否则表示相对位置，1.0表示100%。
                //pivotYType：缩放中心点的Y坐标类型。
                //pivotYValue：缩放中心点的Y坐标。
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,1.4f,0,1.4f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                scaleAnimation.setDuration(2000);
                imageView.startAnimation(scaleAnimation);

                /*myAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_scale);
                imageView.startAnimation(myAnimation);*/
                break;
            case R.id.tv_translate://平移动画
                TranslateAnimation translateAnimation = new TranslateAnimation(200,30,-100,500);
                translateAnimation.setDuration(2000);
                imageView.startAnimation(translateAnimation);

                /*myAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_translate);
                imageView.startAnimation(myAnimation);*/
                break;
            case R.id.tv_all_tweened://动画合集
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setDuration(2000);

                AlphaAnimation al = new AlphaAnimation(0,1);
                al.setDuration(2000);
                animationSet.addAnimation(al);

                RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setDuration(300);
                ra.setInterpolator(new LinearInterpolator());
                ra.setRepeatMode(Animation.REVERSE);
                ra.setRepeatCount(Animation.INFINITE);
                ra.setFillAfter(false);
                animationSet.addAnimation(ra);

                ScaleAnimation sa = new ScaleAnimation(0,1.4f,0,1.4f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                sa.setDuration(2000);
                animationSet.addAnimation(sa);

                TranslateAnimation ta = new TranslateAnimation(200,30,-100,500);
                ta.setDuration(2000);
                animationSet.addAnimation(ta);

                imageView.startAnimation(animationSet);

                /*myAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_all_tweened);
                imageView.startAnimation(myAnimation);*/
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isFinishing()) {
            finish();
        }
    }
}
