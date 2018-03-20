package com.lotus.animationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lotus.animationdemo.comprehensive.ActMenuPop;
import com.lotus.animationdemo.comprehensive.animpath.ActAnimPath;
import com.lotus.animationdemo.comprehensive.hiddenopenanim.ActDropView;
import com.lotus.animationdemo.comprehensive.hiddenopenanim.ActRotateValueAnim;
import com.lotus.animationdemo.comprehensive.launcherview.ActLauncher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.tv_tweened_anim)
    TextView tvTweenedAnim;
    @BindView(R.id.tv_frame_anim)
    TextView tvFrameAnim;
    @BindView(R.id.tv_property_anim)
    TextView tvPropertyAnim;
    @BindView(R.id.tv_hidden_anim)
    TextView tvHiddenAnim;
    @BindView(R.id.tv_luncher_anim)
    TextView tvLuncherAnim;
    @BindView(R.id.tv_anim_path)
    TextView tvAnimPath;
    @BindView(R.id.tv_menu_pop)
    TextView tvMenuPop;
    @BindView(R.id.tv_drop_view)
    TextView tvDropView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_tweened_anim, R.id.tv_frame_anim, R.id.tv_property_anim,
            R.id.tv_hidden_anim, R.id.tv_anim_path, R.id.tv_luncher_anim,
            R.id.tv_menu_pop, R.id.tv_drop_view})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.tv_tweened_anim:
                intent = new Intent(HomeActivity.this, ActTweenedAnimation.class);
                startActivity(intent);
                break;
            case R.id.tv_frame_anim:
                intent = new Intent(HomeActivity.this, ActFrameAnimation.class);
                startActivity(intent);
                break;
            case R.id.tv_property_anim:
                intent = new Intent(HomeActivity.this, ActPropertyAnimation.class);
                startActivity(intent);
                break;
            case R.id.tv_hidden_anim:
                intent = new Intent(HomeActivity.this, ActRotateValueAnim.class);
                startActivity(intent);
                break;
            case R.id.tv_anim_path:
                intent = new Intent(HomeActivity.this, ActAnimPath.class);
                startActivity(intent);
                break;
            case R.id.tv_luncher_anim:
                intent = new Intent(HomeActivity.this, ActLauncher.class);
                startActivity(intent);
                break;
            case R.id.tv_menu_pop:
                intent = new Intent(HomeActivity.this, ActMenuPop.class);
                startActivity(intent);
                break;
            case R.id.tv_drop_view:
                intent = new Intent(HomeActivity.this, ActDropView.class);
                startActivity(intent);
                break;
        }
    }
}
