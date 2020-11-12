package com.ysl.fastframe.utils

import android.util.Log
import java.util.Locale

//import com.ysl.framework.BuildConfig;

/**
 * 可以供我们自行设置日志级别的Logger.
 *
 *
 * Created by Yang on 2017/3/27
 */
object Logger {
    private var isDebug = true //设置是否debug模式
    fun setDebug(debug: Boolean) {
        isDebug = debug
    }

    /**
     * Send a [Log.VERBOSE] log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun v(tag: String, msg: String) {
        if (isDebug) {
            Log.v(tag, msg)
        }
    }

    /**
     * Send a [Log.VERBOSE] log message.
     *
     * @param tag    Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param format The message you would like logged.
     */
    fun v(tag: String, format: String, vararg args: Any) {
        if (isDebug) {
            Log.v(tag, String.format(Locale.getDefault(), format, *args))
        }
    }

    /**
     * Send a [Log.VERBOSE] log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    fun v(tag: String, msg: String, tr: Throwable) {
        if (isDebug) {
            Log.v(tag, msg, tr)
        }
    }

    /**
     * Send a [Log.DEBUG] log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun d(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }

    /**
     * Send a [Log.DEBUG] log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    fun d(tag: String, msg: String, tr: Throwable) {
        if (isDebug) {
            Log.d(tag, msg, tr)
        }
    }

    /**
     * Send an [Log.INFO] log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun i(tag: String, msg: String) {
        if (isDebug) {
            Log.i(tag, msg)
        }
    }

    /**
     * Send a [Log.INFO] log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    fun i(tag: String, msg: String, tr: Throwable) {
        if (isDebug) {
            Log.i(tag, msg, tr)
        }
    }

    /**
     * Send a [Log.WARN] log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun w(tag: String, msg: String) {
        if (isDebug) {
            Log.w(tag, msg)
        }
    }

    /**
     * Send a [Log.WARN] log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    fun w(tag: String, msg: String, tr: Throwable) {
        if (isDebug) {
            Log.w(tag, msg, tr)
        }
    }

    /**
     * Send a [Log.WARN] log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param tr  An exception to log
     */
    fun w(tag: String, tr: Throwable) {
        if (isDebug) {
            Log.w(tag, tr)
        }
    }

    /**
     * Send an [Log.ERROR] log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun e(tag: String, msg: String) {
        if (isDebug) {
            Log.e(tag, msg)
        }
    }

    /**
     * Send a [Log.ERROR] log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    fun e(tag: String, msg: String, tr: Throwable) {
        if (isDebug) {
            Log.e(tag, msg, tr)
        }
    }


}
