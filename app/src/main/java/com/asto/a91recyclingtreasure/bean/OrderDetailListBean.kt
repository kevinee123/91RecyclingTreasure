package com.asto.a91recyclingtreasure.bean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/8
 * 备注 ：订单详情列表
 */
data class OrderDetailListBean(
    val order: Order
)

data class Order(
    val actual_pay: String,
    val audit_id: Any,
    val audit_time: Any,
    val collect_number: String,
    val company_id: Int,
    val created_at: String,
    val detail: List<OrderDetail>,
    val equipment_id: Int,
    val group_id: Int,
    val id: Int,
    val is_del: Int,
    val is_pay: Int,
    val is_settlement: Int,
    val is_supplement: Int,
    val name: String,
    val pay_time: Any,
    val pay_user_id: Any,
    val remark: Any,
    val status: Int,
    val supplement_time: Any,
    val supplier_id: Int,
    val total: Double,
    val updated_at: String,
    val void_id: Any,
    val void_time: Any
)

data class OrderDetail(
    val clasp: Double,
    val collect_number: String,
    val created_at: String,
    val del_flag: Int,
    val equipment_id: Int,
    val gross_weight: Double,
    val id: Int,
    val is_supplement: Int,
    val name: String,
    val order_id: Int,
    val out_time: Any,
    val point: Double,
    val price: Double,
    val price_time: Any,
    val price_user_id: Any,
    val product_id: Any,
    val remark: Any,
    val status: Int,
    val supplier_id: Int,
    val net_weight: Double,
    val tare_weight: Double,
    val updated_at: String,
    val actual_pay : Double
)