package com.ysl.fastframe.interfaces

import android.app.Activity

/**
 * 业务实现此接口，相当于重写BaseActivity
 */
interface IActivityHelper {
    fun onCreate(activity: Activity?)
    fun onStart(activity: Activity?)
    fun onResume(activity: Activity?)
    fun onPause(activity: Activity?)
    fun onStop(activity: Activity?)
    fun onDestroy(activity: Activity?)
}