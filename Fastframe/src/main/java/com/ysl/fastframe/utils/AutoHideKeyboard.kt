package com.ysl.fastframe.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import android.widget.EditText
import android.widget.ScrollView
import androidx.recyclerview.widget.RecyclerView

/**
 * Automatic hidden soft keyboard
 * Created by huang on 2017/6/19.
 */

class AutoHideKeyboard
/**
 */
private constructor(activity: Activity, content: ViewGroup?) {

    init {
        var content = content
        if (content == null) {
            content = activity.findViewById<View>(android.R.id.content) as ViewGroup
        }
        getScrollView(content, activity)
        content.setOnTouchListener { view, motionEvent ->
            dispatchTouchEvent(activity, motionEvent)

            false
        }
    }

    private fun getScrollView(viewGroup: ViewGroup?, activity: Activity) {
        if (null == viewGroup) {
            return
        }
        val count = viewGroup.childCount
        for (i in 0 until count) {
            val view = viewGroup.getChildAt(i)
            if (view is ScrollView) {
                view.setOnTouchListener { view, motionEvent ->
                    dispatchTouchEvent(activity, motionEvent)

                    false
                }
            } else if (view is AbsListView) {
                view.setOnTouchListener { view, motionEvent ->
                    dispatchTouchEvent(activity, motionEvent)

                    false
                }
            } else if (view is RecyclerView) {
                view.setOnTouchListener { view, motionEvent ->
                    dispatchTouchEvent(activity, motionEvent)

                    false
                }
            } else if (view is ViewGroup) {

                this.getScrollView(view, activity)
            }
        }
    }

    private fun dispatchTouchEvent(mActivity: Activity, ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = mActivity.currentFocus
            if (null != v && isShouldHideInput(v, ev)) {
                hideSoftInput(mActivity, v.windowToken)
            }
        }
        return false
    }

    private fun isShouldHideInput(v: View, event: MotionEvent): Boolean {
        if (v is EditText) {
            val rect = Rect()
            v.getHitRect(rect)
            if (rect.contains(event.x.toInt(), event.y.toInt())) {
                return false
            }
        }
        return true
    }

    private fun hideSoftInput(mActivity: Activity, token: IBinder?) {
        if (token != null) {
            val im = mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    companion object {

        /**
         * Initialization method
         */
        fun init(activity: Activity) {
            AutoHideKeyboard(activity, null)
        }

        /**
         * Can pass the outer layout
         */
        fun init(activity: Activity, content: ViewGroup) {
            AutoHideKeyboard(activity, content)
        }
    }
}
