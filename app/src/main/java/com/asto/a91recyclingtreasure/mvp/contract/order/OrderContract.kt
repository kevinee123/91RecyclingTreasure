package com.asto.a91recyclingtreasure.mvp.contract.order

import com.asto.a91recyclingtreasure.bean.OrderCountBean
import com.asto.a91recyclingtreasure.bean.OrderStatisticsBean

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

        /**
         * 刷新今日订单统计
         */
        fun refreshOrderStatistics(orderStatisticsBean: OrderStatisticsBean)
    }

    interface Presenter {

        /**
         * 订单统计条数
         */
        fun selectOrderListCount()

        /**
         * 统计今日订单
         */
        fun orderStatistics()
    }
}