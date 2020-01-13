package com.asto.a91recyclingtreasure.mvp.contract.order

import com.asto.a91recyclingtreasure.bean.OrderCountBean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/2
 * 备注 ：订单主界面
 */
interface OrderContract {
    interface View {

        /**
         * 刷新列表数量统计
         */
        fun refreshTitleCount(orderCountBean : OrderCountBean)

    }

    interface Presenter {
        /**
         * 订单统计条数
         */
        fun selectOrderListCount()
    }
}