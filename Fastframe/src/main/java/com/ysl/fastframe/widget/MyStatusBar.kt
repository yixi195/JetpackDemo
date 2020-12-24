package com.ysl.fastframe.widget

import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.view.View
import com.ysl.fastframe.utils.ScreenUtils.Companion.getStatusHeight

/**
 * 自定义状态栏的高度
 */
class MyStatusBar : View {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        visibility = VISIBLE
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
    }

    override fun setVisibility(visibility: Int) {
        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            super.setVisibility(visibility)
        } else {
            super.setVisibility(GONE)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val params = this.layoutParams
        params.height = getStatusHeight(context)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }
}