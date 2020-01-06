package com.asto.a91recyclingtreasure.mvp.presenter.order

import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.order.PricingContract
import com.asto.a91recyclingtreasure.mvp.view.order.PricingFragment

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：待定价
 */
class PricingPresenter(var view: PricingFragment) : BasePresenter(view),PricingContract.Presenter