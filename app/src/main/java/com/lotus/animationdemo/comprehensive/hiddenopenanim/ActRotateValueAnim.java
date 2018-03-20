package com.lotus.animationdemo.comprehensive.hiddenopenanim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ——平铺展开动画——
 * 按指定高度平铺展开控件
 */

public class ActRotateValueAnim extends AppCompatActivity {

    @BindView(R.id.layout_hide)
    LinearLayout layoutHide;
    @BindView(R.id.layout_bottom)
    RelativeLayout layoutBottom;

    private int height = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_rotate_value_anim);
        ButterKnife.bind(this);

        // Android是消息驱动的模式，View.post的Runnable任务会被加入任务队列，
        // 并且等待第一次TraversalRunnable执行结束后才执行，此时已经执行过一次measure、layout过程了，
        // 所以在后面执行post的Runnable时，已经有measure的结果，因此此时可以获取到View的宽高
        layoutHide.post(new Runnable() {
            @Override
            public void run() {
                //获取需要平铺展开的控件高度
                height = layoutHide.getHeight();
                layoutHide.setVisibility(View.GONE);
            }
        });

        layoutBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //执行展开动画
                HiddenAnimUtils.newInstance( ActRotateValueAnim.this, layoutHide, layoutBottom, height).toggle();
            }
        });
    }

}
