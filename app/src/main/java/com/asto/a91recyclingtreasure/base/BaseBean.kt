package com.asto.a91recyclingtreasure.base

import com.asto.a91recyclingtreasure.core.Common


data class BaseBean<T>(val code : Int, val message: String,val data: T) {

    //成功
    val isSuccess: Boolean
        get() = code == Common.HTTP_SUCCESS

    //登录失效
    val isLoginOut: Boolean
        get() = code == Common.HTTP_LOGIN_OUT


}