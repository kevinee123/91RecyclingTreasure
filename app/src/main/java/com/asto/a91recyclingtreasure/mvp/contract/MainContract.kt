package com.asto.a91recyclingtreasure.mvp.contract

/**
 * 作者 ：nxk
 * 日期 ：2019/12/31
 * 备注 ：主页
 */
interface MainContract {

    interface View{
    }
    interface Presenter {
        /**
         * 获取今日订单/支出统计
         */
        fun statisticsTodayData()

    }
}