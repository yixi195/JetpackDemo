package com.ysl.project.other

import android.content.Intent
import android.view.View
import com.ysl.fastframe.base.fragment.BaseVMFragment
import com.ysl.project.R
import com.ysl.project.other.keyboard.SoftKeyboardAnimActivity
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : BaseVMFragment<NotificationsViewModel>() {

    override fun getContentView(): Int = R.layout.fragment_notifications

    override fun initView(view: View) {
        btn_keyboard.setOnClickListener {
            startActivity(Intent(requireActivity(),SoftKeyboardAnimActivity::class.java))
        }
    }

}