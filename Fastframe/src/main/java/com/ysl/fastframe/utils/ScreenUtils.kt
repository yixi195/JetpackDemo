package com.ysl.fastframe.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout

/**
 * 屏幕相关工具类
 */
class ScreenUtils private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        /**
         * 判断是否是全面屏
         */
        @Volatile
        private var mHasCheckAllScreen: Boolean = false
        @Volatile
        private var mIsAllScreenDevice: Boolean = false

        fun isAllScreenDevice(context: Context): Boolean {
            if (mHasCheckAllScreen) {
                return mIsAllScreenDevice
            }
            mHasCheckAllScreen = true
            mIsAllScreenDevice = false
            // 低于 API 21的，都不会是全面屏。。。
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                return false
            }
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            if (windowManager != null) {
                val display = windowManager.defaultDisplay
                val point = Point()
                display.getRealSize(point)
                val width: Float
                val height: Float
                if (point.x < point.y) {
                    width = point.x.toFloat()
                    height = point.y.toFloat()
                } else {
                    width = point.y.toFloat()
                    height = point.x.toFloat()
                }
                if (height / width >= 1.97f) {
                    mIsAllScreenDevice = true
                }
            }
            return mIsAllScreenDevice
        }

        /**
         * 获得屏幕宽度
         */
        fun getScreenWidth(context: Context): Int {
            //        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            //        DisplayMetrics outMetrics = new DisplayMetrics();
            //        wm.getDefaultDisplay().getMetrics(outMetrics);
            //        return outMetrics.widthPixels;

            val dm = DisplayMetrics()
            val windowMgr = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowMgr.defaultDisplay.getRealMetrics(dm)
            //        // 获取高度
            //        int height = dm.heightPixels;
            // 获取宽度
            return dm.widthPixels
        }

        /**
         * 获得屏幕高度
         */
        fun getScreenHeight(context: Context): Int {
            //        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            //        DisplayMetrics outMetrics = new DisplayMetrics();
            //        wm.getDefaultDisplay().getMetrics(outMetrics);

            val dm = DisplayMetrics()
            val windowMgr = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            windowMgr.defaultDisplay.getRealMetrics(dm)
            // 获取高度
            // 获取宽度
            //int width = dm.widthPixels;
            return dm.heightPixels
        }

        /**
         * 虚拟按键是否显示 true 是  false 否
         * @param context
         * @return
         */
        fun isNavigationBarShow(context: Context): Boolean {
            //虚拟键的view,为空或者不可见时是隐藏状态
            var activity: Activity? = null
            if (context is Activity) {
                activity = context
            }
            if (activity == null) return false

            val view =
                activity.findViewById<View>(android.R.id.navigationBarBackground) ?: return false
            val visible = view.visibility
            return if (visible == View.GONE || visible == View.INVISIBLE) {
                false
            } else {
                true
            }
        }

        /**
         * 设置 View 的宽高比例
         *
         * @param context
         * @param view
         * @param scale w/h
         * @param offsetdp 宽度偏移dp
         * @return
         */
        fun setViewScale(context: Context?, view: View, offsetdp: Float, scale: Float) {
            if (context == null) return
            if (context is Activity) {
                val activity = context as Activity?
                if (activity!!.isDestroyed || activity.isFinishing) return
            }

            if (view.layoutParams is LinearLayout.LayoutParams) {
                val params = view.layoutParams as LinearLayout.LayoutParams
                params.width = getScreenWidth(context) - dp2px(context, offsetdp)
                if (scale == 0f) {
                    params.height = 1
                } else {
                    params.height = (params.width / scale).toInt()
                }
                Logger.i("ysl", "比例以后>>" + params.width + "|" + params.height)
                view.layoutParams = params
            } else if (view.layoutParams is RelativeLayout.LayoutParams) {
                val params = view.layoutParams as RelativeLayout.LayoutParams
                params.width = getScreenWidth(context) - dp2px(context, offsetdp)
                if (scale == 0f) {
                    params.height = 1
                } else {
                    params.height = (params.width / scale).toInt()
                }
                Logger.i("ysl", "比例以后>>" + params.width + "|" + params.height)
                view.layoutParams = params
            } else if (view.layoutParams is FrameLayout.LayoutParams) {
                val params = view.layoutParams as FrameLayout.LayoutParams
                params.width = getScreenWidth(context) - dp2px(context, offsetdp)
                if (scale == 0f) {
                    params.height = 1
                } else {
                    params.height = (params.width / scale).toInt()
                }
                Logger.i("ysl", "比例以后>>" + params.width + "|" + params.height)
                view.layoutParams = params
            }
        }


        /**
         * 获取当前屏幕截图，包含状态栏
         */
        fun captureWithStatusBar(activity: Activity): Bitmap {
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val bmp = view.drawingCache
            val dm = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(dm)
            val ret = Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels)
            view.destroyDrawingCache()
            return ret
        }

        /**
         * 获取当前屏幕截图，不包含状态栏
         *
         * @param activity activity
         * @return Bitmap
         */
        fun captureWithoutStatusBar(activity: Activity): Bitmap {
            val view = activity.window.decorView
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache()
            val bmp = view.drawingCache
            val statusBarHeight = getStatusHeight(activity)
            val dm = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(dm)
            val ret = Bitmap.createBitmap(
                bmp,
                0,
                statusBarHeight,
                dm.widthPixels,
                dm.heightPixels - statusBarHeight
            )
            view.destroyDrawingCache()
            return ret
        }

        /**
         * 获得状态栏的高度
         */
        fun getStatusHeight(context: Context): Int {
            return getInternalDimensionSize(context.resources, "status_bar_height")
        }

        private fun getInternalDimensionSize(res: Resources, key: String): Int {
            var result = 0
            val resourceId = res.getIdentifier(key, "dimen", "android")
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId)
            }
            return result
        }

        @SuppressLint("NewApi")
        fun checkDeviceHasNavigationBar(activity: Context): Boolean {
            //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
            val hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey()
            val hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK)

            return if (!hasMenuKey && !hasBackKey) {
                // 做任何你需要做的,这个设备有一个导航栏
                true
            } else false
        }

        /**
         * 获取虚拟键盘高度
         * @param activity
         * @return
         */
        fun getNavigationBarHeight(activity: Context): Int {
            //!checkDeviceHasNavigationBar(activity) ||
            if (!isNavigationBarShow(activity)) {
                return 0
            }

            val resources = activity.resources
            val resourceId = resources.getIdentifier(
                "navigation_bar_height",
                "dimen", "android"
            )
            //获取NavigationBar的高度
            return resources.getDimensionPixelSize(resourceId)
        }


        /**
         * dpתpx
         */
        fun dp2px(ctx: Context?, dpValue: Float): Int {
            if (ctx == null) return 0

            val scale = ctx.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }


        /**
         * pxתdp
         */
        fun px2dip(ctx: Context?, pxValue: Float): Int {
            if (ctx == null) return 0

            val scale = ctx.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }
    }
}
