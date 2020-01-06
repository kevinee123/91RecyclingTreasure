package com.asto.a91recyclingtreasure.model

import com.asto.a91recyclingtreasure.base.BaseBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import io.reactivex.Observable


interface ApiModel {

    /**
     * 手机号登陆
     * @param mobile 手机号码
     * @param sms_code 验证码
     */
    @POST("/index.php/api/login/sms_login")
    @FormUrlEncoded
    fun login(
        @Field("mobile") mobile: String
        , @Field("sms_code") sms_code: String
    ): Observable<BaseBean<Any>>

}