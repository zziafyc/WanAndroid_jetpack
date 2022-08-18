package com.zziafyc.base_library.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import com.zziafyc.base_library.utils.StatusUtils
import android.view.WindowManager
import android.view.ViewGroup
import android.view.Window
import com.zziafyc.base_library.utils.OSUtils
import java.lang.Exception

object StatusUtils {
    /**
     * Use default color [.defaultColor_21] between 5.0 and 6.0.
     */
    const val USE_DEFAULT_COLOR = -1

    /**
     * Use color [.setUseStatusBarColor] between 5.0 and 6.0.
     */
    const val USE_CUR_COLOR = -2

    /**
     * Default status bar color between 21(5.0) and 23(6.0).
     * If status color is white, you can set the color outermost.
     */
    var defaultColor_21 = ColorUtils.parseColor("#33000000")

    /**
     * Setting the status bar color.
     * It must be more than 21(5.0) to be valid.
     *
     * @param color Status color.
     */
    fun setUseStatusBarColor(activity: Activity, @ColorInt color: Int) {
        var color = color
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            color = Color.GRAY
        }
        setUseStatusBarColor(activity, color, USE_CUR_COLOR)
    }

    /**
     * It must be more than 21(5.0) to be valid.
     * Setting the status bar color.Supper between 21 and 23.
     *
     * @param color        Status color.
     * @param surfaceColor Between 21 and 23,if surfaceColor == [.USE_DEFAULT_COLOR],the status color is defaultColor_21,
     * else if surfaceColor == [.USE_CUR_COLOR], the status color is color,
     * else the status color is surfaceColor.
     */
    fun setUseStatusBarColor(activity: Activity, @ColorInt color: Int, surfaceColor: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M || surfaceColor == USE_CUR_COLOR) color else if (surfaceColor == USE_DEFAULT_COLOR) defaultColor_21 else surfaceColor
        }
    }

    /**
     * Setting the status bar transparently.
     * See [.setUseStatusBarColor].
     */
    @Deprecated("")
    fun setTransparentStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // after 21(5.0)
            setUseStatusBarColor(activity, Color.TRANSPARENT)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // between 19(4.4) and 21(5.0)
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * Setting up whether or not to invade the status bar & status bar font color
     *
     * @param isTransparent 是否沉浸 Whether or not to invade the status bar?
     * If true, will invade the status bar,
     * otherwise, fits system windows.
     * @param isBlack       状态栏字体是否为黑色。
     * Whether the status bar font is set to black?
     * If true, the status bar font will be black,
     * otherwise, it is white.
     */
    fun setSystemStatus(activity: Activity, isTransparent: Boolean, isBlack: Boolean) {
        var flag = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && isTransparent) {
            // after 16(4.1)
            flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isBlack) {
            // after 23(6.0)
            flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        activity.window.decorView.systemUiVisibility = flag
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // between 19(4.4) and 21(5.0)
            if (isTransparent) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            } else {
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
            val contentView =
                activity.window.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
            val childAt = contentView.getChildAt(0)
            if (childAt != null) {
                childAt.fitsSystemWindows = !isTransparent
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && OSUtils.isEMUI3_x()) {
            if (isTransparent) {
                activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            } else {
                activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
            val contentView =
                activity.window.findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
            val childAt = contentView.getChildAt(0)
            if (childAt != null) {
                childAt.fitsSystemWindows = !isTransparent
            }
        }
    }

    /**
     * Get the height of the state bar by reflection.
     *
     * @return Status bar height if it is not equal to -1,
     */
    fun getStatusBarHeight(context: Context): Int {
        return getSizeByReflection(context, "status_bar_height")
    }

    /**
     * Get the height of the state bar by reflection.
     *
     * @return Status bar height if it is not equal to -1,
     */
    fun getNavigationBarHeight(context: Context): Int {
        return getSizeByReflection(context, "navigation_bar_height")
    }

    fun getSizeByReflection(context: Context, field: String?): Int {
        var size = -1
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val height = clazz.getField(field!!)[`object`].toString().toInt()
            size = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return size
    }

    /**
     * Set bottom navigation bar color
     */
    fun setNavigationBar(activity: Activity, @ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.navigationBarColor = color
        }
    }
}