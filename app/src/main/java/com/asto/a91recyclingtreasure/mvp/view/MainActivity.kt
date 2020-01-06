package com.asto.a91recyclingtreasure.mvp.view

import com.asto.a91recyclingtreasure.R
import com.asto.a91recyclingtreasure.base.BaseActivity
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.MainContract
import com.asto.a91recyclingtreasure.mvp.presenter.MainPresenter
import com.asto.a91recyclingtreasure.mvp.view.order.OrderFragment
import com.asto.recyclingbins.core.addFragment
import com.asto.recyclingbins.core.replaceFragment

/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：主界面
 */
class MainActivity : BaseActivity(),MainContract.View {
    var orderFragment : OrderFragment? = null
    override fun showOrder() {
        if(orderFragment == null) {
            orderFragment = OrderFragment(this)
            addFragment(orderFragment!!, R.id.mContentLl)
        }else{
            replaceFragment(orderFragment!!, R.id.mContentLl)
        }
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