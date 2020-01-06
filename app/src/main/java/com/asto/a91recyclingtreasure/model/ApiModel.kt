package com.asto.a91recyclingtreasure.model

import com.asto.a91recyclingtreasure.base.BaseBean
import com.asto.a91recyclingtreasure.bean.LoginBean
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
    @POST("/api/Login.action")
    @FormUrlEncoded
    fun login(
        @Field("login_name") login_name: String
        , @Field("password") password: String
    ): Observable<BaseBean<LoginBean>>

}