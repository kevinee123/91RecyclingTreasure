package com.asto.a91recyclingtreasure.mvp.presenter

import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.MainContract
import com.asto.a91recyclingtreasure.mvp.view.MainActivity

/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：主界面
 */
class MainPresenter(var view : MainActivity) : BasePresenter(view),MainContract.Presenter{

    override fun statisticsTodayData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPricingList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}