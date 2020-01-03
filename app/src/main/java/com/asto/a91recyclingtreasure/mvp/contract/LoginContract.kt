package com.asto.a91recyclingtreasure.mvp.contract

/**
 * 作者 ：nxk
 * 日期 ：2019/12/30
 * 备注 ：登录界面
 */
interface LoginContract {
    interface View {
        /**
         * 显示或者隐藏密码
         */
        fun showOrHidePassword(isShow : Boolean)

        /**
         * 跳转忘记密码界面
         */
        fun startForgotPassword()


    }

    interface Presenter{

        /**
         * 登录
         */
        fun login()
    }
}