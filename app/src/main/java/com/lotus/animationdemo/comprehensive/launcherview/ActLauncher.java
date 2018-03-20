package com.lotus.animationdemo.comprehensive.launcherview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.lotus.animationdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ——小球按指定轨迹运动——
 */
public class ActLauncher extends AppCompatActivity {

    @BindView(R.id.load_view)
    LauncherView loadView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_launcher);
        ButterKnife.bind(this);

        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int mWidth = wm.getDefaultDisplay().getWidth();
        int mHeight = wm.getDefaultDisplay().getHeight();

        loadView.start( mWidth, mHeight );
    }
}
