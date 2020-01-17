package com.asto.a91recyclingtreasure.mvp.presenter.order

import android.annotation.SuppressLint
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderDetailsListContract
import com.asto.a91recyclingtreasure.mvp.view.order.OrderDetailsListActivity
import com.asto.a91recyclingtreasure.util.RxUtil
import com.asto.recyclingbins.core.loginOut
import kotlinx.android.synthetic.main.activity_order_details_list.*

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：订单详情列表
 */
class OrderDetailsListPresenter(var view: OrderDetailsListActivity) : BasePresenter(view),
    OrderDetailsListContract.Presenter {

    /**
     * 订单明细列表
     */
    @SuppressLint("CheckResult")
    override fun selectOrderDetailById(orderId: Int) {
        if (isViewAttached()) {
            view.showLoading()
            model.selectOrderById(orderId).compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                view.mSwipeRefreshLayout.isRefreshing = false
                if (it.isSuccess) {
                    view.showList(it.data)
                } else {
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.loginOut()
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
                    view.setResult(Common.RESULT_REFRESH)
                    view.finish()
                } else {
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.loginOut()
                }
            }
        }
    }


    /**
     * 结算
     *
     * @param order_id 订单id
     * @param status   1 不选择2 审核未付３审核已付
     */
    @SuppressLint("CheckResult")
    override fun orderSettlement(order_id: Int, status: Int) {
        if (isViewAttached()) {
            view.showLoading()
            model.orderSettlement(order_id, status).compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                if (it.isSuccess) {
                    view.setResult(Common.RESULT_REFRESH)
                    view.finish()
                } else {
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.loginOut()
                }
            }
        }
    }


    /**
     * 删除订单详情
     */
    @SuppressLint("CheckResult")
    override fun deleteOrderDetailById(order_detail_id: Int) {
        if (isViewAttached()) {
            view.showLoading()
            model.deleteOrderDetailById(order_detail_id).compose(RxUtil.applySchedulers())
                .subscribe {
                    view.dismissLoading()
                    if (it.isSuccess) {
                        selectOrderDetailById(view.orderId)
                    } else {
                        view.showToast(it.msg)
                        if (it.isLoginOut) view.loginOut()

                    }
                }
        }
    }

    /**
     * 审核未付
     * @param order_id 订单id
     */
    @SuppressLint("CheckResult")
    override fun orderAuditNotPay(order_id: Int){
        if (isViewAttached()) {
            view.showLoading()
            model.orderAuditNotPay(order_id).compose(RxUtil.applySchedulers())
                .subscribe {
                    view.dismissLoading()
                    if (it.isSuccess) {
                        view.setResult(Common.RESULT_REFRESH)
                        view.finish()
                    } else {
                        view.showToast(it.msg)
                        if (it.isLoginOut) view.loginOut()
                    }
                }
        }
    }

    /**
     * 审核支付
     * @param order_id 订单id
     */
    @SuppressLint("CheckResult")
    override fun orderAuditPay(order_id: Int){
        if (isViewAttached()) {
            view.showLoading()
            model.orderAuditPay(order_id).compose(RxUtil.applySchedulers())
                .subscribe {
                    view.dismissLoading()
                    if (it.isSuccess) {
                        view.setResult(Common.RESULT_REFRESH)
                        view.finish()
                    } else {
                        view.showToast(it.msg)
                        if (it.isLoginOut) view.loginOut()

                    }
                }
        }
    }

}