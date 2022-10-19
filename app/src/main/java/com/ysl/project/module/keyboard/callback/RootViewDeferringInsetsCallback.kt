package com.ysl.project.module.keyboard.callback

import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import com.ysl.project.module.keyboard.listener.KeyboardListener
import com.ysl.project.module.keyboard.UiType

class RootViewDeferringInsetsCallback
/**
 * Creates a new [WindowInsetsAnimationCompat] callback with the given
 * [dispatch mode][.getDispatchMode].
 *
 * @param dispatchMode The dispatch mode for this callback. See [.getDispatchMode].
 */(dispatchMode: Int, private val keyboardListener: KeyboardListener) :
    WindowInsetsAnimationCompat.Callback(dispatchMode), OnApplyWindowInsetsListener {
    private var view: View? = null
    private var lastWindowInsets: WindowInsetsCompat? = null
    private var deferredInsets = false
    override fun onApplyWindowInsets(v: View, insets: WindowInsetsCompat): WindowInsetsCompat {
        view = v
        lastWindowInsets = insets
        return WindowInsetsCompat.CONSUMED
    }

    override fun onPrepare(animation: WindowInsetsAnimationCompat) {
        if (animation.typeMask and UiType.KEYBOARY != 0) {
            deferredInsets = true
            keyboardListener.onKeyBoardAnimStart()
        }
    }

    override fun onProgress(
        insets: WindowInsetsCompat,
        runningAnimations: List<WindowInsetsAnimationCompat>
    ): WindowInsetsCompat {
        if (deferredInsets) {
            // First we get the insets which are potentially deferred
            val typesInset = insets.getInsets(UiType.KEYBOARY)
            // Then we get the persistent inset types which are applied as padding during layout
            val otherInset = insets.getInsets(UiType.ALL_BARS)

            // Now that we subtract the two insets, to calculate the difference. We also coerce
            // the insets to be >= 0, to make sure we don't use negative insets.
            val subtract = Insets.subtract(typesInset, otherInset)
            val diff = Insets.max(subtract, Insets.NONE)
            keyboardListener.onKeyBoardHeightChange(diff.bottom)
        }
        return insets
    }

    override fun onEnd(animation: WindowInsetsAnimationCompat) {
        if (deferredInsets && animation.typeMask and UiType.KEYBOARY != 0) {
            deferredInsets = false
            keyboardListener.onKeyBoardAnimEnd()
            if (lastWindowInsets != null) {
                ViewCompat.dispatchApplyWindowInsets(view!!, lastWindowInsets!!)
            }
        }
    }
}