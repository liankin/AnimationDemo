package com.lotus.animationdemo.propertyanimation;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActLayoutTransition extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.btn_add_btns)
    Button btnAddBtns;
    @BindView(R.id.check_box_appear)
    CheckBox checkBoxAppear;
    @BindView(R.id.check_box_change_appear)
    CheckBox checkBoxChangeAppear;
    @BindView(R.id.check_box_disappear)
    CheckBox checkBoxDisappear;
    @BindView(R.id.check_box_change_disappear)
    CheckBox checkBoxChangeDisappear;
    @BindView(R.id.layout_container)
    LinearLayout layoutContainer;
    @BindView(R.id.grid_layout)
    GridLayout gridLayout;
    @BindView(R.id.check_box_scaleX)
    CheckBox checkBoxScaleX;

    private int mVal = 0;
    private LayoutTransition mTransition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_layout_transition);
        ButterKnife.bind(this);

        //默认动画全部开启
        mTransition = new LayoutTransition();
        gridLayout.setLayoutTransition(mTransition);

        btnAddBtns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBtn();
            }
        });

        checkBoxAppear.setOnCheckedChangeListener(this);
        checkBoxChangeDisappear.setOnCheckedChangeListener(this);
        checkBoxDisappear.setOnCheckedChangeListener(this);
        checkBoxChangeAppear.setOnCheckedChangeListener(this);
        checkBoxScaleX.setOnCheckedChangeListener( this);
    }

    /**
     * 添加按钮
     */
    public void addBtn() {
        final Button button = new Button(this);
        button.setText((++mVal) + "");
        gridLayout.addView(button, Math.min(1, gridLayout.getChildCount()));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridLayout.removeView(button);
                mVal--;
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mTransition = new LayoutTransition();
        mTransition.setAnimator(
                LayoutTransition.APPEARING,
                ( checkBoxAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.APPEARING) : null));
        mTransition.setAnimator(
                LayoutTransition.CHANGE_APPEARING,
                ( checkBoxChangeAppear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_APPEARING) : null));
        mTransition.setAnimator(
                LayoutTransition.DISAPPEARING,
                ( checkBoxDisappear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.DISAPPEARING) : null));
        mTransition.setAnimator(
                LayoutTransition.CHANGE_DISAPPEARING,
                ( checkBoxChangeDisappear.isChecked() ? mTransition
                        .getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null));
        //动画支持自定义，还支持设置时间
        mTransition.setAnimator(
                LayoutTransition.APPEARING,
                ( checkBoxScaleX.isChecked() ? ObjectAnimator.ofFloat(this, "scaleX", 0, 1) : null));
        gridLayout.setLayoutTransition(mTransition);
    }
}
