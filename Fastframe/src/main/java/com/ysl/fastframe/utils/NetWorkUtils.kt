package com.ysl.fastframe.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager

/**
 * 网络相关工具类
 */
class NetWorkUtils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        /**
         * 判断网络是否可用
         */
        fun isNetWorkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        /**
         * 检测wifi是否连接
         */
        fun isWifiConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_WIFI
        }

        /**
         * 检测3G是否连接
         */
        fun is3gConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.type == ConnectivityManager.TYPE_MOBILE
        }


        /**
         * 打开网络设置界面
         */
        fun openWirelessSettings(context: Context) {
            context.startActivity(
                Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS).setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                )
            )
        }

        /**
         * 获取当前网络连接类型
         *
         * @param context
         * @return
         */
        fun getNetworkStateName(context: Context): String {
            //获取系统的网络服务
            val connManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    ?: return "NONE"

            //如果当前没有网络

            //获取当前网络类型，如果为空，返回无网络
            val activeNetInfo = connManager.activeNetworkInfo
            if (activeNetInfo == null || !activeNetInfo.isAvailable) {
                return "NONE"
            }

            // 判断是不是连接的是不是wifi
            val wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (null != wifiInfo) {
                val state = wifiInfo.state
                if (null != state)
                    if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                        return "WIFI"
                    }
            }

            // 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
            val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (null != networkInfo) {
                val state = networkInfo.state
                val strSubTypeName = networkInfo.subtypeName
                if (null != state)
                    if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                        when (activeNetInfo.subtype) {
                            //如果是2g类型
                            TelephonyManager.NETWORK_TYPE_GPRS // 联通2g
                                , TelephonyManager.NETWORK_TYPE_CDMA // 电信2g
                                , TelephonyManager.NETWORK_TYPE_EDGE // 移动2g
                                , TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> return "2G"
                            //如果是3g类型
                            TelephonyManager.NETWORK_TYPE_EVDO_A // 电信3g
                                , TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> return "3G"
                            //如果是4g类型
                            TelephonyManager.NETWORK_TYPE_LTE -> return "4G"
                            else ->
                                //中国移动 联通 电信 三种3G制式
                                return if (strSubTypeName.equals(
                                        "TD-SCDMA",
                                        ignoreCase = true
                                    ) || strSubTypeName.equals(
                                        "WCDMA",
                                        ignoreCase = true
                                    ) || strSubTypeName.equals(
                                        "CDMA2000",
                                        ignoreCase = true
                                    )
                                ) {
                                    "3G"
                                } else {
                                    "4G"
                                }
                        }
                    }
            }
            return "NONE"
        }


        val NO_NETWORK = 0
        val NETWORK_CLOSED = 1
        val NETWORK_ETHERNET = 2
        val NETWORK_WIFI = 3
        val NETWORK_MOBILE = 4
        val NETWORK_UNKNOWN = -1


        /**
         * 判断当前网络类型-1为未知网络 0为没有网络连接 1网络断开或关闭   2为以太网 3为WiFi  4为2G 5为3G 6为4G
         */
        fun getNetworkType(context: Context): Int {
            //改为context.getApplicationContext()，防止在Android 6.0上发生内存泄漏
            val connectMgr = context.applicationContext
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                ?: return NO_NETWORK

            val networkInfo = connectMgr.activeNetworkInfo
                ?: // 没有任何网络
                return NO_NETWORK
            if (!networkInfo.isConnected) {
                // 网络断开或关闭
                return NETWORK_CLOSED
            }
            if (networkInfo.type == ConnectivityManager.TYPE_ETHERNET) {
                // 以太网网络
                return NETWORK_ETHERNET
            } else if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                // wifi网络，当激活时，默认情况下，所有的数据流量将使用此连接
                return NETWORK_WIFI
            } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                // 移动数据连接,不能与连接共存,如果wifi打开，则自动关闭
                when (networkInfo.subtype) {
                    TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN,
                        // 2G网络
                    TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP,
                        // 3G网络
                    TelephonyManager.NETWORK_TYPE_LTE ->
                        // 4G网络
                        return NETWORK_MOBILE
                }
            }
            // 未知网络
            return NETWORK_UNKNOWN
        }
    }
}
