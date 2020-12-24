package com.ysl.project.architecture.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Logger
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
/**
 * Created by YangShlai on 2020/11/13.
 */
class OneWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {


    override fun doWork(): Result {
        val outPutData = workDataOf("extra" to "OneWorker执行完成传值---OK")
        Thread.sleep(5000)
        Log.i("ysl","OneWorker执行完成")
        return Result.success(outPutData)
    }
}