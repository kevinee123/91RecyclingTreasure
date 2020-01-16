package com.asto.a91recyclingtreasure.mvp.presenter

import android.annotation.SuppressLint
import android.content.Intent
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.mvp.contract.LoginContract
import com.asto.a91recyclingtreasure.mvp.view.LoginActivity
import com.asto.a91recyclingtreasure.mvp.view.MainActivity
import com.asto.a91recyclingtreasure.util.ASimpleCache
import com.asto.a91recyclingtreasure.util.RxUtil
import com.asto.recyclingbins.core.finishAllActivity
import com.asto.recyclingbins.core.loginOut
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
            model.login(view.mAccountEdt.text.toString(), view.mPasswordEdt.text.toString())
                .compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                if (it.isSuccess) {
                    ASimpleCache.get(view).put(Common.BEAN_TOKEN,it.data.token)
                    ASimpleCache.get(view).put(Common.BEAN_USER,it.data.user)
                    //跳转主界面
                    view.finishAllActivity()
                    view.startActivity(Intent(view,MainActivity::class.java))
                } else {
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.loginOut()
                }
            }
        }
    }

}