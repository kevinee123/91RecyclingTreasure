package com.asto.a91recyclingtreasure.mvp.presenter

import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.LoginContract
import com.asto.a91recyclingtreasure.mvp.view.LoginActivity

/**
 * 作者 ：nxk
 * 日期 ：2019/12/30
 * 备注 ：登录界面
 */
class LoginPresenter(var view : LoginActivity) : BasePresenter(view),LoginContract.Presenter {

    override fun login() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}