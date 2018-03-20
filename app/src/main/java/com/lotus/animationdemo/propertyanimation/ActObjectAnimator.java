package com.lotus.animationdemo.propertyanimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ObjectAnimator、PropertyValuesHolder
 * (PropertyValuesHolder把动画给组合起来，
 * 在属性动画中，如果针对一个对象的多个属性，就同时需要多个动画了，
 * 可以使用PropertyValuesHolder 来实现，
 * 比如需要在平移的过程中，同时改变x,y的缩放，可以这样实现)
 */
public class ActObjectAnimator extends AppCompatActivity {

    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.tv_restore)
    TextView tvRestore;
    @BindView(R.id.tv_overturn)
    TextView tvOverturn;
    @BindView(R.id.tv_shrink)
    TextView tvShrink;
    @BindView(R.id.tv_shrink_restore)
    TextView tvShrinkRestore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_object_animator);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv_overturn, R.id.tv_shrink, R.id.tv_restore, R.id.tv_shrink_restore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_restore:
                restoreView(imageView);
                break;
            case R.id.tv_overturn:
                overturnAnimation(imageView);
                break;
            case R.id.tv_shrink:
                shrinkAnimation(imageView);
                break;
            case R.id.tv_shrink_restore:
                propertyValuesHolder(imageView);
                break;
        }
    }

    /**
     * 还原
     *
     * @param view
     */
    private void restoreView(View view) {
        view.setAlpha(1.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    /**
     * 翻转
     *
     * @param view
     */
    private void overturnAnimation(View view) {
        ObjectAnimator//
                .ofFloat(view, "rotationX", 0.0F, 360.0F)//
                .setDuration(500)//
                .start();
    }

    /**
     * 缩小淡出
     * 特别注意：ObjectAnimator的动画原理是不停的调用setXXX方法更新属性值，
     * 所有使用ObjectAnimator更新属性时的前提是Object必须声明有getXXX和setXXX方法。
     * @param view
     */
    private void shrinkAnimation(final View view) {
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(view, "rotationX", 1.0F, 0.2F)//rotationX这个是设置属性的字符串，不管写的是什么并无影响
                .setDuration(800);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    /**
     * 缩小淡出并还原
     *
     * @param view
     */
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(1000).start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isFinishing()) {
            finish();
        }
    }

}
