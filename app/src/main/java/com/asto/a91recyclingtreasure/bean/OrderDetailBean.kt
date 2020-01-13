package com.asto.a91recyclingtreasure.bean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/9
 * 备注 ：订单详情
 */

data class OrderDetailBean(
    val actual_pay: Double,
    val clasp: Double,
    val collect_number: String,
    val created_at: String,
    val del_flag: Int,
    val equipment_id: Int,
    val gross_weight: Double,
    val id: Int,
    val is_supplement: Int,
    val order_id: Int,
    val out_time: Any,
    val pname: String,
    val point: Double,
    val price: Double,
    val price_time: String,
    val price_user_id: Int,
    val product_id: Int,
    val remark: Any,
    val sname: String,
    val status: Int,
    val supplier_id: Int,
    val tare_weight: Double,
    val total: Any,
    val updated_at: String
)