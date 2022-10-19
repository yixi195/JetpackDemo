package com.ysl.project.module.keyboard

import android.app.Application
import android.view.View
import com.ysl.project.module.keyboard.listener.KeyboardListener
import com.ysl.project.module.keyboard.callback.RootViewDeferringInsetsCallback
import com.ysl.project.module.keyboard.callback.ViewAutoMoveCallback
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import com.ysl.project.module.keyboard.UiType
import com.ysl.project.module.keyboard.SystemUiControll
import com.ysl.project.module.keyboard.callback.LifecycleCallbacks

class SystemUiControll private constructor() {
    private var lifecycleCallbacks: LifecycleCallbacks? = null
    fun register(application: Application) {
        lifecycleCallbacks = LifecycleCallbacks()
        application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
    }

    fun setKeyBoardListener(keyBoardListener: KeyboardListener?) {
        val rootViewInsetsCallback = RootViewDeferringInsetsCallback(
            WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_CONTINUE_ON_SUBTREE,
            keyBoardListener
        )
        ViewCompat.setOnApplyWindowInsetsListener(
            lifecycleCallbacks!!.decorView,
            rootViewInsetsCallback
        )
        ViewCompat.setWindowInsetsAnimationCallback(
            lifecycleCallbacks!!.decorView,
            rootViewInsetsCallback
        )
    }

    fun setAutoMoveView(vararg views: View?) {
        for (view in views) {
            setAutoMoveView(view)
        }
    }

    fun setAutoMoveView(view: View?) {
        val viewAutoMoveCallback =
            ViewAutoMoveCallback(WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_STOP, view)
        ViewCompat.setWindowInsetsAnimationCallback(view!!, viewAutoMoveCallback)
    }

    fun showOrHideKeyBoard(editText: EditText) {
        val visible = lifecycleCallbacks!!.isVisible(UiType.KEYBOARY)
        if (visible) {
            lifecycleCallbacks!!.controller.hide(UiType.KEYBOARY)
        } else {
            editText.requestFocus()
            lifecycleCallbacks!!.controller.show(UiType.KEYBOARY)
        }
    }

    companion object {
        val instence = SystemUiControll()
    }
}