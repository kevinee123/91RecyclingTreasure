package com.asto.a91recyclingtreasure.mvp.presenter.order

import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderContract
import com.asto.a91recyclingtreasure.mvp.view.order.OrderFragment

/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：订单主界面
 */
class OrderPresenter(var view : OrderFragment) :BasePresenter(view),
    OrderContract.Presenter {

}