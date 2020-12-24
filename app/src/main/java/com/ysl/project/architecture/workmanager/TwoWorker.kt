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
class TwoWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val extra = inputData.getString("extra")
        Log.i("ysl","extra===" + extra)

        val outPutData = workDataOf("extra" to "TwoWorker执行完成传值---OK")
        Thread.sleep(5000)
        return Result.success(outPutData)
    }
}