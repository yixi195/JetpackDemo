package com.ysl.fastframe.utils.hilog

import android.util.Log
import androidx.annotation.IntDef

/**
 *  Craete by YangShlai on 2021/5/27
 */
class HiLogType {
    @IntDef(V,D,I,W,E,A)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type{}

    companion object{
        const val V = Log.VERBOSE
        const val D = Log.DEBUG
        const val I = Log.INFO
        const val W = Log.WARN
        const val E = Log.ERROR
        const val A = Log.ASSERT
    }
}