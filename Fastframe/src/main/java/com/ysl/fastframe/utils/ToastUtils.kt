package com.ysl.fastframe.utils

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.ysl.fastframe.R
import com.ysl.fastframe.base.BaseInitHelper

import java.lang.ref.WeakReference


object ToastUtils {

    private var toast: Toast? = null
    private var mShowingToastRef: WeakReference<Toast>? = null
    private var mTextView: TextView? = null

    fun show(text: CharSequence) {
        //        if (TextUtils.isEmpty(text))
        //            return;
        //        if (mShowingToastRef != null) {
        //            toast = mShowingToastRef.get();
        //        }
        //        if (toast == null) {  //保证不重复弹出
        //            if (text.length() < 10) {
        //                toast = Toast.makeText(BaseInitHelper.Companion.getInstance().getApplicationContext(),text, Toast.LENGTH_SHORT);
        //            } else {
        //                toast = Toast.makeText(BaseInitHelper.Companion.getInstance().getApplicationContext(), text, Toast.LENGTH_LONG);
        //            }
        //            mShowingToastRef = new WeakReference<>(toast);
        //        } else {
        //            toast.setText(text);
        //        }
        //        toast.show();
        //***************************自定义Toast*******************************
        if (TextUtils.isEmpty(text))
            return
        if (mShowingToastRef != null) {
            toast = mShowingToastRef!!.get()
        }
        if (toast == null) {  //保证不重复弹出
            toast = Toast(BaseInitHelper.instance.applicationContext)
            mShowingToastRef = WeakReference<Toast>(toast)
        }
        //加载Toast布局
        val toastRoot = LayoutInflater.from(BaseInitHelper.instance.applicationContext)
            .inflate(R.layout.toast, null)
        //初始化布局控件
        mTextView = toastRoot.findViewById<View>(R.id.message) as TextView
        //为控件设置属性
        mTextView!!.text = text
        //Toast的初始化
        //获取屏幕高度
        val wm =
            BaseInitHelper.instance.applicationContext!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val height = wm.defaultDisplay.height
        //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
        toast!!.setGravity(Gravity.TOP, 0, height * 2 / 5)
        if (text.length > 7) {
            toast!!.duration = Toast.LENGTH_LONG
        } else {
            toast!!.duration = Toast.LENGTH_SHORT
        }
        toast!!.view = toastRoot
        toast!!.show()
    }

    fun show(@StringRes resId: Int) {
        show(BaseInitHelper.instance.applicationContext!!.getString(resId))
    }

}