package com.lotus.animationdemo.comprehensive;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.lotus.animationdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 灵动菜单：菜单弹出效果
 */

public class ActMenuPop extends AppCompatActivity {

    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.img_menu_child1)
    ImageView imgMenuChild1;
    @BindView(R.id.img_menu_child2)
    ImageView imgMenuChild2;
    @BindView(R.id.img_menu_child3)
    ImageView imgMenuChild3;
    @BindView(R.id.img_menu_child4)
    ImageView imgMenuChild4;

    private List<ImageView> imageViewList;
    private Boolean isOpendMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_menu_pop);
        ButterKnife.bind(this);

        imageViewList = new ArrayList<>();
        imageViewList.add(imgMenu);
        imageViewList.add(imgMenuChild1);
        imageViewList.add(imgMenuChild2);
        imageViewList.add(imgMenuChild3);
        imageViewList.add(imgMenuChild4);
        isOpendMenu = false;
    }

    private void openMenuAnim(){
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(imageViewList.get(0),
                "alpha",1f,0.5f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewList.get(1),
                "translationX",-230f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewList.get(2),
                "translationX",230f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageViewList.get(3),
                "translationY",-230f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageViewList.get(4),
                "translationY",230f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0,animator1,animator2,animator3,animator4);
        set.start();
        isOpendMenu = true;
    }

    private void closeMenuAnim(){
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(imageViewList.get(0),
                "alpha",0.5f,1f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageViewList.get(1),
                "translationX",0f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageViewList.get(2),
                "translationX",0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageViewList.get(3),
                "translationY",0f);
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageViewList.get(4),
                "translationY",0f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
//        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0,animator1,animator2,animator3,animator4);
        set.start();
        isOpendMenu = false;
    }

    @OnClick({R.id.img_menu, R.id.img_menu_child1, R.id.img_menu_child2, R.id.img_menu_child3, R.id.img_menu_child4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_menu:
                if(!isOpendMenu){
                    openMenuAnim();
                }else {
                    closeMenuAnim();
                }
                break;
            case R.id.img_menu_child1:
                break;
            case R.id.img_menu_child2:
                break;
            case R.id.img_menu_child3:
                break;
            case R.id.img_menu_child4:
                break;
        }
    }
}
