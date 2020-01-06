package com.asto.a91recyclingtreasure.mvp.presenter

import android.annotation.SuppressLint
import android.content.Intent
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.LoginContract
import com.asto.a91recyclingtreasure.mvp.view.LoginActivity
import com.asto.a91recyclingtreasure.mvp.view.MainActivity
import com.asto.a91recyclingtreasure.util.RxUtil
import com.asto.recyclingbins.core.finishAllActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 作者 ：nxk
 * 日期 ：2019/12/30
 * 备注 ：登录界面
 */
class LoginPresenter(var view: LoginActivity) : BasePresenter(view), LoginContract.Presenter {

    @SuppressLint("CheckResult")
    override fun login() {
        if (isViewAttached()) {
            view.showLoading()
            //TODO 测试代码 后期修改
//            model.login(view.mAccountEdt.text.toString(), view.mPasswordEdt.text.toString())
            model.login("admin", "123456")
                .compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                if (it.isSuccess) {
                    //跳转主界面
                    view.finishAllActivity()
                    view.startActivity(Intent(view,MainActivity::class.java))
                } else {
                    view.showToast(it.msg)
                }
            }
        }
    }

}