package com.ysl.project.module.keyboard;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import com.ysl.project.module.keyboard.callback.LifecycleCallbacks;
import com.ysl.project.module.keyboard.callback.RootViewDeferringInsetsCallback;
import com.ysl.project.module.keyboard.callback.ViewAutoMoveCallback;
import com.ysl.project.module.keyboard.listener.KeyboardListener;


public class SystemUiControll {


    private static final SystemUiControll systemUiControll = new SystemUiControll();

    private SystemUiControll() {

    }

    public static SystemUiControll getInstence() {
        return systemUiControll;
    }

    private LifecycleCallbacks lifecycleCallbacks;

    public void register(Application application) {
        lifecycleCallbacks = new LifecycleCallbacks();
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }


    public void setKeyBoardListener(KeyboardListener keyBoardListener) {
        RootViewDeferringInsetsCallback rootViewInsetsCallback = new RootViewDeferringInsetsCallback(WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE, keyBoardListener);
        ViewCompat.setOnApplyWindowInsetsListener(lifecycleCallbacks.getDecorView(), rootViewInsetsCallback);
        ViewCompat.setWindowInsetsAnimationCallback(lifecycleCallbacks.getDecorView(), rootViewInsetsCallback);

    }

    public void setAutoMoveView(View... views) {
        for (View view : views) {
            setAutoMoveView(view);
        }
    }

    public void setAutoMoveView(View view) {
        ViewAutoMoveCallback viewAutoMoveCallback = new ViewAutoMoveCallback(WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_STOP, view);
        ViewCompat.setWindowInsetsAnimationCallback(view, viewAutoMoveCallback);
    }


    public void showOrHideKeyBoard(EditText editText) {

        boolean visible = lifecycleCallbacks.isVisible(UiType.KEYBOARY);
        if (visible) {
            lifecycleCallbacks.getController().hide(UiType.KEYBOARY);
        } else {
            editText.requestFocus();
            lifecycleCallbacks.getController().show(UiType.KEYBOARY);
        }
    }


}
