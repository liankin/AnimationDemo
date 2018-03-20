package com.lotus.animationdemo.propertyanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
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


public class ActUseXml extends AppCompatActivity {

    @BindView(R.id.tv_scaleX)
    TextView tvScaleX;
    @BindView(R.id.tv_scaleXY)
    TextView tvScaleXY;
    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_use_xml);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_scaleX, R.id.tv_scaleXY})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_scaleX:
                scaleXAnimaton( imageView);
                break;
            case R.id.tv_scaleXY:
                scaleXYAnimation( imageView);
                break;
        }
    }

    /**
     * 横向缩放
     * @param view
     */
    private void scaleXAnimaton(View view)
    {
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scalex);
        //缩放起点X、Y轴坐标，以View宽高的一半做为初始点，即以view中心点为缩放点（默认缩放点为view中心点）
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight()/2);
        anim.setTarget( view);
        anim.start();
    }

    /**
     * 横向、纵向缩小以左上角为中心点
     * @param view
     */
    private void scaleXYAnimation( View view){
        // 加载动画
        Animator anim = AnimatorInflater.loadAnimator(this, R.animator.scalexy);
        view.setPivotX(0);
        view.setPivotY(0);
        //显示的调用invalidate
        view.invalidate();
        anim.setTarget(view);
        anim.start();

    }
}
