package com.asto.a91recyclingtreasure.core

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.widget.Toast
import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.umeng.commonsdk.UMConfigure
import io.reactivex.plugins.RxJavaPlugins
import org.jetbrains.anko.runOnUiThread
import java.io.FileNotFoundException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@SuppressLint("Registered")
class MyApplication : Application() {

    var activityList: ArrayList<BaseActivity> = ArrayList()

    companion object {
        var application: MyApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        //初始化Logger
        Logger.addLogAdapter(AndroidLogAdapter())
        //对网络异常统一处理
        initHttpError()
        //初始化友盟 TODO Build.DEVICE 后期可改为IMEI或者TOKEN
        UMConfigure.init(
            this,
            "5e096c0e0cafb2e9ff00038d",
            Build.DEVICE,
            UMConfigure.DEVICE_TYPE_PHONE,
            null
        )
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

    private fun initHttpError() {
        RxJavaPlugins.setErrorHandler {
            //异常处理
            Logger.e(it.cause.toString())

            runOnUiThread {
                activityList[activityList.size - 1].dismissLoading()
                if (it.cause is SocketTimeoutException) {
                    activityList[activityList.size - 1].showToast(getString(R.string.http_error_time_out))
                } else if (it.cause is ConnectException) {
                    activityList[activityList.size - 1].showToast(getString(R.string.http_error_cannot_connect))
                } else if (it.cause is FileNotFoundException) {
                    activityList[activityList.size - 1].showToast(getString(R.string.http_error_file_not_found))
                } else if (it.cause is UnknownHostException) {
                    activityList[activityList.size - 1].showToast(getString(R.string.http_error_unknown_host))
                } else {
                    activityList[activityList.size - 1].showToast(getString(R.string.http_error))
                }
            }
        }
    }

}
