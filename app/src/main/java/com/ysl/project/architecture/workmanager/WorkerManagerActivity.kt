package com.ysl.project.architecture.workmanager

import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ysl.fastframe.base.activity.BaseActivity
import com.ysl.project.R
import kotlinx.android.synthetic.main.activity_worker_manager.*

/**
 * workmanager
 * Created by YangShlai on 2020/11/13.
 */
class WorkerManagerActivity : BaseActivity() {
    private val workManager : WorkManager by lazy {
        WorkManager.getInstance(this)
    }

    override fun getContentView(): Int {
        return R.layout.activity_worker_manager
    }

    override fun initView() {
        btn_single.setOnClickListener {
            val request = OneTimeWorkRequest.from(OneWorker::class.java)
            var result = workManager.enqueue(request)
            tv_msg.text = tv_msg.text.toString() + result.result
        }

        btn_much_sort.setOnClickListener {
            // 多任务按顺序执行
            workManager.beginWith(
                mutableListOf(
                    OneTimeWorkRequest.from(OneWorker::class.java)
                ))
                .then(OneTimeWorkRequestBuilder<TwoWorker>().build())
                .enqueue()
        }

        btn_cancel.setOnClickListener {
            workManager.cancelAllWork()
        }

    }
    override fun initData() {}
}