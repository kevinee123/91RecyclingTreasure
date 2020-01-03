package com.asto.a91recyclingtreasure.base

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.android.tu.loadingdialog.LoadingDialog
import com.asto.a91recyclingtreasure.core.MyApplication
import com.umeng.analytics.MobclickAgent


abstract class BaseActivity: FragmentActivity(),IView {

    abstract val mPresenter : BasePresenter

    companion object {
        var isLogin = false //是否登录
        var isRequest = false //是否已连接USB
    }

    var mLoadingDialog: LoadingDialog? = null
    val application: MyApplication
        get() {
            return getApplication() as MyApplication
        }

    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        application.addActivity(this)

        if (mLoadingDialog == null) initLoadingDialog()
        initPermission() //权限申请

        initData()
        bindinOnClickListener()
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private fun initPermission() {
        val permissions = arrayOf(INTERNET, ACCESS_NETWORK_STATE, MODIFY_AUDIO_SETTINGS, WRITE_EXTERNAL_STORAGE, WRITE_SETTINGS, READ_PHONE_STATE, ACCESS_WIFI_STATE, CHANGE_WIFI_STATE)

        val toApplyList = ArrayList<String>()

        for (perm in permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                //进入到这里代表没有权限.
                toApplyList.add(perm)
            }
        }
        val tmpList = arrayOfNulls<String>(toApplyList.size)
        if (toApplyList.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123)
        }

    }


    protected abstract fun initData()


    protected abstract fun bindinOnClickListener()

    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

    override fun onDestroy() {
        application.removeActivity(this)
        super.onDestroy()
        mPresenter?.detachView()
    }

    private fun initLoadingDialog() {
        val loadBuilder = LoadingDialog.Builder(this).setMessage("加载中...").setCancelable(true)
            .setCancelOutside(true)
        mLoadingDialog = loadBuilder.create()
    }

    override fun showLoading() {
        if (mLoadingDialog == null) initLoadingDialog()
        mLoadingDialog!!.show()
    }

    override fun dismissLoading() {
        mLoadingDialog!!.dismiss()
    }

    override fun showToast(msg: String) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }


    override fun showToast(id: Int) {
        val toast = Toast.makeText(this, resources.getString(id), Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }



    override fun finish() {
        super.finish()
        val activityList = application.getActivityList()
        activityList.removeAt(activityList.size - 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }

}
