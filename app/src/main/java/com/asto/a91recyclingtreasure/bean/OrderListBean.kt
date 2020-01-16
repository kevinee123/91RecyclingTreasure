package com.asto.a91recyclingtreasure.bean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/4
 * 备注 ：订单列表
 */
data class OrderListBean(
    val count: Int,
    val list: List<OrderListChildBean>
)

data class OrderListChildBean(
    val count: Int,
    val created_at: String,
    val gross_weight: Double,
    val id: Int,
    val name: String,
    val net_weight : Double,
    val actual_pay : Double
)