package com.asto.a91recyclingtreasure.bean

import java.io.Serializable

/**
 * 作者 ：nxk
 * 日期 ：2020/1/6
 * 备注 ：登录成功返回数据
 */
data class LoginBean(
    val token: String,
    val user: User
): Serializable

data class User (
    val company_id: Int,
    val group_id: Int,
    val id: Int,
    val login_token: String,
    val mobile: Any,
    val username: String
): Serializable