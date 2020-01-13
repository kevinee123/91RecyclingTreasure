package com.asto.a91recyclingtreasure.mvp.contract.order

import com.asto.a91recyclingtreasure.bean.OrderListBean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/7
 * 备注 ：订单主界面列表
 */
interface OrderListContract {
    interface View {

        fun updateList(bean: OrderListBean)
    }

    interface Presenter {
        /**
         * 订单列表
         * @param status 状态 (1.待定价2.待审核3.审核未付4.审核已付)
         */
        fun selectOrder(status: Int, page: Int, pageSize: Int = 20)

        /**
         * 删除订单
         * @param order_id 订单id
         */
        fun deleteOrderById(order_id: Int)
    }
}