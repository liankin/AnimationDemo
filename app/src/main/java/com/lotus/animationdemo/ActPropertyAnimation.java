package com.lotus.animationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lotus.animationdemo.propertyanimation.ActAnimatorSet;
import com.lotus.animationdemo.propertyanimation.ActLayoutTransition;
import com.lotus.animationdemo.propertyanimation.ActObjectAnimator;
import com.lotus.animationdemo.propertyanimation.ActUseXml;
import com.lotus.animationdemo.propertyanimation.ActValueAnimator;
import com.lotus.animationdemo.propertyanimation.ActViewAnimate;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ——属性动画——
 */
public class ActPropertyAnimation extends AppCompatActivity {

    @BindView(R.id.tv_object_anim)
    TextView tvObjectAnim;
    @BindView(R.id.tv_value_anim)
    TextView tvValueAnim;
    @BindView(R.id.tv_animator_set)
    TextView tvAnimatorSet;
    @BindView(R.id.tv_use_xml)
    TextView tvUseXml;
    @BindView(R.id.tv_view_animator)
    TextView tvViewAnimator;
    @BindView(R.id.tv_layout_transition)
    TextView tvLayoutTransition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_property_animation);
        ButterKnife.bind(this);

        tvObjectAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActPropertyAnimation.this, ActObjectAnimator.class);
                startActivity(intent);
            }
        });
        tvValueAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActPropertyAnimation.this, ActValueAnimator.class);
                startActivity(intent);
            }
        });
        tvAnimatorSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActPropertyAnimation.this, ActAnimatorSet.class);
                startActivity(intent);
            }
        });
        tvUseXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActPropertyAnimation.this, ActUseXml.class);
                startActivity(intent);
            }
        });
        tvViewAnimator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActPropertyAnimation.this, ActViewAnimate.class);
                startActivity(intent);
            }
        });
        tvLayoutTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActPropertyAnimation.this, ActLayoutTransition.class);
                startActivity(intent);
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
