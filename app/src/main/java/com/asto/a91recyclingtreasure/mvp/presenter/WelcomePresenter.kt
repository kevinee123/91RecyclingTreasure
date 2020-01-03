package com.asto.a91recyclingtreasure.mvp.presenter

import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.WelcomeContract
import com.asto.a91recyclingtreasure.mvp.view.WelcomeActivity

/**
 * 作者 ：nxk
 * 日期 ：2019/12/30
 * 备注 ：欢迎界面
 */
class WelcomePresenter(var view : WelcomeActivity) : BasePresenter(view), WelcomeContract.Presenter {

    /**
     * TODO 自动更新
     */
    override fun update() {
        if (isViewAttached()){

        }
    }

}