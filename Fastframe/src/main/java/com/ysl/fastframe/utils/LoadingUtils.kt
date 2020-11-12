package com.ysl.fastframe.utils

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import com.ysl.fastframe.view.CenterLoadingView


/**
 * 等待提示框
 * Created by huang on 2017/5/23.
 */

class LoadingUtils(private val mContext: Context) {

    private var load: CenterLoadingView? = null

    /**
     * 统一耗时操作Dialog
     */
    fun showLoading(txt: String) {
        if (load == null) {
            load = CenterLoadingView(mContext)
        }
        if (load!!.isShowing) {
            load!!.dismiss()
        }
        if (!TextUtils.isEmpty(txt))
            load!!.setTitle(txt)
        if (mContext is Activity && mContext.isFinishing) {
            return
        }
        load!!.show()
    }

    /**
     * 关闭Dialog
     */
    fun dismissLoading() {
        if (mContext is Activity && mContext.isFinishing) {
            return
        }
        if (load != null && load!!.isShowing) {
            load!!.dismiss()
        }
    }


}
