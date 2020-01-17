package com.asto.a91recyclingtreasure.model

import com.asto.a91recyclingtreasure.base.BaseBean
import com.asto.a91recyclingtreasure.bean.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import io.reactivex.Observable
import retrofit2.http.Header


interface ApiModel {

    /**
     * 手机号登陆
     *
     * @param mobile 手机号码
     * @param sms_code 验证码
     */
    @POST("/api/Login.action")
    @FormUrlEncoded
    fun login(
        @Field("login_name") login_name: String
        , @Field("password") password: String
    ): Observable<BaseBean<LoginBean>>

    /**
     * 订单列表
     *
     * @param status 状态 (1.待定价2.待审核3.审核未付4.审核已付)
     * @param page_count 页码
     * @param page_size  条数
     */
    @POST("/api/SelectOrderList.action")
    @FormUrlEncoded
    fun selectOrder(
        @Field("status") status: Int
        , @Field("page_count") page_count: Int
        , @Field("page_size") page_size: Int
    ): Observable<BaseBean<OrderListBean>>

    /**
     * 订单查询
     *
     * @param order_id 订单id
     */
    @POST("/api/SelectOrderById.action")
    @FormUrlEncoded
    fun selectOrderById(
        @Field("order_id") status: Int
    ): Observable<BaseBean<OrderDetailListBean>>

    /**
     * 查询订单详情
     *
     * @param order_detail_id 订单详情id
     */
    @POST("/api/SelectOrderDetailById.action")
    @FormUrlEncoded
    fun selectOrderDetailById(
        @Field("order_detail_id") order_detail_id: Int
    ): Observable<BaseBean<OrderDetailBean>>

    /**
     * 产品信息
     *
     */
    @POST("/api/SelectProductAndType.action")
    fun selectProductAndType(): Observable<BaseBean<List<ProductTypeBean>>>

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
    @POST("/api/OrderDetailPriceSubmit.action")
    @FormUrlEncoded
    fun orderDetailPriceSubmit(
        @Field("order_detail_id") order_detail_id: Int
        ,@Field("product_id") product_id: Int
        ,@Field("price") price: Double
        ,@Field("point") point: Double
        ,@Field("clasp") clasp: Double
        ,@Field("actual_pay") actual_pay: Double
    ): Observable<BaseBean<String>>

    /**
     * 删除订单详情
     * @param order_detail_id 订单详情id
     */
    @POST("/api/DeleteOrderDetailById.action")
    @FormUrlEncoded
    fun deleteOrderDetailById(
        @Field("order_detail_id") order_detail_id: Int
    ): Observable<BaseBean<String>>

    /**
     * 删除订单
     * @param order_id 订单id
     */
    @POST("/api/DeleteOrderById.action")
    @FormUrlEncoded
    fun deleteOrderById(
        @Field("order_id") order_id: Int
    ): Observable<BaseBean<String>>

    /**
     * 结算
     *
     * @param order_id 订单id
     * @param status   1 不选择2 审核未付３审核已付
     */
    @POST("/api/OrderSettlement.action")
    @FormUrlEncoded
    fun orderSettlement(
        @Field("order_id") order_id: Int
        ,@Field("status") status: Int
    ): Observable<BaseBean<String>>

    /**
     * 订单统计条数
     */
    @POST("/api/SelectOrderListCount.action")
    fun selectOrderListCount(): Observable<BaseBean<OrderCountBean>>

    /**
     * 审核未付
     * @param order_id 订单id
     */
    @POST("/api/OrderAuditNotPay.action")
    @FormUrlEncoded
    fun orderAuditNotPay(
        @Field("order_id") order_id: Int
    ): Observable<BaseBean<String>>

    /**
     * 审核支付
     * @param order_id 订单id
     */
    @POST("/api/OrderAuditPay.action")
    @FormUrlEncoded
    fun orderAuditPay(
        @Field("order_id") order_id: Int
    ): Observable<BaseBean<String>>

    /**
     * 补单
     * @param order_id 订单id
     * @param product_id 产品id
     * @param gross_weight 毛重id
     * @param price 价格
     * @param point 扣点
     * @param clasp 扣杂
     * @param actual_pay 实际金额
     */
    @POST("/api/CatchOrderDetail.action")
    @FormUrlEncoded
    fun catchOrderDetail(
        @Field("order_id") order_id: Int
        ,@Field("product_id") product_id: Int
        ,@Field("gross_weight") gross_weight: Double
        ,@Field("price") price: Double
        ,@Field("point") point: Double
        ,@Field("clasp") clasp: Double
        ,@Field("actual_pay") actual_pay: Double
    ): Observable<BaseBean<String>>

    /**
     * 订单统计
     */
    @POST("/api/OrderStatistics.action")
    fun orderStatistics(
    ): Observable<BaseBean<OrderStatisticsBean>>

    /**
     * 登出
     */
    @POST("/api/LoginOut.action")
    fun loginOut(
    ): Observable<BaseBean<String>>

}