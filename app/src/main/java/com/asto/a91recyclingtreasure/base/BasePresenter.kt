package com.asto.a91recyclingtreasure.base

import com.asto.a91recyclingtreasure.model.ApiModel
import com.asto.a91recyclingtreasure.util.RetrofitUtil

open class BasePresenter(var mView :IView?){
    val model: ApiModel
        get() = RetrofitUtil.getRetrofit().create(ApiModel::class.java)

    fun attachView(view :IView){
        mView = view
    }
    fun detachView(){
        mView = null
    }

    fun isViewAttached(): Boolean {
        return mView != null
    }
}