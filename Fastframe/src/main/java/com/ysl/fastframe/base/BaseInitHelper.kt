package com.ysl.fastframe.base

import android.content.Context
import com.ysl.fastframe.interfaces.IActivityHelper
import com.ysl.fastframe.interfaces.IBaseRequirement
import com.ysl.fastframe.interfaces.IFragmentHelper


/**
 * 给这个框架提供应用环境
 */
class BaseInitHelper private constructor() {
    private var mBaseRequirement: IBaseRequirement? = null
    var applicationContext: Context? = null

    /**
     * Activity生命周期监听
     */
    val activityHelper: IActivityHelper?
        get() = if (mBaseRequirement != null) {
            mBaseRequirement!!.activityHelper
        } else null

    /**
     * Fragment生命周期监听
     */
    val fragmentHelper: IFragmentHelper?
        get() = if (mBaseRequirement != null) {
            mBaseRequirement!!.fragmentHelper
        } else null


    fun setBaseRequirement(requirement: IBaseRequirement) {
        mBaseRequirement = requirement
    }

    companion object {

        private var mInstance: BaseInitHelper? = null

        val instance: BaseInitHelper
            get() {
                if (mInstance == null) {
                    mInstance = BaseInitHelper()
                }
                return mInstance!!
            }
    }

}
