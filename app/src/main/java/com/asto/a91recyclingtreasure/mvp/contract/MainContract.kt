package com.asto.a91recyclingtreasure.mvp.contract

/**
 * 作者 ：nxk
 * 日期 ：2019/12/31
 * 备注 ：主页
 */
interface MainContract {

    interface View{
        /**
         * 显示订单界面
         */
        fun showOrder()
    }
    interface Presenter {
        /**
         * 获取今日订单/支出统计
         */
        fun statisticsTodayData()

        /**
         * 待定价列表
         */
        fun getPricingList()

    }
}