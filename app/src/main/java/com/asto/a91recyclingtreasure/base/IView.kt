package com.asto.a91recyclingtreasure.base

interface IView {


    /**
     * 显示加载中
     */
    fun showLoading()

    /**
     * 隐藏加载
     */
    fun dismissLoading()

    /**
     * 显示Toast
     */
    fun showToast(msg: String)

    /**
     * 显示Toast
     */
    fun showToast(id: Int)

}