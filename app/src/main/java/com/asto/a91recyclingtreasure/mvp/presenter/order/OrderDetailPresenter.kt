package com.asto.a91recyclingtreasure.mvp.presenter.order

import android.annotation.SuppressLint
import com.asto.a91recyclingtreasure.base.BasePresenter
import com.asto.a91recyclingtreasure.core.Common
import com.asto.a91recyclingtreasure.mvp.contract.order.OrderDetailContract
import com.asto.a91recyclingtreasure.mvp.view.order.OrderDetailActivity
import com.asto.a91recyclingtreasure.util.RxUtil
import com.asto.recyclingbins.core.loginOut

/**
 * 作者 ：nxk
 * 日期 ：2020/1/9
 * 备注 ：订单详情页面
 */
class OrderDetailPresenter(var view: OrderDetailActivity) : BasePresenter(view),
    OrderDetailContract.Presenter {
    /**
     * 产品信息
     */
    @SuppressLint("CheckResult")
    override fun selectProductAndType() {
        if (isViewAttached()) {
            view.showLoading()
            model.selectProductAndType().compose(RxUtil.applySchedulers()).subscribe {
                view.dismissLoading()
                if (it.isSuccess) {
                    view.showProductView(it.data)
                } else {
                    view.showToast(it.msg)
                    if (it.isLoginOut) view.loginOut()
                }
            }
        }
    }

    /**
     * 查询订单详情
     * @param order_detail_id 订单详情id
     */
    @SuppressLint("CheckResult")
    override fun selectOrderDetailById(order_detail_id: Int) {
        if (isViewAttached()) {
            view.showLoading()
            model.selectOrderDetailById(order_detail_id).compose(RxUtil.applySchedulers())
                .subscribe {
                    view.dismissLoading()
                    if (it.isSuccess) {
                        view.showDetailView(it.data)
                        selectProductAndType()
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
     * 订单明细定价提交
     *
     * @param order_detail_id 订单详情id
     * @param product_id 产品id
     * @param price 价格
     * @param point 扣点
     * @param clasp 扣杂
     * @param actual_pay 实际支出
     */
    @SuppressLint("CheckResult")
    override fun orderDetailPriceSubmit(order_detail_id: Int,product_id: Int,price: Double,point: Double,clasp: Double,actual_pay: Double) {
        if (isViewAttached()) {
            view.showLoading()
            model.orderDetailPriceSubmit(order_detail_id,product_id,price,point,clasp,actual_pay).compose(RxUtil.applySchedulers()).subscribe {
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
     * 补单
     * @param order_id 订单id
     * @param product_id 产品id
     * @param gross_weight 毛重id
     * @param price 价格
     * @param point 扣点
     * @param clasp 扣杂
     * @param actual_pay 实际金额
     */
    @SuppressLint("CheckResult")
    override fun catchOrderDetail(order_id: Int, product_id: Int, gross_weight: Double, price: Double, point: Double,clasp: Double,actual_pay: Double){
        if (isViewAttached()) {
            view.showLoading()
            model.catchOrderDetail(order_id, product_id, gross_weight, price, point,clasp,actual_pay).compose(RxUtil.applySchedulers())
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