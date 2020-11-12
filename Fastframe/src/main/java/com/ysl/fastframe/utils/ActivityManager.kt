package com.ysl.fastframe.utils

import android.app.Activity

import java.util.HashMap

/**
 * Activity管理类
 */
object ActivityManager {
    /**
     * 存放Activity的map
     */
    private val activitys = HashMap<String, Activity>()

    /**
     * 获取管理类中注册的所有Activity的map
     *
     * @return
     */
    fun getActivitys(): Map<String, Activity> {
        return activitys
    }

    /**
     * 根据键值取对应的Activity
     *
     * @param key 键值
     * @return 键值对应的Activity
     */
    fun getActivity(key: String): Activity? {
        return activitys[key]
    }

    /**
     * 注册Activity
     *
     * @param value
     * @param key
     */
    fun addActivity(value: Activity, key: String) {
        Logger.i("addAc: ", "" + value)
        activitys[key] = value
    }

    /**
     * 将key对应的Activity移除掉
     *
     * @param key
     */
    fun removeActivity(key: String) {
        val activity = activitys[key]
        activity?.finish()
        if (activitys.containsKey(key)) {
            activitys.remove(key)
        }
    }

    /**
     * finish掉所有的Activity移除所有的Activity
     */
    fun removeAllActivity() {
        val iterActivity = activitys.values.iterator()
        while (iterActivity.hasNext()) {
            iterActivity.next().finish()
        }
        activitys.clear()
    }
}
