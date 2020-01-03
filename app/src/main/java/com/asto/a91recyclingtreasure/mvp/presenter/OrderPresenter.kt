package com.asto.a91recyclingtreasure.mvp.presenter

import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.OrderContract
import com.asto.a91recyclingtreasure.mvp.view.OrderFragment

/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：订单主界面
 */
class OrderPresenter(var view : OrderFragment) :BasePresenter(view),OrderContract.Presenter {

}