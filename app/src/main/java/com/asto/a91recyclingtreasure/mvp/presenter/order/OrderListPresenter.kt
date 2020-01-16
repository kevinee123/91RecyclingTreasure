package com.asto.a91recyclingtreasure.mvp.presenter.order

import android.annotation.SuppressLint
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderListContract
import com.asto.a91recyclingtreasure.mvp.view.order.OrderListFragment
import com.asto.a91recyclingtreasure.util.RxUtil
import com.asto.recyclingbins.core.loginOut

/**
 * 作者 ：nxk
 * 日期 ：2020/1/7
 * 备注 ：订单主页列表
 */
class OrderListPresenter(var view: OrderListFragment) : BasePresenter(view),
    OrderListContract.Presenter {
    @SuppressLint("CheckResult")
    override fun selectOrder(status: Int, page: Int, pageSize: Int) {
        if (isViewAttached()){
            view.showLoading()
            model.selectOrder(status,page,pageSize).compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                if (it.isSuccess){
                    view.updateList(it.data)
                }else{
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.mActivity.loginOut()
                }
            }
        }
    }

    /**
     * 删除订单
     * @param order_id 订单id
     */
    @SuppressLint("CheckResult")
    override fun deleteOrderById(order_id: Int) {
        if (isViewAttached()) {
            view.showLoading()
            model.deleteOrderById(order_id).compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                if (it.isSuccess) {
                    //刷新列表
                    view.page = 1
                    selectOrder(view.status, view.page)
                    view.orderFragment.mPresenter.selectOrderListCount()
                } else {
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.mActivity.loginOut()
                }
            }
        }
    }

}