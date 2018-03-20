package com.lotus.animationdemo.propertyanimation;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ActViewAnimate extends AppCompatActivity {

    @BindView(R.id.tv_ViewAnim)
    TextView tvViewAnim;
    @BindView(R.id.tv_PropertyValuesHolder)
    TextView tvPropertyValuesHolder;
    @BindView(R.id.tv_ball)
    TextView tvBall;
    @BindView(R.id.tv_show)
    TextView tvShow;

    private float ballY = 0;
    private float mScreenHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_view_animate);
        ButterKnife.bind(this);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.tv_ViewAnim, R.id.tv_PropertyValuesHolder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ViewAnim:
                viewAnim(tvBall);
                break;
            case R.id.tv_PropertyValuesHolder:
                tvShow.setText("显示ViewAnim动画进行情况");
                propertyValuesHolderAnima(tvBall);
                break;
        }
    }

    /**
     * View的anim方法,在SDK11的时候，给View添加了animate方法
     *
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void viewAnim(final View view) {
        // need API12
        view.animate()//
                .alpha(0)//
                .y(mScreenHeight / 2).setDuration(1000)
                // need API 12
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        tvShow.setText( "START");
                    }
                    // need API 16
                }).withEndAction(new Runnable() {
            @Override
            public void run() {
                tvShow.setText( "END");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.setY(ballY);
                        view.setAlpha(1.0f);
                    }
                });
            }
        }).start();
    }

    /**
     * 使用ObjectAnimator实现上面的变化，可使用：PropertyValueHolder
     *
     * @param view
     */
    private void propertyValuesHolderAnima(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", ballY, mScreenHeight / 2, ballY);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY).setDuration(1000).start();
    }

    /**
     * 获取控件在当前窗口内的绝对坐标
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ballY = tvBall.getY();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!isFinishing()) {
            finish();
        }
    }
}
