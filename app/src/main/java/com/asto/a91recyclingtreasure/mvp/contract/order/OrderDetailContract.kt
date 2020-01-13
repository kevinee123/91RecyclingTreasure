package com.asto.a91recyclingtreasure.mvp.contract.order

import com.asto.a91recyclingtreasure.bean.OrderDetailBean
import com.asto.a91recyclingtreasure.bean.ProductTypeBean

/**
 * 作者 ：nxk
 * 日期 ：2020/1/9
 * 备注 ：订单详情界面
 */
interface OrderDetailContract {

    interface View {
        fun showDetailView(orderDetailBean: OrderDetailBean)
        fun showProductView(list : List<ProductTypeBean>)
    }

    interface Presenter {
        /**
         * 查询订单详情
         * @param order_detail_id 订单详情id
         */
        fun selectOrderDetailById(order_detail_id: Int)

        /**
         * 产品类型列表
         */
        fun selectProductAndType()

        /**
         * 删除订单详情
         */
        fun deleteOrderDetailById(order_detail_id : Int)

        /**
         * 订单明细定价提交
         *
         * @param order_detail_id 订单详情id
         * @param product_id 产品id
         * @param price 价格
         * @param point 扣点
         * @param clasp 扣杂
         * @param actual_pay 实际支出
         */
        fun orderDetailPriceSubmit(order_detail_id: Int, product_id: Int, price: Double,point: Double,clasp: Double,actual_pay: Double)

    }
}