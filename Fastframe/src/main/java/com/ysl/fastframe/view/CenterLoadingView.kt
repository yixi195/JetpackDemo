package com.ysl.fastframe.view

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.ysl.fastframe.R

class CenterLoadingView(context: Context?) :
    Dialog(context!!, R.style.loading_dialog) {
    private var ivImage: ImageView? = null
    private var tvMsg: TextView? = null
    private var animationDrawable: AnimationDrawable? = null
    private fun init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.common_dialog_loading_layout)
        ivImage = findViewById<View>(R.id.ivImage) as ImageView
        tvMsg = findViewById<View>(R.id.tvMsg) as TextView
        initAnim()
    }

    private fun initAnim() {
        ivImage!!.setImageResource(R.drawable.animation_loading_footer)
        animationDrawable = ivImage!!.drawable as AnimationDrawable
    }

    override fun show() {
        super.show()
        animationDrawable!!.start()
    }

    override fun dismiss() {
        super.dismiss()
        animationDrawable!!.stop()
    }

    override fun setTitle(title: CharSequence?) {
        if (!TextUtils.isEmpty(title) && null != tvMsg) {
            tvMsg!!.text = title
        }
    }

    init {
        init()
    }
}