package com.asto.a91recyclingtreasure.mvp.view

import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.MainContract
import com.asto.a91recyclingtreasure.mvp.presenter.MainPresenter
import com.asto.recyclingbins.core.addFragment

/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：主界面
 */
class MainActivity : BaseActivity(),MainContract.View {
    override fun showOrder() {
        addFragment()
    }

    override val mPresenter: BasePresenter
        get() = MainPresenter(this)
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initData() {

    }

    override fun bindinOnClickListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}