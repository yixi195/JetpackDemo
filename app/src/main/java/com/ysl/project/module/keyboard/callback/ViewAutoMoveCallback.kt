package com.ysl.project.module.keyboard.callback

import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import com.ysl.project.module.keyboard.UiType

class ViewAutoMoveCallback
/**
 * Creates a new [WindowInsetsAnimationCompat] callback with the given
 * [dispatch mode][.getDispatchMode].
 *
 * @param dispatchMode The dispatch mode for this callback. See [.getDispatchMode].
 */(dispatchMode: Int, private val view: View?) :
    WindowInsetsAnimationCompat.Callback(dispatchMode) {
    private var deferredInsets = false
    override fun onPrepare(animation: WindowInsetsAnimationCompat) {
        if (animation.typeMask and UiType.KEYBOARY != 0) {
            deferredInsets = true
        }
    }

    override fun onProgress(
        insets: WindowInsetsCompat,
        runningAnimations: List<WindowInsetsAnimationCompat>
    ): WindowInsetsCompat {
        if (deferredInsets && view != null) {

            // First we get the insets which are potentially deferred
            val typesInset = insets.getInsets(UiType.KEYBOARY)
            // Then we get the persistent inset types which are applied as padding during layout
            val otherInset = insets.getInsets(UiType.ALL_BARS)

            // Now that we subtract the two insets, to calculate the difference. We also coerce
            // the insets to be >= 0, to make sure we don't use negative insets.
            val subtract = Insets.subtract(typesInset, otherInset)
            val diff = Insets.max(subtract, Insets.NONE)


            // The resulting `diff` insets contain the values for us to apply as a translation
            // to the view
            view.translationX = (diff.left - diff.right).toFloat()
            view.translationY = (diff.top - diff.bottom).toFloat()
        }
        return insets
    }

    override fun onEnd(animation: WindowInsetsAnimationCompat) {
        if (deferredInsets && animation.typeMask and UiType.KEYBOARY != 0) {
            deferredInsets = false
        }
    }
}