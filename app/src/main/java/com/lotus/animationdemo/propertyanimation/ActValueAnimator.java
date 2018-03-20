package com.lotus.animationdemo.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ActValueAnimator extends AppCompatActivity {

    @BindView(R.id.tv_vertical)
    TextView tvVertical;
    @BindView(R.id.tv_parabola)
    TextView tvParabola;
    @BindView(R.id.tv_ball)
    TextView tvBall;
    @BindView(R.id.tv_restore)
    TextView tvRestore;

    private float ballX = 0.0f;
    private float ballY = 0.0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_value_animator);
        ButterKnife.bind(this);

    }

    /**
     * 获取控件在当前窗口内的绝对坐标
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ballX = tvBall.getX();
        ballY = tvBall.getY();
    }

    @OnClick({R.id.tv_vertical, R.id.tv_parabola, R.id.tv_restore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_vertical:
                verticalRun(tvBall);
                break;
            case R.id.tv_parabola:
                parabolaRun(tvBall);
                break;
            case R.id.tv_restore:
                restore();
                break;
        }
    }

    /**
     * 恢复起始位置
     */
    private void restore( ){
        tvBall.setX( ballX);
        tvBall.setY( ballY);
    }

    /**
     * 自由落体
     *
     * @param view
     */
    public void verticalRun(final View view) {
        //获得屏幕的高（两种方法）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();

//        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        int screenHeight = wm.getDefaultDisplay().getHeight();

        ValueAnimator animator = ValueAnimator.ofFloat( 0, screenHeight - 600 );
        animator.setTarget(view);
        animator.setDuration(1000).start();
//      animator.setInterpolator(value)
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }

    /**
     * 抛物线
     *
     * @param view
     */
    public void parabolaRun(final View view) {
        final float x = view.getX();
        final float y = view.getY();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());//LinearInterpolator：线性均匀改变的插值器
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3 + x;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3) + y;
                return point;
            }
        });
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                view.setX(point.x);//通过属性值设置View属性动画
                view.setY(point.y);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) {
//                    parent.removeView( view);//当动画执行完，删除此view
                    //animator还有cancel()和end()方法：cancel动画立即停止，停在当前的位置；end动画直接到最终状态
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isFinishing()) {
            finish();
        }
    }
}
