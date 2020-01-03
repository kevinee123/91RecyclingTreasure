package com.asto.a91recyclingtreasure.mvp.contract

/**
 * 作者 ：nxk
 * 日期 ：2019/12/30
 * 备注 ：欢迎界面
 */
interface WelcomeContract {

    interface View {
        /**
         * 跳转下一步
         */
        fun next()
    }
    interface Presenter {

        /**
         * 自动更新
         */
        fun update()
    }
}