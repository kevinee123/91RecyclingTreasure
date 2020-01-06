package com.asto.a91recyclingtreasure.bean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/6
 * 备注 ：登录成功返回数据
 */
data class LoginBean(
    val company_id: Int,
    val group_id: Int,
    val login_token: String,
    val mobile: Any,
    val username: String
)