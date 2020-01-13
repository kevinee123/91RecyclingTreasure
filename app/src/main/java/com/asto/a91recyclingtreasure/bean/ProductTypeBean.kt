package com.asto.a91recyclingtreasure.bean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/9
 * 备注 ：产品类别列表
 */
data class ProductTypeBean(
    val created_at: String,
    val group_id: Int,
    val id: Int,
    val name: String,
    val product: List<Product>,
    val status: Int,
    val updated_at: String
)

data class Product(
    val company_id: Int,
    val created_at: String,
    val group_id: Int,
    val id: Int,
    val max_price: Double,
    val min_price: Double,
    val name: String,
    val name_py: Any,
    val remark: Any,
    val status: Int,
    val type_id: Int,
    val unit: String,
    val updated_at: String,
    val uri: Any,
    var isSelected: Boolean
)