package com.asto.a91recyclingtreasure.bean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/13
 * 备注 ：
 *
 * @param settlement : 待过皮
 * @param auditNo : 未审核
 * @param audit_payNo : 审核未支付
 * @param audit_payOk : 审核已支付
 *
 */
data class OrderCountBean(
    val auditNo: Int,
    val audit_payNo: Int,
    val audit_payOk: Int,
    val settlement: Int
)