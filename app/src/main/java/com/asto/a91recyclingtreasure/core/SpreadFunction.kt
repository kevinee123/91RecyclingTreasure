package com.asto.recyclingbins.core

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.core.content.ContextCompat.startActivity
import android.provider.Settings.ACTION_SETTINGS
import android.content.Intent
import android.R.attr.fragment
import android.provider.Settings
import android.view.WindowManager
import com.asto.a91recyclingtreasure.core.MyApplication


/**
 * 防止重复点击
 */
fun View.setOnClickDelayListener(clickAction: () -> Unit) {
    var hash: Int = 0
    var lastClickTime: Long = 0
    var SPACE_TIME: Long = 1000
    this.setOnClickListener {
        if (this.hashCode() != hash) {
            hash = this.hashCode()
            lastClickTime = System.currentTimeMillis()
            clickAction()
        } else {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > SPACE_TIME) {
                lastClickTime = System.currentTimeMillis()
                clickAction()
            }
        }
    }
}

/**
 * 关闭Activity直到剩下几层
 *
 * @param remain 剩余Activity数
 */
fun Activity.finishAndRemainHowActivity(remain: Int) {
    val activityList = (application as MyApplication).getActivityList()
    var size = activityList.size
    while (size > remain) {
        activityList.get(size - 1).finish()
        size--
    }
}

/**
 * 关闭所有Activity
 */
fun Activity.finishAllActivity() {
    var activityList = (application as MyApplication).getActivityList()
    for (activity in activityList) activity.finish()
    activityList.clear()
}

/**
 * 创建布局
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}


fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

fun dp2px(context: Context, dpValue: Int): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

//px转dp
fun px2dp(context: Context, pxValue: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        pxValue.toFloat(),
        context.resources.displayMetrics
    )
        .toInt()
}


//显示系统虚拟按键
fun Activity.toSystemShowSetting(){
    val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)
    startActivity(intent)
}

//沉浸式页面
fun Activity.immersion(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //透明状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //透明导航栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
}