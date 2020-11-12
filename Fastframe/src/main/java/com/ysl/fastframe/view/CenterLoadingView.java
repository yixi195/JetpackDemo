package com.ysl.fastframe.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ysl.fastframe.R;

public class CenterLoadingView extends Dialog {

    private ImageView ivImage;
    private TextView tvMsg;

    private AnimationDrawable animationDrawable;

    public CenterLoadingView(Context context) {
        super(context, R.style.loading_dialog);
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.common_dialog_loading_layout);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        initAnim();
    }

    private void initAnim() {
        ivImage.setImageResource(R.drawable.animation_loading_footer);
        animationDrawable = (AnimationDrawable) ivImage.getDrawable();
    }


    @Override
    public void show() {
        super.show();
        animationDrawable.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animationDrawable.stop();
    }


    @Override
    public void setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title) && null!=tvMsg) {
            tvMsg.setText(title);
        }
    }

}