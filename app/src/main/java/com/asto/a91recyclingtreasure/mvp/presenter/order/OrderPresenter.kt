package com.asto.a91recyclingtreasure.mvp.presenter.order

import android.annotation.SuppressLint
import com.asto.a91recyclingtreasure.adapter.OrderAdapter
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.bean.OrderCountBean
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderContract
import com.asto.a91recyclingtreasure.mvp.view.order.OrderFragment
import com.asto.a91recyclingtreasure.util.RxUtil
import com.asto.recyclingbins.core.loginOut

/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：订单主界面
 */
class OrderPresenter(var view: OrderFragment) : BasePresenter(view),
    OrderContract.Presenter {

    /**
     * 订单统计条数
     */
    @SuppressLint("CheckResult")
    override fun selectOrderListCount() {
        if (isViewAttached()) {
            view.showLoading()
            model.selectOrderListCount().compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                if (it.isSuccess) {
                    //刷新tab
                    view.refreshTitleCount(it.data)
                } else {
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.mActivity.loginOut()
                }
            }
        }
    }


}