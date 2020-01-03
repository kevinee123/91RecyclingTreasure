package com.asto.a91recyclingtreasure.core

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.umeng.commonsdk.UMConfigure

@SuppressLint("Registered")
class MyApplication : Application() {

    var activityList: ArrayList<BaseActivity> = ArrayList()

    override fun onCreate() {
        super.onCreate()
        //初始化Logger
        Logger.addLogAdapter(AndroidLogAdapter())
        //初始化友盟 TODO Build.DEVICE 后期可改为IMEI或者TOKEN
        UMConfigure.init(this, "5e096c0e0cafb2e9ff00038d", Build.DEVICE, UMConfigure.DEVICE_TYPE_PHONE, null)
    }


    fun addActivity(activity: BaseActivity) {
        activityList.add(activity)
    }

    fun removeActivity(activity: BaseActivity) {
        activityList.remove(activity)
    }


    fun getActivityList(): MutableList<BaseActivity> {
        return activityList
    }

}
