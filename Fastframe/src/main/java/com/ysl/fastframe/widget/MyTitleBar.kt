package com.ysl.fastframe.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.ysl.fastframe.R

/**
 * 通用头部
 */
class MyTitleBar : RelativeLayout {
    private var rl_back_layer: LinearLayout? = null
    private var right_layer: LinearLayout? = null
    var iv_left_icon: ImageView? = null
    var iv_right_icon: ImageView? = null
    private var tv_left: TextView? = null
    var tv_right: TextView? = null
    private lateinit var tv_middle : TextView
    private var divider_line: View? = null
    private var status_bar: MyStatusBar? = null
    private var isWhite = false
    private var isMaxStyle = false
    private var root: LinearLayout? = null
    private var mParentView: RelativeLayout? = null
    private var mLeftClickListener: OnClickListener? = null
    var rightClickListener: OnClickListener? = null
        private set
    private var mRightTextClickListener: OnClickListener? = null

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(
        context: Context,
        attrs: AttributeSet?
    ) {
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val array = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar)
        isWhite = array.getBoolean(R.styleable.MyTitleBar_isWhiteStyle, true)
        isMaxStyle = array.getBoolean(R.styleable.MyTitleBar_isMaxStyle, false)
        if (isWhite) {
            if (isMaxStyle) {
                inflater.inflate(R.layout.title_bar_white_max, this, true)
            } else {
                inflater.inflate(R.layout.my_title_bar_white, this, true)
            }
        } else {
            inflater.inflate(R.layout.my_title_bar, this, true)
        }
        tv_middle = findViewById(R.id.tv_middle)
        root = findViewById(R.id.root)
        mParentView = findViewById(R.id.head)
        rl_back_layer = findViewById(R.id.rl_back_layer)
        //关闭
        rl_back_layer?.setOnClickListener(OnClickListener { v: View? -> (context as Activity).finish() })
        status_bar = findViewById(R.id.status_bar)
        iv_left_icon = findViewById(R.id.iv_left_icon)
        iv_right_icon = findViewById(R.id.right_iv_icon)
        tv_left = findViewById(R.id.tv_left)
        tv_right = findViewById<View>(R.id.tv_right) as TextView
        right_layer = findViewById<View>(R.id.right_layer) as LinearLayout
        divider_line = findViewById(R.id.divider_line)

        //默认不显示分割线,status_bar
        status_bar?.setVisibility(View.VISIBLE)
        if (isWhite) { //如果是白色默认显示分割线
            //divider_line.setVisibility(View.VISIBLE);
        } else {
            //divider_line.setVisibility(View.VISIBLE);
        }
        if (attrs == null) {
            return
        }
        val count = array.indexCount
        for (i in 0 until count) {
            val attr = array.getIndex(i)
            if (attr == R.styleable.MyTitleBar_leftText) {
                val resId = array.getString(attr)
                tv_left?.setText(resId)
            } else if (attr == R.styleable.MyTitleBar_middleText) {
                val resId = array.getString(attr)
                tv_middle?.text = resId
            } else if (attr == R.styleable.MyTitleBar_rightText) {
                val resId = array.getString(attr)
                tv_right!!.text = resId
            } else if (attr == R.styleable.MyTitleBar_leftIcon) {
                val drawable = array.getDrawable(attr)
                iv_left_icon?.setImageDrawable(drawable)
            } else if (attr == R.styleable.MyTitleBar_rightIcon) {
                val drawable = array.getDrawable(attr)
                iv_right_icon?.setImageDrawable(drawable)
            } else if (attr == R.styleable.MyTitleBar_middleTextColor) {
                val color = array.getColor(attr, 0)
                tv_middle?.setTextColor(color)
            } else if (attr == R.styleable.MyTitleBar_rightTextColor) {
                val color = array.getColor(attr, 0)
                tv_right?.setTextColor(color)
            } else if (attr == R.styleable.MyTitleBar_background) {
                val color = array.getColor(attr, 0)
                mParentView?.setBackgroundColor(color)
                root?.setBackgroundColor(color)
            } else if (attr == R.styleable.MyTitleBar_backgroundDrawable) { //背景图片
                val drawable = array.getDrawable(attr)
                root?.setBackground(drawable)
            } else if (attr == R.styleable.MyTitleBar_statusBarBackgroundColor) {
                val color = array.getColor(attr, 0)
                status_bar?.setBackgroundColor(color)
            } else if (attr == R.styleable.MyTitleBar_rightTextSize) {
                val size = array.getDimension(attr, 0f)
                tv_right!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
            } else if (attr == R.styleable.MyTitleBar_rightBackground) {
                val drawable = array.getDrawable(attr)
                tv_right!!.background = drawable
            } else if (attr == R.styleable.MyTitleBar_leftVisible) {
                val show = array.getBoolean(attr, true)
                val visible =
                    if (show) View.VISIBLE else View.INVISIBLE
                rl_back_layer?.setVisibility(visible)
            } else if (attr == R.styleable.MyTitleBar_rightVisible) {
                val show = array.getBoolean(attr, true)
                val visible =
                    if (show) View.VISIBLE else View.INVISIBLE
                right_layer!!.visibility = visible
            } else if (attr == R.styleable.MyTitleBar_showStatusBar) {
                val show = array.getBoolean(attr, true)
                val visible = if (show) View.VISIBLE else View.GONE
                status_bar?.setVisibility(visible)
            } else if (attr == R.styleable.MyTitleBar_showDividerLine) {
                val show = array.getBoolean(attr, false)
                val visible = if (show) View.VISIBLE else View.GONE
                divider_line?.setVisibility(visible)
            }
        }
        array.recycle()
    }

    fun setLeftText(title: String?) {
        tv_left!!.text = title
    }

    /**
     * 设置 TitleBar 背景
     *
     * @param color
     */
    fun setMytitleBarBackground(@ColorInt color: Int) {
        root!!.setBackgroundColor(color)
    }

    val myTitlRoot: View?
        get() = root

    fun setDividerLineVisable(visable: Int) {
        divider_line!!.visibility = visable
    }

    fun setMiddleText(title: String?) {
        tv_middle.text = title
    }

    fun setMiddleText(resId: Int) {
        tv_middle.setText(resId)
    }

    fun setMiddlerTextVisable(visibility: Int) {
        tv_middle.visibility = visibility
    }

    fun setRightText(title: String?) {
        tv_right!!.text = title
    }

    fun setRightTextColor(color: Int) {
        tv_right!!.setTextColor(color)
    }

    fun setLeftVisible(visible: Int) {
        rl_back_layer!!.visibility = visible
    }

    fun setRightVisible(visible: Int) {
        right_layer!!.visibility = visible
    }

    fun setOnLeftClickListener(listener: OnClickListener?) {
        mLeftClickListener = listener
        rl_back_layer!!.setOnClickListener(listener)
    }

    fun setOnRightClickListener(listener: OnClickListener?) {
        rightClickListener = listener
        right_layer!!.setOnClickListener(listener)
    }

    fun setOnRightTextClickListener(listener: OnClickListener?) {
        mRightTextClickListener = listener
        tv_right!!.setOnClickListener(listener)
    }

    fun setRightIConVisable(visable: Int) {
        iv_right_icon!!.visibility = visable
    }

    val rightLayer: View?
        get() = right_layer
}