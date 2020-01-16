package com.asto.a91recyclingtreasure.mvp.contract.order

import com.asto.a91recyclingtreasure.bean.OrderDetailListBean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：订单详情列表
 */
interface OrderDetailsListContract {

    interface View {

        fun showList(orderDetailListBean: OrderDetailListBean)

    }

    interface Presenter {

        /**
         * 订单明细
         */
        fun selectOrderDetailById(orderId : Int)

        /**
         * 删除订单
         * @param order_id 订单id
         */
        fun deleteOrderById(order_id: Int)

        /**
         * 删除订单详情
         */
        fun deleteOrderDetailById(order_detail_id : Int)

        /**
         * 结算
         *
         * @param order_id 订单id
         * @param status   1 不选择2 审核未付３审核已付
         */
        fun orderSettlement(order_id: Int,status : Int)


        /**
         * 审核未付
         * @param order_id 订单id
         */
        fun orderAuditNotPay(order_id: Int)

        /**
         * 审核支付
         * @param order_id 订单id
         */
        fun orderAuditPay(order_id: Int)

    }
}