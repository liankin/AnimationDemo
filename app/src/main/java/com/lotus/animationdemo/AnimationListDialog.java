package com.lotus.animationdemo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * 帧动画
 */

public class AnimationListDialog extends Dialog {

    private AnimationDrawable anim;

    public AnimationListDialog(Context context) {
        super(context, R.style.alert_dialog);// R.style.alert_dialog自定义的style
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_frame_animation);

        ImageView img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.drawable.animation_list);

        anim = (AnimationDrawable) img.getDrawable();
        anim.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        anim.stop();
    }

    @Override
    public void show() {
        super.show();
        WindowManager windowManager = getWindow().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int)(display.getWidth());
        lp.height = (int)(display.getHeight());
        this.getWindow().setAttributes(lp);
    }
}


