package com.asto.a91recyclingtreasure.mvp.presenter.order

import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.mvp.contract.order.PricingDetailsListContract
import com.asto.a91recyclingtreasure.mvp.view.order.PricingDetailsListActivity

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：订单详情列表
 */
class PricingDetailsListPresenter(var view: PricingDetailsListActivity) : BasePresenter(view),
    PricingDetailsListContract.Presenter {

}